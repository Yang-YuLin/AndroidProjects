package com.example.uibestpractice;

/**
 * Created by 杨玉林 on 2018/4/19.
 */

//定义消息的实体类
public class Msg {

    public static final int TYPE_RECEIVED=0;

    public static final int TYPE_SENT=1;

    private String content;//消息内容

    private int type;//消息类型，消息类型有两个值可选，TYPE_RECEIVED表示这是一条收到的消息，TYPE_SENT表示这是一条发出的消息

    //构造函数
    public Msg(String content,int type){
        this.content=content;
        this.type=type;
    }

    public String getContent(){
        return content;
    }

    public int getType(){
        return type;
    }
}
