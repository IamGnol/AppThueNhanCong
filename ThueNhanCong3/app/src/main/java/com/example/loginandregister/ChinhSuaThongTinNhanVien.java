package com.example.loginandregister;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ChinhSuaThongTinNhanVien extends AppCompatActivity {
    EditText name, sdt;
    TextView email;
    Spinner congviec;
    ImageView trove,anhcanhan;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    Button capnhatthongtin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_thong_tin_nhan_vien);

       email= findViewById(R.id.emailnhanvien);
       name=findViewById(R.id.id_name);
       sdt=findViewById(R.id.id_sdt);
       congviec=findViewById(R.id.id_congviec);
        anhcanhan = findViewById(R.id.anhcanhan);
        capnhatthongtin=findViewById(R.id.capnhat);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        user=fAuth.getCurrentUser();
        storageReference= FirebaseStorage.getInstance().getReference();


        trove= findViewById(R.id.id_trove);

        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChinhSuaThongTinNhanVien.this, ThongTinNhanVienActivity.class));
                finish();
            }
        });

        Intent data=getIntent();
        String Email= data.getStringExtra("Email");
        String Ten= data.getStringExtra("ten");
        String sodienthoai= data.getStringExtra("sodienthoai");
        String Congviec= data.getStringExtra("Congviec");


        email.setText(Email);
        name.setText(Ten);
        sdt.setText(sodienthoai);

        StorageReference profileRef=storageReference.child("User/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener((OnSuccessListener) (uri)->{
            Picasso.get().load((Uri) uri).into(anhcanhan);
        });
        anhcanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryForResult= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryForResult,1000);
            }
        });

        capnhatthongtin.setOnClickListener(view -> {
            if( name.getText().toString().isEmpty()||sdt.getText().toString().isEmpty()){
                Toast.makeText(ChinhSuaThongTinNhanVien.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                return;
            }
            final String email1=email.getText().toString();
            user.updateEmail(email1).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    DocumentReference docRef=fStore.collection("User").document(user.getUid());
                    Map<String,Object> edited=new HashMap<>();
                    edited.put("ten", name.getText().toString());
                    edited.put("sodienthoai", sdt.getText().toString());
                    edited.put("Congviec", congviec.getSelectedItem().toString());
                    docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(ChinhSuaThongTinNhanVien.this, "Đã cập nhật thông tin", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),ThongTinNhanVienActivity.class));
                            finish();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ChinhSuaThongTinNhanVien.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            if(resultCode== Activity.RESULT_OK){
                Uri imageUri= data.getData();
                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        final StorageReference fileRef=storageReference.child("User/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(anhcanhan);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ChinhSuaThongTinNhanVien.this, "Upload không thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}