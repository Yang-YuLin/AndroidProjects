package com.example.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


public class MyService extends Service {

    private static final String TAG = "MyService";

    private DownloadBinder mBinder=new DownloadBinder();

    class DownloadBinder extends Binder {

        public void startDownload(){
            Log.d(TAG, "startDownload executed");
        }

        public int getProgress(){
            Log.d(TAG, "getProgress executed");
            return 0;
        }
    }

    public MyService() {
    }

    @Override
    //这是Service中唯一的一个抽象方法
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    @Override
    //服务第一次创建的时候调用
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate executed");
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        Notification notification=new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);//会让MyService变成一个前台服务，并在系统状态栏显示出来
    }

    @Override
    //每次服务启动的时候调用
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    //服务销毁的时候调用，在这个方法里去回收那些不再使用的资源
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy executed");
    }
}
