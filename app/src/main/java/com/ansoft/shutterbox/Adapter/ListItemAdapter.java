package com.ansoft.shutterbox.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ansoft.shutterbox.Data.ListItemData;
import com.ansoft.shutterbox.Fragments.EventsFragment;
import com.ansoft.shutterbox.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Abinash on 3/7/2016.
 */
public class ListItemAdapter extends BaseAdapter {
    ArrayList<ListItemData> data;
    Activity activity;

    public ListItemAdapter(ArrayList<ListItemData> data, Activity activity) {
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
        cv=activity.getLayoutInflater().inflate(R.layout.item_list, parent, false);
        final ListItemData item=data.get(position);
        ImageView displayImg=(ImageView)cv.findViewById(R.id.displayImg);
        TextView tvEventType=(TextView)cv.findViewById(R.id.eventType);
        TextView tvEventName=(TextView)cv.findViewById(R.id.eventName);
        TextView tvEventDate=(TextView)cv.findViewById(R.id.eventDate);
        TextView tvEventDuration=(TextView)cv.findViewById(R.id.eventDuration);
        LinearLayout linlay=(LinearLayout)cv.findViewById(R.id.linlay);
        Picasso.with(activity).load(item.getImagesLink()[0]).fit().centerCrop().placeholder(R.drawable.sample_img).into(displayImg);
        tvEventType.setText(item.getEventType());
        tvEventName.setText(item.getEventName());
        tvEventDate.setText(item.getEventDate());
        tvEventDuration.setText(item.getEventDuration());
        if (position%2==0){
            linlay.setBackgroundColor(activity.getResources().getColor(R.color.list_green_bg));
        }else {

            linlay.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimary));
        }

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventsFragment.ShowDescription(item, activity);
            }
        });
        return cv;
    }
}
