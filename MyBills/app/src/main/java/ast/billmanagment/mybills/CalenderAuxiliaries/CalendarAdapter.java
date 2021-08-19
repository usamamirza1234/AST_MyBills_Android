package ast.billmanagment.mybills.CalenderAuxiliaries;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import ast.billmanagment.mybills.R;


public class CalendarAdapter extends BaseAdapter {

    private Context mContext;

    private java.util.Calendar month;
    public GregorianCalendar pmonth; // calendar instance for previous month
    /**
     * calendar instance for previous month for getting complete view
     */
    public GregorianCalendar pmonthmaxset;
    private GregorianCalendar selectedDate;
    int firstDay;
    int maxWeeknumber;
    int maxP;
    int calMaxP;
    int lastWeekDay;
    int leftDays;
    int mnthlength;
    String itemvalue, curentDateString;
    DateFormat dateFormat;

    private ArrayList items;
    public static List dayString;
    private View previousView;
    ArrayList<String> lstImportantDates;
    boolean showCurrent = false;

    public CalendarAdapter(Context context, GregorianCalendar monthCalendar, ArrayList<String> lstImportantDates) {
        Locale.setDefault(Locale.US);
        this.mContext = context;
        this.dayString = new ArrayList();
        this.items = new ArrayList();
        this.lstImportantDates = lstImportantDates;
        this.month = monthCalendar;

        this.selectedDate = (GregorianCalendar) monthCalendar.clone();
        Log.d("MONTHCALENDAR", "selectedDate " + selectedDate);

        this.month.set(GregorianCalendar.DAY_OF_MONTH, 1);


        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        this.curentDateString = dateFormat.format(selectedDate.getTime());
        Log.d("MONTHCALENDAR", "curentDateString " + curentDateString);

        refreshDays();
    }


    public CalendarAdapter(Context context, GregorianCalendar monthCalendar, ArrayList<String> lstImportantDates, boolean showCurrent) {
        Locale.setDefault(Locale.US);
        this.mContext = context;
        this.dayString = new ArrayList();
        this.items = new ArrayList();
        this.lstImportantDates = lstImportantDates;
        this.month = monthCalendar;
        this.showCurrent = showCurrent;

        this.selectedDate = (GregorianCalendar) monthCalendar.clone();
        Log.d("MONTHCALENDAR", "selectedDate " + selectedDate);

        this.month.set(GregorianCalendar.DAY_OF_MONTH, 1);


        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        this.curentDateString = dateFormat.format(selectedDate.getTime());
        Log.d("MONTHCALENDAR", "curentDateString " + curentDateString);

        refreshDays();
    }

    public void setItems(ArrayList items) {
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).toString().length() == 1) {
                items.set(i, "0" + items.get(i));
            }
        }
        this.items = items;
    }

    public int getCount() {
        Log.d("MONTHCALENDAR", "dayString getCount Size" + dayString.size());
        return dayString.size();
    }

    public Object getItem(int position) {

        Log.d("MONTHCALENDAR", "dayString getItem " + dayString.get(position));
        return dayString.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new view for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView dayView;
        if (convertView == null) { // if it's not recycled, initialize some
            // attributes
            LayoutInflater vi = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.lay_calendar_item, null);

        }
        dayView = (TextView) v.findViewById(R.id.date);
        // separates daystring into parts.


        String[] separatedTime = dayString.get(position).toString().split("-");


        Log.d("MONTHCALENDAR", "dayString separatedTime " + separatedTime);

        // taking last part of date. ie; 2 from 2012-12-02
        String gridvalue = separatedTime[2].replaceFirst("^0*", "");


        Log.d("MONTHCALENDAR", "dayString gridvalue " + gridvalue);

        System.out
                .println(":::::::::::::::::::::::ADPTER SPLITTED MONTH:::::::::::::::::::::"
                        + separatedTime[1]);
        // checking whether the day is in current month or not.
        if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
            // setting offdays to white color.
            dayView.setTextColor(mContext.getResources().getColor(R.color.thm_dark_navy1));
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
            dayView.setTextColor(mContext.getResources().getColor(R.color.thm_dark_navy1));
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else {
            // setting curent month's days in blue color.


            switch (dayString.get(position).toString()) {
                case "2021-08-22":
                case "2021-08-30":
                case "2021-08-31":
                case "2021-09-1":
                case "2021-09-2":
                case "2021-09-3":
                case "2021-09-4":
                case "2021-09-5":
                case "2021-09-6":
                case "2021-09-7":
                case "2021-09-8":
                case "2021-09-9":
                case "2021-09-10":
                case "2021-09-11":
                case "2021-09-12":
                case "2021-09-13":
                case "2021-09-14":
                case "2021-09-15":
                case "2021-09-16":
                case "2021-09-17":
                case "2021-09-18":
                case "2021-09-30":
                case "2021-09-25":

                    dayView.setTextColor(mContext.getResources().getColor(R.color.thm_yellow_1));
                    dayView.setBackground(mContext.getResources().getDrawable(R.drawable.shp_rect_rounded_white_medium_stoke));
                    break;


                default:
                    dayView.setTextColor(mContext.getResources().getColor(R.color.black_1));
                    break;

            }
        }

        if (showCurrent)
        {
            if (dayString.get(position).equals(curentDateString)) {
                dayView.setTextColor(mContext.getResources().getColor(R.color.thm_green_bright));
                dayView.setBackground(mContext.getResources().getDrawable(R.drawable.shp_rect_rounded_white_medium_stoke));

            }

        }


        dayView.setText(gridvalue);

        // create date string for comparison
        String date = dayString.get(position).toString();

        if (date.length() == 1) {
            date = "0" + date;
        }

        String monthStr = "" + (month.get(GregorianCalendar.MONTH));
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }


        return v;
    }

    public View setSelected(View view) {
        if (previousView != null) {
            previousView.setBackgroundResource(R.drawable.calendar_bg_selector);
        }
        previousView = view;
        view.setBackgroundResource(R.drawable.calendar_bg_selector);
        return view;
    }

    public void refreshDays() {
        // clear items
        items.clear();
        dayString.clear();
        Locale.setDefault(Locale.US);
        pmonth = (GregorianCalendar) month.clone();
        // month start day. ie; sun, mon, etc
        firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
        // finding number of weeks in current month.
        maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
        // allocating maximum row number for the gridview.
        mnthlength = maxWeeknumber * 7;
        maxP = getMaxP(); // previous month maximum day 31,30....
        calMaxP = maxP - (firstDay - 2);// calendar offday starting 24,25 ...
        /**
         * Calendar instance for getting a complete gridview including the three
         * month's (previous,current,next) dates.
         */
        pmonthmaxset = (GregorianCalendar) pmonth.clone();
        /**
         * setting the start date as previous month's required date.
         */
        pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP);

        /**
         * filling calendar gridview.
         */
        for (int n = 0; n < mnthlength; n++) {

            itemvalue = dateFormat.format(pmonthmaxset.getTime());
            pmonthmaxset.add(GregorianCalendar.DATE, 1);
            dayString.add(itemvalue);

        }
    }

    private int getMaxP() {
        int maxP;
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            pmonth.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }
        maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        return maxP;
    }

}