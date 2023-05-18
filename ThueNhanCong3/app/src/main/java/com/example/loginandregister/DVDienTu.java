package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DVDienTu extends AppCompatActivity {
    ImageView trove;
    Button datlich,banggia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dvdien_tu);

        trove= (ImageView) findViewById(R.id.id_trove);
        banggia=findViewById(R.id.banggiadientu);

        datlich=findViewById(R.id.datlich);

        banggia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DVDienTu.this,BangGiaDichVuDienTu.class);
                startActivity(intent);
            }
        });

        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DVDienTu.this, MainActivity.class));
                finish();
            }
        });
        datlich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DVDienTu.this,DatLich2.class);
                startActivity(intent);
            }
        });
    }
}