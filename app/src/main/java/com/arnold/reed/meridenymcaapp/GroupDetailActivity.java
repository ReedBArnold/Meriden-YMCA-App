package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/16/2017
 * Version 0.6
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.arnold.reed.meridenymcaapp.Models.Camper;
import com.arnold.reed.meridenymcaapp.Models.Group;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class GroupDetailActivity extends AppCompatActivity {

    private ListView mGroupCampersListView;

    private DatabaseReference mDatabase, mGroupDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_detail_activity);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mGroupDatabase = mDatabase.child("Groups");

        mGroupCampersListView = (ListView) findViewById(R.id.groupCampersListView);

        ListAdapter groupCampersNameAdapter = new FirebaseListAdapter<Camper>(this, Camper.class, R.layout.group_camper_detail_activity, mGroupDatabase) {
            @Override
            protected void populateView(View v, Camper camper, int position) {
                ((TextView) v.findViewById(R.id.groupCamperNameView)).setText(camper.getName());
                //((TextView)v.findViewById(R.id.currentAttendanceNum)).setText((String)group.getCampers().size());
            }
        };
        mGroupCampersListView.setAdapter(groupCampersNameAdapter);
    }//[ENDS onCreate]


}//[ENDS class]
