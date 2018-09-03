package com.ansoft.shutterbox.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.ansoft.shutterbox.LoaginActivity;
import com.ansoft.shutterbox.HomeActivity;
import com.ansoft.shutterbox.MainActivity;
import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Server.ShutterUser;
import com.ansoft.shutterbox.Util.AlertDialogs.LoadingAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View cv = inflater.inflate(R.layout.fragment_settings, container, false);

        ImageView icDrawer = (ImageView)cv.findViewById(R.id.ic_drawer);
        icDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        Button logoutBtn=(Button)cv.findViewById(R.id.signoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingAlertDialog alertDialog=new LoadingAlertDialog(getActivity(), "Logging out...");
                alertDialog.show();
                new ShutterUser(getActivity()).logOutInBackGround(new ShutterUser.LogOutCallBack() {
                    @Override
                    public void OnDone() {
                        alertDialog.dismiss();
                        Intent intent = new Intent(getActivity(), LoaginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
            }
        });
        return cv;
    }

}
