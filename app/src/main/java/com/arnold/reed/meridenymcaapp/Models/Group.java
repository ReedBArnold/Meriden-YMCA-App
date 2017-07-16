package com.arnold.reed.meridenymcaapp.Models;
/**
 * Created by Reed on 7/11/2017
 * Version 0.5
 *
 */
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Group {

    private String location;
    private String name;
    private String counselor;
    private ArrayList<Camper> campers = new ArrayList<Camper>();

    public Group(){
        // Default constructor
    }

    public Group(String name,String counselor, String location, ArrayList<Camper> campers){
        this.location = location;
        this.name = name;
        this.counselor = counselor;
        this.campers = campers;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> input = new HashMap<>();
        input.put("location", location);
        input.put("name", name);
        input.put("counselor", counselor);
        input.put("campers", campers);
        return input;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
