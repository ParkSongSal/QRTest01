package com.example.user.qr.listviewexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.qr.R;
import com.example.user.qr.ScanActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jun on 2018-03-29.
 */

public class ListActivity extends AppCompatActivity{
    private ListView mListView;
    private ListViewAdapter mListViewAdapter;

    private IntentIntegrator mIntegrator;
    private IScanHelper mIScanHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mListView = findViewById(R.id.listview11);

        mIntegrator = new IntentIntegrator(this);
        mIScanHelper = new IScanHelper(mIntegrator);

        mListViewAdapter = new ListViewAdapter(this, mIScanHelper);
        mListView.setAdapter(mListViewAdapter);

        // 데이터 초기화
        ListViewItem item = new ListViewItem();
        item.setTitleTxt("hello world");
        mListViewAdapter.addItem(item);
        ListViewItem item2 = new ListViewItem();
        item2.setTitleTxt("hello world");
        mListViewAdapter.addItem(item2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null) {
            // qrcode가 없으면
            if (result.getContents() == null) {
                Toast.makeText(ListActivity.this,"취소!",Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(ListActivity.this,"성공!",Toast.LENGTH_LONG).show();
                mIScanHelper.notifyResult(result.getContents());

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }











}
