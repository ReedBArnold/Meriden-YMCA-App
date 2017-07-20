package com.arnold.reed.meridenymcaapp.Models;
/**
 * Created by Reed on 7/16/2017
 * Version 0.6
 *
 */
import com.google.firebase.database.Exclude;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Attendance {

        private String group;
        private String uid;
        private String counselor;
        private ArrayList<Camper> campers = new ArrayList<Camper>();

        public Attendance(){
        // Default constructor
    }

        public Attendance(String uid, String counselor, ArrayList<Camper> campers){
            this.uid = uid;
            this.counselor = counselor;
            this.campers = campers;
    }

        @Exclude
        public Map<String, Object> toMap(){
            HashMap<String, Object> input = new HashMap<>();
            input.put("uid", uid);
            input.put("counselor", counselor);
            input.put("campers", campers);
            return input;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public ArrayList<Camper> getCampers() {
        return campers;
    }

    public void setCampers(ArrayList<Camper> campers) {
        this.campers = campers;
    }
}
