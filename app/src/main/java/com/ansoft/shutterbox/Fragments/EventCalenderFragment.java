package com.ansoft.shutterbox.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ansoft.shutterbox.Data.AppData;
import com.ansoft.shutterbox.Data.ListItemData;
import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Util.Calendar.CompactCalendarView;
import com.ansoft.shutterbox.Util.Calendar.domain.CalendarDayEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventCalenderFragment extends Fragment {

    CompactCalendarView calendarView;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMMMMMMM yyyy", Locale.getDefault());
    ArrayList<ListItemData> list;
    ImageView incmnt, dcment;
    TextView calMonthYear;
    RelativeLayout rl1, rl2, rl3, rl4;
    TextView tvDate1, tvDate2, tvDate3, tvDate4, tvName1, tvName2, tvName3, tvName4;

    public EventCalenderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void insertEvents(CompactCalendarView cal) {
        Log.e("DATE", AppData.getLists().size() + "");
        for (int i = 0; i < AppData.getLists().size(); i++) {
            Log.e("DATE" + i, AppData.getLists().get(i).getEventDate() + "");
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            Date date = null;
            try {
                date = sdf.parse(AppData.getLists().get(i).getEventDate() + "");
            } catch (ParseException e) {

            }
            long timeInMillis = date.getTime();
            CalendarDayEvent events = new CalendarDayEvent(timeInMillis, getResources().getColor(R.color.colorPrimary));
            cal.addEvent(events, true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View cv = inflater.inflate(R.layout.fragment_event_calender, container, false);
        InitializeViews(cv);
        list=new ArrayList<>();
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rl1.getVisibility()==View.VISIBLE){
                    EventsFragment.ShowDescription(list.get(0), getActivity());
                }
            }
        });

        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rl2.getVisibility() == View.VISIBLE) {
                    EventsFragment.ShowDescription(list.get(1), getActivity());
                }
            }
        });

        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rl3.getVisibility()==View.VISIBLE){
                    EventsFragment.ShowDescription(list.get(2), getActivity());
                }
            }
        });

        rl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rl4.getVisibility()==View.VISIBLE){
                    EventsFragment.ShowDescription(list.get(3), getActivity());
                }
            }
        });


        calendarView.setUseThreeLetterAbbreviation(true);
        calMonthYear.setText(dateFormatForMonth.format(calendarView.getFirstDayOfCurrentMonth()));
        incmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                calendarView.showPreviousMonth();
                calMonthYear.setText(dateFormatForMonth.format(calendarView.getFirstDayOfCurrentMonth()));
                refreshFours();
            }
        });

        dcment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                calendarView.showNextMonth();
                calMonthYear.setText(dateFormatForMonth.format(calendarView.getFirstDayOfCurrentMonth()));
                refreshFours();
            }
        });


        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });
        insertEvents(calendarView);
        refreshFours();
        return cv;
    }

    public void InitializeViews(View cv){

        rl1 = (RelativeLayout) cv.findViewById(R.id.rl1);
        rl2 = (RelativeLayout) cv.findViewById(R.id.rl2);
        rl3 = (RelativeLayout) cv.findViewById(R.id.rl3);
        rl4 = (RelativeLayout) cv.findViewById(R.id.rl4);


        tvDate1 = (TextView) cv.findViewById(R.id.tvDate1);
        tvDate2 = (TextView) cv.findViewById(R.id.tvDate2);
        tvDate3 = (TextView) cv.findViewById(R.id.tvDate3);
        tvDate4 = (TextView) cv.findViewById(R.id.tvDate4);

        tvName1 = (TextView) cv.findViewById(R.id.tvName1);
        tvName2 = (TextView) cv.findViewById(R.id.tvName2);
        tvName3 = (TextView) cv.findViewById(R.id.tvName3);
        tvName4 = (TextView) cv.findViewById(R.id.tvName4);

        calendarView = (CompactCalendarView) cv.findViewById(R.id.calendar);
        incmnt = (ImageView) cv.findViewById(R.id.incrmnt);
        dcment = (ImageView) cv.findViewById(R.id.dcrmnt);
        calMonthYear = (TextView) cv.findViewById(R.id.calMonthYear);
    }
    public void refreshFours() {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyy");
        int currentMonth = Integer.parseInt(sdf.format(calendarView.getFirstDayOfCurrentMonth()).split("-")[0]);

        int[] months=new int[AppData.getLists().size()];
        for (int i=0; i<AppData.getLists().size(); i++){
            String date=AppData.getLists().get(i).getEventDate();
            int mn=Integer.parseInt(date.split("-")[0]);
            months[i]=mn;
        }
        int totalEventsInCurrentMonth=0;
        for (int i=0; i<months.length; i++ ){
            if (months[i]==currentMonth){
                totalEventsInCurrentMonth++;
            }
        }

        switch (totalEventsInCurrentMonth){
            case 0:
                rl1.setVisibility(View.GONE);
                rl2.setVisibility(View.GONE);
                rl3.setVisibility(View.GONE);
                rl4.setVisibility(View.GONE);
                break;

            case 1:
                rl2.setVisibility(View.GONE);
                rl3.setVisibility(View.GONE);
                rl4.setVisibility(View.GONE);
                rl1.setVisibility(View.VISIBLE);
                int monthsread=0;
                for (int i=0; i<AppData.getLists().size(); i++){
                    String date=AppData.getLists().get(i).getEventDate();
                    int mn=Integer.parseInt(date.split("-")[0]);

                    if (mn==currentMonth){
                        switch (monthsread){
                            case 0:
                                list.add(AppData.getLists().get(i));
                                tvDate1.setText(date.split("-")[1]);
                                tvName1.setText(AppData.getLists().get(i).getEventName());
                                monthsread++;
                                break;


                            case 1:
                                list.add(AppData.getLists().get(i));
                                tvDate2.setText(date.split("-")[1]);
                                tvName2.setText(AppData.getLists().get(i).getEventName());
                                monthsread++;
                                break;


                            case 2:
                                list.add(AppData.getLists().get(i));
                                tvDate3.setText(date.split("-")[1]);
                                tvName3.setText(AppData.getLists().get(i).getEventName());
                                monthsread++;
                                break;


                            case 3:
                                list.add(AppData.getLists().get(i));
                                tvDate4.setText(date.split("-")[1]);
                                tvName4.setText(AppData.getLists().get(i).getEventName());
                                monthsread++;
                                break;

                        }


                    }
                }
                break;

            case 2:
                rl1.setVisibility(View.VISIBLE);
                rl2.setVisibility(View.VISIBLE);
                rl3.setVisibility(View.GONE);
                rl4.setVisibility(View.GONE);
                int monthsread1=0;
                for (int i=0; i<AppData.getLists().size(); i++){
                    String date=AppData.getLists().get(i).getEventDate();
                    SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
                    int mn=Integer.parseInt(date.split("-")[0]);

                    if (mn==currentMonth){
                        switch (monthsread1){
                            case 0:
                                list.add(AppData.getLists().get(i));
                                tvDate1.setText(date.split("-")[1]);
                                tvName1.setText(AppData.getLists().get(i).getEventName());
                                monthsread1++;
                                break;


                            case 1:
                                list.add(AppData.getLists().get(i));
                                tvDate2.setText(date.split("-")[1]);
                                tvName2.setText(AppData.getLists().get(i).getEventName());
                                monthsread1++;
                                break;


                            case 2:
                                list.add(AppData.getLists().get(i));
                                tvDate3.setText(date.split("-")[1]);
                                tvName3.setText(AppData.getLists().get(i).getEventName());
                                monthsread1++;
                                break;


                            case 3:
                                list.add(AppData.getLists().get(i));
                                tvDate4.setText(date.split("-")[1]);
                                tvName4.setText(AppData.getLists().get(i).getEventName());
                                monthsread1++;
                                break;

                        }


                    }
                }
                break;

            case 3:
                rl1.setVisibility(View.VISIBLE);
                rl2.setVisibility(View.VISIBLE);
                rl3.setVisibility(View.VISIBLE);
                rl4.setVisibility(View.GONE);
                int monthsread2=0;
                for (int i=0; i<AppData.getLists().size(); i++){
                    String date=AppData.getLists().get(i).getEventDate();
                    SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
                    int mn=Integer.parseInt(date.split("-")[0]);

                    if (mn==currentMonth){
                        switch (monthsread2){
                            case 0:
                                list.add(AppData.getLists().get(i));
                                tvDate1.setText(date.split("-")[1]);
                                tvName1.setText(AppData.getLists().get(i).getEventName());
                                monthsread2++;
                                break;


                            case 1:
                                list.add(AppData.getLists().get(i));
                                tvDate2.setText(date.split("-")[1]);
                                tvName2.setText(AppData.getLists().get(i).getEventName());
                                monthsread2++;
                                break;


                            case 2:
                                list.add(AppData.getLists().get(i));
                                tvDate3.setText(date.split("-")[1]);
                                tvName3.setText(AppData.getLists().get(i).getEventName());
                                monthsread2++;
                                break;


                            case 3:
                                list.add(AppData.getLists().get(i));
                                tvDate4.setText(date.split("-")[1]);
                                tvName4.setText(AppData.getLists().get(i).getEventName());
                                monthsread2++;
                                break;

                        }


                    }
                }
                break;

            case 4:
                rl1.setVisibility(View.VISIBLE);
                rl2.setVisibility(View.VISIBLE);
                rl3.setVisibility(View.VISIBLE);
                rl4.setVisibility(View.VISIBLE);
                int monthsread3=0;
                for (int i=0; i<AppData.getLists().size(); i++){
                    String date=AppData.getLists().get(i).getEventDate();
                    SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
                    int mn=Integer.parseInt(date.split("-")[0]);

                    if (mn==currentMonth){
                        switch (monthsread3){
                            case 0:
                                list.add(AppData.getLists().get(i));
                                tvDate1.setText(date.split("-")[1]);
                                tvName1.setText(AppData.getLists().get(i).getEventName());
                                monthsread3++;
                                break;


                            case 1:
                                list.add(AppData.getLists().get(i));
                                tvDate2.setText(date.split("-")[1]);
                                tvName2.setText(AppData.getLists().get(i).getEventName());
                                monthsread3++;
                                break;


                            case 2:
                                list.add(AppData.getLists().get(i));
                                tvDate3.setText(date.split("-")[1]);
                                tvName3.setText(AppData.getLists().get(i).getEventName());
                                monthsread3++;
                                break;


                            case 3:
                                list.add(AppData.getLists().get(i));
                                tvDate4.setText(date.split("-")[1]);
                                tvName4.setText(AppData.getLists().get(i).getEventName());
                                monthsread3++;
                                break;

                        }


                    }
                }
                break;

            default:
                break;
        }

    }

}
