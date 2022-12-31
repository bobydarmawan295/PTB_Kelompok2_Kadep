package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kadep.databinding.ActivityLoginBinding;
import com.example.kadep.models.LoginResponse;
import com.example.kadep.network.StoryEndpoint;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    EditText editUsername, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
    }

    public void onButtonLoginClicked(View view) {

        String username = binding.editUsername.getText().toString();
        String password = binding.editPassword.getText().toString();

            Config config = new Config();
            Call<LoginResponse> call = config.configRetrofit().login(username, password);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    String message = null;
                    JSONObject jsonObject = null;
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null){
                        String token = loginResponse.getAuthorisation().getToken();
                        String name = loginResponse.getUser().getName();
                        String username = loginResponse.getUser().getUsername();
                        String eml = loginResponse.getUser().getEmail();

                        Log.i("success", token);
                        SharedPreferences sharedPreferences = getSharedPreferences("com.example.kadep.SHARED_KEY", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", token);
                        editor.putString("name", name);
                        editor.putString("email", eml);
                        editor.putString("username", username);

                        Log.d("email", eml);
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("logx", true);
                        //intent.putExtra("name", name);
                        startActivity(intent);
                    } else{
//                        message = loginResponse.getStatus();
                        Toast.makeText(getApplicationContext(), "username password salah", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

//    public Integer validation(String email, String password){
//        Integer valid = 1;
//        if(email.isEmpty()){
//            editUsername.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_IN);
//            editUsername.setError("Masukkan email!");
//            valid = 0;
//        }
//        else{
//            editUsername.getBackground().setColorFilter(getResources().getColor(R.color.pabu), PorterDuff.Mode.SRC_ATOP);
//        }
//
//        if(password.isEmpty()){
//            editPassword.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
//            editPassword.setError("Masukkan password");
//            valid = 0;
//        }
//        else{
//            editPassword.getBackground().setColorFilter(getResources().getColor(R.color.pabu), PorterDuff.Mode.SRC_ATOP);
//        }
//
//        return valid;
//    }
}