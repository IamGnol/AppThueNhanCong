package com.example.loginandregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterBangGiaDienTu extends FirebaseRecyclerAdapter<DichVu, AdapterBangGiaDienTu.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterBangGiaDienTu(@NonNull FirebaseRecyclerOptions<DichVu> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull DichVu model) {
        holder.ten.setText(model.getTen());
        holder.gia.setText(model.getGia());
        Glide.with(holder.anh.getContext())
                .load(model.getAnh())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.anh);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dvitem,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView ten,gia;
        ImageView anh;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            ten=itemView.findViewById(R.id.tendv);
            gia=itemView.findViewById(R.id.gia);
            anh=itemView.findViewById(R.id.img);

        }
    }
}
