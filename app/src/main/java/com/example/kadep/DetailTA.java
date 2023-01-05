package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadep.models.DetailSidangResponse;
import com.example.kadep.models.ExaminersItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String namaAgenda = null;
        TextView textNamaMahasiswa;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ta);
        Intent detailIntent = getIntent();
        if(detailIntent != null){
            namaAgenda = detailIntent.getStringExtra("Peserta TA");
            textNamaMahasiswa = findViewById(R.id.nama_peserta_ta);
            String str = namaAgenda.toLowerCase();
            String str2 = StringFormatter.capitalizeWord(str);
            textNamaMahasiswa.setText(str2);
        }

        //api
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.kadep.SHARED_KEY", Context.MODE_PRIVATE);
        gettoken = sharedPreferences.getString("token", "");
        token = "Bearer " + gettoken;

        int idThesis = detailIntent.getIntExtra("Id Thesis", 0);
        List<String> list=new ArrayList<String>();

        Config config = new Config();
        Call<DetailTAResponse> call = config.configRetrofit().getDetailTA(token,idThesis);
        call.enqueue(new Callback<DetailTAResponse>() {
            @Override
            public void onResponse(Call<DetailTAResponse> call, Response<DetailTAResponse> response) {
                DetailTAResponse detailTAResponse = response.body();
                textTanggal.setText(detailTAResponse.getTrialAt());
                try{
                    for (int i = 0; i < detailTAResponse.getExaminers().size(); i++) {
                        String dospeng = detailTAResponse.getExaminers().get(i).getName();
                        list.add(dospeng);
                    }
                    for (int j = 0; j < list_dospeng.length; j++) {
                        ((TextView) findViewById(list_dospeng[j])).setText(list.get(j));
                    }

                }catch(IndexOutOfBoundsException e){
                    Toast.makeText(DetailTA.this, "Penguji TA Belum Ada", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DetailTAResponse> call, Throwable t) {
                Toast.makeText(DetailTA.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Button formPengujiTA = findViewById(R.id.btnPenguji);
        formPengujiTA.setOnClickListener(views -> {
            Intent tetapkanPenguji = new Intent(DetailTA.this, FormPengujiTA.class);
            startActivity(tetapkanPenguji);
        });
        Button formPesertaTA = findViewById(R.id.btnPesertaTA);
        formPesertaTA.setOnClickListener(views -> {
            Intent tetapkanPeserta = new Intent(DetailTA.this, FormPesertaTA.class);
            startActivity(tetapkanPenguji);
        });
    }

    public static class StringFormatter {
        public static String capitalizeWord(String str){
            String words[]=str.split("\\s");
            String capitalizeWord="";
            for(String w:words){
                String first=w.substring(0,1);
                String afterfirst=w.substring(1);
                capitalizeWord+=first.toUpperCase()+afterfirst+" ";
            }
            return capitalizeWord.trim();
        }
    }
}
