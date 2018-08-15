package com.example.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by 杨玉林 on 2018/4/17.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;

    //内部类ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{
        View fruitView;//添加fruitView来保存子项最外层布局的实例
        ImageView fruitImage;
        TextView fruitName;

        //构造函数 参数view通常就是RecyclerView子项的最外层布局
        public ViewHolder(View view){
            super(view);
            fruitView=view;
            fruitImage=(ImageView) view.findViewById(R.id.fruit_image);
            fruitName=(TextView) view.findViewById(R.id.fruit_name);
        }
    }

    //构造函数
    public FruitAdapter(List<Fruit> fruitList){
        mFruitList=fruitList;
    }

    //因为FruitAdapter是继承自RecyclerView.Adapter的，那么就必须重写onCreateViewHolder()、onBindViewHolder()、getItemCount()方法
    @Override
    //onCreateViewHolder()是用于创建ViewHolder实例的
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        //ViewHolder holder=new ViewHolder(view);
        final ViewHolder holder=new ViewHolder(view);
        //为最外层布局注册了点击事件，因为TextView并没有注册点击事件，因为点击文字这个事件会被子项的最外层布局捕获到
        holder.fruitView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position=holder.getAdapterPosition();
                Fruit fruit=mFruitList.get(position);//获取用户点击的position，然后通过position拿到相应的Fruit实例
                Toast.makeText(v.getContext(),"you clicked view "+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        //为ImageView注册了点击事件
        holder.fruitImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position=holder.getAdapterPosition();
                Fruit fruit=mFruitList.get(position);
                Toast.makeText(v.getContext(),"you clicked image "+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    //onBindViewHolder()是用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，通过position参数得到当前项的Fruit实例
    public void onBindViewHolder(ViewHolder holder,int position){
        Fruit fruit=mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    //告诉RecyclerView一共有多少子项
    public int getItemCount(){
        return mFruitList.size();
    }

}
