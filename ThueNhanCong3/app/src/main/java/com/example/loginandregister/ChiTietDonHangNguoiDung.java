package com.example.loginandregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ChiTietDonHangNguoiDung extends AppCompatActivity {
    TextView txtdichvu,txtdiachi,txtnoidung,txtngaydat,txtsdt, txtemailnv, txtxacnhan, txthoanthanh,txtemailnd;
    EditText txtphanhoi;
    Button capnhat,xoadon;
    ImageView trove;
    private String key;
    private String diachi;
    private String dichvu;
    private String ngaydat;
    private String noidung;
    private String sodienthoai;
    private String emailnd;
    private String emailnv;
    private String trangthai;
    private String xacnhan;
    private String phanhoikh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang_nguoi_dung);

        key=getIntent().getStringExtra("key");
        diachi=getIntent().getStringExtra("diachi");
        dichvu=getIntent().getStringExtra("dichvu");
        ngaydat=getIntent().getStringExtra("ngaydat");
        noidung=getIntent().getStringExtra("noidung");
        sodienthoai=getIntent().getStringExtra("sodienthoai");
        emailnd = getIntent().getStringExtra("email");
        emailnv = getIntent().getStringExtra("emailnv");
        phanhoikh=getIntent().getStringExtra("phanhoikh");
        trangthai = getIntent().getStringExtra("hoanthanh");
        xacnhan = getIntent().getStringExtra("xacnhan");
        trove= findViewById(R.id.id_trove);
        xoadon=findViewById(R.id.huydon);

        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietDonHangNguoiDung.this, DanhSachLichDatNguoiDung.class));
                finish();
            }
        });

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
        txtemailnv = findViewById(R.id.emailnv);
        txtemailnv.setText(emailnv);
        txtemailnd = findViewById(R.id.emailnd);
        txtemailnd.setText(emailnd);
        txtphanhoi = findViewById(R.id.danhgia);
        txtphanhoi.setText(phanhoikh);
        txtxacnhan=findViewById(R.id.xacnhan);
        txtxacnhan.setText(xacnhan);
        txthoanthanh=findViewById(R.id.trangthai);
        txthoanthanh.setText(trangthai);
        capnhat=findViewById(R.id.btncapnhatthongtinkhachhang);

            xoadon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (txtxacnhan.getText().equals("Đã xác nhận") || txthoanthanh.getText().equals("Đã hoàn thành")) {
                        AlertDialog.Builder xoadon = new AlertDialog.Builder(view.getContext());
                        xoadon.setTitle("Bạn không thể hủy lịch vì lịch của bạn đã được xác nhận!");
                        xoadon.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        xoadon.create().show();

                    } else {
                        AlertDialog.Builder xoadon = new AlertDialog.Builder(view.getContext());
                        xoadon.setTitle("Bạn có chắc chắn muốn hủy lịch đặt?");
                        xoadon.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new FirebaseDatabaseHelperNguoiDung().deleteLichDat(key, new FirebaseDatabaseHelper.DataStatus() {
                                    @Override
                                    public void DataIsLoaded(List<LichDat> lichdat, List<String> keys) {

                                    }

                                    @Override
                                    public void DataIsInserter() {

                                    }

                                    @Override
                                    public void DataIsUpdated() {

                                    }

                                    @Override
                                    public void DataIsDelete() {
                                        Toast.makeText(ChiTietDonHangNguoiDung.this, "Bạn đã hủy đơn thành công", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), DanhSachLichDatNguoiDung.class));
                                        finish();

                                    }
                                });
                            }
                        });
                        xoadon.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        xoadon.create().show();

                    }
                }
            });

        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LichDat lichDat= new LichDat();
                lichDat.setDichvu(txtdichvu.getText().toString());
                lichDat.setDiachi(txtdiachi.getText().toString());
                lichDat.setNgaydat(txtngaydat.getText().toString());
                lichDat.setNoidung(txtnoidung.getText().toString());
                lichDat.setEmailnv(txtemailnv.getText().toString());
                lichDat.setEmail(txtemailnd.getText().toString());
                lichDat.setSodienthoai(txtsdt.getText().toString());
                lichDat.setHoanthanh(txthoanthanh.getText().toString());
                lichDat.setXacnhan(txtxacnhan.getText().toString());
                lichDat.setPhanhoikh(txtphanhoi.getText().toString());

                new FirebaseDatabaseHelperNguoiDung().updateLichDat(key, lichDat, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<LichDat> lichdat, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserter() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(ChiTietDonHangNguoiDung.this, "Đánh giá thành công", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void DataIsDelete() {

                    }
                });
            }
        });
    }

}