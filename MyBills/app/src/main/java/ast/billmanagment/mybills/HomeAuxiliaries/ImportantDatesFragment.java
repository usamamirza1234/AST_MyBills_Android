package ast.billmanagment.mybills.HomeAuxiliaries;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.fragment.app.Fragment;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.squareup.timessquare.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import ast.billmanagment.mybills.AppConfig;
import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IAdapterCallback;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;
import me.relex.circleindicator.CircleIndicator;

import static com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE;

public class ImportantDatesFragment extends Fragment implements View.OnClickListener {

    IBadgeUpdateListener mBadgeUpdateListener;
    CalendarView calendar;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_important_dates, container, false);

        init();
        bindviews(frg);
//        initializeCalendar();
//        initializeCalendarLib(frg);


        return frg;
    }

    private void initializeCalendarLib(View frg) {
        CalendarPickerView calendar = (CalendarPickerView) frg.findViewById(R.id.calendar_view);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .inMode(RANGE);


    }

    private void init() {
        setBottomBar();

    }

    private void bindviews(View view) {
        calendar = (CalendarView) view.findViewById(R.id.calendarView);


    }


    public void initializeCalendar() {

        calendar.setShowWeekNumber(false);
        calendar.setFirstDayOfWeek(2); // Monday
        calendar.setMinDate(Calendar.getInstance().getTime().getTime());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        calendar.setMaxDate(cal.getTimeInMillis());
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
            mBadgeUpdateListener.setToolbarState(AppConstt.ToolbarState.TOOLBAR_VISIBLE);
            mBadgeUpdateListener.setHeaderTitle("Important Dates");

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
