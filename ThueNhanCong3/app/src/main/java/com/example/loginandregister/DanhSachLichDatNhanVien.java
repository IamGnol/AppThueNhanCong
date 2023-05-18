package com.example.loginandregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DanhSachLichDatNhanVien extends AppCompatActivity {
    private RecyclerView recyclerviewLichDat;
//    private LichDatNhanVienAdapter mLichDatAdapter;
//    private List<LichDat> mListLichDat;
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    String emailnv = user.getEmail();

    ImageView trove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_lich_dat_nhan_vien);

        trove= findViewById(R.id.id_trove);
        recyclerviewLichDat = findViewById(R.id.recyclerViewLichDat);
//        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
//        recyclerviewLichDat.setLayoutManager(linearLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        recyclerviewLichDat.addItemDecoration(dividerItemDecoration);
// /       mListLichDat = new ArrayList<>();
// /      mLichDatAdapter = new LichDatNhanVienAdapter(mListLichDat);
// /       recyclerviewLichDat.setAdapter(mLichDatAdapter);
//          getListLichDatFromDatabase();
        new FirebaseDatabaseHelperNhanVien().readDanhsach(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<LichDat> lichdat, List<String> keys) {
                new RecyclerView_CongfigNhanVien().setConfig(recyclerviewLichDat, DanhSachLichDatNhanVien.this,lichdat,keys);
            }

            @Override
            public void DataIsInserter() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDelete() {

            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DanhSachLichDatNhanVien.this, TrangChuNhanVienActivity.class));
                finish();
            }
        });

    }



    private void getListLichDatFromDatabase() {
        FirebaseDatabase fd = FirebaseDatabase.getInstance();
        DatabaseReference dr = fd.getReference("Thongtinlichdat");

        dr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                LichDat lichdat = snapshot.getValue(LichDat.class);
                if (lichdat != null) {
                    if (lichdat.getEmailnv().compareTo(emailnv) == 0) {
//                        mListLichDat.add(lichdat);
                        new FirebaseDatabaseHelperNhanVien().readDanhsach(new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void DataIsLoaded(List<LichDat> lichdat, List<String> keys) {
                                new RecyclerView_CongfigNhanVien().setConfig(recyclerviewLichDat, DanhSachLichDatNhanVien.this,lichdat,keys);
                            }

                            @Override
                            public void DataIsInserter() {

                            }

                            @Override
                            public void DataIsUpdated() {

                            }

                            @Override
                            public void DataIsDelete() {

                            }
                        });


                    }

//                    mLichDatAdapter.notifyDataSetChanged();
                }

            }



            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}