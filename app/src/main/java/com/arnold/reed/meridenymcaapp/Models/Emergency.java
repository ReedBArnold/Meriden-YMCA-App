package com.arnold.reed.meridenymcaapp.Models;
/**
 * Created by Reed on 7/11/2017
 * Version 0.5
 *
 */
import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Emergency {

    //private String uid;
    private String desc;
    private String counselor;
    private String priority;
    private String location;
    private String date;

    public Emergency(){
        // Default constructor
    }

    public Emergency(String desc, String counselor, String priority, String date, String location){
       // this.uid = uid;
        this.desc = desc;
        this.counselor = counselor;
        this.priority = priority;
        this.date = date;
        this.location = location;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> input = new HashMap<>();
        //input.put("uid", uid);
        input.put("desc", desc);
        input.put("priority", priority);
        input.put("counselor", counselor);
        input.put("location", location);
        input.put("date", date);

        return input;
    }

//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
