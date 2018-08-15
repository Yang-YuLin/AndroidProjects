package com.example.filepersistencetest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit=(EditText) findViewById(R.id.edit);
        String inputText=load();//调用load()方法来读取文件中存储的文本内容
        if(!TextUtils.isEmpty(inputText)){
            edit.setText(inputText);//调用setText()方法将内容填充到EditText里
            edit.setSelection(inputText.length());//将输入光标移动到文本的末尾位置以便继续输入
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_SHORT).show();//弹出还原成功字样
        }
    }

    public String load(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try {
            in=openFileInput("data");//通过openFileInput()方法获取到了一个FileInputStream对象
            reader=new BufferedReader(new InputStreamReader(in));//借助上面的in对象又构建出了一个InputStreamReader对象，接着再使用InputStreamReader构建出一个BufferedReader对象，这样就可以通过BufferedReader进行一行行地读取，把文件中所有的文本内容全部读取出来，并放在一个StringBuilder对象中，最后将读取到的内容返回就可以了
            String line="";
            while((line=reader.readLine())!=null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText =edit.getText().toString();
        save(inputText);
    }

    public void save(String inputText){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try {
            //文件命名为data
            out=openFileOutput("data", Context.MODE_PRIVATE);//通过openFileOutput()方法能够得到一个FileOutputStream对象
            writer=new BufferedWriter(new OutputStreamWriter(out));//然后借助上面的out对象构建出一个OutputStreamWriter对象，这样再使用OutputStreamWriter构建出一个BufferedWriter对象，这样就可以通过BufferedWriter来将文本内容写入到文件中了
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer!=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
