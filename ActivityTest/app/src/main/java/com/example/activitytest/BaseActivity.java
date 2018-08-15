package com.example.activitytest;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;

/**
 * Created by 杨玉林 on 2018/4/13.
 */

//这样每当我们进入到一个活动的界面，该活动的类名就会被打印出来，这样我们就可以时时刻刻知晓当前界面对应的是哪一个活动了
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());//获取当前实例的类名
        ActivityCollector.addActivity(this);//将正在创建的活动添加到活动管理器里
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);//表明一个马上要销毁的活动从活动管理器里移除
    }
}
