package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    private String usn,gettoken,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences = SplashScreen.this.getSharedPreferences("userkey",  Context.MODE_PRIVATE);
        gettoken = sharedPreferences.getString("token", "");
        token = "Bearer " + gettoken;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if( gettoken != null)
                {
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                }else
                {
                    startActivity(new Intent(SplashScreen.this,LoginActivity.class));
                }
                finish();
            }
        }, 2000);
    }
}