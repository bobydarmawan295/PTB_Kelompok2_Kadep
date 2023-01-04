package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kadep.adapters.SeminarAdapter;
import com.example.kadep.adapters.SidangAdapter;
import com.example.kadep.models.PermintaanSidangResponse;
import com.example.kadep.models.SeminarsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PermintaanSidang extends AppCompatActivity implements SidangAdapter.ItemPermintaanSidangClickListener{

    private RecyclerView rv_sidang;
    String token, gettoken;
    SeminarsItem tes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permintaan_sidang);

        rv_sidang = findViewById(R.id.rv_sidang);

        SidangAdapter adapter = new SidangAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter.setListener(this);

        rv_sidang.setLayoutManager(layoutManager);
        rv_sidang.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.kadep.SHARED_KEY", Context.MODE_PRIVATE);
        gettoken = sharedPreferences.getString("token", "");
        token = "Bearer " + gettoken;

        Config config = new Config();
        Call<PermintaanSidangResponse> call = config.configRetrofit().getPermintaanSidang(token);
        call.enqueue(new Callback<PermintaanSidangResponse>() {
            @Override
            public void onResponse(Call<PermintaanSidangResponse> call, Response<PermintaanSidangResponse> response) {
                Log.d("PermintaanSidang-Debug", response.toString());
                PermintaanSidangResponse getPermintaanSidangResponse = response.body();
                if(getPermintaanSidangResponse != null){
                    List<SeminarsItem> listThesis = getPermintaanSidangResponse.getSeminars();
                    Log.d("PermintaanSidang-Debug", String.valueOf(listThesis.size()));
                    adapter.setItemThesis(listThesis);
                }
            }

            @Override
            public void onFailure(Call<PermintaanSidangResponse> call, Throwable t) {
                Toast.makeText(PermintaanSidang.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onItemPermintaanClick(SeminarsItem permintaanSidang) {
        Intent detailSidang = new Intent(this, DetailSidang.class);
        detailSidang.putExtra("Peserta Sidang", permintaanSidang.getThesis().getStudent().getName());
        detailSidang.putExtra("Id Thesis", permintaanSidang.getThesisId());
        startActivity(detailSidang);
    }

}