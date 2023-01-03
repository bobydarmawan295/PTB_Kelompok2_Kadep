package com.example.kadep.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kadep.R;
import com.example.kadep.models.SeminarsItem;

import java.util.ArrayList;
import java.util.List;


public class SidangAdapter extends RecyclerView.Adapter<SidangAdapter.PermintaanSidangViewHolder> {

    private List<SeminarsItem> itemThesis = new ArrayList<>();
    ItemPermintaanSidangClickListener listener;

    public void setItemThesis(List<SeminarsItem> itemThesis) {
        this.itemThesis = itemThesis;
        notifyDataSetChanged();
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
        return itemThesis.size();
    }

    public interface ItemPermintaanSidangClickListener {
        void onItemPermintaanClick(SeminarsItem permintaanSidang);
    }


    @Override
    public void onBindViewHolder(@NonNull PermintaanSidangViewHolder holder, int position) {
        SeminarsItem permintaanSidang = itemThesis.get(position);
        holder.imageMhs.setImageResource((R.drawable.logo_unand));
        String str1= permintaanSidang.getThesis().getStudent().getName();
        String str2 = str1.toLowerCase();
        holder.namaMhs.setText(SeminarAdapter.StringFormatter.capitalizeWord(str2));
        holder.nimMhs.setText(permintaanSidang.getThesis().getStudent().getNim());
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
            SeminarsItem permintaanSidang = itemThesis.get(getAdapterPosition());
            listener.onItemPermintaanClick(permintaanSidang);
        }
    }

    public static class StringFormatter {
        public static String capitalizeWord(String str){
            String words[]=str.split("\\s");
            String capitalizeWord="";
            for(String w:words){
                String first=w.substring(0,1);
                String afterfirst=w.substring(1);
                capitalizeWord+=first.toUpperCase()+afterfirst+" ";
            }
            return capitalizeWord.trim();
        }
    }

}


