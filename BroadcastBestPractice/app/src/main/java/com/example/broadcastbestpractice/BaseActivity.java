package com.example.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 杨玉林 on 2018/5/1.
 */

//作为所有活动的父类

public class BaseActivity extends AppCompatActivity{

    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    //之前在onCreate()和onDestroy()方法里来注册和取消注册广播接收器，现在变的原因是：我们始终需要保证处于栈顶的活动才能接收到这条强制下线广播，非栈顶的活动不应该也没有必要去接收这条广播，所以写在onResume()和onPause()方法里就可以很好地解决这个问题，当一个活动失去栈顶位置时就会自动取消广播接收器的注册
    @Override
    //在活动准备好和用户进行交互的时候调用，此时的活动一定位于返回栈的栈顶，并且处于运行状态
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        receiver=new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);//注册 ForceOfflineReceiver广播接收器
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(receiver!=null){
            unregisterReceiver(receiver);//取消注册 ForceOfflineReceiver广播接收器
            receiver=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder buidler=new AlertDialog.Builder(context);//使用AlertDialog.Builder来构建一个对话框
            buidler.setTitle("Warning");
            buidler.setMessage("You are forced to be offline. Please try to login again.");
            buidler.setCancelable(false);//将对话框设置为不可取消，否则用户按一下Back键就可以关闭对话框继续使用程序了
            //来给对话框注册确定按钮，当用户点击了确定按钮时，就调用ActivityCollector的finishAll()方法来销毁掉所有活动，并重新启动LoginActivity这个活动
            buidler.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();//销毁所有活动
                    Intent intent=new Intent(context,LoginActivity.class);
                    context.startActivity(intent);//重新启动LoginActivity
                }
            });
            buidler.show();
        }
    }
}
