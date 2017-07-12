package com.arnold.reed.meridenymcaapp.Models;
/**
 * Created by Reed on 7/8/2017
 * Version 0.4
 *
 */
import com.google.firebase.database.Exclude;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Attendance {

        private String counselor;
        private ArrayList<Camper> campers = new ArrayList<Camper>();

        public Attendance(){
        // Default constructor
    }

        public Attendance(String counselor, ArrayList<Camper> campers){
        this.counselor = counselor;
        this.campers = campers;
    }

        @Exclude
        public Map<String, Object> toMap(){
        HashMap<String, Object> input = new HashMap<>();
        input.put("counselor", counselor);
        input.put("campers", campers);
        return input;
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
