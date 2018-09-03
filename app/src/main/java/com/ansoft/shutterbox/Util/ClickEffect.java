package com.ansoft.shutterbox.Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ansoft.shutterbox.Data.AppData;
import com.ansoft.shutterbox.MainActivity;

public class ClickEffect {


    public static void ChangeNotification(TextView tv){
        int totalnoty=AppData.getTotalNotification();
        if (totalnoty==0){
            tv.setVisibility(View.GONE);
        }else{
            tv.setVisibility(View.VISIBLE);
            tv.setText(totalnoty);
        }
    }
    public static void OnBackClick(FragmentManager fm){
        MainActivity.displayView(0, fm);
    }
    public static void Opacity(final View view) {
        view.setOnTouchListener(new OnTouchListener() {

            @SuppressLint({"NewApi", "ClickableViewAccessibility"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (v == view) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        v.setAlpha(.5f);
                    } else {
                        v.setAlpha(1f);
                    }
                }
                return false;
            }
        });
    }

}
