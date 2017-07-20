package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/16/2017
 * Version 0.6
 *
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttendanceActivity extends AppCompatActivity {

    private ListView mAttendanceList;
    private Button mSubmitBtn;
    private CheckBox mStatusBtn;
    private Boolean mStatus;
    private String mName, uid, nameTest;
    private FirebaseUser mUser;
    private ArrayList<Camper> mCamperList;

    private DatabaseReference mDatabase, mAttendanceDatabase,mCamperDatabase, mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Firebase.setAndroidContext(this);

        mCamperList = new ArrayList<Camper>();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = mUser.getUid();
        //nameTest = mUser.getDisplayName();

        nameTest = "James";
        mName = "Reed";
        mStatus = false;

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAttendanceDatabase = mDatabase.child("Attendance");

        mUserDatabase = mDatabase.child("Users").child(uid);
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mName = dataSnapshot.child("name").getValue().toString();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        mCamperDatabase = mAttendanceDatabase.child(mName).child("campers");
        mCamperDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot camperSnapshot : dataSnapshot.getChildren()){
                    Camper camper = new Camper();
                    nameTest = camperSnapshot.child("name").getValue().toString();
                    camper.setName(nameTest);
                    camper.setStatus(mStatus);
                    mCamperList.add(camper);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mStatusBtn = (CheckBox) findViewById(R.id.statusCheckBox);

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
                ((TextView) v.findViewById(R.id.nameAttendanceView)).setText(camper.getName());
            }
        };
        mAttendanceList.setAdapter(attendanceAdapter);

    } //[ENDS onCreate]

    public void updateAttendance(){
        final String counselor = mName;
        final Boolean status = false;
        final ArrayList<Camper> campers = mCamperList;

            Attendance attendance = new Attendance(uid,counselor,campers);

            Map<String, Object> attendanceValues = attendance.toMap();
            Map<String, Object> childUpdates = new HashMap<>();

            childUpdates.put("/Attendance/"+counselor, attendanceValues);

            mDatabase.updateChildren(childUpdates);

            Intent i = new Intent(AttendanceActivity.this, MainActivity.class);
            startActivity(i);
            finish();
    }//[ENDS updateAttendance]


    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox)view).isChecked();
        if(checked){
            mStatus = true;
            //mStatusBtn.setText("Present");
        } else {
            mStatus = false;
            //mStatusBtn.setText("Absent");
        }
    }//[ENDS onCheckboxClicked]

} //[ENDS class]
