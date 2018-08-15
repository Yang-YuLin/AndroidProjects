package com.example.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 杨玉林 on 2018/4/15.
 */

//自定义适配器FruitAdapter,这个适配器继承自ArrayAdapter
public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;//要加载的布局文件的id

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @Override
    //getView()方法在每个子项被滚动到屏幕内的时候会被调用（自动）
    //convertView参数用于将之前加载好的布局进行缓存，以便之后可以进行重用
    public View getView(int position, View convertView, ViewGroup parent){
        Fruit fruit=getItem(position);//获取当前项的Fruit实例

        //View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);//动态加载布局文件，当ListView快速滚动的时候，这就会成为性能的瓶颈

        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.fruitImage=(ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName=(TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);//将viewHolder存储在View中
        }else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();//重新获取ViewHolder
        }
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());

       /* ImageView fruitImage=(ImageView) view.findViewById(R.id.fruit_image);
        TextView fruitName=(TextView) view.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(fruit.getImageId());//设置显示的图片
        fruitName.setText(fruit.getName());//设置显示的文字*/

        return view;//返回布局
    }

    //内部类，用于对控件的实例进行缓存，就没有必要像原来一样每次都通过findViewById()方法来获取控件实例了
    class ViewHolder{

        ImageView fruitImage;

        TextView fruitName;
    }
}
