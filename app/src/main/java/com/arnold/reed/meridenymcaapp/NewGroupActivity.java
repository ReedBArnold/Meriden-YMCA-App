package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/11/2017
 * Version 0.5
 *
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arnold.reed.meridenymcaapp.Models.Activity;
import com.arnold.reed.meridenymcaapp.Models.Attendance;
import com.arnold.reed.meridenymcaapp.Models.Camper;
import com.arnold.reed.meridenymcaapp.Models.Group;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NewGroupActivity extends AppCompatActivity {

    private EditText mGroupNameEditT, mCounselorEditT, mLocationEditT, mCamperEditT;
    private Button mAddCamperBtn, mAddGroupBtn;
    private String mCamper;

    private ArrayList<String> mCamperNames;
    private ArrayList<Camper> mCampersList;
    private Camper camper;

    private DatabaseReference mDatabase, mCampersDatabase, mGroupsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mGroupsDatabase = mDatabase.child("Groups");

        mCamperNames = new ArrayList<String>();
        mCampersList = new ArrayList<Camper>();
        //camper = new Camper();

        mGroupNameEditT = (EditText) findViewById(R.id.groupNameEditT);
        mCounselorEditT = (EditText) findViewById(R.id.groupCounselorEditT);
        mLocationEditT = (EditText) findViewById(R.id.groupLocationEditT);

        mCamperEditT = (EditText) findViewById(R.id.camperNameEditT);
        mAddCamperBtn = (Button) findViewById(R.id.addCamperBtn);
        mAddCamperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String camperName = mCamperEditT.getText().toString();
                camper = new Camper(camperName, false);

                mCamperNames.add(camperName);
                mCampersList.add(camper);

                mCamperEditT.setText("");
            }
        });

        mAddGroupBtn = (Button) findViewById(R.id.addGroupBtn);
        mAddGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGroup();
            }
        });
    } //[ENDS onCreate]

    public void addGroup(){
        final String name = mGroupNameEditT.getText().toString();
        final String counselor = mCounselorEditT.getText().toString();
        final String location = mLocationEditT.getText().toString();
        final Boolean status = false;
        final ArrayList<String> camperNames = mCamperNames;
        final ArrayList<Camper> campers = mCampersList;
        Camper camper = new Camper();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(counselor) || TextUtils.isEmpty(location)){
            Toast.makeText(NewGroupActivity.this, "Fields are blank", Toast.LENGTH_LONG).show();
        } else {
            String key = mGroupsDatabase.push().getKey();
            Group group = new Group(name, counselor, location, campers);
            Attendance attendance = new Attendance(counselor,campers);

//            for (Iterator<Camper> it = campers.iterator(); it.hasNext();){
//                camper = new Camper(it.next(),status);
//            }
//          Map<String, Object> camperValues = camper.toMap();
            Map<String, Object> attendanceValues = attendance.toMap();
            Map<String, Object> groupValues = group.toMap();
            Map<String, Object> childUpdates = new HashMap<>();

            childUpdates.put("/Groups/"+key, groupValues);
            childUpdates.put("/Attendance/"+counselor, attendanceValues);

            mDatabase.updateChildren(childUpdates);

            //mGroupsDatabase.push().setValue(new Group(name, counselor, location, campers));
            //mDatabase.child("Counselors").child(counselor).setValue(campers);

//            mDatabase.child("Counselors").child(counselor).addChildEventListener(new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });

            Intent i = new Intent(NewGroupActivity.this, GroupsActivity.class);
            startActivity(i);
            finish();
        }
    }//[ENDS addGroup]

} //[ENDS class]
