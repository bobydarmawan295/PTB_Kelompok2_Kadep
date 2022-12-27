package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kadep.adapters.SidangAdapter;

import java.util.ArrayList;

public class PermintaanSidang extends AppCompatActivity implements SidangAdapter.ItemPermintaanSidangClickListener {

    private RecyclerView rv_sidang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permintaan_sidang);

        rv_sidang = findViewById(R.id.rv_sidang);

        SidangAdapter adapter = new SidangAdapter(getPermintaanSidang());
        adapter.setListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rv_sidang.setLayoutManager(layoutManager);
        rv_sidang.setAdapter(adapter);
    }

    public ArrayList<com.example.kadep.models.PermintaanSidang> getPermintaanSidang(){
        ArrayList<com.example.kadep.models.PermintaanSidang> listPermintaanSidang = new ArrayList<>();
        listPermintaanSidang.add(new com.example.kadep.models.PermintaanSidang(
                null,
                "Boby Darmawan",
                "2011522023"
        ));
        listPermintaanSidang.add(new com.example.kadep.models.PermintaanSidang(
                null,
                "Bang bob",
                "2011522023"
        ));
        listPermintaanSidang.add(new com.example.kadep.models.PermintaanSidang(
                null,
                "M Farhan zuhdi",
                "2011522030"
        ));
        listPermintaanSidang.add(new com.example.kadep.models.PermintaanSidang(
                null,
                "Vallen Adithya Reksana",
                "2011522017"
        ));
        listPermintaanSidang.add(new com.example.kadep.models.PermintaanSidang(
                null,
                "Billie Eilish",
                "2011522023"
        ));
        listPermintaanSidang.add(new com.example.kadep.models.PermintaanSidang(
                null,
                "Bang bob",
                "2011522023"
        ));

        return listPermintaanSidang;
    }


    @Override
    public void onItemPermintaanClick(com.example.kadep.models.PermintaanSidang permintaanSidang) {
        Intent detailSidang = new Intent(this, DetailSidang.class);
        detailSidang.putExtra("Peserta Sidang", permintaanSidang.getNama_mhs());
        startActivity(detailSidang);
//        Toast.makeText(this, "Anda mengklik matkul" + permintaanSidang.getNama_mhs(), Toast.LENGTH_SHORT).show();
    }
}