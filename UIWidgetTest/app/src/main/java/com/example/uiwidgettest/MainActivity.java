package com.example.uiwidgettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;
import android.app.ProgressDialog;

/*//使用匿名类的方式来注册监听器
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //在此处添加逻辑
            }
        });
    }
}*/

//实现对按钮点击事件的监听
//使用实现接口的方式来进行注册监听器
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;

    private ImageView imageView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button) findViewById(R.id.button);
        editText=(EditText) findViewById(R.id.edit_text);
        imageView=(ImageView) findViewById(R.id.image_view);
        progressBar=(ProgressBar) findViewById(R.id.progress_bar);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                //在此处添加逻辑:通过点击按钮来获取EditText中输入的内容
                String inputText=editText.getText().toString();//获取到输入的内容，再转换成字符串
                Toast.makeText(MainActivity.this,inputText,Toast.LENGTH_SHORT).show();

                //在代码中动态地更改ImageView中的图片
                //通过点击按钮来改变ImageView显示的图片
                imageView.setImageResource(R.drawable.img_2);

                //通过不断地点击按钮，就会看到进度条在显示与隐藏之间来回切换
                if(progressBar.getVisibility()==View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility(View.GONE);
                }

                //在代码中动态地更改进度条的进度
                //每点击一次按钮，我们就获取进度条的当前进度，然后在现有的进度上加10作为更新后的进度
                int progress=progressBar.getProgress();
                progress=progress+10;
                progressBar.setProgress(progress);

                //在当前界面弹出对话框
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("This is a Dialog");
                dialog.setMessage("Something important.");
                dialog.setCancelable(false);//对话框可否用Back键关闭
                //调用setPositiveButton()方法为对话框设置确定按钮的点击事件
                dialog.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                    }
                });
                //调用setNegativeButton()方法为对话框设置取消按钮的点击事件
                dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                    }
                });
                dialog.show();//将对话框显示出来

                //在弹出对话框的同时显示一个进度条，一般用于表示当前操作比较耗时，让用户耐心地等待
                ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);//如果这里写的false，表示ProgressDialog是不能通过Back键取消掉的，这时你就一定要在代码中做好控制，当数据加载完成后必须调用ProgressDialog的dismiss()方法来关闭对话框，否则ProgressDialog将会一直存在
                progressDialog.show();
                break;
            default:
                break;
        }
    }
}
