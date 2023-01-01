package com.example.kadep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kadep.databinding.ActivityMainBinding;
import com.example.kadep.models.LogoutResponse;
import com.example.kadep.network.StoryEndpoint;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity-Debug";
    private  ActivityMainBinding binding;
    String token;
    private boolean isLoggedIn = false;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    PermintaanTaFragment permintaanTA = new PermintaanTaFragment();
    MahasiwaTaFragment mahasiwaTA = new MahasiwaTaFragment();
    ProfilFragment profil = new ProfilFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        SharedPreferences sharedPref = getSharedPreferences("com.example.kadep.SHARED_KEY", Context.MODE_PRIVATE);

        token = sharedPref.getString("token","");
        Log.d("token", token);
        if (token.equals("")){
            Log.i("token", token);
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d(TAG, token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        Intent mainIntent = getIntent();
        String username = mainIntent.getStringExtra("name");
        isLoggedIn = mainIntent.getBooleanExtra("IS_LOGGED_IN", false);


        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.permintaan:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, permintaanTA).commit();
                        return true;
                    case R.id.mahasiswa:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mahasiwaTA).commit();
                        return true;
                    case R.id.menu_profil:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profil).commit();
                        return true;
                }
                return false;
            }
        });

//        if(!isLoggedIn){
//            Intent loginIntent = new Intent(this, LoginActivity.class);
//            startActivity(loginIntent);
//            finish();
//        }

//        binding.nama.setText(username);



    }


}