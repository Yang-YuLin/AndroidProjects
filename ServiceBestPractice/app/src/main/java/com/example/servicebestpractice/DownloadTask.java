package com.example.servicebestpractice;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 杨玉林 on 2018/5/12.
 */

public class DownloadTask extends AsyncTask<String,Integer,Integer> {

    public static final int TYPE_SUCCESS=0;

    public static final int TYPE_FAILED=1;

    public static final int TYPE_PAUSED=2;

    public static final int TYPE_CANCELED=3;

    private DownloadListener listener;

    private boolean isCanceled=false;

    private boolean isPaused=false;

    private int lastProgress;

    //下载的状态会通过DownloadListener listener这个参数回调
    public DownloadTask(DownloadListener listener){
        this.listener=listener;
    }

    @Override
    //用于在后台执行具体的下载逻辑
    protected Integer doInBackground(String... params) {
        InputStream is=null;
        RandomAccessFile savedFile=null;
        File file=null;
        try {
            long downloadLength=0;//记录已下载的文件长度
            String downloadUrl=params[0];
            String fileName=downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file=new File(directory+fileName);
            if(file.exists()){
                downloadLength=file.length();
            }
            long contentLength=getContentLength(downloadUrl);
            if(contentLength==0){
                return TYPE_FAILED;
            }else if(contentLength==downloadLength){
                //已下载字节和文件总字节相等，说明已经下载完成了
                return TYPE_SUCCESS;
            }
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    //断点下载，指定从哪个字节开始下载
                    .addHeader("RANGE","bytes="+downloadLength+"-")//header用于告诉服务器我们想要从哪个字节开始下载
                    .url(downloadUrl)
                    .build();
            Response response=client.newCall(request).execute();
            if(response!=null){
                is=response.body().byteStream();
                savedFile=new RandomAccessFile(file,"rw");
                savedFile.seek(downloadLength);//跳过已下载的字节
                byte[] b=new byte[1024];
                int total=0;
                int len;
                while((len=is.read(b))!=-1){
                    if(isCanceled){
                        return TYPE_CANCELED;
                    }else if(isPaused){
                        return TYPE_PAUSED;
                    }else{
                        total+=len;
                        savedFile.write(b,0,len);
                        //计算已下载的百分比
                        int progress=(int) ((total+downloadLength)*100/contentLength);
                        publishProgress(progress);//从子线程切换到UI线程
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is!=null){
                    is.close();
                }
                if(savedFile!=null){
                    savedFile.close();
                }
                if(isCanceled && file!=null){
                    file.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    //用于在界面上更新当前的下载进度
    protected void onProgressUpdate(Integer... values) {
        //首先从参数中获取到当前的下载进度
        int progress=values[0];
        //和上一次的下载进度进行对比
        if(progress>lastProgress){
            listener.onProgress(progress);//通知下载进度更新
            lastProgress=progress;
        }
    }

    @Override
    //用于通知最终的下载结果
    protected void onPostExecute(Integer status) {
        //根据参数传入的下载状态来进行回调
        switch(status){
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            default:
                break;
        }
    }

    public void pauseDownload(){
        isPaused=true;
    }

    public void cancelDownload(){
        isCanceled=true;
    }

    private long getContentLength(String downloadUrl) throws IOException{
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response=client.newCall(request).execute();
        if(response!=null && response.isSuccessful()){
            long contentLength=response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        return 0;
    }
}
