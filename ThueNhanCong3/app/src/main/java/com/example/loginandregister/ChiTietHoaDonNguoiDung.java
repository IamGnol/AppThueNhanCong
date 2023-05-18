package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChiTietHoaDonNguoiDung extends AppCompatActivity {
    ImageView trove;
    TextView txtdichvu,txtdiachi,txtngaydat,txtnoidung,txtnhanvien,txtkhachhang,txtsdt,txtghichu,txttongtien,txtngaytaodon;
    DatabaseReference reference;
    private String key;
    private String diachi;
    private String dichvu;
    private String ngaydat;
    private String noidung;
    private String sodienthoai;
    private String email;
    private String emailnv;
    private String tongtien;
    private String ghichu;
    private String ngaytaodon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don_nguoi_dung);
        reference= FirebaseDatabase.getInstance().getReference().child("HoaDon");
        key=getIntent().getStringExtra("key");
        diachi=getIntent().getStringExtra("diachi");
        dichvu=getIntent().getStringExtra("dichvu");
        ngaydat=getIntent().getStringExtra("ngaydat");
        noidung=getIntent().getStringExtra("noidung");
        sodienthoai=getIntent().getStringExtra("sodienthoai");
        email = getIntent().getStringExtra("email");
        emailnv = getIntent().getStringExtra("emailnv");
        ghichu = getIntent().getStringExtra("ghichu");
        tongtien = getIntent().getStringExtra("tongtien");
        ngaytaodon=getIntent().getStringExtra("ngaylap");


        txtdichvu=findViewById(R.id.dichvu);
        txtdichvu.setText(dichvu);
        txtdiachi=findViewById(R.id.diachi);
        txtdiachi.setText(diachi);
        txtngaydat=findViewById(R.id.ngaydat);
        txtngaydat.setText(ngaydat);
        txtnoidung=findViewById(R.id.noidung);
        txtnoidung.setText(noidung);
        txtnhanvien=findViewById(R.id.emailnv);
        txtnhanvien.setText(emailnv);
        txtkhachhang=findViewById(R.id.email);
        txtkhachhang.setText(email);
        txtsdt=findViewById(R.id.sdt);
        txtsdt.setText(sodienthoai);
        txtghichu=findViewById(R.id.ghichu);
        txtghichu.setText(ghichu);
        txttongtien=findViewById(R.id.tongtien);
        txttongtien.setText(tongtien);
        txtngaytaodon=findViewById(R.id.txtngaytaodon);
        txtngaytaodon.setText(ngaytaodon);
        trove= findViewById(R.id.id_trove);

        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietHoaDonNguoiDung.this, DanhSachHoaDonCuaKhachHang.class));
                finish();
            }
        });
    }
}