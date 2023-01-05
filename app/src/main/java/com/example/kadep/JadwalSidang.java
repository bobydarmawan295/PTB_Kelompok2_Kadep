    package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class JadwalSidang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_sidang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}