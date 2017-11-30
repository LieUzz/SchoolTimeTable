package com.example.schooltimetable_zjy;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class TimetableActivity extends AppCompatActivity {

    private CourseTableView courseTableView;
    private CourseDbHelper dbHelper;
    private Intent AlarmIntent;
    private Calendar mCalendar;

    List<Course> courseList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);


        dbHelper = new CourseDbHelper(this,"CourseStore.db",null,2);
        courseTableView = (CourseTableView) findViewById(R.id.ctv);
        //用于跳转到examActivity
        TextView shiftExam = (TextView) findViewById(R.id.shift_exam);
        TextView person = (TextView) findViewById(R.id.swift_my);

        shiftExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimetableActivity.this,examActivity.class);
                startActivity(intent);
            }
        });

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimetableActivity.this,PersonActivity.class);
                startActivity(intent);
            }
        });

        TextView addCourse = (TextView) findViewById(R.id.course_add);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimetableActivity.this,AddCourseActivity.class);
                startActivity(intent);
            }
        });
        initCourse();

        courseTableView.updateCourseViews(courseList);
        courseTableView.setOnCourseItemClickListener(new CourseTableView.OnCourseItemClickListener() {
            @Override
            public void onCourseItemClick(TextView tv, int jieci, int day, String des) {
                showCourse(des,day,jieci);
            }
        });

        AlarmIntent = new Intent(this, LongRunningService.class);
        //开启关闭Service
        startService(AlarmIntent);
        //设置一个Toast来提醒使用者提醒的功能已经开始
        Toast.makeText(TimetableActivity.this,"提醒的功能已经开启，关闭界面则会取消提醒。"
                ,Toast.LENGTH_LONG).show();

    }

    /*private void startRemind(){
        //得到日历实例，主要是为了下面的获取时间
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());

        //获取当前毫秒值
        long systemTime = System.currentTimeMillis();

        //是设置日历的时间，主要是让日历的年月日和当前同步
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        // 这里时区需要设置一下，不然可能个别手机会有8个小时的时间差
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //设置在几点提醒  设置的为13点
        mCalendar.set(Calendar.HOUR_OF_DAY, 3);
        //设置在几分提醒  设置的为25分
        mCalendar.set(Calendar.MINUTE, 39);
        //下面这两个看字面意思也知道
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);

        //上面设置的就是13点25分的时间点

        //获取上面设置的13点25分的毫秒值
        long selectTime = mCalendar.getTimeInMillis();

        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if(systemTime > selectTime) {
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        AlarmIntent = new Intent(this,LongRunningService.class);
        //开启关闭Service
        //startService(AlarmIntent);
        //设置一个Toast来提醒使用者提醒的功能已经开始
        Toast.makeText(TimetableActivity.this,"提醒的功能已经开启，关闭界面则会取消提醒。"
                ,Toast.LENGTH_LONG).show();

        PendingIntent pi = PendingIntent.getBroadcast(TimetableActivity.this,0,AlarmIntent,0);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        am.setRepeating(AlarmManager.RTC_WAKEUP,
                mCalendar.getTimeInMillis(), (1000 * 60 * 60 * 24), pi);

    }*/

    /**
     * 关闭提醒
     */
    /*/private void stopRemind(){

        Intent intent = new Intent(TimetableActivity.this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(TimetableActivity.this, 0,
                intent, 0);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        //取消警报
        am.cancel(pi);
        Toast.makeText(this, "关闭了提醒", Toast.LENGTH_SHORT).show();

    }*/


    @Override
    protected void onDestroy(){
        super.onDestroy();
        //在Activity被关闭后，关闭Service
        stopService(AlarmIntent);
    }

    //初始化课表
    public void initCourse(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Course",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                String course = cursor.getString(cursor.getColumnIndex("course"));
                int week = cursor.getInt(cursor.getColumnIndex("week"));
                int number = cursor.getInt(cursor.getColumnIndex("number"));
                Course cc = new Course(course,week,number);
                courseList.add(cc);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    // 显示课程详情信息
    private void showCourse(final String course, int week, int number){
        final AlertDialog.Builder courseDialog =
                new AlertDialog.Builder(TimetableActivity.this);
        courseDialog.setTitle(course);
        courseDialog.setMessage("本课程上课时间为\n星期"+week+"第"+number+"节课");
        courseDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        courseDialog.setNegativeButton("删除",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.delete("Course","course = ?",new String[]{course});
                        Toast.makeText(TimetableActivity.this,"Delete course success",
                                Toast.LENGTH_LONG).show();
                    }
                });
        courseDialog.show();
    }

}
