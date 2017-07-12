package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/8/2017
 * Version 0.4
 *
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.arnold.reed.meridenymcaapp.Models.Emergency;
import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmergenciesActivity extends AppCompatActivity {

    private Button mAddEmergBtn;
    private TextView mPriorityView, mLocView, mDateView, mDescView, mCounselorView;
    private TextView mPastPriorityView, mPastLocView, mPastDateView, mPastDescView, mPastCounselorView;

    private Emergency emergency;
    private String priorityPast;

    private DatabaseReference mDatabase;
    private DatabaseReference mNewEmergDatabase;
    private DatabaseReference mOldEmergDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencies);
        Firebase.setAndroidContext(this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Emergencies");
        mNewEmergDatabase = mDatabase.child("New Emergencies");
        mOldEmergDatabase = mDatabase.child("Old Emergencies");

        emergency = new Emergency();

        mPriorityView = (TextView) findViewById(R.id.priorityLevelView);
        mLocView = (TextView) findViewById(R.id.locationView);
        mDateView = (TextView) findViewById(R.id.dateView);
        mDescView = (TextView) findViewById(R.id.descView);
        mCounselorView = (TextView) findViewById(R.id.counselorView);

        mPastPriorityView = (TextView) findViewById(R.id.pastPriorityLevelView);
        mPastLocView = (TextView) findViewById(R.id.pastLocationView);
        mPastDateView = (TextView) findViewById(R.id.pastDateView);
        mPastDescView = (TextView) findViewById(R.id.pastDescriptionView);
        mPastCounselorView = (TextView) findViewById(R.id.pastCounselorView);


        mAddEmergBtn = (Button) findViewById(R.id.addEmergBtn);
        mAddEmergBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EmergenciesActivity.this, NewEmergencyActivity.class);
                startActivity(i);
                finish();
            }
        });

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Emergency emergency = dataSnapshot.getValue(Emergency.class);

                mPriorityView.setText(emergency.getPriority());
                mLocView.setText(emergency.getLocation());
                mDateView.setText(emergency.getDate());
                mDescView.setText(emergency.getDesc());
                mCounselorView.setText(emergency.getCounselor());

                priorityPast = emergency.getPriority();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                mPastPriorityView.setText(priorityPast);
                mPastLocView.setText(emergency.getLocation());
                mPastDateView.setText(emergency.getDate());
                mPastDescView.setText(emergency.getDesc());
                mPastCounselorView = mCounselorView;
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                mPastPriorityView.setText(priorityPast);
                mPastLocView.setText(emergency.getLocation());
                mPastDateView.setText(emergency.getDate());
                mPastDescView.setText(emergency.getDate());
                mPastCounselorView = mCounselorView;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    } //[ENDS onCreate]

//    @Override
//    protected void onStart(){
//        super.onStart();
//
//
//        //mPriorityView.setText();
//    } //[ENDS onStart]


} //[ENDS class]
