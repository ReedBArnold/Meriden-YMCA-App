package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/8/2017
 * Version 0.4
 *
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.arnold.reed.meridenymcaapp.Models.Attendance;
import com.arnold.reed.meridenymcaapp.Models.Camper;
import com.arnold.reed.meridenymcaapp.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "Registration";

    private EditText mNameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private RadioButton mDirectorBtn, mCounselorBtn;
    private String userType;

    private Button mRegisterBtn;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserDatabase = mDatabase.child("Users");

        mNameField = (EditText) findViewById(R.id.nameEditR);
        mEmailField = (EditText) findViewById(R.id.emailEditR);
        mPasswordField = (EditText) findViewById(R.id.passwordEditR);

        mDirectorBtn = (RadioButton) findViewById(R.id.directorBtn);
        mDirectorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType(1);
            }
        });

        mCounselorBtn = (RadioButton) findViewById(R.id.counselorBtn);
        mCounselorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType(2);
            }
        });

        mRegisterBtn = (Button) findViewById(R.id.registerButton);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    } //[ENDS onCreate]


    public void setType(int i){
        if(i==1){
            userType = "Director";
        } else if(i==2){
            userType = "Counselor";
        }
    } //[ENDS setType]


    public void registerUser(){
        final String name = mNameField.getText().toString();
        final String email = mEmailField.getText().toString();
        final String password = mPasswordField.getText().toString();
        final String type = userType;

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)){
            Toast.makeText(RegistrationActivity.this, "Fields are blank", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "AccountCreation:Success");

                        String uid = mAuth.getCurrentUser().getUid();

                        String key = mUserDatabase.push().getKey();
                        User user = new User(uid,name, email, password, type);

                        Map<String, Object> userValues = user.toMap();
                        Map<String, Object> childUpdates = new HashMap<>();
                        Map<String, Object> attendanceValues = new HashMap();

                        if(userType.equals("Counselor")){
                            ArrayList<Camper> campers = new ArrayList<Camper>();
//                            Camper camper1 = new Camper("Tim",false);
//                            campers.add(camper1);
                            Attendance attendance = new Attendance(name,campers);
                            attendanceValues = attendance.toMap();
                            childUpdates.put("/Attendance/"+name, attendanceValues);
                        }

                        childUpdates.put("/Users/" + key, userValues);
                        mDatabase.updateChildren(childUpdates);

                        //mDatabase.push().setValue(new User(username, email, password, type));

                        Intent i = new Intent(RegistrationActivity.this, SignInActivity.class);
                        startActivity(i);
                        finish();

                    } else{Toast.makeText(RegistrationActivity.this, "Registration Problem", Toast.LENGTH_LONG).show();}
                }
            });
        }
    } //[ENDS registerUser]


} //[ENDS class]
