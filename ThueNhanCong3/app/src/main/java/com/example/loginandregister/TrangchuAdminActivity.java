package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

public class TrangchuAdminActivity extends AppCompatActivity {
    RelativeLayout ttkhachhang,ttnhanvien,tthoadon;
    ImageView donhang,nguoidung;
    Button btnDangxuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu_admin);
        donhang= findViewById(R.id.imgdonhang);
        nguoidung=findViewById(R.id.nguoidung);
        ttkhachhang=findViewById(R.id.ttkhanghang);
        ttnhanvien=findViewById(R.id.ttnhanvien);
        tthoadon=findViewById(R.id.tthoadon);
        btnDangxuat=findViewById(R.id.dangxuat);


        tthoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangchuAdminActivity.this, AdminDanhSachHoaDon.class));
                finish();
            }
        });

        ttkhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangchuAdminActivity.this, AdminDanhSachNguoiDung.class));
                finish();
            }
        });
        ttnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangchuAdminActivity.this, AdminDanhSachNhanVien.class));
                finish();
            }
        });

        donhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangchuAdminActivity.this, AdminDonHangActivity.class));
                finish();
            }
        });
        nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangchuAdminActivity.this, DangKiNhanVien.class));
                finish();

            }
        });
        btnDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(TrangchuAdminActivity.this,Login.class);
                startActivity(intent);
                finish();

            }
        });



    }
}