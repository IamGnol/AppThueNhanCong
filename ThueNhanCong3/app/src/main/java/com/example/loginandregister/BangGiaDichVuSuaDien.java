package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BangGiaDichVuSuaDien extends AppCompatActivity {
    ImageView trove;
    RecyclerView recyclerView;
    AdapterBangGiaSuaDien adapterBangGiaSuaDien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_gia_dich_vu_sua_dien);

        trove= (ImageView) findViewById(R.id.id_trove);
        recyclerView=findViewById(R.id.recyclerViewBangGia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BangGiaDichVuSuaDien.this, DVSuaDien.class));
                finish();
            }
        });
        FirebaseRecyclerOptions<DichVu> options=
                new FirebaseRecyclerOptions.Builder<DichVu>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("DichVuSuaDien"), DichVu.class)
                        .build();
        adapterBangGiaSuaDien= new AdapterBangGiaSuaDien(options);
        recyclerView.setAdapter(adapterBangGiaSuaDien);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapterBangGiaSuaDien.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterBangGiaSuaDien.stopListening();
    }
}