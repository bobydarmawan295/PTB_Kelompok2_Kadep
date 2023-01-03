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
import com.example.kadep.models.SeminarsItem;

import java.util.ArrayList;
import java.util.List;

public class SeminarAdapter extends RecyclerView.Adapter<SeminarAdapter.PermintaanSeminarViewHolder>{

    private List<SeminarsItem> itemSeminar = new ArrayList<>();

    public void setItemSeminar(List<SeminarsItem> itemSeminar) {
        this.itemSeminar = itemSeminar;
        notifyDataSetChanged();
    }

//    ArrayList<PermintaanSeminar> listPermintaanSeminar =  new ArrayList<>();
    ItemPermintaanSeminarClickListener listener;

//    public SeminarAdapter(ArrayList<PermintaanSeminar> detailSeminar) {
//        this.listPermintaanSeminar = detailSeminar;
//    }

    public void setListener(ItemPermintaanSeminarClickListener listener) {
        this.listener = listener;
    }

//    public void setListPermintaanSeminar(ArrayList<PermintaanSeminar> listPermintaanSeminar) {
//        this.listPermintaanSeminar = listPermintaanSeminar;
//    }

    @NonNull
    @Override
    public PermintaanSeminarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_seminar, parent, false);
        return new SeminarAdapter.PermintaanSeminarViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return itemSeminar.size();
    }

    public interface ItemPermintaanSeminarClickListener {
        void onItemPermintaanClick(SeminarsItem permintaanSeminar);
    }

    @Override
    public void onBindViewHolder(@NonNull PermintaanSeminarViewHolder holder, int position) {
        SeminarsItem permintaanSeminar = itemSeminar.get(position);
        holder.imageMhs.setImageResource((R.drawable.logo_unand));
        String str1= permintaanSeminar.getThesis().getStudent().getName();
        String str2 = str1.toLowerCase();
        holder.namaMhs.setText(StringFormatter.capitalizeWord(str2));
        holder.nimMhs.setText(permintaanSeminar.getThesis().getStudent().getNim());
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
            SeminarsItem permintaanSeminar = itemSeminar.get(getAdapterPosition());
            listener.onItemPermintaanClick(permintaanSeminar);
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
