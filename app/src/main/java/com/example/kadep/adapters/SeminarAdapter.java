package com.example.kadep.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kadep.R;
import com.example.kadep.models.PermintaanSeminar;
import com.example.kadep.models.PermintaanSidang;

import java.util.ArrayList;

public class SeminarAdapter extends RecyclerView.Adapter<SeminarAdapter.PermintaanSeminarViewHolder>{

    ArrayList<PermintaanSeminar> listPermintaanSeminar =  new ArrayList<>();
    ItemPermintaanSeminarClickListener listener;

    public SeminarAdapter(ArrayList<PermintaanSeminar> detailSeminar) {
        this.listPermintaanSeminar = detailSeminar;
    }

    public void setListener(ItemPermintaanSeminarClickListener listener) {
        this.listener = listener;
    }

    public void setListPermintaanSeminar(ArrayList<PermintaanSeminar> listPermintaanSeminar) {
        this.listPermintaanSeminar = listPermintaanSeminar;
    }

    @NonNull
    @Override
    public PermintaanSeminarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_seminar, parent, false);
        return new SeminarAdapter.PermintaanSeminarViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listPermintaanSeminar.size();
    }

    public interface ItemPermintaanSeminarClickListener {
        void onItemPermintaanClick(PermintaanSeminar permintaanSeminar);
    }

    @Override
    public void onBindViewHolder(@NonNull PermintaanSeminarViewHolder holder, int position) {
        PermintaanSeminar permintaanSeminar = listPermintaanSeminar.get(position);
        holder.imageMhs.setImageResource((R.drawable.logo_unand));
        holder.namaMhs.setText(permintaanSeminar.getNama_mhs());
        holder.nimMhs.setText(permintaanSeminar.getNim_mhs());
    }

    public class PermintaanSeminarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imageMhs;
        public TextView namaMhs, nimMhs;


        public PermintaanSeminarViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMhs = itemView.findViewById(R.id.image_mhs1);
            namaMhs = itemView.findViewById(R.id.nama_mhs1);
            nimMhs = itemView.findViewById(R.id.nim_mhs1);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            PermintaanSeminar permintaanSeminar = listPermintaanSeminar.get(getAdapterPosition());
            listener.onItemPermintaanClick(permintaanSeminar);
        }
    }

}
