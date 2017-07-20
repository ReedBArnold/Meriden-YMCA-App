package com.arnold.reed.meridenymcaapp.Models;
/**
 * Created by Reed on 7/16/2017
 * Version 0.6
 *
 */
import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

public class Camper {
    private String name;
    private Boolean status;

    public Camper(){
        // Default constructor
    }

    public Camper(String name, Boolean status){
        this.name = name;
        this.status = status;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> input = new HashMap<>();
        input.put("name", name);
        input.put("status",status);

        return input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
