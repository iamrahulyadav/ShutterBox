package com.ansoft.shutterbox.Adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansoft.shutterbox.Data.InboxData;
import com.ansoft.shutterbox.MainActivity;
import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Server.ShutterUser;
import com.ansoft.shutterbox.Util.AlertDialogs.PurchaseAlertDialog;

import java.util.ArrayList;

/**
 * Created by Abinash on 3/21/2016.
 */
public class InboxListAdapter extends BaseAdapter{
    ArrayList<InboxData> data;
    Activity activity;

    public InboxListAdapter(ArrayList<InboxData> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View cv, ViewGroup parent) {
        cv=activity.getLayoutInflater().inflate(R.layout.item_list_msg, parent, false);
        final TextView tvMsgTitle=(TextView)cv.findViewById(R.id.msgTitle);
        final TextView tvMsgBody=(TextView)cv.findViewById(R.id.msgBody);
        final ImageView dropdownBtn=(ImageView)cv.findViewById(R.id.dropdownImg);
        final InboxData item=data.get(position);
        tvMsgTitle.setText(item.getMsgTitle());
        tvMsgBody.setText(item.getMsgBody());
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("Getting Msgs FORM ADAPTER", item.getMsgId());
                new ShutterUser(activity).setRead(item.getMsgId());
                if (!item.isRead()) {
                    item.setIsRead(true);
                    MainActivity.refreshNotification(activity);
                }
                if (tvMsgBody.getVisibility() == View.GONE) {
                    dropdownBtn.setImageResource(R.drawable.inbox_arrow_up);
                    tvMsgBody.setVisibility(View.VISIBLE);
                    tvMsgTitle.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    dropdownBtn.setImageResource(R.drawable.inbox_arrow);
                    tvMsgBody.setVisibility(View.GONE);
                    tvMsgTitle.setTypeface(Typeface.DEFAULT);
                }
            }
        });
        return cv;
    }

}
