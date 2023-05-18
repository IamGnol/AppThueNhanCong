package com.example.loginandregister;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_ConfigDichvu {
    private Context mcontext;
    private DichvuAdapter dichvuAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<DichVu>dichVus,List<String>keys){
        mcontext=context;
        dichvuAdapter= new DichvuAdapter(dichVus,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(dichvuAdapter);

    }

    class DichvuItemView extends RecyclerView.ViewHolder {
        private TextView tendv,giadv,motadv;
        private String key;
        public DichvuItemView(ViewGroup parent){
            super(LayoutInflater.from(mcontext).
                    inflate(R.layout.dvitem,parent,false));



            tendv=(TextView)itemView.findViewById(R.id.tendv);
            giadv=(TextView)itemView.findViewById(R.id.gia);
            motadv=(TextView)itemView.findViewById(R.id.mota);


            // tenkhachhang=(TextView)itemView.findViewById(R.id.txttenkhachhang);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(mcontext,ChiTietDichVu.class);

                    intent.putExtra("key",key);
                    intent.putExtra("ten",tendv.getText().toString());
                    intent.putExtra("mota",motadv.getText().toString());
                    mcontext.startActivity(intent);
                }
            });



        }
        public void bind(DichVu dichVu,String key){
            tendv.setText(dichVu.getTen());
            giadv.setText(dichVu.getGia());
            motadv.setText(dichVu.getMota());

            //      tenkhachhang.setText(lichDat.getTenkhachhang());
            this.key=key;
        }



    }
    class  DichvuAdapter extends  RecyclerView.Adapter<DichvuItemView>{
        private List<DichVu> dichVus;
        private List<String> mkeys;

        public DichvuAdapter(List<DichVu> dichVus, List<String> mkeys) {
            this.dichVus = dichVus;
            this.mkeys = mkeys;
        }

        @NonNull
        @Override
        public DichvuItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DichvuItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DichvuItemView holder, int position) {
            holder.bind(dichVus.get(position),mkeys.get(position));

        }

        @Override
        public int getItemCount() {
            return dichVus.size();
        }
    }
}
