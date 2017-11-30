package com.example.schooltimetable_zjy;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/11.
 */

public class ExamIngFragment extends Fragment {
    View view = null;
    examActivity examActivity1;
    private List<exams> examsList = new ArrayList<>();
    String subject,date,time,place;

    public View onCreateView(LayoutInflater inflater, ViewGroup
                             container, Bundle savedInstanceState){
        examActivity1= (examActivity) getActivity();
        view = inflater.inflate(R.layout.frag_exam_ing,container,false);
        initExams();
        ExamAdapter adapter = new ExamAdapter(getActivity(),
                R.layout.item_exams,examsList);
        ListView listView = (ListView) view.findViewById(R.id.exam_ing_list);
        listView.setAdapter(adapter);

        return view;
    }

    private void initExams(){
        exams math = new exams("Math","1月8号","9:00-11:00","A201");
        examsList.add(math);
        exams english = new exams("English","1月7号","15:00-17:00","C413");
        examsList.add(english);
        exams computer = new exams("Computer","1月9号","11:00-13:00","C413");
        examsList.add(computer);
        if(TextUtils.isEmpty(subject)){}
        else
        {
            exams subj = new exams(subject,date,time,place);
            examsList.add(subj);
        }
    }

    public void addExam(String subject1, String date1, String time1, String place1){
        this.subject = subject1;
        this.date = date1;
        this.time = time1;
        this.place = place1;
    }

}
