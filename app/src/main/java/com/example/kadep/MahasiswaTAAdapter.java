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

//extends recyclerview adapter dan disediakan holder
public class MahasiswaTAAdapter extends RecyclerView.Adapter<MahasiswaTAAAdapter.PermintaanMahasiswaTAViewHolder>{

    private List<MahasiswaTAItem> itemMahasiswaTA = new ArrayList<>();

    public void setItemMahasiswaTA(List<MahasiswaTAItem> itemMahasiswaTA) {
        this.itemMahasiswaTA = itemMahasiswaTA;
        notifyDataSetChanged();
    }

//    ArrayList<PermintaanMahasiswaTA> listPermintaanMahasiswaTA =  new ArrayList<>();
    ItemPermintaanMahasiswaTAClickListener listener;

//    public SeminarAdapter(ArrayList<PermintaanMahasiswaTA> detailMahasiswaTA) {
//        this.listPermintaanMahasiswaTA = detailMahasiswaTA;
//    }

    public void setListener(ItemPermintaanMahasiswaTAClickListener listener) {
        this.listener = listener;
    }

//    public void setListPermintaanMahasiswaTA(ArrayList<PermintaanMahasiswaTA> listPermintaanMahasiswaTA) {
//        this.listPermintaanMahasiswaTA = listPermintaanMahasiswaTA;
//    }

    @NonNull
    @Override
    public PermintaanMahasiswaTAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_MahasiswaTA, parent, false);
        return new MahasiswaTAAdapter.PermintaanMahasiswaTAViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return itemMahasiswaTA.size();
    }

    //membuat inner interface pada adapter sehingga dapat di klik
    public interface ItemPermintaanMahasiswaTAClickListener {
        void onItemPermintaanClick(MahasiswaTAItem permintaanMahasiswaTA);
    }

    @Override
    //memasukkan data kedalam view holder
        public void onBindViewHolder(@NonNull PermintaanMahasiswaTAViewHolder holder, int position) {
        //membuat objek dan mengambil data dari list
        MahasiswaTAItem permintaanMahasiswaTA = itemMahasiswaTA.get(position);
        holder.imageMhs.setImageResource((R.drawable.logo_unand));
        String str1= permintaanMahasiswaTA.getThesis().getStudent().getName();
        String str2 = str1.toLowerCase();
        //melakukan binding data dengan view holder
        holder.namaMhs.setText(StringFormatter.capitalizeWord(str2));
        holder.nimMhs.setText(permintaanMahasiswaTA.getThesis().getStudent().getNim());
    }
    //class permintaan mahasiswa TA view holder harus merupakan turunan dari recycler view view holder
    //view holder merepresentaikan layout list
    //mengimplementasikan interface onClickListener sehingga dapat di klik
    public class PermintaanMahasiswaTAViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //properti class pemintaanmahasiswaTA view holder
        public ImageView imageMhs;
        public TextView namaMhs, nimMhs;

        //pada constructor berikut akan diambil objek yang akan di binding
        public PermintaanMahasiswaTAViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMhs = itemView.findViewById(R.id.image_mhs1);
            namaMhs = itemView.findViewById(R.id.nama_mhs1);
            nimMhs = itemView.findViewById(R.id.nim_mhs1);

            //menset item yang menhandle klik
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            MahasiswaTAItem permintaanMahasiswaTA = itemMahasiswaTA.get(getAdapterPosition());
            listener.onItemPermintaanClick(permintaanMahasiswaTA);
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
