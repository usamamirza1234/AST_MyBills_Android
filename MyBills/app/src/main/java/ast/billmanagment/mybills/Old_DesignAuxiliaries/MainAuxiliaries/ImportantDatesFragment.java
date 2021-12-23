package ast.billmanagment.mybills.Old_DesignAuxiliaries.MainAuxiliaries;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ast.billmanagment.mybills.Old_DesignAuxiliaries.MaterialCalendar.decorators.DisableSelectedDatesDecorator;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.MaterialCalendar.decorators.EventDecorator;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.MaterialCalendar.materialcalendarview.CalendarDay;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.MaterialCalendar.materialcalendarview.MaterialCalendarView;
import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;

public class ImportantDatesFragment extends Fragment implements View.OnClickListener {


    private MaterialCalendarView materialCalendarView;
    private MaterialCalendarView materialCalendarView_nextMonth;
    IBadgeUpdateListener mBadgeUpdateListener;
    private SimpleDateFormat mFormat, mFormatForApi;
    ImportantDatesRcvAdapter importantDatesRcvAdapter;
    ArrayList<DModel_ImportantDates> lstImportantDates;
    RecyclerView rcvImportantDates;
    ArrayList<String> strlstImportantDates;

    View frg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        frg = inflater.inflate(R.layout.fragment_calender_custom, container, false);

        init();
        bindviews(frg);
        populateCalenders();
        populateImportantDates();
        return frg;
    }

    //region Material Calender
    private void populateCalenders() {
        if (getActivity() != null) {
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            Date selectedDate = new Date();
            mFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            mFormatForApi = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            materialCalendarView.setSelectedDate(selectedDate);
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

            for (int i = 0; i < strlstImportantDates.size(); i++) {

//                String date = "2021-08-28";
                String date = strlstImportantDates.get(i).toString();
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


            Calendar cal = Calendar.getInstance(Locale.ENGLISH);

//            cal.add(Calendar.MONTH, 2);
//            Date selectedDate = cal.getTime();

            Date selectedDate = new Date();


            mFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            mFormatForApi = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            materialCalendarView_nextMonth.setSelectedDate(selectedDate);


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


            for (int i = 0; i < strlstImportantDates.size(); i++) {

//                String date = "2021-08-28";
                String date = strlstImportantDates.get(i).toString();
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
    //endregion

    //region Important Dates
    private void populateImportantDates() {
        lstImportantDates.clear();
        int size = (strlstImportantDates.size());
        for (int i = 0; i < size; i++) {


            lstImportantDates.add(new DModel_ImportantDates(strlstImportantDates.get(i).toString() + "", "300" + i, "ISECO"));

        }

        if (importantDatesRcvAdapter == null) {

            importantDatesRcvAdapter = new ImportantDatesRcvAdapter(getActivity(), lstImportantDates, (eventId, position) -> {

            });

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rcvImportantDates.setLayoutManager(linearLayoutManager);
            rcvImportantDates.setAdapter(importantDatesRcvAdapter);

        } else {
            importantDatesRcvAdapter.notifyDataSetChanged();
        }

    }
    //endregion

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


        }
    }


    //region Fragment Compulsory
    private void init() {
        setBottomBar();
        lstImportantDates = new ArrayList<>();
        strlstImportantDates = new ArrayList();


        strlstImportantDates.add("2021-08-24");
        strlstImportantDates.add("2021-08-26");
        strlstImportantDates.add("2021-08-30");
        strlstImportantDates.add("2021-08-30");
        strlstImportantDates.add("2021-08-31");
        strlstImportantDates.add("2021-09-1");

        strlstImportantDates.add("2021-09-3");
        strlstImportantDates.add("2021-09-4");

        strlstImportantDates.add("2021-09-6");

        strlstImportantDates.add("2021-09-13");

        strlstImportantDates.add("2021-09-25");


    }

    private void bindviews(View view) {

        rcvImportantDates = view.findViewById(R.id.frg_my_bills_rcvImportantDate);
        materialCalendarView = view.findViewById(R.id.dlg_reorder_mcv_calndr);
        materialCalendarView_nextMonth = view.findViewById(R.id.dlg_reorder_mcv_calndr_nextmonth);


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
    //endregion

}
