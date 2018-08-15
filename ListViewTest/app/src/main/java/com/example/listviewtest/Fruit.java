package com.example.listviewtest;

/**
 * Created by 杨玉林 on 2018/4/15.
 */

public class Fruit {

    private String name;

    private int imageId;

    //构造函数，函数名同类名
    public Fruit(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
