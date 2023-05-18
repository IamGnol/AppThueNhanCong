package com.example.loginandregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ThongtincanhanActivity extends AppCompatActivity {
    ImageView trove, anhcanhan;
    Button capnhat;
    TextView email,name, sdt, maukhau, isUser;
    FirebaseUser user;
    DatabaseReference reference;
    FirebaseFirestore fStore;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseAuth fAuth;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtincanhan);
        anhcanhan = findViewById(R.id.anhcanhan);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        userID = fAuth.getCurrentUser().getUid();
        trove = findViewById(R.id.id_trove);
        email = findViewById(R.id.id_emailkhachhang);
        name = findViewById(R.id.id_name);
        sdt = findViewById(R.id.id_sdt);
        maukhau = findViewById(R.id.id_matkhau);
        capnhat = findViewById(R.id.btncapnhat);
        isUser=findViewById(R.id.id_isUser);


        //load ảnh đại diện
        StorageReference profileRef=storageReference.child("User/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener((OnSuccessListener) (uri)->{
            Picasso.get().load((Uri) uri).into(anhcanhan);
        });



        capnhat.setOnClickListener((v) -> {
            Intent intent=new Intent(v.getContext(),ChinhsuathongtincanhanActivity.class);
            intent.putExtra("Email",email.getText().toString());
            intent.putExtra("ten",name.getText().toString());
            intent.putExtra("sodienthoai",sdt.getText().toString());
            startActivity(intent);
        });




        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongtincanhanActivity.this, MainActivity.class));
                finish();

            }
        });

        DocumentReference documentReference = fStore.collection("User").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                email.setText(value.getString("Email"));
                name.setText(value.getString("ten"));
                sdt.setText(value.getString("sodienthoai"));
                maukhau.setText(value.getString("Matkhau"));
            }
        });

    }



}