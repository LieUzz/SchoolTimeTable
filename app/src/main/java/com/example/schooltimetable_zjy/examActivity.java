package com.example.schooltimetable_zjy;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class examActivity extends AppCompatActivity {

    FragmentManager fm = null;
    FragmentTransaction transaction = null;
    ExamIngFragment examIngFragment = null;
    ExamOverFragment examOverFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        TextView shiftTimeTable = (TextView) findViewById(R.id.shift_timeTable);
        shiftTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(examActivity.this,TimetableActivity.class);
                startActivity(intent);
            }
        });
        final TextView ingExam = (TextView) findViewById(R.id.exam_ing);
        final TextView overExam = (TextView) findViewById(R.id.exam_over);
        TextView addExam = (TextView) findViewById(R.id.exam_add);
        initFM();
        ingExam.setBackgroundColor(Color.parseColor("#1E90FF"));
        ingExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingExam.setBackgroundColor(Color.parseColor("#1E90FF"));
                overExam.setBackgroundColor(Color.parseColor("#FFFFFF"));
                selectFM(1);
            }
        });


        overExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overExam.setBackgroundColor(Color.parseColor("#1E90FF"));
                ingExam.setBackgroundColor(Color.parseColor("#FFFFFF"));
                selectFM(2);
            }
        });

        addExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(examActivity.this,AddExamActivity.class);
                startActivity(intent);
            }
        });

        //接受addExamActivity传过来的数据并传递给ExamIngFragment
        Intent intent = getIntent();
        String subject = intent.getStringExtra("add_subject");
        String date = intent.getStringExtra("add_date");
        String time = intent.getStringExtra("add_time");
        String place = intent.getStringExtra("add_place");
        if (TextUtils.isEmpty(subject)){}
        else
            examIngFragment.addExam(subject,date,time,place);
        //Toast.makeText(examActivity.this,subject,Toast.LENGTH_LONG).show();


    }

    private void initFM(){
        fm = getFragmentManager();
        transaction = fm.beginTransaction();
        if (examIngFragment == null){
            examIngFragment = new ExamIngFragment();
            transaction.add(R.id.exam_fragment,examIngFragment);
        }else {
            transaction.show(examIngFragment);
        }
        transaction.commit();
    }

    private void selectFM(int i){
        fm = getFragmentManager();
        transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i){
            case 1:
                examIngFragment = new ExamIngFragment();
                transaction.add(R.id.exam_fragment,examIngFragment);
                transaction.addToBackStack(null);
                break;
            case 2:
                examOverFragment = new ExamOverFragment();
                transaction.add(R.id.exam_fragment,examOverFragment);
                transaction.addToBackStack(null);
                break;
            default:
                break;
        }
        transaction.commit();
    }
    private void hideFragment(FragmentTransaction fragmentTransaction){
        if (examIngFragment != null)
            fragmentTransaction.hide(examIngFragment);
        if (examOverFragment != null)
            fragmentTransaction.hide(examOverFragment);
    }

}
