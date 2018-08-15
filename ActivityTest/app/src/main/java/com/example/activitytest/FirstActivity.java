package com.example.activitytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.Menu;
import android.content.Intent;
import android.util.Log;

public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("FirstActivity", this.toString());
        Log.d("FirstActivity", "Task id is "+getTaskId());//打印当前返回栈的id
        setContentView(R.layout.first_layout);
        Button button1=(Button) findViewById(R.id.button_1);//得到按钮的实例
        button1.setOnClickListener(new View.OnClickListener(){//为按钮注册一个监听器，点击按钮时就会执行onClick()方法
            @Override
            public void onClick(View v){
                //Toast.makeText(FirstActivity.this,"You clicked Button 1",Toast.LENGTH_SHORT).show();
                //finish();

                //Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
                //startActivity(intent);

                //Intent intent=new Intent("com.example.activitytest.ACTION_START");
                //intent.addCategory("com.example.activitytest.MY_CATEGORY");
                //startActivity(intent);

                //调用系统的浏览器
                //Intent intent=new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("http://www.baidu.com"));//将网址字符串解析成一个Uri对象，并将这个Uri对象传递进去
                //startActivity(intent);

                //在程序中调用系统拨号界面
                //Intent intent=new Intent(Intent.ACTION_DIAL);
                //intent.setData(Uri.parse("tel:10086"));
                //startActivity(intent);

                //向下一个活动传递数据
                //String data="Hello SecondActivity";
                //Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
                //intent.putExtra("extra_data",data);//接收两个参数，一个键一个值，第二个参数值才是真正要传递的数据
                //startActivity(intent);

                //Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
                //用下面这个方法启动SecondActivity
                //startActivityForResult(intent,1);//在活动销毁的时候能够返回一个结果给上一个活动(在SecondActivity被销毁之后会回调上一个活动的onActivityResult()方法)

               /* //在FirstActivity的基础上启动FirstActivity
                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);*/

                //只需这行代码就可以启动SecondActivity

                SecondActivity.actionStart(FirstActivity.this,"data1","data2");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    //定义菜单响应事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){//判断我们点击的是哪一个菜单项
            case R.id.add_item:
                Toast.makeText(this,"You clicked Add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"You clicked Remove",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    //三个参数分别为:启动活动时传入的请求码；返回数据时传入的处理结果；携带着返回数据的Intent
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String returnedData=data.getStringExtra("data_return");
                    Log.d("FirstActivity",returnedData);//打印
                }
                break;
            default:
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("FirstActivity", "onRestart");
    }
}
