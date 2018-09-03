package com.ansoft.shutterbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ansoft.shutterbox.Server.ServerConfig;
import com.ansoft.shutterbox.Server.ShutterUser;
import com.ansoft.shutterbox.Util.AlertDialogs.LoadingAlertDialog;
import com.ansoft.shutterbox.Util.AlertDialogs.SavingAlertDialog;
import com.ansoft.shutterbox.Util.Font;
import com.ansoft.shutterbox.Util.MonthName;

public class RegisterActivity extends Activity {

    int bDay = 0;
    int bMonth = 0;
    int bYear = 0;
    EditText confirmPasswordField;
    String confirmpassword;
    EditText dobField;
    String email = "";
    ImageView backImg;
    EditText emailField;
    EditText fullNameField;
    String fullname = "";
    String gender = "";
    EditText genderField;
    String nationality = "";
    EditText nationalityField;
    String password = "";
    EditText passwordField;
    EditText phoneNumberField;
    String phonenumber = "";
    Button registerBtn;
    TextView registerTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUI();
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoaginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });
        dobField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });
        nationalityField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] name = getResources().getStringArray(R.array.countries_array);

                final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                final AlertDialog alert = builder.create();
                builder.setTitle("Choose your country");
                ListView list = new ListView(RegisterActivity.this);
                ArrayAdapter adapter = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, name);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        nationality = name[position];
                        nationalityField.setText(nationality);
                        alert.dismiss();
                    }
                });
                alert.setView(list);
                alert.show();
            }
        });
        genderField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] name = getResources().getStringArray(R.array.gender_array);

                final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                final AlertDialog alert = builder.create();
                builder.setTitle("Select your gender");
                ListView list = new ListView(RegisterActivity.this);
                ArrayAdapter adapter = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_list_item_1, name);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        gender = name[position];
                        genderField.setText(gender);
                        alert.dismiss();
                    }
                });
                alert.setView(list);
                alert.show();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                ParseUser user=new ParseUser();
                viewToString();
                user.setEmail(email);
                user.setUsername(email);
                user.setPassword(password);
                user.put(PC.KEY_BIRTH_DAY, bDay);
                user.put(PC.KEY_BIRTH_MONTH, bMonth);
                user.put(PC.KEY_BIRTH_YEAR, bYear);
                user.put(PC.KEY_FULLNAME, fullname);
                user.put(PC.KEY_GENDER, gender);
                user.put(PC.KEY_NATIONALITY, nationality);
                user.put(PC.KEY_PHONE_NUMBER, phonenumber);
                user.put(PC.KEY_TOTAL_CREDITS, 100);
                user.put(PC.KEY_PASSWORD, Integer.parseInt(password));
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        alert.dismiss();
                        if (e == null) {
                            ThanksforregisteringDialog thanksalert = new ThanksforregisteringDialog(RegisterActivity.this);
                            thanksalert.show();
                        } else {
                            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                */

                viewToString();
                if (bDay == 0) {
                    SavingAlertDialog alert = new SavingAlertDialog(RegisterActivity.this, 0, "Please select your birthday");
                    alert.show();
                } else if (bMonth == 0) {
                    SavingAlertDialog alert = new SavingAlertDialog(RegisterActivity.this, 0, "Please select your birthday");
                    alert.show();
                } else if (bYear == 0) {
                    SavingAlertDialog alert = new SavingAlertDialog(RegisterActivity.this, 0, "Please select your birthday");
                    alert.show();
                } else if (gender == "") {
                    SavingAlertDialog alert = new SavingAlertDialog(RegisterActivity.this, 0, "Please select your gender");
                    alert.show();
                } else if (nationality == "") {
                    SavingAlertDialog alert = new SavingAlertDialog(RegisterActivity.this, 0, "Please select your nationality");
                    alert.show();
                } else if (fullname == "") {
                    SavingAlertDialog alert = new SavingAlertDialog(RegisterActivity.this, 0, "Please enter your fullname");
                    alert.show();
                } else if (email == "") {
                    SavingAlertDialog alert = new SavingAlertDialog(RegisterActivity.this, 0, "Please enter your email");
                    alert.show();
                } else if (phonenumber == "") {
                    SavingAlertDialog alert = new SavingAlertDialog(RegisterActivity.this, 0, "Please enter your phone number");
                    alert.show();
                } else {
                    ShutterUser user = new ShutterUser(RegisterActivity.this);
                    user.setBirthday(bDay + "");
                    user.setBirthmonth(bMonth + "");
                    user.setBirthyear(bYear + "");
                    user.setEmail(email + "");
                    user.setEmailVerified(0 + "");
                    user.setFirstname(fullname);
                    user.setLastname(fullname);
                    user.setGender(gender);
                    user.setMycredits(0 + "");
                    user.setNationality(nationality);
                    user.setPassword(password);
                    user.setPhonenumber(phonenumber);
                    final LoadingAlertDialog alert = new LoadingAlertDialog(RegisterActivity.this, "Signing up...");
                    alert.show();
                    user.register(new ShutterUser.RegsisterCallback() {

                        @Override
                        public void onDone(String result) {
                            alert.dismiss();
                            if (result == ServerConfig.REGISTER_FEEDBACK_SUCCESS) {

                                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intent);
                                finish();
                            } else if (result == ServerConfig.ERR_DUPLICATE_EMAIL) {
                                SavingAlertDialog alert = new SavingAlertDialog(RegisterActivity.this, 0, "Duplicate Email");
                                alert.show();
                            }
                        }
                    });
                }
            }
        });
    }

    protected Dialog onCreateDialog(int i) {
        if (i == 999) {
            return new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    bYear = year;
                    bMonth = monthOfYear + 1;
                    bDay = dayOfMonth;
                    dobField.setText((new StringBuilder()).append((new MonthName()).getName(bMonth)).append("-").append(bDay).append("-").append(bYear).append(" "));

                }
            }, 1990, 0, 1);
        } else {
            return null;
        }
    }

    public void viewToString() {
        fullname = fullNameField.getText().toString();
        email = emailField.getText().toString();
        password = passwordField.getText().toString();
        confirmpassword = confirmPasswordField.getText().toString();
        phonenumber = phoneNumberField.getText().toString();
    }

    public void initUI() {
        registerTxt = (TextView) findViewById(R.id.regTxt);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        fullNameField = (EditText) findViewById(R.id.fullNameField);
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        confirmPasswordField = (EditText) findViewById(R.id.confirmPasswordField);
        phoneNumberField = (EditText) findViewById(R.id.phoneNumberField);
        dobField = (EditText) findViewById(R.id.dobField);
        nationalityField = (EditText) findViewById(R.id.nationalityField);
        genderField = (EditText) findViewById(R.id.genderField);
        backImg = (ImageView) findViewById(R.id.backImg);
        registerBtn.setTypeface((new Font(this)).getFont());
        registerTxt.setTypeface((new Font(this)).getFont());
        fullNameField.setTypeface((new Font(this)).getFont());
        emailField.setTypeface((new Font(this)).getFont());
        passwordField.setTypeface((new Font(this)).getFont());
        confirmPasswordField.setTypeface((new Font(this)).getFont());
        phoneNumberField.setTypeface((new Font(this)).getFont());
        dobField.setTypeface((new Font(this)).getFont());
        nationalityField.setTypeface((new Font(this)).getFont());
        genderField.setTypeface((new Font(this)).getFont());
    }
}
