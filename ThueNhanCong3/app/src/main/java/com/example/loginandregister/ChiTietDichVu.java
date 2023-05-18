package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChiTietDichVu extends AppCompatActivity {

    // ko sài
    ImageView trove;
    TextView ten,mota;
    Button datlich;

    private String key;
    private String tendv;
    private String motadv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dich_vu);
        key=getIntent().getStringExtra("key");
        tendv=getIntent().getStringExtra("ten");
        motadv=getIntent().getStringExtra("mota");

        datlich=findViewById(R.id.datlich);
        mota=findViewById(R.id.mota);
        mota.setText(motadv);
        trove= findViewById(R.id.id_trove);
        ten=findViewById(R.id.tendv);
        ten.setText(tendv);


        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietDichVu.this, MainActivity.class));
                finish();
            }
        });
        datlich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ChiTietDichVu.this,DatLich2.class);
                startActivity(intent);

            }
        });
    }
}