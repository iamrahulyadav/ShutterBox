package com.ansoft.shutterbox;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansoft.shutterbox.Adapter.NavDrawerItem;
import com.ansoft.shutterbox.Adapter.NavDrawerListAdapter;
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

public class MainActivity extends FragmentActivity {

    public static DrawerLayout mDrawerLayout;
    public static RelativeLayout notylayout;
    public static TextView totalNoty;
    public static ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private FrameLayout frame;
    ArrayList<ListItemData> data;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        notylayout = (RelativeLayout) findViewById(R.id.rlnoty);

        totalNoty = (TextView) findViewById(R.id.tvTotalNoty);
        notylayout.bringToFront();
        frame = (FrameLayout) findViewById(R.id.frame_container);
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(MainActivity.this, navDrawerItems);
        mDrawerList.setAdapter(adapter);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                frame.setTranslationX(slideOffset * drawerView.getWidth() / 2);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0, getSupportFragmentManager());
        }
        int width = getResources().getDisplayMetrics().widthPixels / 3;
        DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) mDrawerList.getLayoutParams();
        params.width = width;
        mDrawerList.setLayoutParams(params);
        refreshNotification(MainActivity.this);
    }

    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position, getSupportFragmentManager());
        }
    }

    public static void refreshNotification(Activity ac){
        ShutterUser us=new ShutterUser(ac);
        us.getNotification(new ShutterUser.notificationcallback() {
            @Override
            public void onGet(int x) {
                totalNoty.setText(x+"");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    public static void displayView(int position, FragmentManager mn) {
        // update the main content by replacing fragments
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new MyProfileFragment();
                break;
            case 2:
                fragment = new EventsFragment();
                break;
            case 3:
                fragment = new TopUpFragment();
                break;
            case 4:
                fragment = new InboxFragment();
                break;
            case 5:
                fragment = new ContactUsFragment();
                break;

            case 6:
                fragment = new SettingsFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            mn.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            mDrawerLayout.closeDrawer(mDrawerList);

            notylayout.bringToFront();
        } else {
            // error in creating fragment
            Log.e("HomeActivity", "Error in creating fragment");
        }
    }
}
