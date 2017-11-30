package com.example.schooltimetable_zjy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCourseActivity extends AppCompatActivity {
    private CourseDbHelper dbHelper;

    EditText addDes,addWeek,addNumber,addPlace,addTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);


        TextView back = (TextView) findViewById(R.id.back_to_course);
        addDes = (EditText) findViewById(R.id.course_add_subject_edit);
        addWeek = (EditText) findViewById(R.id.course_add_date_edit);
        addNumber = (EditText) findViewById(R.id.course_add_SJ_edit);
        addPlace = (EditText) findViewById(R.id.course_add_place_edit);
        addTeacher = (EditText) findViewById(R.id.course_add_teacher_edit);
        dbHelper = new CourseDbHelper(this,"CourseStore.db",null,2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCourseActivity.this,TimetableActivity.class);
                startActivity(intent);
            }
        });

        TextView addItem = (TextView) findViewById(R.id.course_add_item);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("course",addDes.getText().toString());
                values.put("week",Integer.parseInt(addWeek.getText().toString()));
                values.put("number",Integer.parseInt(addNumber.getText().toString()));
                //values.put("place",addPlace.getText().toString());
                //values.put("teacher",addTeacher.getText().toString());
                db.insert("Course",null,values);

                Toast.makeText(AddCourseActivity.this,"add course success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddCourseActivity.this,TimetableActivity.class);
                startActivity(intent);
            }
        });


    }
}
