package com.arnold.reed.meridenymcaapp;
/**
 * Created by Reed on 7/8/2017
 * Version 0.4
 *
 *
 * Class to control the splash screen and it's timer
 *
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, SignInActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }  //[ENDS onCreate]

}  //[ENDS class]