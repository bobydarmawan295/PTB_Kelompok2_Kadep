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
import com.example.kadep.models.PermintaanSeminarResponse;
import com.example.kadep.models.SeminarsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PermintaanSeminar extends AppCompatActivity implements SeminarAdapter.ItemPermintaanSeminarClickListener{

    private RecyclerView rvSeminar;
    String token, gettoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permintaan_seminar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvSeminar = findViewById(R.id.rv_seminar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        SeminarAdapter adapter = new SeminarAdapter();
        adapter.setListener(this);

        rvSeminar.setLayoutManager(layoutManager);
        rvSeminar.setAdapter(adapter);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.kadep.SHARED_KEY", Context.MODE_PRIVATE);
        gettoken = sharedPreferences.getString("token", "");
        token = "Bearer " + gettoken;

        Config config = new Config();
        Call<PermintaanSeminarResponse> call = config.configRetrofit().getPermintaanSeminar(token);
        call.enqueue(new Callback<PermintaanSeminarResponse>() {
            @Override
            public void onResponse(Call<PermintaanSeminarResponse> call, Response<PermintaanSeminarResponse> response) {
                Log.d("PermintaanSeminar-Debug", response.toString());
                PermintaanSeminarResponse getPermintaanSidangResponse = response.body();
                if(getPermintaanSidangResponse != null){
                    List<SeminarsItem> listSeminar = getPermintaanSidangResponse.getSeminars();
                    Log.d("PermintaanSeminar-Debug", String.valueOf(listSeminar.size()));
                    adapter.setItemSeminar(listSeminar);
                }
            }

            @Override
            public void onFailure(Call<PermintaanSeminarResponse> call, Throwable t) {
                Toast.makeText(PermintaanSeminar.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemPermintaanClick(SeminarsItem permintaanSeminar) {
        Intent detailSeminar = new Intent(this, DetailSeminar.class);
        detailSeminar.putExtra("Peserta Sidang", permintaanSeminar.getThesis().getStudent().getName());
        startActivity(detailSeminar);
    }
}