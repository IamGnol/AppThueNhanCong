package com.example.loginandregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatLich2 extends AppCompatActivity {
//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://login-b5e1d-default-rtdb.firebaseio.com");
    DatabaseReference reference;
    DatePickerDialog.OnDateSetListener setListener;
    EditText  hoten,noidung,diachi;
    ImageView ngay;
    TextView email,sdt;
    EditText ngaydat;
    Spinner tendichvu;
    List<String> tendv;
    Button dat,trove;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_lich2);

        tendichvu=findViewById(R.id.txttendichvu);
        tendv=new ArrayList<>();
        hoten =findViewById(R.id.txttenkhachhang);
        sdt=findViewById(R.id.txtsdt);
        noidung=findViewById(R.id.txtnoidung);
        ngaydat=findViewById(R.id.txtngaydat);
        ngay=findViewById(R.id.imgngay);
        diachi=findViewById(R.id.txtdiachi);
        dat=findViewById(R.id.btnDat);
        email = findViewById(R.id.txtemail);
        trove=findViewById(R.id.id_trove);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        user=fAuth.getCurrentUser();
        userID=fAuth.getCurrentUser().getUid();


        ngaydat.setShowSoftInputOnFocus(false);


        reference=FirebaseDatabase.getInstance().getReference();
        reference.child("dichvu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot chilSnapshot:snapshot.getChildren()){
                    String spinnerten= chilSnapshot.child("ten").getValue(String.class);
                    tendv.add(spinnerten);
                }

                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(DatLich2.this, android.R.layout.simple_spinner_item,tendv);
                 arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                 tendichvu.setAdapter(arrayAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DatLich2.this, MainActivity.class));
                finish();
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        Calendar ngaydatt = Calendar.getInstance();
        Calendar ngayhtt = Calendar.getInstance();

        int year= ngayhtt.get(Calendar.YEAR);
        int month = ngayhtt.get(Calendar.MONTH);
        int day= ngayhtt.get(Calendar.DAY_OF_MONTH);

        ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog= new DatePickerDialog(DatLich2.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                ngaydat.setText(date);
            }
        };


        DocumentReference documentReference= fStore.collection("User").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                email.setText(value.getString("Email"));
                hoten.setText(value.getString("ten"));
                sdt.setText(value.getString("sodienthoai"));
            }
        });


        dat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String dichvu1= tendichvu.getSelectedItem().toString();
                        String hoten1 = hoten.getText().toString();
                        String sdt1 = sdt.getText().toString();
                        String noidung1 = noidung.getText().toString();
                        String ngaydat1 = ngaydat.getText().toString();
                        String diachi1 = diachi.getText().toString();
                        String email1= email.getText().toString();
                        if(hoten1.isEmpty()||noidung1.isEmpty()||ngaydat1.isEmpty()||diachi1.isEmpty()){
                            Toast.makeText(DatLich2.this, "Bạn chưa nhập đầy đủ thông tin của dịch vụ trên", Toast.LENGTH_SHORT).show();
                        }else if(!ngaydat1.contains("/")){
                            Toast.makeText(DatLich2.this, "Hãy nhập đủ ngày tháng năm", Toast.LENGTH_SHORT).show();
                        }else {
                            Map<String, Object> dichvu = new HashMap<>();
                            dichvu.put("dichvu", tendichvu.getSelectedItem().toString());
                            dichvu.put("tenkhachhang", hoten.getText().toString());
                            dichvu.put("sodienthoai", sdt.getText().toString());
                            dichvu.put("noidung", noidung.getText().toString());
                            dichvu.put("ngaydat", ngaydat.getText().toString());
                            dichvu.put("diachi", diachi.getText().toString());
                            dichvu.put("email", email.getText().toString());
                            dichvu.put("emailnv", "Chờ nhân viên");
                            dichvu.put("xacnhan", "Chưa xác nhận");
                            dichvu.put("hoanthanh", "Chưa hoàn thành");
                            dichvu.put("phanhoikh","Chưa phản hồi");
                            FirebaseDatabase.getInstance().getReference().child("Thongtinlichdat").push()
                                    .setValue(dichvu)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(DatLich2.this, "Đặt lịch thành công", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(DatLich2.this, "Đặt lịch thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }

//
//                        databaseReference.child("").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                                    if(hoten1.isEmpty()||sdt1.isEmpty()||noidung1.isEmpty()||ngaydat1.isEmpty()||diachi1.isEmpty()){
//                                        Toast.makeText(DatLich2.this, "Bạn chưa nhập đầy đủ thông tin của dịch vụ trên", Toast.LENGTH_SHORT).show();
//                                    }else {
//                                        databaseReference.child("Thongtinlichdat").child(sdt1).child("dichvu").setValue(dichvu1);
//                                        databaseReference.child("Thongtinlichdat").child(sdt1).child("email").setValue(email1);
//                                        databaseReference.child("Thongtinlichdat").child(sdt1).child("tenkhachhang").setValue(hoten1);
//                                        databaseReference.child("Thongtinlichdat").child(sdt1).child("sodienthoai").setValue(sdt1);
//                                        databaseReference.child("Thongtinlichdat").child(sdt1).child("noidung").setValue(noidung1);
//                                        databaseReference.child("Thongtinlichdat").child(sdt1).child("ngaydat").setValue(ngaydat1);
//                                        databaseReference.child("Thongtinlichdat").child(sdt1).child("diachi").setValue(diachi1);
//                                        databaseReference.child("Thongtinlichdat").child(sdt1).child("emailnv").setValue("Chọn nhân viên");
//                                        databaseReference.child("Thongtinlichdat").child(sdt1).child("xacnhan").setValue("Chưa xác nhận");
//                                        databaseReference.child("Thongtinlichdat").child(sdt1).child("hoanthanh").setValue("Chưa hoàn thành");
//                                        Toast.makeText(DatLich2.this, "Bạn đã đặt dịch vụ thành công", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//
//
//                                }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });

                    }
                });



    }

}