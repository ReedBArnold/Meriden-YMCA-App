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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity{

    private static final String TAG = "SignIn";

    private EditText mEmailField;
    private EditText mPasswordField;
    private TextView mRegisterLink;

    private Button mSignInBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        Firebase.setAndroidContext(this);

        mAuth = FirebaseAuth.getInstance();

        mEmailField = (EditText) findViewById(R.id.nameEditR);
        mPasswordField = (EditText) findViewById(R.id.passwordEditR);

        mRegisterLink = (TextView) findViewById(R.id.registerLinkView);
        mRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister();
            }
        });

        mSignInBtn = (Button) findViewById(R.id.signInButton);
        mSignInBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startSignIn();
            }
        });
    } //[ENDS onCreate]


    @Override
    protected void onStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
        }
    } //[ENDS onStart]


    private void onRegister(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(SignInActivity.this, RegistrationActivity.class));
        }
    } //[ENDS onRegister]


    private void startSignIn(){
        final String email = mEmailField.getText().toString();
        final String password = mPasswordField.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(SignInActivity.this, "Fields are blank", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Log.d(TAG, "signUserWithEmail:Success");

                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if(currentUser != null){
                            Intent i = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                    } else{Toast.makeText(SignInActivity.this, "Sign-In Problem", Toast.LENGTH_LONG).show();}
                }
            });
        }
    } //[ENDS startSignIn]


} // [ENDS class]
