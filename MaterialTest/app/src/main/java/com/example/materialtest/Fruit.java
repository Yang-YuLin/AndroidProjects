package com.example.materialtest;

/**
 * Created by 杨玉林 on 2018/7/9.
 */

public class Fruit {

    private String name;//水果的名字

    private int imageId;//水果对应图片的资源id

    public Fruit(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return  name;
    }

    public int getImageId() {
        return imageId;
    }
}
