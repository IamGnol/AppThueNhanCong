package com.example.loginandregister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AdminDanhSachNhanVien extends AppCompatActivity {
    ImageView trove;
    RecyclerView recyclerView;
    ArrayList<NhanVien> nhanViens;
    ThongtinhanvienAdapter thongtinhanvienAdapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_danh_sach_nhan_vien);

        trove= findViewById(R.id.id_trove);
        recyclerView=findViewById(R.id.recyclerViewNguoiDung);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db=FirebaseFirestore.getInstance();
        nhanViens= new ArrayList<NhanVien>();
        thongtinhanvienAdapter= new ThongtinhanvienAdapter(AdminDanhSachNhanVien.this,nhanViens);
        recyclerView.setAdapter(thongtinhanvienAdapter);
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDanhSachNhanVien.this, TrangchuAdminActivity.class));
                finish();
            }
        });
        EventChangListener();
    }

    private void EventChangListener() {
        db.collection("User").orderBy("isEmpl", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        for(DocumentChange dc: value.getDocumentChanges()){

                            if(dc.getType()== DocumentChange.Type.ADDED){

                                nhanViens.add(dc.getDocument().toObject(NhanVien.class));
                            }
                            thongtinhanvienAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}