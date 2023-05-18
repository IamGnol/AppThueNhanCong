package com.example.loginandregister;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDataHelperHoaDonNguoiDung {
    private FirebaseDatabase mdatabase;
    private DatabaseReference mreference;
    private List<HoaDon>dshoaDons=new ArrayList<>();
  //  private List<HoaDon> dshoaDons= new ArrayList<>();
    public interface DataStatus{
        void DataIsLoaded(List<HoaDon>hoaDons,List<String>keys);
    }

    public FirebaseDataHelperHoaDonNguoiDung() {
        mdatabase=FirebaseDatabase.getInstance();
        mreference=mdatabase.getReference("HoaDon");
    }
    public void readDanhsach(final DataStatus dataStatus){
        mreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                String emailnd = user.getEmail();

                dshoaDons.clear();
                List<String>keys= new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    HoaDon hoaDon=keyNode.getValue(HoaDon.class);
                    if (hoaDon.getEmail().compareTo(emailnd)==0)
                    {
                        dshoaDons.add(hoaDon);
                   }

                }
               dataStatus.DataIsLoaded(dshoaDons,keys);
         //       dataStatus.DataIsLoaded(dshoaDons,keys);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
