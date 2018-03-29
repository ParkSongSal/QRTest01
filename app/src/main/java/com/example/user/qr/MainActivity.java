package com.example.user.qr;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.qr.listviewexample.ListActivity;
import com.example.user.qr.storeDB.DBAdapter;
import com.example.user.qr.storeDB.ListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListAdapter.ListBtnClickListener {
    private DBAdapter mDb;
    //private DBAdapter adapter;
    Button GenerateBtn;
    Button clearBtn;
    TextView date;
    Intent intent;


    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date nowdate = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd(E)");  //2018-03-02(월)
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(nowdate);


    private ListView listview;
    private ListAdapter adapter;
    private List<Store> storelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_activity_main_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });

        date=(TextView) findViewById(R.id.date);
        date.setText(formatDate); // TextView 에 현재 시간 문자열 할당

        listview = (ListView) findViewById(R.id.listview);
        intent=getIntent();

        mDb = new DBAdapter(this);
        storelist = mDb.getAllStore();
        adapter = new ListAdapter(getApplicationContext(), storelist);
        listview.setAdapter(adapter);

        mDb.insertStore(intent.getStringExtra("storeName"), intent.getStringExtra("storeAddress"));
        refreshList();

        //storelist = new ArrayList<>();
        //storelist.add(new Store("담양판그릴", "진월동 한국 아델리움 앞", "17:04", "22:01"));

        clearBtn = (Button) findViewById(R.id.clearBtn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
                startActivity(intent);
            }
        });

        GenerateBtn = (Button) findViewById(R.id.GenerateBtn);
        GenerateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GenerateQRcode.class);
                startActivity(intent);
            }
        });

        //리스트뷰 클릭시 삭제
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {

                AlertDialog diaBox = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete")
                        .setMessage("정말로 삭제하시겠습니까?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Store i = storelist.get(position);
                                mDb.deleteStore(i.getId());
                                refreshList();
                            }
                        })
                        .setNegativeButton("No", null)
                        .create();
                diaBox.show();

                return false;
            }
        });
    } //on create 종료



    private void refreshList() {
        storelist.clear();
        storelist.addAll(mDb.getAllStore());
        adapter.notifyDataSetInvalidated();
    }

    @Override
    public void onListBtnClick(int position) {
        //Toast.makeText(this, "안녕하세요.", Toast.LENGTH_SHORT).show() ;
        Intent intent = new Intent(this,ScanActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        // Alert을 이용해 종료시키기

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

        dialog.setTitle("종료 알림")

                .setMessage("정말 종료하시겠습니까?")

                .setPositiveButton("종료합니다", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }

                })

                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this, "종료하지 않습니다", Toast.LENGTH_SHORT).show();

                    }

                }).create().show();

    } //뒤로가기 종료버튼
}