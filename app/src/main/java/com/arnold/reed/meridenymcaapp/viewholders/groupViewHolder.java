package com.arnold.reed.meridenymcaapp.viewholders;
/**
 * Created by Reed on 6/20/2017
 * Version 0.2
 *
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arnold.reed.meridenymcaapp.Models.Activity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class groupViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

    private final TextView mGroupNameField;
    private View mView;
    private Context mContext;


    public groupViewHolder(View view){
        super(view);
        mGroupNameField = (TextView) itemView.findViewById(android.R.id.text1);

        mView = view;
        mContext = view.getContext();
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        final ArrayList<Activity> groupsList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    }

    public void setGroupName(String groupName){
        mGroupNameField.setText(groupName);
    }
}
