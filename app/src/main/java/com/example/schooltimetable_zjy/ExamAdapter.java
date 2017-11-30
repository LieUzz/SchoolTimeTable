package com.example.schooltimetable_zjy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11.
 */

public class ExamAdapter extends ArrayAdapter<exams> {
    private int resourceId;

    public ExamAdapter(Context context, int examResourceId,
                       List<exams> objects){
        super(context,examResourceId,objects);
        resourceId = examResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        exams exams = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,
                parent, false);
        TextView examSubject = (TextView) view.findViewById(R.id.exam_subject);
        TextView examDate = (TextView) view.findViewById(R.id.exam_date);
        TextView examTime = (TextView) view.findViewById(R.id.exam_time);
        TextView examPlace = (TextView) view.findViewById(R.id.exam_place);
        examSubject.setText(exams.getSubject());
        examDate.setText(exams.getDate());
        examTime.setText(exams.getTime());
        examPlace.setText(exams.getPlace());
        return view;
    }

}
