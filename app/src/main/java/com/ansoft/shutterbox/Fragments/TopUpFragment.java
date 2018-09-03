package com.ansoft.shutterbox.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansoft.shutterbox.HomeActivity;
import com.ansoft.shutterbox.MainActivity;
import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Server.ShutterUser;
import com.ansoft.shutterbox.Util.ClickEffect;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopUpFragment extends Fragment {

    TextView tvCredit;
    ImageView fiftyCredits;
    ImageView hundredCredits;
    RelativeLayout rlback;
    public TopUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View cv=inflater.inflate(R.layout.fragment_top_up, container, false);
        rlback=(RelativeLayout)cv.findViewById(R.id.rlback);
        rlback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickEffect.OnBackClick(getFragmentManager());
            }
        });
        ClickEffect.Opacity(rlback);
        ImageView icDrawer = (ImageView)cv.findViewById(R.id.ic_drawer);
        icDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        tvCredit=(TextView)cv.findViewById(R.id.tvCredit);
        fiftyCredits=(ImageView)cv.findViewById(R.id.fiftyCredits);
        hundredCredits=(ImageView)cv.findViewById(R.id.hundredCredits);
        ClickEffect.Opacity(fiftyCredits);
        ClickEffect.Opacity(hundredCredits);
        fiftyCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ShutterUser(getActivity()).requestCredit(50);
            }

        });

        hundredCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShutterUser(getActivity()).requestCredit(100);
            }
        });
        return cv;
    }

}
