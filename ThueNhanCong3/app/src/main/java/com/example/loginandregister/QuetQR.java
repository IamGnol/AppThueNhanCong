package com.example.loginandregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.zip.ZipEntry;

public class QuetQR extends AppCompatActivity {
    private ImageView hinh;
    private TextView ten,ngaysinh,dienthoai,congviec;
    private Button quetqr;
    ImageView trove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quet_qr);

        trove= findViewById(R.id.id_trove);
        hinh=findViewById(R.id.imghinh);
        ten=findViewById(R.id.txtname);
        ngaysinh=findViewById(R.id.txtngaysinh);
        dienthoai=findViewById(R.id.txtdienthoai);
        congviec=findViewById(R.id.txtcongviec);
        quetqr=findViewById(R.id.btnquetma);
        IntentIntegrator intentIntegrator= new IntentIntegrator(this);
        quetqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentIntegrator.initiateScan();
            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuetQR.this, MainActivity.class));
                finish();
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //đóng gói kết quả
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                Picasso.get().load(result.getContents()).into(hinh);
                try{
                    JSONObject jsonObject= new JSONObject(result.getContents());
                    ten.setText(jsonObject.getString("ten"));
                    ngaysinh.setText(jsonObject.getString("ngaysinh"));
                    dienthoai.setText(jsonObject.getString("dienthoai"));
                    congviec.setText(jsonObject.getString("congviec"));
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }

        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }


    }
}