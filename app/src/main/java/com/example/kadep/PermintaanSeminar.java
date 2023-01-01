package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.kadep.adapters.SeminarAdapter;

import java.util.ArrayList;

public class PermintaanSeminar extends AppCompatActivity implements SeminarAdapter.ItemPermintaanSeminarClickListener{

    private RecyclerView rvSeminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permintaan_seminar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvSeminar = findViewById(R.id.rv_seminar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        SeminarAdapter adapter = new SeminarAdapter(getPermintaanSeminar());
        adapter.setListener(this);

        rvSeminar.setLayoutManager(layoutManager);
        rvSeminar.setAdapter(adapter);

    }

    public ArrayList<com.example.kadep.models.PermintaanSeminar> getPermintaanSeminar(){
        ArrayList<com.example.kadep.models.PermintaanSeminar> listPermintaanSeminar = new ArrayList<>();
        listPermintaanSeminar.add(new com.example.kadep.models.PermintaanSeminar(
                null,
                "Boby Darmawan",
                "2011522023"
        ));
        listPermintaanSeminar.add(new com.example.kadep.models.PermintaanSeminar(
                null,
                "Bang bob",
                "2011522023"
        ));
        listPermintaanSeminar.add(new com.example.kadep.models.PermintaanSeminar(
                null,
                "M Farhan zuhdi",
                "2011522030"
        ));
        listPermintaanSeminar.add(new com.example.kadep.models.PermintaanSeminar(
                null,
                "Vallen Adithya Reksana",
                "2011522017"
        ));
        listPermintaanSeminar.add(new com.example.kadep.models.PermintaanSeminar(
                null,
                "Billie Eilish",
                "2011522023"
        ));
        listPermintaanSeminar.add(new com.example.kadep.models.PermintaanSeminar(
                null,
                "Bang bob",
                "2011522023"
        ));

        listPermintaanSeminar.add(new com.example.kadep.models.PermintaanSeminar(
                null,
                "umibozu",
                "2011522023"
        ));

        listPermintaanSeminar.add(new com.example.kadep.models.PermintaanSeminar(
                null,
                "Bang gin",
                "2011522023"
        ));

        return listPermintaanSeminar;
    }

    @Override
    public void onItemPermintaanClick(com.example.kadep.models.PermintaanSeminar permintaanSeminar) {
        Intent detailSeminar = new Intent(this, DetailSeminar.class);
        detailSeminar.putExtra("Peserta Sidang", permintaanSeminar.getNama_mhs());
        startActivity(detailSeminar);
    }
}