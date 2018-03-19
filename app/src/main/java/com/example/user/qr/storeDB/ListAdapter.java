package com.example.user.qr.storeDB;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.example.user.qr.R;
import com.example.user.qr.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-02-23.
 */


public class ListAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<Store> storelist;
    int resourceId ;

    // 버튼 클릭 이벤트를 위한 Listener 인터페이스 정의.
    public interface ListBtnClickListener {
        void onListBtnClick(int position) ;
    }

    private ListBtnClickListener listBtnClickListener ;

    // ListViewBtnAdapter 생성자. 마지막에 ListBtnClickListener 추가.
    ListAdapter(Context context, int resource, ArrayList<Store> storelist, ListBtnClickListener clickListener) {


        // resource id 값 복사. (super로 전달된 resource를 참조할 방법이 없음.)
        this.resourceId = resource ;

        this.listBtnClickListener = clickListener ;
    }


    public ListAdapter(Context context, List<Store> storelist) {
        this.context = context;
        this.storelist = storelist;

    }

    @Override
    public int getCount() {
        return storelist.size();
    }

    @Override
    public Object getItem(int i) {
        return storelist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final int pos = i;
        final Context context = parent.getContext();

        View v = View.inflate(context, R.layout.item, null);

        //ImageView ImageTxt = (ImageView) v.findViewById(R.id.ImageTxt);
        TextView TitleTxt = (TextView) v.findViewById(R.id.TitleTxt);
        TextView AddressTxt = (TextView) v.findViewById(R.id.AddressTxt);
        TextView WorkTxt = (TextView) v.findViewById(R.id.WorkTxt);
        TextView LeaveTxt = (TextView) v.findViewById(R.id.LeaveTxt);

        Button WorkBtn = (Button) v.findViewById(R.id.WorkBtn);
        WorkBtn.setTag(i);
        WorkBtn.setOnClickListener(this);

        //ImageTxt.setImageBitmap(storelist.get(i).getImage());
        TitleTxt.setText(storelist.get(i).getTitle());
        AddressTxt.setText(storelist.get(i).getAddress());
        //WorkTxt.setText(storelist.get(i).getWork());
       //LeaveTxt.setText(storelist.get(i).getLeave());


        v.setTag(storelist.get(i).getTitle());
        return v;

    }


    public void onClick(View v) {
        if (this.listBtnClickListener != null)
        {
            this.listBtnClickListener.onListBtnClick((int)v.getTag());
        }
    }

}

