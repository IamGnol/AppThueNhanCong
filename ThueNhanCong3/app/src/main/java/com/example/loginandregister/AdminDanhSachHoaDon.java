package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class AdminDanhSachHoaDon extends AppCompatActivity {
    private RecyclerView recyclerView;
    ImageView trove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_danh_sach_hoa_don);
        recyclerView=findViewById(R.id.dshoadon);
        trove = findViewById(R.id.id_trove);
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDanhSachHoaDon.this, TrangchuAdminActivity.class));
                finish();
            }
        });

        new FirebaseDatabaseHelperAdminDSHoaDon().readDanhsach(new FirebaseDataHelperHoaDonNguoiDung.DataStatus() {
            @Override
            public void DataIsLoaded(List<HoaDon> hoaDons, List<String> key) {
                new RecyclerView_ConfigAdminDSHoaDon().setConfig(recyclerView, AdminDanhSachHoaDon.this,hoaDons,key);
            }
        });
    }
}