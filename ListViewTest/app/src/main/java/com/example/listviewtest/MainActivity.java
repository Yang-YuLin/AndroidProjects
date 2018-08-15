package com.example.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//import static android.R.attr.data;

//定制ListView界面
public class MainActivity extends AppCompatActivity {

    //private String[] data={"Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry","Mango","Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry","Mango"};

    private List<Fruit> fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();//初始化水果数据
        FruitAdapter adapter=new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        /*//借助适配器将数组中的数据传递给ListView,ArrayAdapter是适配器的实现类，可以通过泛型来指定要适配的数据类型，然后在构造函数中把要适配的数据传入
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);//构建适配器对象*/
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);//调用setAdapter()方法将构造好的适配器对象传递进去

        //使用setOnItemClickListener()方法为ListView注册了一个监听器，当用户点击ListView中的任何一个子项时，就会回调onItemClick()方法
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            //onItemClick()通过position参数判断出用户点击的是哪一个子项，然后获取到相应的水果，并通过Toast将水果的名字显示出来
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                Fruit fruit=fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits(){
        for(int i=0;i<2;i++){
            Fruit apple=new Fruit("Apple",R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana=new Fruit("Banana",R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange=new Fruit("Orange",R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon=new Fruit("Watermelon",R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear=new Fruit("Pear",R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape=new Fruit("Grape",R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple=new Fruit("Pineapple",R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry=new Fruit("Strawberry",R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry=new Fruit("Cherry",R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango=new Fruit("Mango",R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }
}
