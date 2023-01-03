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
import android.widget.Toast;

import com.example.kadep.models.LogoutResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    Button jadwalButton, mintaSidangButton, jadwalSeminar, mintaSeminar, mahasiswaTA;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);

        ImageView logout_icon = view.findViewById(R.id.logout2);
        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        jadwalButton = view.findViewById(R.id.jadwalSidang);
        jadwalButton.setOnClickListener(views -> {
            Intent jadwalSidang = new Intent(getActivity().getApplicationContext(), JadwalSidang.class);
            startActivity(jadwalSidang);
        });

        mintaSidangButton = view.findViewById(R.id.mintaSidang);
        mintaSidangButton.setOnClickListener(views -> {
            Intent mintaSidang = new Intent(getActivity().getApplicationContext(), PermintaanSidang.class);
            startActivity(mintaSidang);
        });

        jadwalSeminar = view.findViewById(R.id.button4);
        jadwalSeminar.setOnClickListener(views -> {
            Intent mintaSeminar = new Intent(getActivity().getApplicationContext(), JadwalSeminar.class);
            startActivity(mintaSeminar);
        });

        mintaSeminar = view.findViewById(R.id.button5);
        mintaSeminar.setOnClickListener(views -> {
            Intent mintaSeminar = new Intent(getActivity().getApplicationContext(), PermintaanSeminar.class);
            startActivity(mintaSeminar);
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

