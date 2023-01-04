package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class FormPengujiSidang extends AppCompatActivity {

    private static final String CHANNEL_ID = "test_kanal";
    private NotificationManagerCompat notificationManager;
    private Button buttonShow;

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