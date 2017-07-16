package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/11/2017
 * Version 0.5
 *
 */
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.arnold.reed.meridenymcaapp.Models.Activity;
import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivitiesActivity extends AppCompatActivity {

    private ListView mActivitiesList;
    private Button mAddActivityBtn;

    private DatabaseReference mDatabase;
    private FragmentManager fm;
    private FragmentTransaction fgTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        Firebase.setAndroidContext(this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Activities");

        mActivitiesList = (ListView) findViewById(R.id.activitiesListView);

        mAddActivityBtn = (Button) findViewById(R.id.addActivityBtn);
        mAddActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivitiesActivity.this, NewActivityScreen.class);
                startActivity(i);
            }
        });

        ListAdapter adapter = new FirebaseListAdapter<Activity>(this, Activity.class, R.layout.activity_detail, mDatabase) {
            @Override
            protected void populateView(View v, Activity dailyActivity, int position) {
                ((TextView)v.findViewById(R.id.activityNameView)).setText(dailyActivity.getName());
                ((TextView)v.findViewById(R.id.activityDateView)).setText(dailyActivity.getDate());
                ((TextView)v.findViewById(R.id.activityDescView)).setText(dailyActivity.getDesc());
                ((TextView)v.findViewById(R.id.activityCounselorView)).setText(dailyActivity.getCounselor());
                ((TextView)v.findViewById(R.id.activityLocationView)).setText(dailyActivity.getLocation());
            }
        };
        mActivitiesList.setAdapter(adapter);
    } //[ENDS onCreate]


} //[ENDS class]
