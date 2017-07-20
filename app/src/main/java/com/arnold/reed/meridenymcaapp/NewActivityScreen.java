package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/16/2017
 * Version 0.6
 *
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arnold.reed.meridenymcaapp.Models.Activity;
import com.arnold.reed.meridenymcaapp.Models.Emergency;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NewActivityScreen extends AppCompatActivity {

    private EditText mNameField;
    private EditText mDateField;
    private EditText mCounselorField;
    private EditText mLocationField;
    private EditText mDescField;

    private Button mAddActivityBtn;

    private DatabaseReference mDatabase, mActivitiesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_screen);
        //Firebase.setAndroidContext(this);

        mNameField = (EditText) findViewById(R.id.actNameEditT);
        mDateField = (EditText) findViewById(R.id.dateEditT);
        mCounselorField = (EditText) findViewById(R.id.counselorEditT);
        mLocationField= (EditText) findViewById(R.id.locationEditT);
        mDescField = (EditText) findViewById(R.id.descEditT);

        mAddActivityBtn = (Button) findViewById(R.id.addActivityBtn);
        mAddActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addActivity();
            }
        });

    } //[ENDS onCreate]

    public void addActivity(){
        final String name = mNameField.getText().toString();
        final String date = mDateField.getText().toString();
        final String counselor = mCounselorField.getText().toString();
        final String location = mLocationField.getText().toString();
        final String description = mDescField.getText().toString();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mActivitiesDatabase = mDatabase.child("Activities");

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(date) || TextUtils.isEmpty(counselor) || TextUtils.isEmpty(location) || TextUtils.isEmpty(description)){
            Toast.makeText(NewActivityScreen.this, "Fields are blank", Toast.LENGTH_LONG).show();
        } else {
            String key = mActivitiesDatabase.push().getKey();
            Activity activity = new Activity(name, description, counselor, date, location);
            Map<String, Object> activitiesValues = activity.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/Activities/" + key, activitiesValues);
            mDatabase.updateChildren(childUpdates);

            Intent i = new Intent(NewActivityScreen.this, ActivitiesActivity.class);
            startActivity(i);
            finish();
        }
    } //[ENDS addActivity]

} //[ENDS class]
