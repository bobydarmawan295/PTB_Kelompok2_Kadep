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

public class DetailSidang extends AppCompatActivity {

    TextView textTanggal, textPukulMulai, textPukulSelesai, textNilaiSidang, textDospeng1, textDospeng2;
    String  token, gettoken;
    ExaminersItem penguji;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String namaAgenda = null;
        TextView textNamaMahasiswa;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sidang);

        Intent detailIntent = getIntent();
        if(detailIntent != null){
            namaAgenda = detailIntent.getStringExtra("Peserta Sidang");
            textNamaMahasiswa = findViewById(R.id.nama_peserta_sidang);
            String str = namaAgenda.toLowerCase();
            String str2 = StringFormatter.capitalizeWord(str);
            textNamaMahasiswa.setText(str2);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textTanggal = findViewById(R.id.tanggal_sidang);
        textPukulMulai = findViewById(R.id.tanggal_mulai);
        textPukulSelesai = findViewById(R.id.tanggal_selesai);
        textNilaiSidang = findViewById(R.id.nilai_sidang);
//        textDospeng1 = findViewById(R.id.dospeng1);
//        textDospeng2 = findViewById(R.id.dospeng2);
        int[] list_dospeng= {
                R.id.dospeng1,
                R.id.dospeng2,
        };


        SharedPreferences sharedPreferences = getSharedPreferences("com.example.kadep.SHARED_KEY", Context.MODE_PRIVATE);
        gettoken = sharedPreferences.getString("token", "");
        token = "Bearer " + gettoken;

        int idThesis = detailIntent.getIntExtra("Id Thesis", 0);
        List<String> list=new ArrayList<String>();

        Config config = new Config();
        Call<DetailSidangResponse> call = config.configRetrofit().getDetailSidang(token,idThesis);
        call.enqueue(new Callback<DetailSidangResponse>() {
            @Override
            public void onResponse(Call<DetailSidangResponse> call, Response<DetailSidangResponse> response) {
                DetailSidangResponse detailSidangResponse = response.body();
                textTanggal.setText(detailSidangResponse.getTrialAt());
                try{
                    for (int i = 0; i < detailSidangResponse.getExaminers().size(); i++) {
                        String dospeng = detailSidangResponse.getExaminers().get(i).getName();
                        list.add(dospeng);
                    }
                    for (int j = 0; j < list_dospeng.length; j++) {
                        ((TextView) findViewById(list_dospeng[j])).setText(list.get(j));
                    }

                }catch(IndexOutOfBoundsException e){
                    Toast.makeText(DetailSidang.this, "Penguji Sidang Belum Ada", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DetailSidangResponse> call, Throwable t) {
                Toast.makeText(DetailSidang.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Button formPengujiSidang = findViewById(R.id.btnPenguji);
        formPengujiSidang.setOnClickListener(views -> {
            Intent tetapkanPenguji = new Intent(DetailSidang.this, FormPengujiSidang.class);
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