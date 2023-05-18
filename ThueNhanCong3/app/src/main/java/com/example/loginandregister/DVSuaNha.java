package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DVSuaNha extends AppCompatActivity {
    ImageView trove;
    Button datlich,banggia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dvsua_nha);

        trove= (ImageView) findViewById(R.id.id_trove);
        datlich=findViewById(R.id.datlich);
        banggia=findViewById(R.id.banggiasuanha);

        banggia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DVSuaNha.this,BangGiaDichVuSuaNha.class);
                startActivity(intent);
            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DVSuaNha.this, MainActivity.class));
                finish();
            }
        });
        datlich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DVSuaNha.this,DatLich2.class);
                startActivity(intent);
            }
        });
    }
}