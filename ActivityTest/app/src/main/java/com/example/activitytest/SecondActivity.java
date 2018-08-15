package com.example.activitytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Context;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("SecondActivity", this.toString());
        Log.d("SecondActivity", "Task id is "+getTaskId());
        setContentView(R.layout.second_layout);

       /* Intent intent=getIntent();//获取用于启动SecondActivity的Intent
        String data=intent.getStringExtra("extra_data");
        Log.d("SecondActivity",data);*/

        Button button2=(Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*Intent intent=new Intent();
                intent.putExtra("data_return","Hello FirstActivity");
                setResult(RESULT_OK,intent);//这个方法专门用于向上一个活动返回处理结果
                finish();//销毁当前活动*/

               /* Intent intent=new Intent(SecondActivity.this,FirstActivity.class);
                startActivity(intent);*/

                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    //通过重写这个方法来解决按Back键无法向上一个活动返回数据的问题,当用户按下Back键，就会去执行onBackPressed()方法中的代码
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("data_return","Hello FirstActivity");
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity", "onDestroy");
    }

    public static void actionStart(Context context,String data1,String data2){
        Intent intent=new Intent(context,SecondActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }
}
