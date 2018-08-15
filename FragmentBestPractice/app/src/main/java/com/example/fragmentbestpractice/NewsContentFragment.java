package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 杨玉林 on 2018/4/24.
 */

public class NewsContentFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }

    //用于将新闻的标题和内容显示在界面上的
    public void refresh(String newsTitle,String newsContent){
        View visibilityLayout=view.findViewById(R.id.visibility_layout);//获取到新闻标题和内容的控件
        visibilityLayout.setVisibility(View.VISIBLE);

        TextView newsTitleText=(TextView) view.findViewById(R.id.news_title);
        TextView newsContentText=(TextView) view.findViewById(R.id.news_content);
        //将方法传递进来的参数设置进去，为布局的TextView设置显示内容
        newsTitleText.setText(newsTitle);//刷新新闻的标题
        newsContentText.setText(newsContent);//刷新新闻的内容
    }
}
