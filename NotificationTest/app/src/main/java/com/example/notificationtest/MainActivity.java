package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice=(Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
        NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);//想取消哪条通知，在cancel()方法中传入该通知的id即可
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.send_notice:
                Intent intent=new Intent(this,NotificationActivity.class);
                PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
                NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //构造器：NotificationCompat.Builder
                Notification notification=new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")
                        //.setStyle(new NotificationCompat.BigTextStyle().bigText("This is content text This is content text This is content text This is content text This is content text This is content text This is content text This is content text This is content text This is content text."))
                        //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.big_image)))
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                manager.notify(1,notification);//显示一个通知
                break;
            default:
                break;
        }
    }
}
