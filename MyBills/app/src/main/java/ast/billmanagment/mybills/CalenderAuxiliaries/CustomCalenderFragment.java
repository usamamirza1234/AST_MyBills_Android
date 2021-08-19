package ast.billmanagment.mybills.CalenderAuxiliaries;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

import ast.billmanagment.mybills.HomeAuxiliaries.FeaturedOutletsViewPagerAdapter;
import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;

public class CustomCalenderFragment extends Fragment implements View.OnClickListener {

    public GregorianCalendar currentMonth, nextMonth, currentItemMonth, itemNextMonth;// calendar instances.

    public CalendarAdapter currentMonthAdapter;// adapter instance
    public CalendarNextMonthAdapter nextMonthAdapter;// adapter instance
    public Handler currentMonthHandler;// for grabbing some event values for showing the dot
    public Handler nextMonthHandler;// for grabbing some event values for showing the dot
    // marker.
    public ArrayList CurrentItems; // container to store calendar items which
    public ArrayList itemsNextMonth; // container to store calendar items which
    // needs showing the event marker


    IBadgeUpdateListener mBadgeUpdateListener;

    private FeaturedOutletsViewPagerAdapter featuredOutletsViewPagerAdapter;
    View frg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        frg = inflater.inflate(R.layout.fragment_calender_custom, container, false);

        init();
        bindviews(frg);

        initializeCalendarLib(frg);
        initializeNextMonthCalendarLib(frg);


        return frg;
    }

    private void initializeNextMonthCalendarLib(View frg) {
        Locale.setDefault(Locale.US);
        nextMonth = (GregorianCalendar) GregorianCalendar.getInstance();
        if (nextMonth.get(GregorianCalendar.MONTH) == nextMonth.getActualMaximum(GregorianCalendar.MONTH)) {
            nextMonth.set((nextMonth.get(GregorianCalendar.YEAR) + 1), nextMonth.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            nextMonth.set(GregorianCalendar.MONTH, nextMonth.get(GregorianCalendar.MONTH) + 1);
        }

        itemNextMonth = (GregorianCalendar) nextMonth.clone();

        itemsNextMonth = new ArrayList();

        ArrayList<String> lstImportantDates = new ArrayList();


        lstImportantDates.add("2021-08-01");
        lstImportantDates.add("2021-08-02");
        lstImportantDates.add("2021-08-03");
        lstImportantDates.add("2021-08-07");
        lstImportantDates.add("2021-08-09");
        lstImportantDates.add("2021-08-14");
        lstImportantDates.add("2021-08-30");
        lstImportantDates.add("2021-08-31");
        lstImportantDates.add("2021-09-01");
        lstImportantDates.add("2021-09-07");
        lstImportantDates.add("2021-09-14");
        lstImportantDates.add("2021-09-15");
        lstImportantDates.add("2021-09-16");
        lstImportantDates.add("2021-09-17");
        lstImportantDates.add("2021-09-18");
        lstImportantDates.add("2021-09-25");
        lstImportantDates.add("2021-09-30");


        ArrayList<String> newlstImportantDates = new ArrayList();

        for (int i=0; i<lstImportantDates.size();i++)
        {
            String strPhoneNumber = String.valueOf(lstImportantDates.get(i).toString());
            strPhoneNumber = "" + strPhoneNumber.substring(5);
            newlstImportantDates.add(strPhoneNumber);
            Log.d("BackTrackingWorking", "strPhoneNumber "+ strPhoneNumber);
        }

        nextMonthAdapter = new CalendarNextMonthAdapter(getContext(), nextMonth, newlstImportantDates);

        GridView gridview1 = (GridView) frg.findViewById(R.id.gridview1);
        gridview1.setAdapter(nextMonthAdapter);

        nextMonthHandler = new Handler();
        nextMonthHandler.post(calendarNextMonthUpdater);

        TextView title1 = (TextView) frg.findViewById(R.id.title1);
        title1.setText(android.text.format.DateFormat.format("MMMM yyyy", nextMonth));
    }

    private void initializeCalendarLib(View frg) {



        Locale.setDefault(Locale.US);
        currentMonth = (GregorianCalendar) GregorianCalendar.getInstance();
        currentItemMonth = (GregorianCalendar) currentMonth.clone();
        CurrentItems = new ArrayList();

        ArrayList<String> lstImportantDates = new ArrayList();

        lstImportantDates.add("2021-08-30");
        lstImportantDates.add("2021-08-31");
        lstImportantDates.add("2021-09-1");
        lstImportantDates.add("2021-09-2");
        lstImportantDates.add("2021-09-3");
        lstImportantDates.add("2021-09-4");
        lstImportantDates.add("2021-09-5");
        lstImportantDates.add("2021-09-6");
        lstImportantDates.add("2021-09-7");
        lstImportantDates.add("2021-09-8");
        lstImportantDates.add("2021-09-9");
        lstImportantDates.add("2021-09-10");
        lstImportantDates.add("2021-09-11");
        lstImportantDates.add("2021-09-12");
        lstImportantDates.add("2021-09-13");
        lstImportantDates.add("2021-09-14");
        lstImportantDates.add("2021-09-15");
        lstImportantDates.add("2021-09-16");
        lstImportantDates.add("2021-09-17");
        lstImportantDates.add("2021-09-18");
        lstImportantDates.add("2021-09-25");
        lstImportantDates.add("2021-09-30");

        currentMonthAdapter = new CalendarAdapter(getContext(), currentMonth, lstImportantDates,true);

        GridView gridview = (GridView) frg.findViewById(R.id.gridview);
        gridview.setAdapter(currentMonthAdapter);

        currentMonthHandler = new Handler();
        currentMonthHandler.post(calendarUpdater);

        TextView title = (TextView) frg.findViewById(R.id.title);
        title.setText(android.text.format.DateFormat.format("MMMM yyyy", currentMonth));


//        if (nextMonth.get(GregorianCalendar.MONTH) == nextMonth.getActualMaximum(GregorianCalendar.MONTH)) {
//            nextMonth.set((nextMonth.get(GregorianCalendar.YEAR) + 1), nextMonth.getActualMinimum(GregorianCalendar.MONTH), 1);
//        } else {
//            nextMonth.set(GregorianCalendar.MONTH, nextMonth.get(GregorianCalendar.MONTH) + 1);
//        }


//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView parent, View v,
//                                    int position, long id) {
//
//                ((CalendarAdapter) parent.getAdapter()).setSelected(v);
//                String selectedGridDate = CalendarAdapter.dayString
//                        .get(position).toString();
//                String[] separatedTime = selectedGridDate.split("-");
//                String gridvalueString = separatedTime[2].replaceFirst("^0*",
//                        "");// taking last part of date. ie; 2 from 2012-12-02.
//                int gridvalue = Integer.parseInt(gridvalueString);
//                // navigate to next or previous month on clicking offdays.
//                if ((gridvalue > 10) && (position < 8)) {
//                    setPreviousMonth();
//                    refreshCalendar();
//                } else if ((gridvalue < 7) && (position > 28)) {
//                    setNextMonth();
//                    refreshCalendar();
//                }
//                ((CalendarAdapter) parent.getAdapter()).setSelected(v);
//
//                showToast(selectedGridDate);
//
//            }
//        });
    }

    private void init() {
        setBottomBar();


    }

    private void bindviews(View view) {
////        // checking whether the day is in current month or not.
////        if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay))
////        {
////            // setting offdays to white color.
////            dayView.setTextColor(mContext.getResources().getColor(R.color.light_gray));
////            dayView.setClickable(false);
////            dayView.setFocusable(false);
////        }
////
////        else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
////            dayView.setTextColor(mContext.getResources().getColor(R.color.light_gray));
////            dayView.setClickable(false);
////            dayView.setFocusable(false);
////        }
////
////
////        else {
////            // setting curent month's days in blue color.
////            if (dayString.get(position).equals(curentDateString)) {
////                dayView.setTextColor(mContext.getResources().getColor(R.color.blue));
////            } else {
////                dayView.setTextColor(mContext.getResources().getColor(R.color.white));
////            }
////        }
////
////        if (dayString.get(position).equals(curentDateString)) {
////            setSelected(v);
////            previousView = v;
////        } else {
////            v.setBackgroundColor(mContext.getResources().getColor(R.color.thm_yellow_1));
////        }
////        dayView.setText(gridvalue);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


        }
    }

    void setBottomBar() {

        try {
            mBadgeUpdateListener = (IBadgeUpdateListener) getActivity();
        } catch (ClassCastException castException) {
            castException.printStackTrace(); // The activity does not implement the listener
        }
        if (getActivity() != null && isAdded()) {
            mBadgeUpdateListener.setToolbarState(AppConstt.ToolbarState.TOOLBAR_BACK_HIDDEN);
            mBadgeUpdateListener.setHeaderTitle(getString(R.string.frg_important_dates));

        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isHidden()) {
            setBottomBar();
        }
    }

//    protected void setNextMonth() {
//        if (month.get(GregorianCalendar.MONTH) == month.getActualMaximum(GregorianCalendar.MONTH)) {
//            month.set((month.get(GregorianCalendar.YEAR) + 1), month.getActualMinimum(GregorianCalendar.MONTH), 1);
//        } else {
//            month.set(GregorianCalendar.MONTH, month.get(GregorianCalendar.MONTH) + 1);
//        }
//
//    }
//
//    protected void setPreviousMonth() {
//        if (month.get(GregorianCalendar.MONTH) == month
//                .getActualMinimum(GregorianCalendar.MONTH)) {
//            month.set((month.get(GregorianCalendar.YEAR) - 1),
//                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
//        } else {
//            month.set(GregorianCalendar.MONTH,
//                    month.get(GregorianCalendar.MONTH) - 1);
//        }
//
//    }

//    protected void showToast(String string) {
//        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
//
//    }

//    public void refreshCalendar() {
//        TextView title = (TextView) frg.findViewById(R.id.title);
//
//        adapter.refreshDays();
//        adapter.notifyDataSetChanged();
//        handler.post(calendarUpdater); // generate some calendar items
//
//        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
//    }

    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            CurrentItems.clear();

            // Print dates of the current week
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String itemvalue;
            for (int i = 0; i < 7; i++) {
                itemvalue = df.format(currentItemMonth.getTime());
                currentItemMonth.add(GregorianCalendar.DATE, 1);
                CurrentItems.add("2012-09-12");
                CurrentItems.add("2012-10-07");
                CurrentItems.add("2012-10-15");
                CurrentItems.add("2012-10-20");
                CurrentItems.add("2012-11-30");
                CurrentItems.add("2012-11-28");
            }

            currentMonthAdapter.setItems(CurrentItems);
            currentMonthAdapter.notifyDataSetChanged();
        }
    };


    public Runnable calendarNextMonthUpdater = new Runnable() {

        @Override
        public void run() {
            itemsNextMonth.clear();

            // Print dates of the current week
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String itemvalue;
            for (int i = 0; i < 7; i++) {
                itemvalue = df.format(itemNextMonth.getTime());
                itemNextMonth.add(GregorianCalendar.DATE, 1);
                itemsNextMonth.add("2012-09-12");
                itemsNextMonth.add("2012-10-07");
                itemsNextMonth.add("2012-10-15");
                itemsNextMonth.add("2012-10-20");
                itemsNextMonth.add("2012-11-30");
                itemsNextMonth.add("2012-11-28");
            }

            nextMonthAdapter.setItems(itemsNextMonth);
            nextMonthAdapter.notifyDataSetChanged();
        }
    };
}
