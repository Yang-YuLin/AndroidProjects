package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";

    private MyService.DownloadBinder downloadBinder;

    //匿名类
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        //在活动与服务成功绑定的时候调用
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(MyService.DownloadBinder) service;//有了这个实例，活动和服务之间的关系就变得非常紧密了
            //实现了活动指挥服务干什么就去干什么的功能
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        //在活动与服务连接断开的时候调用
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startService=(Button) findViewById(R.id.start_service);
        Button stopService=(Button) findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        Button bindService=(Button) findViewById(R.id.bind_service);
        Button unbindService=(Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        Button startIntentService=(Button) findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent=new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent=new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent=new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);//绑定服务，BIND_AUTO_CREATE表示活动和服务进行绑定后自动创建服务
                break;
            case R.id.unbind_service:
                unbindService(connection);//解绑服务
                break;
            case R.id.start_intent_service:
                Log.d(TAG, "Thread id is "+Thread.currentThread().getId());
                Intent intentService=new Intent(this,MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }
}
