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



        Log.d("BackTrackingWorking", "Integer.parseInt(gridvalue) " + Integer.parseInt(gridvalue));
        Log.d("BackTrackingWorking", "Integer.parseInt(gridvalue) " + firstDay);

        // checking whether the day is in current month or not.
        if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {

            Log.d("BackTrackingWorking", "Month Start gridvalue : " + Integer.parseInt(gridvalue));
            Log.d("BackTrackingWorking", " Month Start  firstDay : " + firstDay);

            // setting offdays to white color.
            dayView.setTextColor(mContext.getResources().getColor(R.color.thm_dark_navy1));
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {


            Log.d("BackTrackingWorking", "Month End gridvalue " + Integer.parseInt(gridvalue));




            dayView.setTextColor(mContext.getResources().getColor(R.color.thm_dark_navy1));
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else {
            // setting curent month's days in blue color.



        }


        Log.d("BackTrackingWorking", "Month dayString " + dayString.get(position).toString());


        String strPhoneNumber = String.valueOf(dayString.get(position).toString());
        strPhoneNumber = "" + strPhoneNumber.substring(5);

        Log.d("BackTrackingWorking", "strPhoneNumber " + strPhoneNumber);

        switch ("08-22") {

            case "01-01" :
            case "01-02" :
            case "01-03" :
            case "01-04" :
            case "01-05" :
            case "01-06" :
            case "01-07" :
            case "01-08" :
            case "01-09" :
            case "01-10" :
            case "01-11" :
            case "01-12" :
            case "01-13" :
            case "01-14" :
            case "01-15" :
            case "01-16" :
            case "01-17" :
            case "01-18" :
            case "01-19" :
            case "01-20" :
            case "01-21" :
            case "01-22" :
            case "01-23" :
            case "01-24" :
            case "01-25" :
            case "01-26" :
            case "01-27" :
            case "01-28" :
            case "01-29" :
            case "01-30" :
            case "01-31" :
            case "02-01" :
            case "02-02" :
            case "02-03" :
            case "02-04" :
            case "02-05" :
            case "02-06" :
            case "02-07" :
            case "02-08" :
            case "02-09" :
            case "02-10" :
            case "02-11" :
            case "02-12" :
            case "02-13" :
            case "02-14" :
            case "02-15" :
            case "02-16" :
            case "02-17" :
            case "02-18" :
            case "02-19" :
            case "02-20" :
            case "02-21" :
            case "02-22" :
            case "02-23" :
            case "02-24" :
            case "02-25" :
            case "02-26" :
            case "02-27" :
            case "02-28" :
            case "02-29" :
            case "02-30" :
            case "02-31" :
            case "03-01" :
            case "03-02" :
            case "03-03" :
            case "03-04" :
            case "03-05" :
            case "03-06" :
            case "03-07" :
            case "03-08" :
            case "03-09" :
            case "03-10" :
            case "03-11" :
            case "03-12" :
            case "03-13" :
            case "03-14" :
            case "03-15" :
            case "03-16" :
            case "03-17" :
            case "03-18" :
            case "03-19" :
            case "03-20" :
            case "03-21" :
            case "03-22" :
            case "03-23" :
            case "03-24" :
            case "03-25" :
            case "03-26" :
            case "03-27" :
            case "03-28" :
            case "03-29" :
            case "03-30" :
            case "03-31" :
            case "04-01" :
            case "04-02" :
            case "04-03" :
            case "04-04" :
            case "04-05" :
            case "04-06" :
            case "04-07" :
            case "04-08" :
            case "04-09" :
            case "04-10" :
            case "04-11" :
            case "04-12" :
            case "04-13" :
            case "04-14" :
            case "04-15" :
            case "04-16" :
            case "04-17" :
            case "04-18" :
            case "04-19" :
            case "04-20" :
            case "04-21" :
            case "04-22" :
            case "04-23" :
            case "04-24" :
            case "04-25" :
            case "04-26" :
            case "04-27" :
            case "04-28" :
            case "04-29" :
            case "04-30" :
            case "04-31" :
            case "05-01" :
            case "05-02" :
            case "05-03" :
            case "05-04" :
            case "05-05" :
            case "05-06" :
            case "05-07" :
            case "05-08" :
            case "05-09" :
            case "05-10" :
            case "05-11" :
            case "05-12" :
            case "05-13" :
            case "05-14" :
            case "05-15" :
            case "05-16" :
            case "05-17" :
            case "05-18" :
            case "05-19" :
            case "05-20" :
            case "05-21" :
            case "05-22" :
            case "05-23" :
            case "05-24" :
            case "05-25" :
            case "05-26" :
            case "05-27" :
            case "05-28" :
            case "05-29" :
            case "05-30" :
            case "05-31" :
            case "06-01" :
            case "06-02" :
            case "06-03" :
            case "06-04" :
            case "06-05" :
            case "06-06" :
            case "06-07" :
            case "06-08" :
            case "06-09" :
            case "06-10" :
            case "06-11" :
            case "06-12" :
            case "06-13" :
            case "06-14" :
            case "06-15" :
            case "06-16" :
            case "06-17" :
            case "06-18" :
            case "06-19" :
            case "06-20" :
            case "06-21" :
            case "06-22" :
            case "06-23" :
            case "06-24" :
            case "06-25" :
            case "06-26" :
            case "06-27" :
            case "06-28" :
            case "06-29" :
            case "06-30" :
            case "06-31" :
            case "07-01" :
            case "07-02" :
            case "07-03" :
            case "07-04" :
            case "07-05" :
            case "07-06" :
            case "07-07" :
            case "07-08" :
            case "07-09" :
            case "07-10" :
            case "07-11" :
            case "07-12" :
            case "07-13" :
            case "07-14" :
            case "07-15" :
            case "07-16" :
            case "07-17" :
            case "07-18" :
            case "07-19" :
            case "07-20" :
            case "07-21" :
            case "07-22" :
            case "07-23" :
            case "07-24" :
            case "07-25" :
            case "07-26" :
            case "07-27" :
            case "07-28" :
            case "07-29" :
            case "07-30" :
            case "07-31" :
            case "08-01" :
            case "08-02" :
            case "08-03" :
            case "08-04" :
            case "08-05" :
            case "08-06" :
            case "08-07" :
            case "08-08" :
            case "08-09" :
            case "08-10" :
            case "08-11" :
            case "08-12" :
            case "08-13" :
            case "08-14" :
            case "08-15" :
            case "08-16" :
            case "08-17" :
            case "08-18" :
            case "08-19" :
            case "08-20" :
            case "08-21" :
            case "08-22" :
            case "08-23" :
            case "08-24" :
            case "08-25" :
            case "08-26" :
            case "08-27" :
            case "08-28" :
            case "08-29" :
            case "08-30" :
            case "08-31" :
            case "09-01" :
            case "09-02" :
            case "09-03" :
            case "09-04" :
            case "09-05" :
            case "09-06" :
            case "09-07" :
            case "09-08" :
            case "09-09" :
            case "09-10" :
            case "09-11" :
            case "09-12" :
            case "09-13" :
            case "09-14" :
            case "09-15" :
            case "09-16" :
            case "09-17" :
            case "09-18" :
            case "09-19" :
            case "09-20" :
            case "09-21" :
            case "09-22" :
            case "09-23" :
            case "09-24" :
            case "09-25" :
            case "09-26" :
            case "09-27" :
            case "09-28" :
            case "09-29" :
            case "09-30" :
            case "09-31" :
            case "10-01" :
            case "10-02" :
            case "10-03" :
            case "10-04" :
            case "10-05" :
            case "10-06" :
            case "10-07" :
            case "10-08" :
            case "10-09" :
            case "10-10" :
            case "10-11" :
            case "10-12" :
            case "10-13" :
            case "10-14" :
            case "10-15" :
            case "10-16" :
            case "10-17" :
            case "10-18" :
            case "10-19" :
            case "10-20" :
            case "10-21" :
            case "10-22" :
            case "10-23" :
            case "10-24" :
            case "10-25" :
            case "10-26" :
            case "10-27" :
            case "10-28" :
            case "10-29" :
            case "10-30" :
            case "10-31" :
            case "11-01" :
            case "11-02" :
            case "11-03" :
            case "11-04" :
            case "11-05" :
            case "11-06" :
            case "11-07" :
            case "11-08" :
            case "11-09" :
            case "11-10" :
            case "11-11" :
            case "11-12" :
            case "11-13" :
            case "11-14" :
            case "11-15" :
            case "11-16" :
            case "11-17" :
            case "11-18" :
            case "11-19" :
            case "11-20" :
            case "11-21" :
            case "11-22" :
            case "11-23" :
            case "11-24" :
            case "11-25" :
            case "11-26" :
            case "11-27" :
            case "11-28" :
            case "11-29" :
            case "11-30" :
            case "11-31" :
            case "12-01" :
            case "12-02" :
            case "12-03" :
            case "12-04" :
            case "12-05" :
            case "12-06" :
            case "12-07" :
            case "12-08" :
            case "12-09" :
            case "12-10" :
            case "12-11" :
            case "12-12" :
            case "12-13" :
            case "12-14" :
            case "12-15" :
            case "12-16" :
            case "12-17" :
            case "12-18" :
            case "12-19" :
            case "12-20" :
            case "12-21" :
            case "12-22" :
            case "12-23" :
            case "12-24" :
            case "12-25" :
            case "12-26" :
            case "12-27" :
            case "12-28" :
            case "12-29" :
            case "12-30" :
            case "12-31" :
                dayView.setTextColor(mContext.getResources().getColor(R.color.thm_yellow_1));
                dayView.setBackground(mContext.getResources().getDrawable(R.drawable.shp_rect_rounded_white_medium_stoke));
                break;


            default:
                dayView.setTextColor(mContext.getResources().getColor(R.color.black_1));
                break;

        }

        if (showCurrent)
        {
            if (dayString.get(position).equals(curentDateString)) {
                dayView.setText(gridvalue);
                dayView.setTextColor(mContext.getResources().getColor(R.color.thm_green_bright));
                dayView.setBackground(mContext.getResources().getDrawable(R.drawable.shp_rect_rounded_white_medium_stoke));

            }

        }




        dayView.setText(gridvalue);
        return v;
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