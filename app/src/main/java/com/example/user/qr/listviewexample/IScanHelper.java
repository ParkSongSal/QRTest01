package com.example.user.qr.listviewexample;

import com.google.zxing.integration.android.IntentIntegrator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jun on 2018-03-29.
 */

public class IScanHelper {
    private IntentIntegrator mIntentIntegrator;
    private ScanResult mScanResult;
    public IScanHelper(IntentIntegrator integrator){
        mIntentIntegrator = integrator;
    }

    public void startScan(ScanResult scanResult){
        this.mScanResult = scanResult;
        mIntentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        mIntentIntegrator.setPrompt("Scanning...");
        mIntentIntegrator.setCameraId(0);
        mIntentIntegrator.setBeepEnabled(false);
        mIntentIntegrator.setBarcodeImageEnabled(false);
        mIntentIntegrator.setOrientationLocked(true);  // false 가로 , true 세로 화면
        mIntentIntegrator.initiateScan();
    }
    public void notifyResult(String result){
        mScanResult.onScan(result);
    }

    public interface ScanResult{
        void onScan(String scanResult);
    }
}
