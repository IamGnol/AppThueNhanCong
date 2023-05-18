package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BangGiaDichVuDienLanh extends AppCompatActivity {
    ImageView trove;
    RecyclerView recyclerView;
    AdapterBangGiaDienLanh adapterBangGiaDienLanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_gia_dich_vu_dien_lanh2);

        trove= (ImageView) findViewById(R.id.id_trove);
        recyclerView=findViewById(R.id.recyclerViewBangGia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BangGiaDichVuDienLanh.this, DVDienLanh.class));
                finish();
            }
        });
        FirebaseRecyclerOptions<DichVu> options=
                new FirebaseRecyclerOptions.Builder<DichVu>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("DichVuDienLanh"), DichVu.class)
                        .build();
        adapterBangGiaDienLanh= new AdapterBangGiaDienLanh(options);
        recyclerView.setAdapter(adapterBangGiaDienLanh);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapterBangGiaDienLanh.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterBangGiaDienLanh.stopListening();
    }
}