package com.example.user.qr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateQRcode extends AppCompatActivity {


    private Button GenerateBtn;
    private EditText Qrname,QrAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode);


        final Context context = this;
        Qrname = (EditText) findViewById(R.id.Qrname);
        QrAddress = (EditText) findViewById(R.id.QrAddress);
        GenerateBtn = (Button) findViewById(R.id.GenerateBtn);
        GenerateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text2Qr = Qrname.getText().toString();
                String text1Qr = QrAddress.getText().toString();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr,BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent = new Intent(GenerateQRcode.this,GenerateList.class);
                    intent.putExtra("storeName",text2Qr);  // 가게 명
                    intent.putExtra("storeAddress",text1Qr); // 가게 주소
                    //intent.putExtra("pic",bitmap); // 생성된 Qr코드
                    startActivity(intent);

                }
                catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
