package com.example.user.qr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class ScanActivity extends AppCompatActivity {


    private Button buttonScan;
    private TextView textViewName, textViewAddress, textViewResult;

    private IntentIntegrator integrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewAddress = (TextView) findViewById(R.id.textAddress);
        textViewResult = (TextView) findViewById(R.id.textViewResult);


        //intializing scan object
        //qrScan = new IntentIntegrator(this);
        final Activity activity = this;
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scanning...");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.setOrientationLocked(true);  // false 가로 , true 세로 화면
                integrator.initiateScan();
                //qrScan.setPrompt("Scanning...");
                //qrScan.initiateScan();
            }
        });
    }


    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null) {
            // qrcode가 없으면
            if (result.getContents() == null) {
                Toast.makeText(ScanActivity.this,"취소!",Toast.LENGTH_LONG).show();

            } else {
                //qrcode 결과가 있으면
                Toast.makeText(ScanActivity.this,"스캔완료",Toast.LENGTH_LONG).show();


                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    textViewName.setText(result.getFormatName());
                    //textViewAddress.setText(obj.getString("address"));



                } catch (JSONException e) {
                    e.printStackTrace();
                    textViewName.setText(result.getFormatName());
                    textViewAddress.setText(result.getBarcodeImagePath());
                    textViewResult.setText(result.getContents());
                    //QR코드 링크
                    textViewResult.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(result.getContents()));
                            startActivity(intent);
                        }
                    });

                    Log.d("Tag","uri가 입력되었습니다.");
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //뒤로가기 버튼 눌렀을 때 메인화면으로  이동
    @Override
    public void onBackPressed() {
        finish();
        Toast.makeText(this,"메인 화면으로 돌아갑니다.",Toast.LENGTH_LONG).show();
    }
}
