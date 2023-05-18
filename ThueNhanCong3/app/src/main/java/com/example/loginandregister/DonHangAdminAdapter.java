package com.example.loginandregister;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class DonHangAdminAdapter extends FirebaseRecyclerAdapter<LichDat,DonHangAdminAdapter.myViewHolder> {
    public DonHangAdminAdapter(@NonNull FirebaseRecyclerOptions<LichDat> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull LichDat model) {
        holder.tendonhang.setText(model.getDichvu());
        holder.ngaydat.setText(model.getNgaydat());
        holder.sodienthoai.setText(model.getSodienthoai());
        holder.noidung.setText(model.getNoidung());
        holder.diachi.setText(model.getDiachi());
        holder.emailnv.setText(model.getEmailnv());
        holder.hoanthanh.setText(model.getHoanthanh());
        holder.xacnhan.setText(model.getXacnhan());
        holder.email.setText(model.getEmail());
        holder.phanhoikh.setText(model.getPhanhoikh());

        holder.chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.tendonhang.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_chitietdonhang))
                        .setExpanded(true,2000)
                        .create();
                View myView=dialogPlus.getHolderView();
                TextView dichvu=myView.findViewById(R.id.dichvu);
                TextView diachi=myView.findViewById(R.id.diachi);
                TextView ngaydat=myView.findViewById(R.id.ngaydat);
                TextView noidung=myView.findViewById(R.id.noidung);
                TextView emailkh=myView.findViewById(R.id.email);
                TextView ttemailnv=myView.findViewById(R.id.txtemailnv);
                TextView phanhoi=myView.findViewById(R.id.phanhoikhachang);
                TextView sdt=myView.findViewById(R.id.sdt);
                TextView trangthai=myView.findViewById(R.id.trangthai);
                TextView xacnhan=myView.findViewById(R.id.xacnhan);
                CheckBox chuaxn=myView.findViewById(R.id.chuaxn);
                CheckBox daxn=myView.findViewById(R.id.daxn);
                CheckBox chuaht=myView.findViewById(R.id.chuaht);
                CheckBox daht=myView.findViewById(R.id.daht);
                Spinner emailnv=myView.findViewById(R.id.emailnv);
                Button capnhat=myView.findViewById(R.id.btncapnhatthongtinkhachhang);
                Button xoadon=myView.findViewById(R.id.btnxoadonhang);


                chuaxn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        daxn.setChecked(false);
                        chuaxn.setChecked(b);
                    }
                });
                daxn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        chuaxn.setChecked(false);
                        daxn.setChecked(b);
                    }
                });
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


                dichvu.setText(model.getDichvu());
                diachi.setText(model.getDiachi());
                ngaydat.setText(model.getNgaydat());
                noidung.setText(model.getNoidung());
                emailkh.setText(model.getEmail());
                ttemailnv.setText(model.getEmailnv());
                sdt.setText(model.getSodienthoai());
                phanhoi.setText(model.getPhanhoikh());
                trangthai.setText(model.getHoanthanh());
                xacnhan.setText(model.getXacnhan());

                daht.setChecked(false);
                chuaht.setChecked(false);
                chuaxn.setChecked(false);
                chuaxn.setChecked(false);







                capnhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object>map= new HashMap<>();
                        if(ttemailnv.getText().equals("Chờ nhân viên")){
                            map.put("emailnv",emailnv.getSelectedItem().toString());
                        }
                    
                        if(chuaxn.isChecked()){
                            map.put("xacnhan", chuaxn.getText().toString());

                        }else if(daxn.isChecked()){
                            map.put("xacnhan", daxn.getText().toString());
                        }


                        if (chuaht.isChecked()){
                            map.put("hoanthanh", chuaht.getText().toString());
                        }else if(daht.isChecked()) {
                            map.put("hoanthanh", daht.getText().toString());
                        }


                        FirebaseDatabase.getInstance().getReference().child("Thongtinlichdat")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(emailkh.getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();


                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
                xoadon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder= new AlertDialog.Builder(holder.tendonhang.getContext());
                        builder.setTitle("Xóa Đơn Hàng ");
                        builder.setMessage("Bạn có muốn xóa...?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                FirebaseDatabase.getInstance().getReference().child("Thongtinlichdat")
                                        .child(getRef(position).getKey()).removeValue();
                                Toast.makeText(emailkh.getContext(), "Xóa đơn thành công", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();
                    }
                });




                dialogPlus.show();
            }
        });



    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlichdatadmin,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        private TextView tendonhang,ngaydat,sodienthoai,noidung,diachi,emailnv,hoanthanh,xacnhan,email,phanhoikh;
        Button chitiet;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tendonhang=(TextView)itemView.findViewById(R.id.txttendonhang);
            ngaydat=(TextView)itemView.findViewById(R.id.txtngaydat);
            sodienthoai=(TextView)itemView.findViewById(R.id.txtsdtdat);
            noidung=(TextView)itemView.findViewById(R.id.txtnoidung);
            diachi=(TextView)itemView.findViewById(R.id.txtdiachi);
            emailnv=(TextView)itemView.findViewById(R.id.txtemailnv);
            hoanthanh=(TextView)itemView.findViewById(R.id.txthoanthanh);
            xacnhan=(TextView)itemView.findViewById(R.id.txtxacnhan);
            email=(TextView)itemView.findViewById(R.id.txtemail);
            chitiet=itemView.findViewById(R.id.chitiet);
            phanhoikh=(TextView) itemView.findViewById(R.id.txtphanhoi);
        }
    }
}
