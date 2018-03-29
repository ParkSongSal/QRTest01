package com.example.user.qr.listviewexample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.qr.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2018-03-29.
 */

public class ListViewAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<ListViewItem> mListViewItem = new ArrayList<>();
    private IScanHelper mIScanHelper;

    public ListViewAdapter(Context context, IScanHelper iScanHelper){
        this.mInflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        mIScanHelper = iScanHelper;
    }

    public void addItem(ListViewItem item){
        mListViewItem.add(item);
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mListViewItem.size();
    }

    @Override
    public Object getItem(int i) {
        return mListViewItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListViewItem.Holder holder;
        if(view == null){
            view = mInflater.inflate(R.layout.item, null);
            holder = new ListViewItem.Holder(view, mIScanHelper);
            view.setTag(holder);
        }else{
            holder = (ListViewItem.Holder) view.getTag();
        }
        mListViewItem.get(i).setToHolder(holder);
        return view;
    }
}
