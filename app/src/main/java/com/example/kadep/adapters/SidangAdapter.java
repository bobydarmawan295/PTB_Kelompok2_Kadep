package com.example.kadep.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kadep.R;
import com.example.kadep.models.PermintaanSidang;

import java.util.ArrayList;


public class SidangAdapter extends RecyclerView.Adapter<SidangAdapter.PermintaanSidangViewHolder> {

    ArrayList<PermintaanSidang> listPermintaanSidang =  new ArrayList<>();
    ItemPermintaanSidangClickListener listener;

    public SidangAdapter(ArrayList<PermintaanSidang> detailSidang) {
        this.listPermintaanSidang = detailSidang;
    }

    public void setListPermintaanSidang(ArrayList<PermintaanSidang> detailSidang) {
        this.listPermintaanSidang = detailSidang;
    }

    public SidangAdapter(ArrayList<PermintaanSidang> listPermintaanSidang, ItemPermintaanSidangClickListener listener) {
        this.listPermintaanSidang = listPermintaanSidang;
        this.listener = listener;
    }

    public void setListener(ItemPermintaanSidangClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PermintaanSidangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sidang, parent, false);
        return new PermintaanSidangViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return listPermintaanSidang.size();
    }

    public interface ItemPermintaanSidangClickListener {
        void onItemPermintaanClick(PermintaanSidang permintaanSidang);
    }

    @Override
    public void onBindViewHolder(@NonNull PermintaanSidangViewHolder holder, int position) {
        PermintaanSidang permintaanSidang = listPermintaanSidang.get(position);
        holder.imageMhs.setImageResource((R.drawable.logo_unand));
        holder.namaMhs.setText(permintaanSidang.getNama_mhs());
        holder.nimMhs.setText(permintaanSidang.getNim_mhs());
    }

    public class PermintaanSidangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageMhs;
        public TextView namaMhs, nimMhs;

        public PermintaanSidangViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMhs = itemView.findViewById(R.id.image_mhs);
            namaMhs = itemView.findViewById(R.id.nama_mhs);
            nimMhs = itemView.findViewById(R.id.nim_mhs);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            PermintaanSidang permintaanSidang = listPermintaanSidang.get(getAdapterPosition());
            listener.onItemPermintaanClick(permintaanSidang);
        }
    }

}


