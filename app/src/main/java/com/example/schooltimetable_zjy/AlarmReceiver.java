package com.example.schooltimetable_zjy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.admin.SystemUpdateInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Administrator on 2017/11/25.
 */

public class AlarmReceiver extends BroadcastReceiver{

    private CourseDbHelper dbHelper;
    private NotificationManager mNotificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {

        //通知
        mNotificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);
        String id = "my_channel_01";


        Notification builder = new Notification.Builder(context)
                .setAutoCancel(true)
                .setTicker("新消息")
                .setContentTitle("今日课程")
                .setContentText("详情")
                .setSmallIcon(R.drawable.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .getNotification();
        mNotificationManager.notify(1,builder);
        System.out.println("闹钟响了~~");


        dbHelper = new CourseDbHelper(context,"CourseStore.db",null,2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Course where week = 2",null);
        Log.d("今天的课程有:"," ");
        if (cursor.moveToFirst()){
            do{
                String course = cursor.getString(cursor.getColumnIndex("course"));
                int week = cursor.getInt(cursor.getColumnIndex("week"));
                int number = cursor.getInt(cursor.getColumnIndex("number"));
                Log.d("TIMETABLE",course);

            }while (cursor.moveToNext());
        }
        cursor.close();




        //再次开启LongRunningService这个服务，从而可以
        Intent i = new Intent(context, LongRunningService.class);
        context.startService(i);


    }
}
