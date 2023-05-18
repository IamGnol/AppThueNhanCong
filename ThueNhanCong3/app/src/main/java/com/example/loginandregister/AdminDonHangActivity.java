package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminDonHangActivity extends AppCompatActivity {
    ImageView trove;
    RecyclerView recyclerView;
    FirebaseDatabaseHelper firebaseDatabaseHelper;
    private SearchView timkiem;
   // vd mới
    DonHangAdminAdapter donHangAdminAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_don_hang);
        timkiem=findViewById(R.id.timkiem);
        timkiem.clearFocus();

        timkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Search(newText);
                return true;
            }
        });
        trove= findViewById(R.id.id_trove);
        recyclerView= findViewById(R.id.recyclerViewdonhang);
//Tạm ẩn
//        new FirebaseDatabaseHelper().readDanhsach(new FirebaseDatabaseHelper.DataStatus() {
//            @Override
//            public void DataIsLoaded(List<LichDat> lichdat, List<String> keys) {
//                findViewById(R.id.progressBar).setVisibility(View.GONE);
//                new RecyclerView_Config().setConfig(recyclerView,AdminDonHangActivity.this,lichdat,keys);
//            }
//
//            @Override
//            public void DataIsInserter() {
//
//            }
//
//            @Override
//            public void DataIsUpdated() {
//
//            }
//
//            @Override
//            public void DataIsDelete() {
//
//            }
//        }); tạm ẩn
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDonHangActivity.this, TrangchuAdminActivity.class));
                finish();
            }
        });
        FirebaseRecyclerOptions<LichDat> options =
                new FirebaseRecyclerOptions.Builder<LichDat>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Thongtinlichdat"), LichDat.class)
                        .build();
        donHangAdminAdapter= new DonHangAdminAdapter(options);
        recyclerView.setAdapter(donHangAdminAdapter);

    }

    private void Search(String newText) {
        FirebaseRecyclerOptions<LichDat> options =
                new FirebaseRecyclerOptions.Builder<LichDat>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Thongtinlichdat").orderByChild("ngaydat").startAt(newText).endAt(newText+"~"), LichDat.class)
                        .build();
        donHangAdminAdapter= new DonHangAdminAdapter(options);
        donHangAdminAdapter.startListening();
        recyclerView.setAdapter(donHangAdminAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        donHangAdminAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        donHangAdminAdapter.stopListening();
    }
}