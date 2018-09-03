package com.ansoft.shutterbox.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ansoft.shutterbox.Adapter.InboxListAdapter;
import com.ansoft.shutterbox.Data.AppData;
import com.ansoft.shutterbox.Data.InboxData;
import com.ansoft.shutterbox.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InboxNewsFragment extends Fragment {

    ArrayList<InboxData> data;
    ListView listView;
    public InboxNewsFragment() {
    }
    public void AddData(){
        data=new ArrayList<>();

        for (InboxData dt: AppData.getInboxdata()){
            if (!dt.isTransaction()){
                data.add(dt);
            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cv=inflater.inflate(R.layout.fragment_inbox_news, container, false);
        AddData();
        listView=(ListView)cv.findViewById(R.id.listView2);
        listView.setAdapter(new InboxListAdapter(data, getActivity()));
        return cv;
    }

}
