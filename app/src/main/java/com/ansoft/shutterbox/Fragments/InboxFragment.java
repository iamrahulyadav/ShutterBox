package com.ansoft.shutterbox.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ansoft.shutterbox.HomeActivity;
import com.ansoft.shutterbox.MainActivity;
import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Util.ClickEffect;

/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment extends Fragment {

    ViewPager mViewPager;
    LinearLayout tab1, tab2;
    TextView tvtab1, tvtab2;
    public InboxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View cv=inflater.inflate(R.layout.fragment_inbox, container, false);
        ImageView icDrawer = (ImageView)cv.findViewById(R.id.ic_drawer);
        icDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        mViewPager = (ViewPager) cv.findViewById(R.id.viewpager);
        tab1 = (LinearLayout) cv.findViewById(R.id.tab1);
        tab2 = (LinearLayout) cv.findViewById(R.id.tab2);
        tvtab1 = (TextView) cv.findViewById(R.id.tvtab1);
        tvtab2 = (TextView) cv.findViewById(R.id.tvtab2);

        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new InboxTransactionFragment();

                    case 1:
                        return new InboxNewsFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tab1.setBackgroundResource(R.drawable.bg_event_selected);
                        tab2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        tvtab1.setTextColor(getResources().getColor(R.color.colorPrimary));
                        tvtab2.setTextColor(getResources().getColor(R.color.white));
                        break;

                    case 1:
                        tab2.setBackgroundResource(R.drawable.bg_event_selected);
                        tab1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        tvtab1.setTextColor(getResources().getColor(R.color.white));
                        tvtab2.setTextColor(getResources().getColor(R.color.colorPrimary));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ClickEffect.Opacity(tab1);
        ClickEffect.Opacity(tab2);
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
                tab1.setBackgroundResource(R.drawable.bg_event_selected);
                tab2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                tvtab1.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvtab2.setTextColor(getResources().getColor(R.color.white));
            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
                tab2.setBackgroundResource(R.drawable.bg_event_selected);
                tab1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                tvtab1.setTextColor(getResources().getColor(R.color.white));
                tvtab2.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        return cv;
    }

}
