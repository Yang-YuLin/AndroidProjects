package com.example.activitytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ThirdActivity", "Task id is "+getTaskId());
        setContentView(R.layout.third_layout);
        //在ThirdActivity界面想通过点击按钮直接退出程序
        Button button3=(Button) findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ActivityCollector.finishAll();
                //android.os.Process.killProcess(android.os.Process.myPid());//杀掉当前程序的进程
            }
        });
    }
}
