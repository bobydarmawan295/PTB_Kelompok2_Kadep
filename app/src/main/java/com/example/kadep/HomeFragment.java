package com.example.kadep;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    Button jadwalButton, mintaSidangButton, jadwalSeminar, mintaSeminar, mahasiswaTA;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);

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
}