package com.example.kadep;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadep.models.LogoutResponse;
import com.example.kadep.models.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfilFragment extends Fragment {

    TextView textNama, textNip, textUname, textEmail;
    String nama, nip, email, token, gettoken;
    Button updateProfile,changePassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_profil, container, false);

        ImageView logout_icon = view.findViewById(R.id.logout);
        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        updateProfile = view.findViewById(R.id.profil_update_btn);
        updateProfile.setOnClickListener(views -> {
            Intent updateProfil = new Intent(getActivity().getApplicationContext(), UpdateProfile.class);
            startActivity(updateProfil);
        });

        changePassword = view.findViewById(R.id.change_password_btn);
        changePassword.setOnClickListener(views -> {
            Intent gantiPass = new Intent(getActivity().getApplicationContext(), GantiPassword.class);
            startActivity(gantiPass);
        });

        textNama = view.findViewById(R.id.nama_fill);
        textUname = view.findViewById(R.id.username_fill);
        textEmail = view.findViewById(R.id.email_fill);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.example.kadep.SHARED_KEY", Context.MODE_PRIVATE);
        gettoken = sharedPreferences.getString("token", "");
        token = "Bearer " + gettoken;

        Config config = new Config();
        Call<ProfileResponse> call = config.configRetrofit().profile(token);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse profileResponse = response.body();
                textNama.setText(profileResponse.getName());
                textUname.setText(profileResponse.getName());
                textEmail.setText(profileResponse.getEmail());
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });

        return view;
    }

    public void logout() {
        Config config = new Config();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("com.example.kadep.SHARED_KEY", Context.MODE_PRIVATE);
        String gettoken = sharedPreferences.getString("token", "");
        String token = "Bearer " + gettoken;

        Call<LogoutResponse> call = config.configRetrofit().logout(token);
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {

                if (response.code() == 200){
                    if (response.isSuccessful()){
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        sharedPreferences.edit().clear().apply();
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}