package com.example.loginandregister;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RecyclerView_ConfigNguoiDung {
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
        private TextView hoanthanh;
        private TextView xacnhan;
        private TextView ngaydat;
        private  TextView sodienthoai;
        private TextView noidung;
        private TextView diachi;
        private TextView emailnv;
        private TextView emailnd;
        private EditText phanhoikh;

        private String key;

        public LichDatItemView(ViewGroup parent) {
            super(LayoutInflater.from(mcontext).
                    inflate(R.layout.itemlichdatnguoidung, parent, false));
                    tendonhang = itemView.findViewById(R.id.txttendonhang);
                    hoanthanh = itemView.findViewById(R.id.txthoanthanh);
                    xacnhan = itemView.findViewById(R.id.txtxacnhan);
                    ngaydat=itemView.findViewById(R.id.txtngaydat);
                    sodienthoai=itemView.findViewById(R.id.txtsdtdat);
                    noidung=itemView.findViewById(R.id.txtnoidung);
                    diachi=itemView.findViewById(R.id.txtdiachi);
                    emailnv=itemView.findViewById(R.id.txtemail);
                    emailnd=itemView.findViewById(R.id.txtemailnv);
                    phanhoikh=itemView.findViewById(R.id.txtphanhoikh);

            // tenkhachhang=(TextView)itemView.findViewById(R.id.txttenkhachhang);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext, ChiTietDonHangNguoiDung.class);
                    intent.putExtra("key", key);
                    intent.putExtra("dichvu", tendonhang.getText().toString());
                    intent.putExtra("diachi", diachi.getText().toString());
                    intent.putExtra("ngaydat", ngaydat.getText().toString());
                    intent.putExtra("noidung", noidung.getText().toString());
                    intent.putExtra("sodienthoai", sodienthoai.getText().toString());
                    intent.putExtra("emailnv", emailnv.getText().toString());
                    intent.putExtra("hoanthanh", hoanthanh.getText().toString());
                    intent.putExtra("xacnhan", xacnhan.getText().toString());
                    intent.putExtra("email", emailnd.getText().toString());
                    intent.putExtra("phanhoikh",phanhoikh.getText().toString());
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
            emailnd.setText(lichDat.getEmail());
            phanhoikh.setText(lichDat.getPhanhoikh());
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
