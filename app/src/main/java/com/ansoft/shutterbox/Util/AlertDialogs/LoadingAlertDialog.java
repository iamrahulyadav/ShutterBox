// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.ansoft.shutterbox.Util.AlertDialogs;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Server.Myuser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadingAlertDialog
{

    Activity activity;
    Dialog builder;
    String loadString;

    public LoadingAlertDialog(Activity activity1, String s)
    {
        activity = activity1;
        loadString = s;
    }

    public void dismiss()
    {
        builder.dismiss();
    }

    public void show()
    {

        Myuser user = new Myuser();
        user.setEmail("email");
        user.setFirstname("abinash");
        user.setLastname("neupane");
        user.setPassword("password");
        user.setPhonenumber("phonenumber");
        user.Signup(new Myuser.SignupCallBack() {
            @Override
            public void OnDone() {
                Toast.makeText(activity, "Successfully signed up!", Toast.LENGTH_LONG).show();
            }
        });
        builder = new Dialog(activity,
                R.style.CustomDialog2);

        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_loading_message,
                null);
        builder.setContentView(dialogView);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = 0.9f;
        builder.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        wlp.gravity = Gravity.TOP;
        window.setAttributes(wlp);
        TextView loadText=(TextView)dialogView.findViewById(R.id.loadingTxt);
        loadText.setText(loadString);
        builder.show();
        String[] arr;
        for (int i=0; i<arr.length; i++){
            Pattern p = Pattern.compile("%(.*?)%");
            Matcher m = p.matcher(arr[i]);
            if (m.matches()){

            }
        }
        Pattern p = Pattern.compile("%(.*?)%");
        Matcher m = p.matcher(str);
        if (m.matches()){

        }

    }
}
