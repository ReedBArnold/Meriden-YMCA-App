package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/11/2017
 * Version 0.5
 *
 */
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.arnold.reed.meridenymcaapp.Models.Attendance;
import com.arnold.reed.meridenymcaapp.Models.Camper;
import com.arnold.reed.meridenymcaapp.Models.Group;
import com.arnold.reed.meridenymcaapp.Models.User;
import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AttendanceActivity extends AppCompatActivity {

    private ListView mAttendanceList;
    private Button mSubmitBtn;
    private String name, uid;
    private FirebaseUser mUser;

    private DatabaseReference mDatabase, mAttendanceDatabase, mGroupDatabase, mCamperDatabase, mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Firebase.setAndroidContext(this);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = mUser.getUid();
        name = "Reed";

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAttendanceDatabase = mDatabase.child("Attendance");
        //mGroupDatabase = mDatabase.child("Groups");
        mUserDatabase = mDatabase.child("Users").child(uid);
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //User user = dataSnapshot.getValue(User.class);
                //name = user.getName();
                name = String.valueOf(dataSnapshot.child("name").getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mCamperDatabase = mAttendanceDatabase.child(name).child("campers");

        mAttendanceList = (ListView) findViewById(R.id.attendanceListView);
        mSubmitBtn = (Button) findViewById(R.id.attendanceSubmitBtn);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAttendance();
            }
        });

        ListAdapter attendanceAdapter = new FirebaseListAdapter<Camper>(this, Camper.class, R.layout.attendance_camper_layout, mCamperDatabase) {
            @Override
            protected void populateView(View v, Camper camper, int position){
                //((TextView) v.findViewById(R.id.nameAttendanceView)).setText(camper.getName());
                ((TextView) v.findViewById(R.id.nameAttendanceView)).setText(camper.getName());
            }
        };

        mAttendanceList.setAdapter(attendanceAdapter);

    } //[ENDS onCreate]

    public void updateAttendance(){


    }//[ENDS updateAttendance]

} //[ENDS class]
