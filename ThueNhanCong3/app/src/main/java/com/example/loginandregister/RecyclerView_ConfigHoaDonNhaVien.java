package com.example.loginandregister;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RecyclerView_ConfigHoaDonNhaVien {
    private Context mcontext;
    private LichDatAdapter mlichDatAdapter;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    public void setConfig(RecyclerView recyclerView, Context context, List<HoaDon> hoaDons, List<String>keys){
        mcontext=context;
        mlichDatAdapter= new LichDatAdapter(hoaDons,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mlichDatAdapter);

    }
    class LichDatItemView extends RecyclerView.ViewHolder {
        private TextView tendonhang;
        private TextView ngaydat;
        private  TextView sodienthoai;
        private TextView noidung;
        private TextView diachi;
        private TextView emailnv;
        private TextView emailnd;
        private TextView tongtien;
        private TextView ghichu;
        private  TextView ngaylap;

        private String key;

        public LichDatItemView(ViewGroup parent) {
            super(LayoutInflater.from(mcontext).
                    inflate(R.layout.itemhoadonnguoidung, parent, false));
            tendonhang = itemView.findViewById(R.id.txttendonhang);
            ngaydat=itemView.findViewById(R.id.txtngaydat);
            sodienthoai=itemView.findViewById(R.id.txtsdt);
            noidung=itemView.findViewById(R.id.txtnoidung);
            diachi=itemView.findViewById(R.id.txtdiachi);
            emailnv=itemView.findViewById(R.id.txtemailnv);
            emailnd=itemView.findViewById(R.id.txtemailnd);
            tongtien=itemView.findViewById(R.id.txttongtien);
            ghichu=itemView.findViewById(R.id.txtghichu);
            ngaylap=itemView.findViewById(R.id.txtngaylap);


            // tenkhachhang=(TextView)itemView.findViewById(R.id.txttenkhachhang);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext, ChiTietHoaDonNhanVien.class);
                    intent.putExtra("key", key);
                    intent.putExtra("dichvu", tendonhang.getText().toString());
                    intent.putExtra("diachi", diachi.getText().toString());
                    intent.putExtra("ngaydat", ngaydat.getText().toString());
                    intent.putExtra("noidung", noidung.getText().toString());
                    intent.putExtra("sodienthoai", sodienthoai.getText().toString());
                    intent.putExtra("emailnv", emailnv.getText().toString());
                    intent.putExtra("email", emailnd.getText().toString());
                    intent.putExtra("ghichu", ghichu.getText().toString());
                    intent.putExtra("tongtien", tongtien.getText().toString());
                    intent.putExtra("ngaylap", ngaylap.getText().toString());
//                    intent.putExtra("tenkhachhang",tenkhachhang.getText().toString());
                    mcontext.startActivity(intent);
                }
            });
        }
        public void bind(HoaDon hoaDon,String key){

            tendonhang.setText(hoaDon.getDichvu());
            diachi.setText(hoaDon.getDiachi());
            ngaydat.setText(hoaDon.getNgaydat());
            noidung.setText(hoaDon.getNoidung());
            sodienthoai.setText(hoaDon.getSodienthoai());
            emailnv.setText(hoaDon.getEmailnv());
            emailnd.setText(hoaDon.getEmail());
            tongtien.setText(hoaDon.getTongtien());
            ghichu.setText(hoaDon.getGhichu());
            ngaylap.setText(hoaDon.getNgaylap());
            //      tenkhachhang.setText(lichDat.getTenkhachhang());
            this.key=key;
        }
    }
    class  LichDatAdapter extends  RecyclerView.Adapter<LichDatItemView>{
        private List<HoaDon> mhoaDons;
        private List<String> mkeys;

        public LichDatAdapter(List<HoaDon> mhoaDons, List<String> mkeys) {
            this.mhoaDons = mhoaDons;
            this.mkeys = mkeys;
        }

        @NonNull
        @Override
        public LichDatItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LichDatItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull LichDatItemView holder, int position) {
            holder.bind(mhoaDons.get(position),mkeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mhoaDons.size();
        }
    }
}
