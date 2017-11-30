package com.example.schooltimetable_zjy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/17.
 */

public class CourseDbHelper extends SQLiteOpenHelper {
    public static final String CREATE_COURSE = "create table Course("
            +"courseId integer primary key autoincrement,"
            +"course text,"
            +"week integer,"
            +"number integer)";
    private Context mContext;

    public CourseDbHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_COURSE);
        Toast.makeText(mContext,"Create succeed",Toast.LENGTH_LONG).show();
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){

    }
}
