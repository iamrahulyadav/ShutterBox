// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.ansoft.shutterbox.Util.AlertDialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansoft.shutterbox.R;

public class SavingAlertDialog
{

    Activity activity;
    Dialog builder;
    int x;
    String msg;

    public SavingAlertDialog(Activity activity, int x, String msg) {
        this.activity = activity;
        this.x = x;
        this.msg = msg;
    }

    public void dismiss()
    {
        builder.dismiss();
    }

    public void show()
    {
        builder = new Dialog(activity,
                R.style.CustomDialog3);

        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_saving_message,
                null);
        builder.setContentView(dialogView);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = 0.0f;
        builder.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
        TextView loadText=(TextView)dialogView.findViewById(R.id.loadingTxt);
        ImageView progressImg=(ImageView)dialogView.findViewById(R.id.progressImg);
        loadText.setText(msg);
        if (x==0){
            progressImg.setVisibility(View.GONE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    builder.dismiss();
                }
            }, 1000);
        }else {
            progressImg.setVisibility(View.VISIBLE);
        }
        builder.show();
    }
}
