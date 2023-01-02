package com.example.kadep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kadep.models.UpdateProfileResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends AppCompatActivity {

    EditText editEmail, editNama;
    private String gettoken, token, name, email, name2, email2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editEmail = findViewById(R.id.update_email);
        editNama = findViewById(R.id.update_nama);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.kadep.SHARED_KEY", MODE_PRIVATE);;
        gettoken = sharedPreferences.getString("token", "");
        token = "Bearer " + gettoken;

        Button updateProfil = findViewById(R.id.update_btn);
        updateProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name2 = editEmail.getText().toString();
                email2 = editNama.getText().toString();

                Config config = new Config();
                Call<UpdateProfileResponse> call = config.configRetrofit().updateProfile(token, email2, name2);
                call.enqueue(new Callback<UpdateProfileResponse>() {
                    @Override
                    public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                        UpdateProfileResponse updateProfile = response.body();
                        String message;
                        JSONObject jsonObject = null;
                        Log.d("status", updateProfile.getMessage());
                        if (response.code() == 200){
                            if (response.isSuccessful()) {
                                message = response.body().getMessage();
                                Toast.makeText(UpdateProfile.this, message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
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
                            message = updateProfile.getMessage();
                            Toast.makeText(UpdateProfile.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}