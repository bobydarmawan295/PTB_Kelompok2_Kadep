package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailSeminar extends AppCompatActivity {

    String namaAgenda = null;
    TextView textNamaMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_seminar);

        Intent detailIntent = getIntent();
        if(detailIntent != null){
            namaAgenda = detailIntent.getStringExtra("Peserta Sidang");
            textNamaMahasiswa = findViewById(R.id.nama_peserta_sidang1);
            textNamaMahasiswa.setText(namaAgenda);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


}