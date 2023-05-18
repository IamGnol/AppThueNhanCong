package com.example.loginandregister;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerOptions;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //user

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private List<Photo> listphoto;
    private Timer mTimer;
     String phone="0916378900";


    RelativeLayout dienlanh,ongnuoc,suanha,suadien,donuoc,dientu;
    private FirebaseDatabase user;
    private DatabaseReference reference;
    private String sdt ;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MainAdapter mainAdapter;
    FloatingActionButton mFloat;
    Toolbar toolbarmenu;
    TextView txtname, txtemail;
    ImageView qrcode,hoadon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.viewPager);
        mFloat=findViewById(R.id.floatingbutton);
        circleIndicator=findViewById(R.id.circle_indicator);
        listphoto=getListPhoto();
        photoAdapter= new PhotoAdapter(this,listphoto);
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlideImage();

        hoadon=findViewById(R.id.hoadon);
        dienlanh=findViewById(R.id.dienlanh);
        ongnuoc=findViewById(R.id.ongnuoc);
        suanha=findViewById(R.id.suanha);
        suadien=findViewById(R.id.suadien);
        donuoc=findViewById(R.id.donuoc);
        dientu=findViewById(R.id.dientu);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        toolbarmenu=(Toolbar)findViewById(R.id.toobaltrangchu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout) ;
        qrcode=findViewById(R.id.imgqrcode);
        //mới
        new FirebasedataHelperDichvu().readDanhsach(new FirebasedataHelperDichvu.DataStatus() {
            @Override
            public void DataIsLoaded(List<DichVu> dichVus, List<String> keys) {
                new RecyclerView_ConfigDichvu().setConfig(recyclerView, MainActivity.this,
                        dichVus,keys);
            }
        });

        mFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("tel://"+phone)));
            }
        });

        dienlanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,DVDienLanh.class);
                startActivity(intent);
            }
        });
        ongnuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,DVSuaOngNuoc.class);
                startActivity(intent);
            }
        });
        suanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,DVSuaNha.class);
                startActivity(intent);
            }
        });
        suadien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,DVSuaDien.class);
                startActivity(intent);
            }
        });
        donuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,DVDoNuoc.class);
                startActivity(intent);
            }
        });
        dientu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,DVDienTu.class);
                startActivity(intent);
            }
        });



        actionToolbal();
        initUi();


        //menu
      //  navigationView.bringToFront();//chọn nổi navication

        navigationView.setNavigationItemSelectedListener(this);
        showUserInformation();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        FirebaseRecyclerOptions<DichVu> options =
//                new FirebaseRecyclerOptions.Builder<DichVu>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("dichvu"), DichVu.class)
//                        .build();
//
//        mainAdapter=new MainAdapter(options);
//        recyclerView.setAdapter(mainAdapter);

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,QuetQR.class);
                startActivity(intent);
            }
        });
        hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,DanhSachHoaDonCuaKhachHang.class);
                startActivity(intent);
            }
        });




//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        drawerLayout = findViewById(R.id.drawerlayout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
//                R.string.openmenunavigation, R.string.closemenunavigation);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationview = findViewById(R.id.navigation);
//        navigationview.setNavigationItemSelectedListener(this);
//        replaceFragment(new TrangchuNguoidung());
//        navigationview.getMenu().findItem(R.id.trangchu).setChecked(true);
//
//        //canhan=findViewById(R.id.ttcanhan);
        //canhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, ThongtincanhanActivity.class));
//               finish();
//            }
//        });

    //    canhan=(ImageView) findViewById(R.id.img_canhan);
//        canhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, ThongtincanhanActivity.class));
//                finish();
//
//            }
//        });
    }
     private  void initUi(){
         navigationView=(NavigationView)findViewById(R.id.navigationView);

         txtemail=navigationView.getHeaderView(0).findViewById(R.id.tvemail);

     }

    private void actionToolbal() {
        setSupportActionBar(toolbarmenu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarmenu.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbarmenu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mainAdapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mainAdapter.stopListening();
//    }
    //chọn menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.lich:
                Intent intent1= new Intent(MainActivity.this,DanhSachLichDatNguoiDung.class);
                startActivity(intent1);
                break;
            case R.id.thongtin:
                Intent intent= new Intent(MainActivity.this,ThongtincanhanActivity.class);
                startActivity(intent);
                break;
            case R.id.thongtinlienhe:
                Intent intent3= new Intent(MainActivity.this,ThongtinlienheActivity.class);
                startActivity(intent3);
                break;
            case R.id.dangxuat:
                FirebaseAuth.getInstance().signOut();
                Intent intent2= new Intent(MainActivity.this,Login.class);
                startActivity(intent2);

                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //lấy tên người dùng cho navication View
    private void showUserInformation(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        String email=user.getEmail();


        txtemail.setText(email);

    }
    private List<Photo> getListPhoto(){
        List<Photo> list= new ArrayList<>();
        list.add(new Photo(R.drawable.anh1));
        list.add(new Photo(R.drawable.anh2));
        return  list;
    }
    private void autoSlideImage(){
        if(listphoto==null|| listphoto.isEmpty()|| viewPager==null){
            return;
        }
        //khởi tạo timer
        if(mTimer==null){
            mTimer= new Timer();
        }
        mTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run(){
                        int currentItem= viewPager.getCurrentItem();
                        int totalItem=listphoto.size()-1;
                        if(currentItem<totalItem){
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        }else{
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000);//set thời gian
    }


    //hủy nếu ko tồn tại nữa
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimer!=null){
            mTimer.cancel();
            mTimer=null;
        }
    }
}