package ast.billmanagment.mybills.CalenderAuxiliaries;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import ast.billmanagment.mybills.AppConfig;
import ast.billmanagment.mybills.HomeAuxiliaries.FeaturedOutletsViewPagerAdapter;
import ast.billmanagment.mybills.MaterialCalendar.decorators.DisableDatesDecorator;
import ast.billmanagment.mybills.MaterialCalendar.decorators.DisableSelectedDatesDecorator;
import ast.billmanagment.mybills.MaterialCalendar.decorators.DisableUnavailableDatesDecorator;
import ast.billmanagment.mybills.MaterialCalendar.decorators.EventDecorator;
import ast.billmanagment.mybills.MaterialCalendar.materialcalendarview.CalendarDay;
import ast.billmanagment.mybills.MaterialCalendar.materialcalendarview.MaterialCalendarView;
import ast.billmanagment.mybills.MaterialCalendar.materialcalendarview.OnDateSelectedListener;
import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;

public class CustomCalenderFragment extends Fragment implements View.OnClickListener {



    private MaterialCalendarView materialCalendarView;
    private MaterialCalendarView materialCalendarView_nextMonth;

    IBadgeUpdateListener mBadgeUpdateListener;





    private SimpleDateFormat mFormat, mFormatForApi;

    private long mDateMillies = 0;




    View frg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        frg = inflater.inflate(R.layout.fragment_calender_custom, container, false);

        init();
        bindviews(frg);


        return frg;
    }




    public GregorianCalendar month, itemmonth;// calendar instances.
    private void init() {
        setBottomBar();


    }



    private void bindviews(View view) {

        if (getActivity() != null) {

            materialCalendarView = view.findViewById(R.id.dlg_reorder_mcv_calndr);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
//            cal.add(Calendar.MONTH, 2);
            Date selectedDate = new Date();
            mFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            mFormatForApi = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            materialCalendarView.setSelectedDate(selectedDate);
            mDateMillies = selectedDate.getTime();


            materialCalendarView.setPagingEnabled(false);

            materialCalendarView.state().edit()
                    .setFirstDayOfWeek(Calendar.MONDAY)
                    .setMinimumDate(CalendarDay.from(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1))
                    .commit();



//            materialCalendarView.addDecorator(new EventDecorator(getActivity()));
//            materialCalendarView.addDecorator(new DisableDatesDecorator());   /// before date highlights
//            materialCalendarView.addDecorator(new TodayDateDecorator(getActivity()));

            ArrayList<CalendarDay> listDates = new ArrayList<>();

            CalendarDay dateTimeSlots = new CalendarDay();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);



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


            for (int i = 0; i < lstImportantDates.size(); i++) {

//                String date = "2021-08-28";
                    String date = lstImportantDates.get(i).toString();
                Date days = null;

                if (date != null) {

                    try {
                        days = dateFormat.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Log.d("LOG_AS", "formated date time: " + days.getTime() + "");


                    CalendarDay calDays = dateTimeSlots.from(days);

                    listDates.add(calDays);


                }
            }

            materialCalendarView.addDecorator(new DisableSelectedDatesDecorator(getActivity(), listDates));

        }

        if (getActivity() != null) {

            materialCalendarView_nextMonth = view.findViewById(R.id.dlg_reorder_mcv_calndr_nextmonth);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);

//            cal.add(Calendar.MONTH, 2);
//            Date selectedDate = cal.getTime();

            Date selectedDate = new Date();



            mFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            mFormatForApi = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            materialCalendarView_nextMonth.setSelectedDate(selectedDate);
            mDateMillies = selectedDate.getTime();


            materialCalendarView_nextMonth.setPagingEnabled(false);

            materialCalendarView_nextMonth.state().edit()
                    .setFirstDayOfWeek(Calendar.MONDAY)
                    .setMinimumDate(CalendarDay.from(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1))
                    .commit();



            materialCalendarView_nextMonth.addDecorator(new EventDecorator(getActivity()));
//            materialCalendarView_nextMonth.addDecorator(new DisableDatesDecorator());
//            materialCalendarView_nextMonth.addDecorator(new TodayDateDecorator(getActivity()));

            ArrayList<CalendarDay> listDates = new ArrayList<>();

            CalendarDay dateTimeSlots = new CalendarDay();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);



            ArrayList<String> lstImportantDates = new ArrayList();

            lstImportantDates.add("2021-08-30");
            lstImportantDates.add("2021-08-31");


            lstImportantDates.add("2021-09-3");
            lstImportantDates.add("2021-09-4");
            lstImportantDates.add("2021-09-5");
            lstImportantDates.add("2021-09-6");
            lstImportantDates.add("2021-09-7");
            lstImportantDates.add("2021-09-8");
            lstImportantDates.add("2021-09-9");



            for (int i = 0; i < lstImportantDates.size(); i++) {

//                String date = "2021-08-28";
                String date = lstImportantDates.get(i).toString();
                Date days = null;

                if (date != null) {

                    try {
                        days = dateFormat.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Log.d("LOG_AS", "formated date time: " + days.getTime() + "");


                    CalendarDay calDays = dateTimeSlots.from(days);

                    listDates.add(calDays);


                }
            }

            materialCalendarView_nextMonth.addDecorator(new DisableSelectedDatesDecorator(getActivity(), listDates));
            materialCalendarView_nextMonth.setNextMonth();
        }

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


}
