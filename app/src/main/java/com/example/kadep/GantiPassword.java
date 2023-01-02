package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kadep.models.ChangePasswordResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GantiPassword extends AppCompatActivity {

    private EditText editOldPass, editNewPass, editConfPass;
    private String oldPass, newPass, confPass, gettoken, token, status, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editOldPass = findViewById(R.id.password_lama);
        editNewPass = findViewById(R.id.password_baru);
        editConfPass = findViewById(R.id.confirm_password);


        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.kadep.SHARED_KEY", MODE_PRIVATE);;

        Button editpw_submit = findViewById(R.id.ganti_password);
        editpw_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gettoken = sharedPreferences.getString("token", "");
                token = "Bearer " + gettoken;
                Log.d("token1", token);
                oldPass = editOldPass.getText().toString();
                newPass = editNewPass.getText().toString();
                confPass = editConfPass.getText().toString();
                
                Config config = new Config();
                Call<ChangePasswordResponse> call = config.configRetrofit().changePassword(token, oldPass, newPass, confPass);
                call.enqueue(new Callback<ChangePasswordResponse>() {
                    @Override
                    public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                        ChangePasswordResponse changePasswordResponse = response.body();
                        String message;
                        JSONObject jsonObject = null;
                        Log.d("status", changePasswordResponse.getMessage());
                        if (response.code() == 200){
                            if (changePasswordResponse.getStatus().equals("success")) {
                                message = response.body().getMessage();
                                Intent intent = new Intent(GantiPassword.this, MainActivity.class);
                                setResult(RESULT_OK, intent);
                                startActivity(intent);
                                finish();
                            }
                        }

                        else{
                            message = changePasswordResponse.getMessage();
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Password lama salah!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });
    }
}