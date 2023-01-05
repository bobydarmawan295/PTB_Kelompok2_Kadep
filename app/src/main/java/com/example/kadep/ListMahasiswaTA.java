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

public class PermintaanMahasiswaTA extends AppCompatActivity implements MahasiswaTAAdapter.ItemPermintaanMahasiswaTAClickListener{

    private RecyclerView rvSeminar;
    String token, gettoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permintaan_TA);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvMahasiswaTA = findViewById(R.id.rv_seminar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //membuat objek adapter
        MahasiswaTAAdapter adapter = new MahasiswaTAAdapter();
        adapter.setListener(this);

        rvMahasiswaTA.setLayoutManager(layoutManager);
        rvMahasiswaTA.setAdapter(adapter);

        //api
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.kadep.SHARED_KEY", Context.MODE_PRIVATE);
        gettoken = sharedPreferences.getString("token", "");
        token = "Bearer " + gettoken;

        Config config = new Config();
        Call<ListMahasiswaTAResponse> call = config.configRetrofit().getListMahasiswaTA(token);
        call.enqueue(new Callback<ListMahasiswaTAResponse>() {
            @Override
            public void onResponse(Call<ListMahasiswaTAResponse> call, Response<ListMahasiswaTArResponse> response) {
                Log.d("PermintaanSeminar-Debug", response.toString());
                ListMahasiswaTAResponse getListMahasiswaResponse = response.body();
                if(getListMahasiswaTAResponse != null){
                    List<MahasiswaTAItem> listMahasiswaTA = getListMahasiswaResponseResponse.getSeminars();
                    Log.d("ListMahasiswaTA-Debug", String.valueOf(listMahasiswaTA.size()));
                    adapter.setItemMahasiswaTA(listMahasiswaTA);
                }
            }

            @Override
            public void onFailure(Call<ListMahasiswaTAResponse> call, Throwable t) {
                Toast.makeText(ListMahasiswaTA.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemListClick(SeminarsItem ListMahasiswaTA) {
        Intent detailTA = new Intent(this, DetailTA.class);
        detailTA.putExtra("Peserta TA", listMahasiswaTA.getThesis().getStudent().getName());
        startActivity(detailMahasiswaTA);
    }
}