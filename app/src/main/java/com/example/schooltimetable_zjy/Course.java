package com.example.schooltimetable_zjy;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/16.
 */

public class Course implements Serializable {
    private static final long serialVersionUID = -9121734039844677432L;
    private int classNumber;//课程节次

    private int day;         //星期
    private String des;      //课程名字
    private int spanNum = 2;//跨越的课程时间，这里默认为2节课
    private String teacher; //授课老师
    private String place;   //授课地点

    private String ClassRoomName;
    private String ClassTypeName;

    public Course(String des, int day, int classNumber ) {
        this.classNumber = classNumber;
        this.day = day;
        this.des = des;
    }

    public Course() {
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getSpanNum() {
        return spanNum;
    }

    public void setSpanNum(int spanNum) {
        this.spanNum = spanNum;
    }

    public String getPlace(){
        return place;
    }

    public void setPlace(String place){
        this.place = place;
    }

    public String getTeacher(){
        return teacher;
    }

    public void setTeacher(String teacher){
        this.teacher = teacher;
    }
    @Override
    public String toString() {
        return "Course [classNumber=" + classNumber + ", day=" + day + ", des=" + des
                + ", spanNun=" + spanNum + "]";
    }

    public String getClassRoomName() {
        return ClassRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        ClassRoomName = classRoomName;
    }

    public String getClassTypeName() {
        return ClassTypeName;
    }

    public void setClassTypeName(String classTypeName) {
        ClassTypeName = classTypeName;
    }


}
