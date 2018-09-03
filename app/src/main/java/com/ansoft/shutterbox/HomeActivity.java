package com.ansoft.shutterbox;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ansoft.shutterbox.Adapter.NavDrawerItem;
import com.ansoft.shutterbox.Adapter.NavDrawerListAdapter;
import com.ansoft.shutterbox.Data.AppData;
import com.ansoft.shutterbox.Data.ListItemData;
import com.ansoft.shutterbox.Fragments.ContactUsFragment;
import com.ansoft.shutterbox.Fragments.EventsFragment;
import com.ansoft.shutterbox.Fragments.HomeFragment;
import com.ansoft.shutterbox.Fragments.InboxFragment;
import com.ansoft.shutterbox.Fragments.MyProfileFragment;
import com.ansoft.shutterbox.Fragments.SettingsFragment;
import com.ansoft.shutterbox.Fragments.TopUpFragment;
import com.ansoft.shutterbox.Server.ShutterUser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeActivity extends FragmentActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ShutterUser user=new ShutterUser(HomeActivity.this);
        user.getEventsInBackground(new ShutterUser.getEventsCallback() {
            @Override
            public void onDone() {
                ShutterUser us = new ShutterUser(HomeActivity.this);
                us.getMessagesInBackground(new ShutterUser.getMsgCallback() {
                    @Override
                    public void onDone() {
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

    }



    public void solution(){
        String s1="12";
        String s2="(75, 180)";
        String s3="(+90.0, -147.45)";
        String s4="(77.11112223331, 149.99999999)";
        String s5="(+90, +180)";
        String s6="(90, 180)";
        String s7="(-90.0000000, -180.0000)";
        String s8="(75, 280)";
        String s9="(+190.0, -147.45)";
        String s10="(90., 180.)";
        String s11="(-090.0000, -180.00000)";

        Pattern p=Pattern.compile("^(\\()([-+]?)([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)(\\))$");
        Matcher m = p.matcher(s1);
        if (m.matches()){
            System.out.print("\nValid");
            Toast.makeText(HomeActivity.this, "Valid", Toast.LENGTH_LONG).show();
        }
        Matcher m2 = p.matcher(s2);
        if (m.matches()){
            System.out.print("\nValid");
        }
        Matcher m3 = p.matcher(s3);
        if (m.matches()){
            System.out.print("\nValid");
        }
        Matcher m4 = p.matcher(s4);
        if (m.matches()){
            System.out.print("\nValid");
        }
        Matcher m5 = p.matcher(s5);
        if (m.matches()){
            System.out.print("\nValid");
        }
        Matcher m6 = p.matcher(s6);
        if (m.matches()){
            System.out.print("\nValid");
        }
        Matcher m7 = p.matcher(s7);
        if (m.matches()){
            System.out.print("\nValid");
        }
        Matcher m8 = p.matcher(s8);
        if (m.matches()){
            System.out.print("\nValid");
        }
        Matcher m9 = p.matcher(s9);
        if (m.matches()){
            System.out.print("\nValid");
        }
        Matcher m10 = p.matcher(s10);
        if (m.matches()){
            System.out.print("\nValid");
        }
        Matcher m11 = p.matcher(s11);
        if (m.matches()){
            System.out.print("\nValid");
        }

    }

    /*
    public void insesrtData(){

        data=new ArrayList<>();
        ListItemData dt1=new ListItemData();
        dt1.setEventCost(20);
        dt1.setEventDate("03-26-2016");
        dt1.setEventDescription("To identify personnel meeting these criteria, we used data supplied by the U.S. Armed Services Center for Unit Records Research (USASCURR) with assistance from OSAGWI and the Center for Health Promotion and Preventive Medicine (CHPPM). In brief, we augmented a database of personnel who were in ODS/DS originally compiled by the Defense Manpower Data Center (DMDC) with information linking persons to units and units to locations. From this combined database, personnel in units that were not located in theater between August 1, 1990, and July 31, 1991, were ineligible to be sampled. We erred on the side of inclusion, since exclusion was dependent on known ineligibility. Personnel who could not be linked to units, or who were in units that could not be linked to locations, remained eligible to be sampled.");
        dt1.setEventDuration("9pm to 11pm");
        dt1.setEventLocation("1600 Amphitheatre Parkway Mountain View, CA 94043");
        dt1.setEventMaxPart(50);
        dt1.setEventName("Fundraising training event");
        dt1.setEventTheme("Fund");
        dt1.setEventType("Blue Event");
        String[] images=new String[4];
        images[0]="http://aakbossevents.com/wp-content/uploads/2014/10/events-and-programs-lo-res.jpg";
        images[1]="http://myeventgroup.com/public/assets/uploads/images/MGI2OWRmYWEyNDQ5NmVjY2.jpg";
        images[2]="http://www.skiheavenly.com/~/media/heavenly/images/732x260%20header%20images/events-heavenly-header.ashx";
        images[3]="http://www.pink-lemonade.org/images/events.jpg";
        dt1.setImagesLink(images);
        data.add(dt1);


        ListItemData dt2=new ListItemData();
        dt2.setEventCost(20);
        dt2.setEventDate("03-23-2016");
        dt2.setEventDescription("To identify personnel meeting these criteria, we used data supplied by the U.S. Armed Services Center for Unit Records Research (USASCURR) with assistance from OSAGWI and the Center for Health Promotion and Preventive Medicine (CHPPM). In brief, we augmented a database of personnel who were in ODS/DS originally compiled by the Defense Manpower Data Center (DMDC) with information linking persons to units and units to locations. From this combined database, personnel in units that were not located in theater between August 1, 1990, and July 31, 1991, were ineligible to be sampled. We erred on the side of inclusion, since exclusion was dependent on known ineligibility. Personnel who could not be linked to units, or who were in units that could not be linked to locations, remained eligible to be sampled.");
        dt2.setEventDuration("9pm to 11pm");
        dt2.setEventLocation("1600 Amphitheatre Parkway Mountain View, CA 94043");
        dt2.setEventMaxPart(50);
        dt2.setEventName("Fundraising training event");
        dt2.setEventTheme("Fund");
        dt2.setEventType("Blue Event");
        String[] images2=new String[4];
        images2[3]="http://aakbossevents.com/wp-content/uploads/2014/10/events-and-programs-lo-res.jpg";
        images2[1]="http://myeventgroup.com/public/assets/uploads/images/MGI2OWRmYWEyNDQ5NmVjY2.jpg";
        images2[2]="http://www.skiheavenly.com/~/media/heavenly/images/732x260%20header%20images/events-heavenly-header.ashx";
        images2[0]="http://www.pink-lemonade.org/images/events.jpg";
        dt2.setImagesLink(images2);
        data.add(dt2);

        ListItemData dt3=new ListItemData();
        dt3.setEventCost(20);
        dt3.setEventDate("03-29-2016");
        dt3.setEventDescription("To identify personnel meeting these criteria, we used data supplied by the U.S. Armed Services Center for Unit Records Research (USASCURR) with assistance from OSAGWI and the Center for Health Promotion and Preventive Medicine (CHPPM). In brief, we augmented a database of personnel who were in ODS/DS originally compiled by the Defense Manpower Data Center (DMDC) with information linking persons to units and units to locations. From this combined database, personnel in units that were not located in theater between August 1, 1990, and July 31, 1991, were ineligible to be sampled. We erred on the side of inclusion, since exclusion was dependent on known ineligibility. Personnel who could not be linked to units, or who were in units that could not be linked to locations, remained eligible to be sampled.");
        dt3.setEventDuration("9pm to 11pm");
        dt3.setEventLocation("1600 Amphitheatre Parkway Mountain View, CA 94043");
        dt3.setEventMaxPart(50);
        dt3.setEventName("Fundraising training event");
        dt3.setEventTheme("Fund");
        dt3.setEventType("Blue Event");
        String[] images3=new String[4];
        images3[1]="http://aakbossevents.com/wp-content/uploads/2014/10/events-and-programs-lo-res.jpg";
        images3[0]="http://myeventgroup.com/public/assets/uploads/images/MGI2OWRmYWEyNDQ5NmVjY2.jpg";
        images3[2]="http://www.skiheavenly.com/~/media/heavenly/images/732x260%20header%20images/events-heavenly-header.ashx";
        images3[3]="http://www.pink-lemonade.org/images/events.jpg";
        dt3.setImagesLink(images3);
        data.add(dt3);

        ListItemData dt4=new ListItemData();
        dt4.setEventCost(20);
        dt4.setEventDate("03-10-2016");
        dt4.setEventDescription("To identify personnel meeting these criteria, we used data supplied by the U.S. Armed Services Center for Unit Records Research (USASCURR) with assistance from OSAGWI and the Center for Health Promotion and Preventive Medicine (CHPPM). In brief, we augmented a database of personnel who were in ODS/DS originally compiled by the Defense Manpower Data Center (DMDC) with information linking persons to units and units to locations. From this combined database, personnel in units that were not located in theater between August 1, 1990, and July 31, 1991, were ineligible to be sampled. We erred on the side of inclusion, since exclusion was dependent on known ineligibility. Personnel who could not be linked to units, or who were in units that could not be linked to locations, remained eligible to be sampled.");
        dt4.setEventDuration("9pm to 11pm");
        dt4.setEventLocation("1600 Amphitheatre Parkway Mountain View, CA 94043");
        dt4.setEventMaxPart(50);
        dt4.setEventName("Fundraising training event");
        dt4.setEventTheme("Fund");
        dt4.setEventType("Blue Event");
        String[] images4=new String[4];
        images4[2]="http://aakbossevents.com/wp-content/uploads/2014/10/events-and-programs-lo-res.jpg";
        images4[1]="http://myeventgroup.com/public/assets/uploads/images/MGI2OWRmYWEyNDQ5NmVjY2.jpg";
        images4[0]="http://www.skiheavenly.com/~/media/heavenly/images/732x260%20header%20images/events-heavenly-header.ashx";
        images4[3]="http://www.pink-lemonade.org/images/events.jpg";
        dt4.setImagesLink(images4);
        data.add(dt4);

        AppData.setLists(data);

    }
    */
}
