package com.ansoft.shutterbox.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ansoft.shutterbox.Adapter.ListItemAdapter;
import com.ansoft.shutterbox.Data.AppData;
import com.ansoft.shutterbox.Data.ListItemData;
import com.ansoft.shutterbox.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {



    ListView listView;
    ListItemAdapter adapter;

    public EventListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cv=inflater.inflate(R.layout.fragment_event_list, container, false);
        listView=(ListView)cv.findViewById(R.id.listView);
        adapter=new ListItemAdapter(AppData.getLists(), getActivity());
        listView.setAdapter(adapter);
        return cv;
    }

}
