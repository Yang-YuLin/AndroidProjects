package com.example.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();//初始化水果数据
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);//获取到RecyclerView的实例
        /*LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//设置布局的排列方向，默认是纵向排列的*/
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);//参数分别为布局的列数和布局的排列方向
        recyclerView.setLayoutManager(layoutManager);//指定RecyclerView的布局方式是LinearLayoutManager(线性布局)
        FruitAdapter adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);//为RecyclerView准备好了适配器FruitAdapter
    }

    private void initFruits(){
        for(int i=0;i<2;i++){
            //保证各水果名字的长短差距都比较大，子项的高度也就各不相同了
            Fruit apple=new Fruit(getRandomLengthName("Apple"),R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana=new Fruit(getRandomLengthName("Banana"),R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange=new Fruit(getRandomLengthName("Orange"),R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon=new Fruit(getRandomLengthName("Watermelon"),R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear=new Fruit(getRandomLengthName("Pear"),R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape=new Fruit(getRandomLengthName("Grape"),R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple=new Fruit(getRandomLengthName("Pineapple"),R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry=new Fruit(getRandomLengthName("Strawberry"),R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry=new Fruit(getRandomLengthName("Cherry"),R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango=new Fruit(getRandomLengthName("Mango"),R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }

    private String getRandomLengthName(String name){
        Random random=new Random();
        int length=random.nextInt(20)+1;//构造一个1到20之间的随机数
        StringBuilder builder=new StringBuilder();
        //将参数中传入的字符串重复随机遍
        for(int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }
}
