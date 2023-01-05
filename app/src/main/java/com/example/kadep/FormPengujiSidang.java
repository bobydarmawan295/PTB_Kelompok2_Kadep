package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kadep.models.FormPengujiSidangResponse;
import com.example.kadep.models.UpdateProfileResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPengujiSidang extends AppCompatActivity {

    private static final String CHANNEL_ID = "test_kanal";
    private NotificationManagerCompat notificationManager;
    private Button buttonShow, penetapanPenguji;
    EditText editPenguji, editPosisi;
    private String gettoken, token,penguji, posisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_penguji_sidang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notificationManager = NotificationManagerCompat.from(this);
        createNotificationChannel();

        buttonShow = findViewById(R.id.btnPenetapanPenguji);
        buttonShow.setOnClickListener(view -> {

            Intent resultIntent = new Intent(FormPengujiSidang.this, FormPengujiSidang.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(FormPengujiSidang.this);
            stackBuilder.addNextIntentWithParentStack(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(0,
                            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(FormPengujiSidang.this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setContentTitle("Penetapan penguji sidang")
                    .setContentText("Penguji telah ditetapkan.")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Much longer text that cannot fit one line..."))
                    .setContentIntent(resultPendingIntent)
                    .addAction(R.drawable.ic_baseline_notifications_24, "Lihat", resultPendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            notificationManager.notify(101, builder.build());
        });

        editPenguji = findViewById(R.id.id_penguji2);
        editPosisi= findViewById(R.id.posisi_penguji2);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.kadep.SHARED_KEY", MODE_PRIVATE);;
        gettoken = sharedPreferences.getString("token", "");
        token = "Bearer " + gettoken;

        Intent detailIntent = getIntent();
        Integer idSidang= detailIntent.getIntExtra("Id Sidang", 0);

        penetapanPenguji = findViewById(R.id.btnPenetapanPenguji);
        penetapanPenguji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penguji = editPenguji.getText().toString();
                posisi = editPosisi.getText().toString();

                int penguji2 =Integer.parseInt(penguji);
                int posisi2 = Integer.parseInt(posisi);

                Config config = new Config();
                Call<FormPengujiSidangResponse> call = config.configRetrofit().isiFormPenguji(token, idSidang,penguji2, posisi2);
                call.enqueue(new Callback<FormPengujiSidangResponse>() {
                    @Override
                    public void onResponse(Call<FormPengujiSidangResponse> call, Response<FormPengujiSidangResponse> response) {
                        FormPengujiSidangResponse pengujiSidangResponse= response.body();
                        String message;
                        JSONObject jsonObject = null;
                        Log.d("status", pengujiSidangResponse.getMessage());
                        if (response.code() == 200){
                            if (response.isSuccessful()) {
                                message = response.body().getMessage();
                                Toast.makeText(FormPengujiSidang.this, message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FormPengujiSidang.this, MainActivity.class);
                                setResult(RESULT_OK, intent);
                                startActivity(intent);
                            }
                        }
                        else if(response.code() == 403){
                            if(!response.isSuccessful()){
                                try {
                                    jsonObject = new JSONObject(response.errorBody().string());
                                    message = jsonObject.getString("message");
                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        else{
                            message = pengujiSidangResponse.getMessage();
                            Toast.makeText(FormPengujiSidang.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FormPengujiSidangResponse> call, Throwable t) {
                        Toast.makeText(FormPengujiSidang.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "contoh notifikasi";
            String description = "ini hanya kanal untuk contoh notifikasi";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            notificationManager.createNotificationChannel(channel);

        }
    }
}