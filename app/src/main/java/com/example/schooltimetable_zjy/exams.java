package com.example.schooltimetable_zjy;

/**
 * Created by Administrator on 2017/11/11.
 */

public class exams {
    private String subject;
    private String time;
    private String place;
    private String date;

    public exams(){};

    public exams(String subject, String date, String time,String place){
        this.place = place;
        this.subject = subject;
        this.date = date;
        this.time = time;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getPlace(){
        return place;
    }

    public void setPlace(String place){
        this.place = place;
    }
}
