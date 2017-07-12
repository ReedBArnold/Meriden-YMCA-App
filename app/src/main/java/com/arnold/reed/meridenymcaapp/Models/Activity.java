package com.arnold.reed.meridenymcaapp.Models;
/**
 * Created by Reed on 7/8/2017
 * Version 0.4
 *
 */
import com.google.firebase.database.Exclude;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Activity {

    //private String uid;
    private String name;
    private String desc;
    private String counselor;
    private String location;
    private String date;

    public Activity(){
        // Default constructor
    }


    public Activity(String name, String desc, String counselor, String date, String location){
        //this.uid = uid;
        this.name = name;
        this.desc = desc;
        this.counselor = counselor;
        this.date = date;
        this.location = location;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> input = new HashMap<>();
        //input.put("uid", uid);
        input.put("name", name);
        input.put("desc", desc);
        input.put("counselor", counselor);
        input.put("location", location);
        input.put("date", date);

        return input;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
