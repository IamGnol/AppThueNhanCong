package com.example.loginandregister;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RecyclerView_CongfigNhanVien {
    private Context mcontext;
    private LichDatAdapter mlichDatAdapter;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;


    public void setConfig(RecyclerView recyclerView, Context context, List<LichDat> lichDats, List<String>keys){
        mcontext=context;
        mlichDatAdapter= new LichDatAdapter(lichDats,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mlichDatAdapter);

    }
    class LichDatItemView extends RecyclerView.ViewHolder {
        private TextView tendonhang;
        private TextView ngaydat;
        private TextView sodienthoai;
        private TextView noidung;
        private TextView diachi;
        private TextView emailnv;
        private TextView hoanthanh;
        private TextView xacnhan;
        private TextView email;
        private Button hoadon;


        private String key;
        public LichDatItemView(ViewGroup parent){
            super(LayoutInflater.from(mcontext).
                    inflate(R.layout.itemlichdatnhanvien,parent,false));
                    tendonhang=(TextView)itemView.findViewById(R.id.txttendonhang);
                    ngaydat=(TextView)itemView.findViewById(R.id.txtngaydat);
                    sodienthoai=(TextView)itemView.findViewById(R.id.txtsdtdat);
                    noidung=(TextView)itemView.findViewById(R.id.txtnoidung);
                    diachi=(TextView)itemView.findViewById(R.id.txtdiachi);
                    emailnv=(TextView)itemView.findViewById(R.id.txtemailnv);
                    hoanthanh=(TextView)itemView.findViewById(R.id.txthoanthanh);
                    xacnhan=(TextView)itemView.findViewById(R.id.txtxacnhan);
                    email=itemView.findViewById(R.id.txtemail);
                 hoadon=itemView.findViewById(R.id.hoadon);

            // tenkhachhang=(TextView)itemView.findViewById(R.id.txttenkhachhang);
            hoadon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(mcontext,ChiTietHoaDon.class);
                    intent.putExtra("key",key);
                    intent.putExtra("dichvu",tendonhang.getText().toString());
                    intent.putExtra("diachi",diachi.getText().toString());
                    intent.putExtra("ngaydat",ngaydat.getText().toString());
                    intent.putExtra("noidung",noidung.getText().toString());
                    intent.putExtra("sodienthoai",sodienthoai.getText().toString());
                    intent.putExtra("emailnv",emailnv.getText().toString());
                    intent.putExtra("email",email.getText().toString());

                    mcontext.startActivity(intent);
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(mcontext,ChiTietDonHangNhanVien.class);
                    intent.putExtra("key",key);
                    intent.putExtra("dichvu",tendonhang.getText().toString());
                    intent.putExtra("diachi",diachi.getText().toString());
                    intent.putExtra("ngaydat",ngaydat.getText().toString());
                    intent.putExtra("noidung",noidung.getText().toString());
                    intent.putExtra("sodienthoai",sodienthoai.getText().toString());
                    intent.putExtra("emailnv",emailnv.getText().toString());
                    intent.putExtra("hoanthanh",hoanthanh.getText().toString());
                    intent.putExtra("xacnhan",xacnhan.getText().toString());


                    intent.putExtra("email",email.getText().toString());
//                    intent.putExtra("tenkhachhang",tenkhachhang.getText().toString());
                    mcontext.startActivity(intent);
                }
            });
        }
        public void bind(LichDat lichDat,String key){

            tendonhang.setText(lichDat.getDichvu());
            diachi.setText(lichDat.getDiachi());
            ngaydat.setText(lichDat.getNgaydat());
            noidung.setText(lichDat.getNoidung());
            sodienthoai.setText(lichDat.getSodienthoai());
            emailnv.setText(lichDat.getEmailnv());
            hoanthanh.setText(lichDat.getHoanthanh());
            xacnhan.setText(lichDat.getXacnhan());
            email.setText(lichDat.getEmail());
            //      tenkhachhang.setText(lichDat.getTenkhachhang());
            this.key=key;
        }


    }
    class  LichDatAdapter extends  RecyclerView.Adapter<LichDatItemView>{
        private List<LichDat> mlichDats;
        private List<String> mkeys;

        public LichDatAdapter(List<LichDat> mlichDats, List<String> mkeys) {
            this.mlichDats = mlichDats;
            this.mkeys = mkeys;
        }

        @NonNull
        @Override
        public LichDatItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LichDatItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull LichDatItemView holder, int position) {
            holder.bind(mlichDats.get(position),mkeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mlichDats.size();
        }
    }
}