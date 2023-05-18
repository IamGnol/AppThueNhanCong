package com.example.loginandregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.List;

public class LichDatNhanVienAdapter extends RecyclerView.Adapter<LichDatNhanVienAdapter.LichDatViewHolder> {
    private List<LichDat> mListLichDat;

    public LichDatNhanVienAdapter(List<LichDat> mListLichDat) {
        this.mListLichDat = mListLichDat;
    }

    @NonNull
    @Override
    public LichDatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlichdatadmin, parent, false);
        return new LichDatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichDatViewHolder holder, int position) {
        LichDat lichdat = mListLichDat.get(position);
        if (lichdat == null) {
            return;
        }
        holder.tendichvu.setText(lichdat.getDichvu());
        holder.hoanthanh.setText(lichdat.getHoanthanh());
        holder.xacnhan.setText(lichdat.getXacnhan());
        holder.ngaydat.setText(lichdat.getNgaydat());
        holder.sodienthoai.setText(lichdat.getSodienthoai());
        holder.noidung.setText(lichdat.getNoidung());
        holder.diachi.setText(lichdat.getDiachi());
        holder.emailnv.setText(lichdat.getEmailnv());
        holder.email.setText(lichdat.getEmail());
//
        holder.chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.email.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_chi_tiet_don_hang_nhan_vien))
                        .setExpanded(true, 1900)
                        .create();

                View view1= dialogPlus.getHolderView();
                TextView dv=view1.findViewById(R.id.dichvu);
                TextView diachi=view1.findViewById(R.id.diachi);
                TextView ngay=view1.findViewById(R.id.ngaydat);
                TextView nd=view1.findViewById(R.id.noidung);
                TextView email=view1.findViewById(R.id.email);
                TextView emailnv= view1.findViewById(R.id.emailnv);
                TextView dt=view1.findViewById(R.id.sdt);
                EditText trangthai=view1.findViewById(R.id.trangthai);

                Button capnhat=view1.findViewById(R.id.btncapnhatthongtinkhachhang);

                CheckBox daht=view1.findViewById(R.id.daht);
                CheckBox chuaht=view1.findViewById(R.id.chuaht);

                chuaht.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        daht.setChecked(false);
                        chuaht.setChecked(b);
                    }
                });
                daht.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        chuaht.setChecked(false);
                        daht.setChecked(b);
                    }
                });



                dv.setText(lichdat.getDichvu());
                diachi.setText(lichdat.getDiachi());
                ngay.setText(lichdat.getNgaydat());
                nd.setText(lichdat.getNoidung());
                email.setText(lichdat.getEmail());
                emailnv.setText(lichdat.getEmailnv());
                trangthai.setText(lichdat.getHoanthanh());
                dt.setText(lichdat.getSodienthoai());


                dialogPlus.show();

//                capnhat.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        DatabaseReference mDatabase;
//                        mDatabase = FirebaseDatabase.getInstance().getReference();
//                        String key = mDatabase.child("Thongtinlichdat").push().getKey();
//                        LichDat post = new LichDat();
//
//
//                        Map<String, Object> childUpdates = new HashMap<>();
//                        childUpdates.put("/posts/" + key, postValues);
//                        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);
//
//
//
//                    }
//
//                });


            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListLichDat != null) {
            return mListLichDat.size();
        }
        return 0;
    }

    public class LichDatViewHolder extends RecyclerView.ViewHolder {
        private TextView tendichvu;
        private TextView hoanthanh;
        private TextView xacnhan;
        private TextView ngaydat;
        private TextView sodienthoai;
        private TextView noidung;
        private TextView diachi;
        private TextView emailnv;
        private TextView email;
        private Button chitiet;


        public LichDatViewHolder(@NonNull View itemView) {
            super(itemView);
            tendichvu = itemView.findViewById(R.id.txttendonhang);
            hoanthanh = itemView.findViewById(R.id.txthoanthanh);
            xacnhan = itemView.findViewById(R.id.txtxacnhan);
            ngaydat = itemView.findViewById(R.id.txtngaydat);
            sodienthoai = itemView.findViewById(R.id.txtsdtdat);
            noidung = itemView.findViewById(R.id.txtnoidung);
            diachi = itemView.findViewById(R.id.txtdiachi);
            emailnv = itemView.findViewById(R.id.txtemailnv);
            email = itemView.findViewById(R.id.txtemail);
            chitiet=itemView.findViewById(R.id.chitiet);

        }
    }
}