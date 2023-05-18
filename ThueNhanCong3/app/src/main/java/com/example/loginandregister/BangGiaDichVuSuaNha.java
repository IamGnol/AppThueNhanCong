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

public class BangGiaDichVuSuaNha extends AppCompatActivity {
    ImageView trove;
    RecyclerView recyclerView;
    AdapterBangGiaSuaNha adapterBangGiaSuaNha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_gia_dich_vu_sua_nha);

        trove= (ImageView) findViewById(R.id.id_trove);
        recyclerView=findViewById(R.id.recyclerViewBangGia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BangGiaDichVuSuaNha.this, DVSuaNha.class));
                finish();
            }
        });
        FirebaseRecyclerOptions<DichVu> options=
                new FirebaseRecyclerOptions.Builder<DichVu>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("DichVuSuaNha"), DichVu.class)
                        .build();
        adapterBangGiaSuaNha= new AdapterBangGiaSuaNha(options);
        recyclerView.setAdapter(adapterBangGiaSuaNha);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapterBangGiaSuaNha.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterBangGiaSuaNha.stopListening();
    }
}