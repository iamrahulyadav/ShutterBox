package com.ansoft.shutterbox.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansoft.shutterbox.HomeActivity;
import com.ansoft.shutterbox.MainActivity;
import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Server.CurrentUser;
import com.ansoft.shutterbox.Util.Font;

public class HomeFragment extends Fragment {

    EditText creditText;
    ImageView icDrawer;
    TextView icHello;
    EditText nameField;
    Button topupBtn;
    ImageView profilePhoto;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cv=inflater.inflate(R.layout.fragment_home, container, false);
        icDrawer = (ImageView)cv.findViewById(R.id.ic_drawer);
        icDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        profilePhoto = (ImageView) cv.findViewById(R.id.profilePhoto);
        if (!new CurrentUser(getActivity()).getProfilePhoto().isEmpty()){
            byte[] decodedString = Base64.decode(new CurrentUser(getActivity()).getProfilePhoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profilePhoto.setImageBitmap(decodedByte);
        }
        icHello = (TextView)cv.findViewById(R.id.tvHello);
        nameField = (EditText)cv.findViewById(R.id.nameField);
        creditText = (EditText)cv.findViewById(R.id.creditField);
        topupBtn = (Button)cv.findViewById(R.id.topupBtn);
        icHello.setTypeface((new Font(getActivity())).getFont());
        nameField.setTypeface((new Font(getActivity())).getFont());
        creditText.setTypeface((new Font(getActivity())).getFont());
        topupBtn.setTypeface((new Font(getActivity())).getFont());
        nameField.setText(new CurrentUser(getActivity()).getFullname());
        creditText.setText(new CurrentUser(getActivity()).getMycredits()+" Credits");
        topupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.displayView(3, getFragmentManager());
            }
        });
        return cv;
    }
}
