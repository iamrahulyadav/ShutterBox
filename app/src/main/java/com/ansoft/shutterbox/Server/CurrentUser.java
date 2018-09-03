package com.ansoft.shutterbox.Server;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Abinash on 3/3/2016.
 */
public class CurrentUser {
    String KEY_LOGIN_PREF = "loginpref";
    String KEY_LOGGED_IN = "loggedIn";
    String KEY_FULLNAME="fullname";
    String KEY_EMAIL="email";
    String KEY_PROFILE_PHOTO="profilePhoto";
    String KEY_USERID="userId";
    String KEY_GENDER="gender";
    String KEY_PHONENUMBER="phNumber";
    String KEY_PASSWORD="password";
    String KEY_NATIONALITY="nationlaity";
    String KEY_MYCREDITS="mycredits";
    String KEY_BYEAR="byear";
    String KEY_BMONTH="bmonth";
    String KEY_BDAY="bday";
    Activity activity;
    SharedPreferences pref;

    public String getFullname() {
        return pref.getString(KEY_FULLNAME, "");
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, "");
    }

    public String getUserid() {
        return pref.getString(KEY_USERID, "");
    }

    public String getGender() {
        return pref.getString(KEY_GENDER, "");
    }

    public String getPhoneNumber(){
        return pref.getString(KEY_PHONENUMBER, "");
    }

    public String getPassword(){
        return pref.getString(KEY_PASSWORD, "");
    }
    public String getNationality() {
        return pref.getString(KEY_NATIONALITY, "");
    }

    public int getMycredits() {
        return pref.getInt(KEY_MYCREDITS, 0);
    }

    public int getByear() {
        return pref.getInt(KEY_BYEAR, 0);
    }

    public int getBmonth() {
        return pref.getInt(KEY_BMONTH, 0);
    }

    public int getBday() {
        return pref.getInt(KEY_BDAY, 0);
    }

    public String getProfilePhoto(){
        return pref.getString(KEY_PROFILE_PHOTO, "");
    }
    public void SaveUserInfo(String fn, String em, String uid, String gen, String nat, int cr, int by, int bm, int bd, String phnmber, String pw){
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(KEY_FULLNAME, fn);
        editor.putString(KEY_EMAIL, em);
        editor.putString(KEY_PHONENUMBER, phnmber);
        editor.putString(KEY_PASSWORD, pw);
        editor.putString(KEY_USERID, uid);
        editor.putString(KEY_GENDER, gen);
        editor.putString(KEY_NATIONALITY, nat);
        editor.putInt(KEY_MYCREDITS, cr);
        editor.putInt(KEY_BYEAR, by);
        editor.putInt(KEY_BMONTH, bm);
        editor.putInt(KEY_BDAY, bd);
        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.commit();
    }

    public void setFullname(String fn){
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(KEY_FULLNAME, fn);
        editor.commit();
    }

    public void setProfilePhoto(String blob){
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(KEY_PROFILE_PHOTO, blob);
        editor.commit();
    }
    public void setEmail(String fn){
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(KEY_EMAIL, fn);
        editor.commit();
    }

    public void setCredits(int fn) {
        SharedPreferences.Editor editor=pref.edit();
        editor.putInt(KEY_MYCREDITS, fn);
        editor.commit();
    }
    public void setPhoneNumber(String fn){
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(KEY_PHONENUMBER, fn);
        editor.commit();
    }
    public void setPassword(String fn){
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(KEY_PASSWORD, fn);
        editor.commit();
    }
    public CurrentUser(Activity activity) {
        this.activity = activity;
        pref = activity.getSharedPreferences(KEY_LOGIN_PREF, activity.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {
        if (pref.contains(KEY_LOGGED_IN)) {
            if (pref.getBoolean(KEY_LOGGED_IN, false)) {
                return true;
            }
        }
        return false;
    }


    public void login(){
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.commit();
    }

    public void logout(){
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(KEY_LOGGED_IN, false);
        editor.commit();
    }
}
