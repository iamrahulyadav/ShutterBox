package com.ansoft.shutterbox.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import android.os.Handler;
import android.widget.Toast;

import com.ansoft.shutterbox.Data.ListItemData;
import com.ansoft.shutterbox.HomeActivity;
import com.ansoft.shutterbox.MainActivity;
import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Server.CurrentUser;
import com.ansoft.shutterbox.Server.ShutterUser;
import com.ansoft.shutterbox.Util.AlertDialogs.PurchaseAlertDialog;
import com.ansoft.shutterbox.Util.ClickEffect;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

public class EventsFragment extends Fragment {

    public static ViewPager mViewPager;
    LinearLayout tab1, tab2;
    ImageView imgtab1, imgtab2;
    static String password = "";
    public static LinearLayout tabs;
    public static ScrollView descriptionInScrollView;

    public static TextView tvDescription, tvBacktoList, tvDate, tvDuration, tvTheme, tvMaxPart, tvCost, tvLocation;
    TextView tvtab1, tvtab2;
    public static Button registerBtn;
    public static SliderLayout imageSlider;
    public static LinearLayout creditlin;
    public static TextView tvTotalCredits, tvCost2, backTxt;
    public static EditText pw1, pw2, pw3, pw4;
    public static RelativeLayout costLayout;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

    }

    public static void ShowNormal() {
        creditlin.setBackgroundResource(R.drawable.bg_cost_default);
        tabs.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
        descriptionInScrollView.setVisibility(View.GONE);
        costLayout.setVisibility(View.GONE);
    }

    public static void ShowDescription(final ListItemData data, final Activity ac) {
        costLayout.setVisibility(View.GONE);
        imageSlider.removeAllSliders();
        tabs.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
        descriptionInScrollView.setVisibility(View.VISIBLE);
        for (int i = 0; i < data.getImagesLink().length; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(ac);
            sliderView.image(data.getImagesLink()[i]);
            imageSlider.addSlider(sliderView);
        }
        tvDescription.setText(data.getEventDescription());
        tvDate.setText(data.getEventDate());
        tvDuration.setText(data.getEventDuration());
        tvTheme.setText(data.getEventTheme());
        tvMaxPart.setText(data.getEventMaxPart() + "");
        tvCost.setText(data.getEventCost() + " Credits");
        tvLocation.setText(data.getEventLocation());
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCost(data, ac);
            }
        });


    }

    public static void showKeyboard(Activity ac) {
        InputMethodManager imm = (InputMethodManager) ac.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void hideKeyboard(EditText ad, Activity ac) {
        InputMethodManager imm = (InputMethodManager) ac.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ad.getWindowToken(), 0);
    }

    public static void showCost(final ListItemData data, final Activity activity) {
        showKeyboard(activity);
        costLayout.setVisibility(View.VISIBLE);
        imageSlider.removeAllSliders();
        tabs.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
        tvTotalCredits.setText(new CurrentUser(activity).getMycredits() + "");
        descriptionInScrollView.setVisibility(View.GONE);
        final String pass[] = (new CurrentUser(activity).getPassword() + "").split("(?!^)");
        pw1.setEnabled(true);
        pw2.setEnabled(false);
        pw3.setEnabled(false);
        pw4.setEnabled(false);
        backTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(pw1, activity);
                ShowNormal();
                password = "";
                pw1.setText("");
                pw2.setText("");
                pw3.setText("");
                pw4.setText("");
            }
        });
        pw1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    pw1.setEnabled(false);
                    pw2.setEnabled(true);
                    pw1.clearFocus();
                    pw2.requestFocus();
                    hideKeyboard(pw1, activity);
                    showKeyboard(activity);

            }
        });

        pw2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    pw2.setEnabled(false);
                    pw3.setEnabled(true);
                    pw2.clearFocus();
                    pw3.requestFocus();
                    hideKeyboard(pw2, activity);
                    showKeyboard(activity);
            }
        });

        pw3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    pw3.setEnabled(false);
                    pw4.setEnabled(true);
                    pw3.clearFocus();
                    pw4.requestFocus();
                    hideKeyboard(pw3, activity);
                    showKeyboard(activity);
            }
        });

        pw4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equalsIgnoreCase(pass[3])) {
                    creditlin.setBackgroundResource(R.drawable.bg_cost);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            new ShutterUser(activity).incrementCredit(-data.getEventCost());
                            final PurchaseAlertDialog alert = new PurchaseAlertDialog(activity, "Your balance is credit 50", "Thank you and we will send you the message shortly", new PurchaseAlertDialog.OkClickCallBack() {
                                @Override
                                public void onBtnClick() {
                                    ShowNormal();
                                    hideKeyboard(pw1, activity);
                                    hideKeyboard(pw2, activity);
                                    hideKeyboard(pw3, activity);
                                    hideKeyboard(pw4, activity);
                                    password = "";
                                    pw1.setText("");
                                    pw2.setText("");
                                    pw3.setText("");
                                    pw4.setText("");
                                }
                            });
                            alert.show();
                        }
                    }, 1000);
                }else{
                    Toast.makeText(activity, "Wrong password", Toast.LENGTH_LONG).show();
                }
            }
        });
        tvCost2.setText(data.getEventCost() + "");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View cv = inflater.inflate(R.layout.fragment_events, container, false);
        InitializeView(cv);
        ImageView icDrawer = (ImageView) cv.findViewById(R.id.ic_drawer);
        icDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new EventListFragment();

                    case 1:
                        return new EventCalenderFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        tvBacktoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowNormal();
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
                        imgtab1.setImageResource(R.drawable.ic_list_selected);
                        imgtab2.setImageResource(R.drawable.ic_calender_default);
                        tvtab1.setTextColor(getResources().getColor(R.color.colorPrimary));
                        tvtab2.setTextColor(getResources().getColor(R.color.white));
                        break;

                    case 1:
                        tab2.setBackgroundResource(R.drawable.bg_event_selected);
                        tab1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        imgtab1.setImageResource(R.drawable.ic_list_default);
                        imgtab2.setImageResource(R.drawable.ic_calender_selected);
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
                imgtab1.setImageResource(R.drawable.ic_list_selected);
                imgtab2.setImageResource(R.drawable.ic_calender_default);
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
                imgtab1.setImageResource(R.drawable.ic_list_default);
                imgtab2.setImageResource(R.drawable.ic_calender_selected);
                tvtab1.setTextColor(getResources().getColor(R.color.white));
                tvtab2.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        return cv;
    }

    public void InitializeView(View cv) {
        creditlin = (LinearLayout) cv.findViewById(R.id.creditlin);
        registerBtn = (Button) cv.findViewById(R.id.registerBtn);
        costLayout = (RelativeLayout) cv.findViewById(R.id.costLayout);
        tvTotalCredits = (TextView) cv.findViewById(R.id.tvTotalCredit);
        tvCost2 = (TextView) cv.findViewById(R.id.tvCost2);
        backTxt = (TextView) cv.findViewById(R.id.backtext2);
        pw1 = (EditText) cv.findViewById(R.id.pw1);
        pw2 = (EditText) cv.findViewById(R.id.pw2);
        pw3 = (EditText) cv.findViewById(R.id.pw3);
        pw4 = (EditText) cv.findViewById(R.id.pw4);
        imageSlider = (SliderLayout) cv.findViewById(R.id.ImageSlider);
        tvDescription = (TextView) cv.findViewById(R.id.tvDescription);
        tvBacktoList = (TextView) cv.findViewById(R.id.textView2);
        tvDate = (TextView) cv.findViewById(R.id.tvDate);
        tvDuration = (TextView) cv.findViewById(R.id.tvDuration);
        tvTheme = (TextView) cv.findViewById(R.id.tvTheme);
        tvMaxPart = (TextView) cv.findViewById(R.id.tvMaxPart);
        tvCost = (TextView) cv.findViewById(R.id.tvCost);
        tvLocation = (TextView) cv.findViewById(R.id.tvLocation);
        tabs = (LinearLayout) cv.findViewById(R.id.linearLayout);
        descriptionInScrollView = (ScrollView) cv.findViewById(R.id.ScrollViewDesc);
        mViewPager = (ViewPager) cv.findViewById(R.id.viewpager);
        tab1 = (LinearLayout) cv.findViewById(R.id.tab1);
        tab2 = (LinearLayout) cv.findViewById(R.id.tab2);
        imgtab1 = (ImageView) cv.findViewById(R.id.imgtab1);
        imgtab2 = (ImageView) cv.findViewById(R.id.imgtab2);
        tvtab1 = (TextView) cv.findViewById(R.id.tvtab1);
        tvtab2 = (TextView) cv.findViewById(R.id.tvtab2);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
