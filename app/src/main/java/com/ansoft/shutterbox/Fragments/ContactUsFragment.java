package com.ansoft.shutterbox.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ansoft.shutterbox.MainActivity;
import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Server.ShutterUser;
import com.ansoft.shutterbox.Util.AlertDialogs.LoadingAlertDialog;
import com.ansoft.shutterbox.Util.ClickEffect;

public class ContactUsFragment extends Fragment {

    EditText cname, cemail, cphnumber, cbody;
    Button subMitBtn;

    RelativeLayout rlback;
    ImageView icDrawer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cv=inflater.inflate(R.layout.fragment_contact_us, container, false);
        cname=(EditText)cv.findViewById(R.id.nameField);
        rlback=(RelativeLayout)cv.findViewById(R.id.rlback);
        rlback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickEffect.OnBackClick(getFragmentManager());
            }
        });

        ClickEffect.Opacity(rlback);
        cemail=(EditText)cv.findViewById(R.id.emailField);
        cphnumber=(EditText)cv.findViewById(R.id.phField);
        cbody=(EditText)cv.findViewById(R.id.commentandquestionfield);
        subMitBtn=(Button)cv.findViewById(R.id.submitBtn);
        subMitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cname.getText().toString().isEmpty()||cemail.getText().toString().isEmpty()||cphnumber.getText().toString().isEmpty()||cbody.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_LONG).show();
                }else {

                    final LoadingAlertDialog alert=new LoadingAlertDialog(getActivity(), "Saving...");
                    alert.show();
                    ShutterUser user=new ShutterUser(getActivity());
                    user.SaveComment(cname.getText().toString(), cemail.getText().toString(), cphnumber.getText().toString(), cbody.getText().toString(), new ShutterUser.CommentCallBack() {
                        @Override
                        public void OnDone() {
                            alert.dismiss();
                        }
                    });
                }
            }
        });


        icDrawer = (ImageView)cv.findViewById(R.id.ic_drawer);
        icDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        return cv;
    }
}
