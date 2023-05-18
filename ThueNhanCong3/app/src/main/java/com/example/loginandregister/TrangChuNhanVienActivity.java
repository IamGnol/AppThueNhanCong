package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

public class TrangChuNhanVienActivity extends AppCompatActivity {
    ImageView thongtin,lich;
    RelativeLayout dshoadon;
    Button dangxuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_nhan_vien);
        thongtin=findViewById(R.id.imgthongtin);
        lich=findViewById(R.id.imglich);
        dshoadon=findViewById(R.id.hoadon);
        dangxuat=findViewById(R.id.dangxuat);

        dshoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuNhanVienActivity.this, DanhSachHoaDonNhanVien.class));
                finish();
            }
        });

        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(TrangChuNhanVienActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
        thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuNhanVienActivity.this, ThongTinNhanVienActivity.class));
                finish();

            }
        });
        lich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuNhanVienActivity.this, DanhSachLichDatNhanVien.class));
                finish();
            }
        });
    }
}