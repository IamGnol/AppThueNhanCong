package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DVSuaDien extends AppCompatActivity {
    ImageView trove;
    Button datlich,banggia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dvsua_dien);


        trove= (ImageView) findViewById(R.id.id_trove);
        datlich=findViewById(R.id.datlich);
        banggia=findViewById(R.id.banggiasuadien);

        banggia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DVSuaDien.this,BangGiaDichVuSuaDien.class);
                startActivity(intent);
            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DVSuaDien.this, MainActivity.class));
                finish();
            }
        });
        datlich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DVSuaDien.this,DatLich2.class);
                startActivity(intent);
            }
        });
    }
}