package com.example.schooltimetable_zjy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CourseDbHelper courseDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.study);
        imageView.setImageResource(R.drawable.study);
        Button button = (Button) findViewById(R.id.main_enter);
        Button button1 = (Button) findViewById(R.id.enroll);
        final TextView stuId = (TextView) findViewById(R.id.stu_id);
        final TextView cipher = (TextView) findViewById(R.id.cipher);
        courseDbHelper = new CourseDbHelper(this,"CourseStore.db",null,1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TimetableActivity.class);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("验证失败");
                dialog.setMessage("用户名或密码错误");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                    }
                });
                if(stuId.getText().toString().equals("1512190222")
                        &&cipher.getText().toString().equals("zhengjiayu"))
                    startActivity(intent);
                else
                    dialog.show();
            }
        });

        // 原本用于创建学生的，但是不希望做的太麻烦，临时用来创建课程数据库
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseDbHelper.getWritableDatabase();
            }
        });

    }
}
