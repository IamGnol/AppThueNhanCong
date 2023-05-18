package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

public class ChiTietHoaDon extends AppCompatActivity {
    ImageView trove,ngay;
    TextView txtdichvu,txtdiachi,txtngaydat,txtnoidung,txtnhanvien,txtkhachhang,txtsdt;
    EditText editghichu,editdongia,ngaylaphd, editsoluong;
    Button xuatdon;
    DatabaseReference reference;
    DatePickerDialog.OnDateSetListener setListener;

    private String key;
    private String diachi;
    private String dichvu;
    private String ngaydat;
    private String noidung;
    private String sodienthoai;
    private String email;
    private String emailnv;
    private String ghichu;
    private String dongia;
    private String ngaylap;
    private String soluong;
    private String tongtien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);

        reference= FirebaseDatabase.getInstance().getReference().child("HoaDon");
        key=getIntent().getStringExtra("key");
        diachi=getIntent().getStringExtra("diachi");
        dichvu=getIntent().getStringExtra("dichvu");
        ngaydat=getIntent().getStringExtra("ngaydat");
        noidung=getIntent().getStringExtra("noidung");
        sodienthoai=getIntent().getStringExtra("sodienthoai");
        email = getIntent().getStringExtra("email");
        emailnv = getIntent().getStringExtra("emailnv");
        ghichu=getIntent().getStringExtra("ghichu");
        ngaylap=getIntent().getStringExtra("ngaylap");
        soluong=getIntent().getStringExtra("soluong");
        dongia=getIntent().getStringExtra("dongia");
        tongtien=getIntent().getStringExtra("tongtien");

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
        ngaylaphd=findViewById(R.id.ngaylaphoadon);
        ngaylaphd.setText(ngaylap);
        editsoluong=findViewById(R.id.soluong);
        editsoluong.setText(soluong);

        ngay=findViewById(R.id.imgngay);
        ngaylaphd.setShowSoftInputOnFocus(false);

        editghichu=findViewById(R.id.ghichu);
        editghichu.setText(ghichu);
        editdongia=findViewById(R.id.dongia);
        editdongia.setText(dongia);
        xuatdon=findViewById(R.id.xuatdon);
        xuatdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ngaylaphd.getText().toString().isEmpty()){
                    Toast.makeText(ChiTietHoaDon.this, "Chưa chọn ngày", Toast.LENGTH_SHORT).show();

                }
                else if(editsoluong.getText().toString().isEmpty()){
                    Toast.makeText(ChiTietHoaDon.this, "Chưa nhập số lượng", Toast.LENGTH_SHORT).show();

                }
                 else if(editdongia.getText().toString().isEmpty()){
                    Toast.makeText(ChiTietHoaDon.this, "Chưa nhập đơn giá", Toast.LENGTH_SHORT).show();
                }
                else {
                    InsertHoaDon();
                }


            }
        });


        trove= findViewById(R.id.id_trove);

        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietHoaDon.this, DanhSachLichDatNhanVien.class));
                finish();
            }
        });

        final Calendar calendar= Calendar.getInstance();
        final int year= calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day= calendar.get(Calendar.DAY_OF_MONTH);

        ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog= new DatePickerDialog(ChiTietHoaDon.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth+"/"+month+"/"+year;
                ngaylaphd.setText(date);
            }
        };
    }
    private void InsertHoaDon(){
        String dichvu= txtdichvu.getText().toString();
        String diachi= txtdiachi.getText().toString();
        String ngaydat= txtngaydat.getText().toString();
        String noidung= txtnoidung.getText().toString();
        String emailnv= txtnhanvien.getText().toString();
        String email= txtkhachhang.getText().toString();
        String sodienthoai= txtsdt.getText().toString();
        String ghichu= editghichu.getText().toString();
        String ngaylap= ngaylaphd.getText().toString();
        String soluong= editsoluong.getText().toString();
        String dongia= editdongia.getText().toString();

        int sl = Integer.parseInt(editsoluong.getText().toString());
        float dg = Float.parseFloat(editdongia.getText().toString());
        float tt = sl * dg;
        String tongtien = String.valueOf(tt);
        HoaDon hoaDon= new HoaDon(dichvu,email,emailnv,ngaydat,noidung,sodienthoai,diachi,dongia, tongtien,ghichu,ngaylap, soluong);
        reference.push().setValue(hoaDon);
        Toast.makeText(ChiTietHoaDon.this, "Tạo hóa đơn thành công", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),DanhSachLichDatNhanVien.class));
        finish();

    }
}