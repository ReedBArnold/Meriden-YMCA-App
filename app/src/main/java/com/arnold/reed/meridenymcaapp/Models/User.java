package com.arnold.reed.meridenymcaapp.Models;
/**
 * Created by Reed on 7/11/2017
 * Version 0.5
 *
 */
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String uid;
    private String name;
    private String email;
    private String password;
    private String type;

    public User(){
        // Default constructor
    }

    public User(String uid, String name, String email, String password, String type){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> input = new HashMap<>();
        input.put("uid",uid);
        input.put("name", name);
        input.put("email", email);
        input.put("password", password);
        input.put("type", type);

        return input;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
