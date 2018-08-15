package com.example.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase=(Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();//创建数据库
            }
        });
        Button addData=(Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Book book=new Book();//创建出模型类的实例
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });
        Button updateData=(Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               /*//只能对已存储的对象进行操作
               Book book=new Book();//创建出模型类的实例
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("Unknow");
                book.save();
                book.setPrice(10.99);
                book.save();*/

                //能对未存储的对象进行操作
                Book book=new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name =? and author =?","The Lost Symbol","Dan Brown");//这个方法中可以指定一个条件约束
            }
        });
        Button deleteData=(Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(Book.class,"price < ?","15");
            }
        });
        Button queryData=(Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                List<Book> books=DataSupport.findAll(Book.class);
                for(Book book:books){
                    Log.d(TAG, "book name is "+book.getName());
                    Log.d(TAG, "book author is "+book.getAuthor());
                    Log.d(TAG, "book pages is "+book.getPages());
                    Log.d(TAG, "book price is "+book.getPrice());
                    Log.d(TAG, "book press is "+book.getPress());
                }
            }
        });
    }
}
