package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadep.models.DetailSeminarResponse;
import com.example.kadep.models.ExaminersItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSeminar extends AppCompatActivity {
        TextView textTanggalSidang, textNIM, jamMulai, textJudul, textGender, textDospeng1, textDospeng2;
        String  token, gettoken;
        ExaminersItem penguji;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            String namaAgenda = null;
            TextView textNamaMahasiswa;

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_seminar);

            Intent detailIntent = getIntent();
            if(detailIntent != null){
                namaAgenda = detailIntent.getStringExtra("Peserta Seminar");
                textNamaMahasiswa = findViewById(R.id.nama_peserta_seminar);
                String str = namaAgenda.toLowerCase();
                String str2 = com.example.kadep.DetailSeminar.StringFormatter.capitalizeWord(str);
                textNamaMahasiswa.setText(str2);
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            textNIM = findViewById(R.id.nim_seminar);
            textTanggalSidang = findViewById(R.id.tanggalSeminar);
            jamMulai = findViewById(R.id.jamSeminar);
            textJudul = findViewById(R.id.judulSeminar);
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
            String NIM = detailIntent.getStringExtra("NIM");
            String tanggalMulai = detailIntent.getStringExtra("tanggalMulaiTA");
            String tanggalSidang= detailIntent.getStringExtra("judulSidang");
            Integer idSidang= detailIntent.getIntExtra("Id Sidang", 0);
            String id = idSidang.toString();
            int gender = detailIntent.getIntExtra("gender", 0);
            List<String> list=new ArrayList<String>();

            Config config = new Config();
            Call<DetailSeminarResponse> call = config.configRetrofit().getDetailSeminar(token,idThesis);
            call.enqueue(new Callback<DetailSeminarResponse>() {
                @Override
                public void onResponse(Call<DetailSeminarResponse> call, Response<DetailSeminarResponse> response) {
                    DetailSeminarResponse detailSeminarResponse = response.body();
                    textNIM.setText(NIM);
                    if(gender == 1){
                        textGender.setText("Laki-laki");
                    }else{
                        textGender.setText("Perempuan");
                    }
                    Toast.makeText(com.example.kadep.DetailSeminar.this, id, Toast.LENGTH_SHORT).show();
                    textTanggalSidang.setText(detailSeminarResponse.getRegisteredAt());
                    jamMulai.setText(tanggalMulai);
                    textJudul.setText(tanggalSidang);
//                    try{
//                        for (int i = 0; i < detailSidangResponse.getExaminers().size(); i++) {
//                            String dospeng = detailSidangResponse.getExaminers().get(i).getName();
//                            list.add(dospeng);
//                        }
//                        for (int j = 0; j < list_dospeng.length; j++) {
//                            ((TextView) findViewById(list_dospeng[j])).setText(list.get(j));
//                        }
//
//                    }catch(IndexOutOfBoundsException e){
//                        Toast.makeText(com.example.kadep.DetailSeminar.this, "Penguji Sidang Belum Ada", Toast.LENGTH_SHORT).show();
//                    }

                }

                @Override
                public void onFailure(Call<DetailSeminarResponse> call, Throwable t) {
                    Toast.makeText(com.example.kadep.DetailSeminar.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            Button formPengujiSidang = findViewById(R.id.btnPengujiSeminar);
            formPengujiSidang.setOnClickListener(views -> {
                Intent tetapkanPenguji = new Intent(com.example.kadep.DetailSeminar.this, FormPengujiSidang.class);
                tetapkanPenguji.putExtra("Id Sidang", idSidang);
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

//    String namaAgenda = null;
//    TextView textNamaMahasiswa;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail_seminar);
//
//        Intent detailIntent = getIntent();
//        if(detailIntent != null){
//            namaAgenda = detailIntent.getStringExtra("Peserta Sidang");
//            textNamaMahasiswa = findViewById(R.id.nama_peserta_seminar);
//            textNamaMahasiswa.setText(namaAgenda);
//        }
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//    }
//
//
//}