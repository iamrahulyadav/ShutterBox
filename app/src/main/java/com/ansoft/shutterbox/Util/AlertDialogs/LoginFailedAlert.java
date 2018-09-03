// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.ansoft.shutterbox.Util.AlertDialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Util.Font;

public class LoginFailedAlert
{

    Activity activity;
    Dialog builder;

    public LoginFailedAlert(Activity activity1)
    {
        activity = activity1;
    }

    public void dismiss()
    {
        builder.dismiss();
    }

    public void show()
    {
        builder = new Dialog(activity,
                R.style.CustomDialog2);

        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_login_failed,
                null);
        builder.setContentView(dialogView);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = 0.9f;
        builder.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        wlp.gravity = Gravity.TOP;
        window.setAttributes(wlp);
        Button tryAgain=(Button)dialogView.findViewById(R.id.tryAgainBtn);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        builder.show();
    }
}
