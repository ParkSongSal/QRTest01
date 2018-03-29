package com.example.user.qr.listviewexample;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.qr.R;

/**
 * Created by jun on 2018-03-29.
 */

public class ListViewItem {
    private String workTxt;
    private String leaveTxt;
    private String titleTxt;
    private String addressTxt;

    public String getWorkTxt() {
        return workTxt;
    }

    public void setWorkTxt(String workTxt) {
        this.workTxt = workTxt;
    }

    public String getLeaveTxt() {
        return leaveTxt;
    }

    public void setLeaveTxt(String leaveTxt) {
        this.leaveTxt = leaveTxt;
    }

    public String getTitleTxt() {
        return titleTxt;
    }

    public void setTitleTxt(String titleTxt) {
        this.titleTxt = titleTxt;
    }

    public String getAddressTxt() {
        return addressTxt;
    }

    public void setAddressTxt(String addressTxt) {
        this.addressTxt = addressTxt;
    }

    public void setToHolder(Holder holder){
        holder.getWorkTxt().setText(getWorkTxt());
        holder.getAddressTxt().setText(getAddressTxt());
        holder.getTitleTxt().setText(getTitleTxt());
        holder.getLeaveTxt().setText(getLeaveTxt());

    }
    public static class Holder{
        private Button workBtn;
        private Button leaveBtn;
        private TextView workTxt;
        private TextView leaveTxt;
        private TextView titleTxt;
        private TextView addressTxt;
        public Holder(View view, final IScanHelper helper){
            workBtn =view.findViewById(R.id.WorkBtn);
            leaveBtn =view.findViewById(R.id.LeaveBtn);

            workTxt =view.findViewById(R.id.WorkTxt);
            workBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helper.startScan(new IScanHelper.ScanResult() {
                        @Override
                        public void onScan(String scanResult) {
                            addressTxt.setText(scanResult);
                        }
                    });
                }
            });

            leaveTxt =view.findViewById(R.id.LeaveTxt);
            titleTxt =view.findViewById(R.id.TitleTxt);
            addressTxt =view.findViewById(R.id.AddressTxt);
        }

        public Button getWorkBtn() {
            return workBtn;
        }

        public Button getLeaveBtn() {
            return leaveBtn;
        }

        public TextView getWorkTxt() {
            return workTxt;
        }

        public TextView getLeaveTxt() {
            return leaveTxt;
        }

        public TextView getTitleTxt() {
            return titleTxt;
        }

        public TextView getAddressTxt() {
            return addressTxt;
        }
    }
}
