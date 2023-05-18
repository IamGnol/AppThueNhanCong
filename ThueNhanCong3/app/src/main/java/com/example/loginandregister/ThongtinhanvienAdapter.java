package com.example.loginandregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThongtinhanvienAdapter extends RecyclerView.Adapter<ThongtinhanvienAdapter.MyViewHolder> {

    Context context;
    ArrayList<NhanVien> nhanViens;

    public ThongtinhanvienAdapter(Context context, ArrayList<NhanVien> nhanViens) {
        this.context = context;
        this.nhanViens = nhanViens;
    }

    @NonNull
    @Override
    public ThongtinhanvienAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemdanhsachnhanvien,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongtinhanvienAdapter.MyViewHolder holder, int position) {

        NhanVien nhanVien= nhanViens.get(position);
        holder.tennv.setText(nhanVien.getTen());
        holder.emailnv.setText(nhanVien.getEmail());

    }

    @Override
    public int getItemCount() {
        return nhanViens.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tennv, emailnv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tennv=itemView.findViewById(R.id.txttenkh);
            emailnv=itemView.findViewById(R.id.txtemailkh);
        }
    }
}
