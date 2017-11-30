package com.example.schooltimetable_zjy;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/11/11.
 */

public class ExamOverFragment extends Fragment {
    View view = null;
    examActivity examActivity1;
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState){
        examActivity1= (examActivity) getActivity();
        view = inflater.inflate(R.layout.frag_exam_over,container,false);
        return view;
    }
}
