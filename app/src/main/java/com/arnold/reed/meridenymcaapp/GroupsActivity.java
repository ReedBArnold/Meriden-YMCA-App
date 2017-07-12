package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/8/2017
 * Version 0.4
 *
 */
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.arnold.reed.meridenymcaapp.Models.Activity;
import com.arnold.reed.meridenymcaapp.Models.Group;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GroupsActivity extends AppCompatActivity {

    private ExpandableListView mGroupListView;
    private Button mAddGroup;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        //RecyclerView groups = (RecyclerView) findViewById(R.id.)

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mGroupListView= (ExpandableListView) findViewById(R.id.groupListView);

        mAddGroup = (Button) findViewById(R.id.addGroupBtn);
        mAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupsActivity.this, NewGroupActivity.class);
                startActivity(i);
            }
        });

        ExpandableListAdapter groupsAdapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return 0;
            }

            @Override
            public int getChildrenCount(int i) {
                return 0;
            }

            @Override
            public Object getGroup(int i) {
                return null;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return null;
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return false;
            }
        };



       ListAdapter groupNamesAdapter = new FirebaseListAdapter<Group>(this, Group.class, R.layout.group_parent_layout, mDatabase) {
            @Override
            protected void populateView(View v, Group group, int position) {
                ((TextView)findViewById(R.id.groupNameView)).setText(group.getName());
            }
        };


    } //[ENDS onCreate]


} //[ENDS class]
