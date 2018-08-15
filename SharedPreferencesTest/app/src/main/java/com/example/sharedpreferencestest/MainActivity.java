package com.example.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData=(Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","Tom");
                editor.putInt("age",28);
                editor.putBoolean("married",false);
                editor.apply();//调用apply()方法将添加的数据提交，从而完成数据存储操作。
            }
        });
        Button restoreData=(Button) findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
                String name=pref.getString("name","");//如果没有找到所传入键对应的值，就会使用方法中传入的默认值来代替
                int age=pref.getInt("age",0);
                boolean married=pref.getBoolean("married",false);
                Log.d(TAG, "name is "+name);//通过Log将这些值打印出来
                Log.d(TAG, "age is "+age);
                Log.d(TAG, "married is "+married);
            }
        });
    }
}
