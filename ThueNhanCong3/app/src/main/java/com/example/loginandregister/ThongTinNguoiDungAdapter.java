package com.example.loginandregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThongTinNguoiDungAdapter extends RecyclerView.Adapter<ThongTinNguoiDungAdapter.MyViewHolder> {

    Context context;
    ArrayList<NguoiDung> nguoiDungs;

    public ThongTinNguoiDungAdapter(Context context, ArrayList<NguoiDung> nguoiDungs) {
        this.context = context;
        this.nguoiDungs = nguoiDungs;
    }

    @NonNull
    @Override
    public ThongTinNguoiDungAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemthongtinnguoidung,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongTinNguoiDungAdapter.MyViewHolder holder, int position) {

        NguoiDung nguoiDung= nguoiDungs.get(position);
        holder.tenkh.setText(nguoiDung.getTen());
        holder.emailkh.setText(nguoiDung.getEmail());

    }

    @Override
    public int getItemCount() {
        return nguoiDungs.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tenkh, emailkh;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tenkh=itemView.findViewById(R.id.txttenkh);
            emailkh=itemView.findViewById(R.id.txtemailkh);
        }
    }
}
