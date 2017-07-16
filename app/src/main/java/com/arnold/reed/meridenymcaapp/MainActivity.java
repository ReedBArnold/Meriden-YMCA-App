package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/11/2017
 * Version 0.5
 *
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button mSignOut;
    private Button mAttendance;
    private Button mActivities;
    private Button mGroups;
    private Button mEmergencies;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

//        mToolbar = (Toolbar) findViewById(R.id.homeScreenBar);
//        setSupportActionBar(mToolbar);

        mAuth = FirebaseAuth.getInstance();


        mAttendance = (Button) findViewById(R.id.attendanceBtn);
        mAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AttendanceActivity.class);
                startActivity(i);
            }
        });

        mActivities = (Button) findViewById(R.id.activitiesBtn);
        mActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ActivitiesActivity.class);
                startActivity(i);
            }
        });

        mGroups = (Button) findViewById(R.id.groupsBtn);
        mGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GroupsActivity.class);
                startActivity(i);
            }
        });

        mEmergencies = (Button) findViewById(R.id.emergenciesBtn);
        mEmergencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EmergenciesActivity.class);
                startActivity(i);
            }
        });

        mSignOut = (Button) findViewById(R.id.signOutButton);
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                mAuthListener.onAuthStateChanged(mAuth);
            }
        });
    } //[ENDS onCreate]

} //[ENDS class]
