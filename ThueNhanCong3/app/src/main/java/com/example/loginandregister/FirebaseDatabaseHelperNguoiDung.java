package com.example.loginandregister;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelperNguoiDung
{
    private FirebaseDatabase mdatabase;
    private DatabaseReference mreference;
    private List<LichDat> dslichdat= new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<LichDat> lichdat,List<String>keys);
        void DataIsInserter();
        void DataIsUpdated();
        void DataIsDelete();
    }
    public FirebaseDatabaseHelperNguoiDung() {
        mdatabase=FirebaseDatabase.getInstance();
        mreference=mdatabase.getReference("Thongtinlichdat");
    }
    public void readDanhsach(final FirebaseDatabaseHelper.DataStatus dataStatus){
        mreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                String emailnd = user.getEmail();

                dslichdat.clear();
                List<String>keys= new ArrayList<>();
                for(DataSnapshot keyNode:snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    LichDat lichDat=keyNode.getValue(LichDat.class);
                    if (lichDat.getEmail().compareTo(emailnd)==0)
                    {
                        dslichdat.add(lichDat);
                    }

                }
                dataStatus.DataIsLoaded(dslichdat,keys);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void updateLichDat(String key,LichDat lichDat, final FirebaseDatabaseHelper.DataStatus dataStatus){
        mreference.child(key).setValue(lichDat)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }
    public void deleteLichDat(String key,final FirebaseDatabaseHelper.DataStatus dataStatus){
        mreference.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        dataStatus.DataIsDelete();
                    }
                });
    }

}