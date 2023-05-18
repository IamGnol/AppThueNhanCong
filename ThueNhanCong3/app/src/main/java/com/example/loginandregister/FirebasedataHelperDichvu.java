package com.example.loginandregister;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebasedataHelperDichvu {
    private FirebaseDatabase mdatabase;
    private DatabaseReference mreference;
    private List<DichVu> dichVus= new ArrayList<>();
    public interface DataStatus{
        void DataIsLoaded(List<DichVu> dichVus,List<String>keys);

    }
    public FirebasedataHelperDichvu() {
        mdatabase=FirebaseDatabase.getInstance();
        mreference=mdatabase.getReference("dichvu");
    }
    public void readDanhsach(final DataStatus dataStatus){
        mreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dichVus.clear();
                List<String>keys= new ArrayList<>();
                for(DataSnapshot keyNode:snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    DichVu dichVu=keyNode.getValue(DichVu.class);
                    dichVus.add(dichVu);
//                    DichVu dichVu=keyNode.getValue(DichVu.class);
//                    dichVus.add(dichVu);
                }
                dataStatus.DataIsLoaded(dichVus,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
