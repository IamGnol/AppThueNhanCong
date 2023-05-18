package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ChiTietDonHangNhanVien extends AppCompatActivity {
    TextView txtdichvu,txtdiachi,txtnoidung,txtngaydat,txtsdt, txtemail, txtxacnhan, txthoanthanh;
    ImageView trove;
    private String key;
    private String diachi;
    private String dichvu;
    private String ngaydat;
    private String noidung;
    private String sodienthoai;
    private String email;
    private String emailnv;
    private String trangthai;
    private String xacnhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang_nhan_vien);

        key=getIntent().getStringExtra("key");
        diachi=getIntent().getStringExtra("diachi");
        dichvu=getIntent().getStringExtra("dichvu");
        ngaydat=getIntent().getStringExtra("ngaydat");
        noidung=getIntent().getStringExtra("noidung");
        sodienthoai=getIntent().getStringExtra("sodienthoai");
        email = getIntent().getStringExtra("email");
        emailnv = getIntent().getStringExtra("emailnv");
        trangthai = getIntent().getStringExtra("hoanthanh");
        xacnhan = getIntent().getStringExtra("xacnhan");

        txtdichvu=findViewById(R.id.dichvu);
        txtdichvu.setText(dichvu);
        txtdiachi=findViewById(R.id.diachi);
        txtdiachi.setText(diachi);
        txtnoidung=findViewById(R.id.noidung);
        txtnoidung.setText(noidung);
        txtngaydat=findViewById(R.id.ngaydat);
        txtngaydat.setText(ngaydat);
        txtsdt=findViewById(R.id.sdt);
        txtsdt.setText(sodienthoai);
        txtemail = findViewById(R.id.email);
        txtemail.setText(email);
        txtxacnhan=findViewById(R.id.xacnhan);
        txtxacnhan.setText(xacnhan);
        txthoanthanh=findViewById(R.id.trangthai);
        txthoanthanh.setText(trangthai);
        trove= findViewById(R.id.id_trove);

        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietDonHangNhanVien.this, TrangChuNhanVienActivity.class));
                finish();
            }
        });
//        txthoanthanh.findViewById(R.id.trangthai);
//        txthoanthanh.setText(trangthai);
//        txtxacnhan.findViewById(R.id.xacnhan);
//        txtxacnhan.setText(xacnhan);



//        trove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();return;
//            }
//        });
    }
}