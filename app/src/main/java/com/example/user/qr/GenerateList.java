package com.example.user.qr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateList extends AppCompatActivity {

    private TextView storeName,storeAddress;
    private ImageView imageView;
    private String QrName,QrAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list);


        storeName = (TextView) findViewById(R.id.storeName);
        storeAddress = (TextView) findViewById(R.id.storeAddress);


        try{
            QrName = getIntent().getStringExtra("storeName");
            QrAddress = getIntent().getStringExtra("storeAddress");
            storeName.setText(QrName);
            storeAddress.setText(QrAddress);

        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("Tag","QR코드 이름이 입력되지 않았습니다.");
        }


        imageView = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap = getIntent().getParcelableExtra("pic");
        imageView.setImageBitmap(bitmap);
        Log.d("Tag","QR코드가 생성되었습니다.");
    }
}
