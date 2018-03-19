package com.example.user.qr.storeDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.qr.Store;

import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {
    private static final String DB_NAME = "promise4.db";
    private static final int VERSION = 1;
    private static final String ID = "_id";
    private static final String TITLE = "StoreName";
    private static final String ADDRESS = "address";
    //private static final String WORK = "date";

    private static final String TABLE_NAME = "StoreList";
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " integer primary key autoincrement, " +
                    TITLE + " not null, " +
                    ADDRESS + " not null)";
   // + DATE + " not null )";

    private SQLiteDatabase db;

    public DBAdapter(Context context) {
        super(context, DB_NAME, null, VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public synchronized void close() {
        db.close();
        super.close();
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // create
    public boolean insertStore(String title,String address) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put(ADDRESS,address);
        //cv.put(DATE, work);
        return db.insert(TABLE_NAME, null, cv) != -1;
    }

    public boolean insertStore(Store store) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, store.getTitle());   //가게명
        cv.put(ADDRESS, store.getAddress()); //가게 주소
        //cv.put(DATE, store.getWork());  //출근 시간
        return db.insert(TABLE_NAME, null, cv) != -1;
    }


    // read
    public ArrayList<Store> getAllStore() {
        ArrayList<Store> store = new ArrayList<Store>();
        Cursor c = db.query(TABLE_NAME, new String[] {ID, TITLE,ADDRESS}, null, null, null, null, ID + " DESC");

        if (c.moveToFirst()) {
            final int indexId = c.getColumnIndex(ID);
            final int indexTitle = c.getColumnIndex(TITLE);
            final int indexAddress = c.getColumnIndex(ADDRESS);
            //final int indexWork = c.getColumnIndex(DATE);

            do {
                int id = c.getInt(indexId);
                String title = c.getString(indexTitle);
                String address = c.getString(indexAddress);
                //String date = c.getString(indexDate);
                store.add(new Store(id, title,address));
            } while (c.moveToNext());
        }
        c.close();

        return store;
    }

    // update
    /*public boolean updateMemo(Memo i) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, i.getTitle());
        cv.put(CONTENT,i.getNote());
        cv.put(DATE, i.getDate());
        String[] params = new String[] { Integer.toString(i.getId()) };
        int result = db.update(TABLE_NAME, cv, ID + "=?", params);
        return result > 0;
    }*/
    public boolean updateStore(long id,Store s) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, s.getTitle());
        cv.put(ADDRESS,s.getAddress());
        //cv.put(DATE, s.getDate());
        String[] params = new String[] { Long.toString(id) };
        int result = db.update(TABLE_NAME, cv, ID + "=?", params);
        return result > 0;
    }

    // delete
    public boolean deleteMemo(int id) {
        String[] params = new String[] { Integer.toString(id) };
        int result = db.delete(TABLE_NAME, ID + "=?", params);
        return result > 0;
    }

    public boolean deleteAll() {
        int result=db.delete(TABLE_NAME, null, null);
        return result > 0;
    }
}

