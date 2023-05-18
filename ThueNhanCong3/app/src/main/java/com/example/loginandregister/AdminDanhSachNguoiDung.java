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

public class AdminDanhSachNguoiDung extends AppCompatActivity {
    ImageView trove;
    RecyclerView recyclerView;
    ArrayList<NguoiDung> nguoiDungs;
    ThongTinNguoiDungAdapter thongTinNguoiDungAdapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_danh_sach_nguoi_dung);

        trove= findViewById(R.id.id_trove);
        recyclerView=findViewById(R.id.recyclerViewNguoiDung);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db=FirebaseFirestore.getInstance();
        nguoiDungs= new ArrayList<NguoiDung>();
        thongTinNguoiDungAdapter= new ThongTinNguoiDungAdapter(AdminDanhSachNguoiDung.this, nguoiDungs);
        recyclerView.setAdapter(thongTinNguoiDungAdapter);
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDanhSachNguoiDung.this, TrangchuAdminActivity.class));
                finish();
            }
        });
        EventChangListener();
    }

    private void EventChangListener() {
        db.collection("User").orderBy("isUser", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        for(DocumentChange dc: value.getDocumentChanges()){

                            if(dc.getType()== DocumentChange.Type.ADDED){

                                nguoiDungs.add(dc.getDocument().toObject(NguoiDung.class));
                            }
                            thongTinNguoiDungAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}