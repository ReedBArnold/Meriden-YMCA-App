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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.arnold.reed.meridenymcaapp.Models.Emergency;
import com.arnold.reed.meridenymcaapp.Models.User;
import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewEmergencyActivity extends AppCompatActivity {

    private String mPriority;
    private Spinner mSpinner;
    private EditText mDescription;
    private EditText mLocation;
    private EditText mCounselor;
    private Date mDate;

    private Button mEmergButton;

    private DatabaseReference mDatabase, mEmergDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_emergency);
        Firebase.setAndroidContext(this);

        mDate = new Date();
        mDescription = (EditText) findViewById(R.id.descEmerEditT);
        mLocation = (EditText) findViewById(R.id.locEmerEditT);
        mCounselor = (EditText) findViewById(R.id.counselorEmerEditT);
        mSpinner = (Spinner) findViewById(R.id.prioritySpinner);

        mEmergButton = (Button) findViewById(R.id.emergencyBtn);
        mEmergButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmergency();
            }
        });
    } //[ENDS onCreate]

    public void addEmergency(){
        final String description = mDescription.getText().toString();
        final String location = mLocation.getText().toString();
        final String counselor = mCounselor.getText().toString();
        final String date = mDate.toString();
        final String priority = mSpinner.getSelectedItem().toString();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mEmergDatabase = mDatabase.child("Emergencies");

        if(TextUtils.isEmpty(description) || TextUtils.isEmpty(location) || TextUtils.isEmpty(counselor) || TextUtils.isEmpty(counselor)){
            Toast.makeText(NewEmergencyActivity.this, "Fields are blank", Toast.LENGTH_LONG).show();
        } else {

            String key = mEmergDatabase.push().getKey();
            Emergency emergency = new Emergency(description, counselor, priority, date, location);
            Map<String, Object> emergencyValues = emergency.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/Emergencies/" + key, emergencyValues);
            mDatabase.updateChildren(childUpdates);

            Intent i = new Intent(NewEmergencyActivity.this, EmergenciesActivity.class);
            startActivity(i);
            finish();
        }
    } //[ENDS addEmergency]

} //[ENDS class]
