package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/16/2017
 * Version 0.6
 *
 */
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.arnold.reed.meridenymcaapp.Models.Activity;
import com.arnold.reed.meridenymcaapp.Models.Camper;
import com.arnold.reed.meridenymcaapp.Models.Group;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity {

    private ListView mGroupListView;
    private Button mAddGroup;
    private ArrayList<Camper> campers;

    private DatabaseReference mDatabase, mGroupDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mGroupDatabase = mDatabase.child("Groups");

        mGroupListView= (ListView) findViewById(R.id.groupListView);
        campers = new ArrayList<Camper>();

        mAddGroup = (Button) findViewById(R.id.addGroupBtn);
        mAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupsActivity.this, NewGroupActivity.class);
                startActivity(i);
            }
        });


        ListAdapter groupNamesAdapter = new FirebaseListAdapter<Group>(this, Group.class, R.layout.group_parent_layout, mGroupDatabase) {
            @Override
            protected void populateView(View v, Group group, int position) {
                ((TextView)v.findViewById(R.id.groupNameView)).setText(group.getName());
                //campers.add(group.getCampers().get(position));
                //((TextView)v.findViewById(R.id.currentAttendanceNum)).setText(group.getCampers().size());
            }
        };

        mGroupListView.setAdapter(groupNamesAdapter);
        mGroupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GroupsActivity.this, GroupDetailActivity.class);
                startActivity(intent);
            }
        });

    } //[ENDS onCreate]


} //[ENDS class]
