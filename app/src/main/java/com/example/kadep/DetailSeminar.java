package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadep.adapters.SeminarAdapter;
import com.example.kadep.models.DetailSidangResponse;
import com.example.kadep.models.ExaminersItem;
import com.example.kadep.models.SeminarsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSeminar extends AppCompatActivity {

    TextView textTanggalSeminar, textNIM, textTanggalMulai, textJudul, textGender, textDospeng1, textDospeng2;
    String  token, gettoken;
    ExaminersItem penguji;


    @SuppressLint("MissingInflatedId")
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
            String str2 = SeminarAdapter.StringFormatter.capitalizeWord(str);
            textNamaMahasiswa.setText(str2);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textNIM = findViewById(R.id.nim_seminar);
        textTanggalSeminar = findViewById(R.id.tanggal_sidang);
        textTanggalMulai = findViewById(R.id.tanggalSeminar);
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
        String judul= detailIntent.getStringExtra("judulSeminar");
        Integer idSeminar= detailIntent.getIntExtra("Id Seminar", 0);
        String id = idSeminar.toString();
        List<String> list=new ArrayList<String>();

        Config config = new Config();
        Call<SeminarsItem> call = config.configRetrofit().getDetailSeminar(token,idThesis);
        call.enqueue(new Callback<SeminarsItem>() {
            @Override
            public void onResponse(Call<SeminarsItem> call, Response<SeminarsItem> response) {
                SeminarsItem detailSeminarResponse = response.body();
                textNIM.setText(NIM);
                Toast.makeText(DetailSeminar.this, id, Toast.LENGTH_SHORT).show();
                textTanggalSeminar.setText(detailSeminarResponse.getSeminarAt());
                textTanggalMulai.setText(tanggalMulai);
                textJudul.setText(judul);
                try{
                    for (int i = 0; i < detailSeminarResponse.getExaminers().size(); i++) {
                        String dospeng = detailSeminarResponse.getExaminers().get(i).getName();
                        list.add(dospeng);
                    }
                    for (int j = 0; j < list_dospeng.length; j++) {
                        ((TextView) findViewById(list_dospeng[j])).setText(list.get(j));
                    }

                }catch(IndexOutOfBoundsException e){
                    Toast.makeText(DetailSeminar.this, "Penguji Sidang Belum Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SeminarsItem> call, Throwable t) {
                public void onFailure(Call<DetailSidangResponse> call, Throwable t) {
                    Toast.makeText(DetailSidang.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        Button formPengujiSidang = findViewById(R.id.btnPenguji);
        formPengujiSidang.setOnClickListener(views -> {
            Intent tetapkanPenguji = new Intent(DetailSidang.this, FormPengujiSidang.class);
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