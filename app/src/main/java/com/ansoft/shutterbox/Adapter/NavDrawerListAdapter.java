package com.ansoft.shutterbox.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansoft.shutterbox.Fragments.EventsFragment;
import com.ansoft.shutterbox.R;

import java.util.ArrayList;

/**
 * Created by Abinash on 3/6/2016.
 */
public class NavDrawerListAdapter extends BaseAdapter {
    private Activity context;
    private ArrayList<NavDrawerItem> navDrawerItems;

    public NavDrawerListAdapter(Activity activity, ArrayList arraylist)
    {
        context = activity;
        navDrawerItems = arraylist;
    }
    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater mLayoutInflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=mLayoutInflater.inflate(R.layout.drawer_list_item, null);
        }
        ImageView imgIcon=(ImageView)convertView.findViewById(R.id.icon);
        TextView txtTitle=(TextView)convertView.findViewById(R.id.title);
        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        return convertView;
    }
}
