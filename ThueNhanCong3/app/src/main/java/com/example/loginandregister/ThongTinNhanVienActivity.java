package com.example.loginandregister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ThongTinNhanVienActivity extends AppCompatActivity {
    ImageView trove,anhcanhan;
    Button capnhat;
    TextView email,name, sdt,congviec;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    StorageReference storageReference;
    FirebaseStorage storage;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nhan_vien);
        email=findViewById(R.id.id_emailkhachhang);
        name=findViewById(R.id.id_name);
        sdt=findViewById(R.id.id_sdt);
        congviec=findViewById(R.id.id_congviec);
        anhcanhan = findViewById(R.id.anhcanhan);
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        trove=findViewById(R.id.id_trove);
        capnhat = findViewById(R.id.btncapnhat);
        fAuth= FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        userID=fAuth.getCurrentUser().getUid();

        StorageReference profileRef=storageReference.child("User/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener((OnSuccessListener) (uri)->{
            Picasso.get().load((Uri) uri).into(anhcanhan);
        });
        DocumentReference documentReference= fStore.collection("User").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                email.setText(value.getString("Email"));
                name.setText(value.getString("ten"));
                sdt.setText(value.getString("sodienthoai"));
                congviec.setText(value.getString("Congviec"));
            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongTinNhanVienActivity.this, TrangChuNhanVienActivity.class));
                finish();
            }
        });
        capnhat.setOnClickListener((v) -> {
            Intent intent=new Intent(v.getContext(),ChinhSuaThongTinNhanVien.class);
            intent.putExtra("Email",email.getText().toString());
            intent.putExtra("ten",name.getText().toString());
            intent.putExtra("sodienthoai",sdt.getText().toString());
            intent.putExtra("Congviec",congviec.getText().toString());
            startActivity(intent);
        });


    }
}