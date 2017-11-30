package com.example.schooltimetable_zjy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddExamActivity extends AppCompatActivity {
    EditText addSubject, addDate, addTime, addPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        TextView back = (TextView) findViewById(R.id.back);
        addSubject = (EditText) findViewById(R.id.exam_add_subject_edit);
        addDate = (EditText) findViewById(R.id.exam_add_date_edit);
        addTime = (EditText) findViewById(R.id.exam_add_time_edit);
        addPlace = (EditText) findViewById(R.id.exam_add_place_edit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddExamActivity.this,examActivity.class);
                startActivity(intent);
            }
        });

        TextView addItem = (TextView) findViewById(R.id.add_item);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddExamActivity.this,examActivity.class);
                intent.putExtra("add_subject",addSubject.getText().toString());
                intent.putExtra("add_date",addDate.getText().toString());
                intent.putExtra("add_time",addTime.getText().toString());
                intent.putExtra("add_place",addPlace.getText().toString());
                //Log.d("AddExamActivity",addSubject.getText().toString());
                startActivity(intent);
                //Toast.makeText(AddExamActivity.this, addSubject.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
