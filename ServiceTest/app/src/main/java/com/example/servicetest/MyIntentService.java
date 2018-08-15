package com.example.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by 杨玉林 on 2018/5/12.
 */

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";
    
    public MyIntentService(){
        super("MyIntentService");//调用父类的有参构造函数
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //打印当前线程的id
        Log.d(TAG, "Thread id is "+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy executed");
    }
}
