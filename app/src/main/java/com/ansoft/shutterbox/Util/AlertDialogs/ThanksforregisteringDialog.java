package com.ansoft.shutterbox.Util.AlertDialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.ansoft.shutterbox.HomeActivity;
import com.ansoft.shutterbox.R;

public class ThanksforregisteringDialog {

    Activity activity;
    Dialog builder;

    public ThanksforregisteringDialog(Activity activity) {
        this.activity = activity;
    }

    public void show() {
        builder = new Dialog(activity,
                R.style.CustomDialog);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_thanks_for_reg,
                null);
        builder.setContentView(dialogView);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = 0.9f;
        builder.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        wlp.gravity = Gravity.TOP;
        window.setAttributes(wlp);
        Button btn = (Button) dialogView.findViewById(R.id.tryAgainBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
                activity.finish();
            }
        });
        builder.show();
    }

    public void dismiss() {
        builder.dismiss();
    }
}
