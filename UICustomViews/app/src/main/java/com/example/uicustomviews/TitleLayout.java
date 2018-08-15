package com.example.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by 杨玉林 on 2018/4/15.
 */

//创建自定义控件
public class TitleLayout extends LinearLayout {
    //构造函数
    public TitleLayout(Context context,AttributeSet attrs){
        super(context,attrs);
        //对标题栏布局进行动态加载
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button titleBack=(Button) findViewById(R.id.title_back);//通过findViewById()方法得到按钮的实例
        Button titleEdit=(Button) findViewById(R.id.title_edit);
        //调用setOnClickListener()方法给两个按钮注册了点击事件
        titleBack.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                ((Activity) getContext()).finish();//当点击返回按钮时销毁掉当前的活动
            }
        });
        titleEdit.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(),"You clicked Edit button",Toast.LENGTH_SHORT).show();//当点击编辑按钮时弹出一段文本
            }
        });
    }
}
