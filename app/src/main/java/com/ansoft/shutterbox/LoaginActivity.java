package com.ansoft.shutterbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ansoft.shutterbox.DB.DBTask;
import com.ansoft.shutterbox.DB.IDBTaskListener;
import com.ansoft.shutterbox.Server.ServerConfig;
import com.ansoft.shutterbox.Server.ShutterUser;
import com.ansoft.shutterbox.Util.AlertDialogs.LoadingAlertDialog;
import com.ansoft.shutterbox.Util.AlertDialogs.LoginFailedAlert;
import com.ansoft.shutterbox.Util.AlertDialogs.PurchaseAlertDialog;
import com.ansoft.shutterbox.Util.ClickEffect;
import com.ansoft.shutterbox.Util.Font;
import com.ansoft.shutterbox.Util.GMailSender;

public class LoaginActivity extends Activity {

    Button loginBtn;
    TextView loginTxt;
    EditText passwordField;
    Button registerBtn;
    TextView forgotPassword;
    EditText userNameField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loagin);
        loginTxt = (TextView)findViewById(R.id.forgotPasswordField);
        registerBtn = (Button)findViewById(R.id.registerBtn);
        forgotPassword=(TextView)findViewById(R.id.forgotPasswordField);
        ClickEffect.Opacity(forgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userNameField.getText().toString().isEmpty()){
                    Toast.makeText(LoaginActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
                }else {


                    ShutterUser us=new ShutterUser(LoaginActivity.this);
                    us.setEmail(userNameField.getText().toString());

                    final LoadingAlertDialog alert = new LoadingAlertDialog(LoaginActivity.this, "Requesting...");
                    alert.show();
                    us.forgotpassword(new ShutterUser.forgotPassword() {
                        @Override
                        public void onDone(final String response) {
                            if (!response.equalsIgnoreCase("NE")) {
                                DBTask dbt = new DBTask(new IDBTaskListener() {
                                    @Override
                                    public void onDoInBackground() {
                                        try {
                                            GMailSender sender = new GMailSender("neupane.abinash@gmail.com", "Pokhara123456");
                                            sender.sendMail("Password request for shutterbox",
                                                    "Your password is "+response,
                                                    "neupane.abinash@gmail.com",
                                                    userNameField.getText().toString());
                                        } catch (Exception e) {
                                            Log.e("SendMail", e.getMessage(), e);
                                        }
                                    }

                                    @Override
                                    public void onPostExcute() {
                                        alert.dismiss();
                                        final PurchaseAlertDialog alertDialog = new PurchaseAlertDialog(LoaginActivity.this, "Forgot Password", "We have sent an password request email to your account mail. Please check it and reset the password. \n Thanks", new PurchaseAlertDialog.OkClickCallBack() {
                                            @Override
                                            public void onBtnClick() {

                                            }
                                        });
                                        alertDialog.show();
                                    }

                                    @Override
                                    public void onPreExcute() {
                                    }
                                });

                                dbt.execute(new Void[0]);
                            }
                        }
                    });
                }
            }
        });
        loginBtn = (Button)findViewById(R.id.loginBtn);
        userNameField = (EditText)findViewById(R.id.usernameField);
        passwordField = (EditText)findViewById(R.id.passwordField);
        registerBtn.setTypeface((new Font(this)).getFont());
        loginBtn.setTypeface((new Font(this)).getFont());
        loginTxt.setTypeface((new Font(this)).getFont());
        userNameField.setTypeface((new Font(this)).getFont());
        passwordField.setTypeface((new Font(this)).getFont());
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoaginActivity.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LoadingAlertDialog alert=new LoadingAlertDialog(LoaginActivity.this, "Logging In..");
                alert.show();
                ShutterUser user=new ShutterUser(LoaginActivity.this);
                user.setEmail(userNameField.getText().toString());
                user.setPassword(passwordField.getText().toString());
                user.login(new ShutterUser.LoginCallBack() {
                    @Override
                    public void OnDone(String result) {
                        alert.dismiss();
                        if (result == ServerConfig.LOGIN_SUCCESS) {
                            Intent intent = new Intent(LoaginActivity.this, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else if (result == ServerConfig.LOGIN_INCORRECT_CREDENTIAL) {
                            LoginFailedAlert failedAlert = new LoginFailedAlert(LoaginActivity.this);
                            failedAlert.show();
                        }
                    }
                });
            }
        });
    }
}
