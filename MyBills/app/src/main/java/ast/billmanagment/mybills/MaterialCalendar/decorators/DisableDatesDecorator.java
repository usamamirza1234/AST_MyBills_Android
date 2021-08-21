package ast.billmanagment.mybills.MaterialCalendar.decorators;

import android.util.Log;

import ast.billmanagment.mybills.MaterialCalendar.materialcalendarview.CalendarDay;
import ast.billmanagment.mybills.MaterialCalendar.materialcalendarview.DayViewDecorator;
import ast.billmanagment.mybills.MaterialCalendar.materialcalendarview.DayViewFacade;
import ast.billmanagment.mybills.MaterialCalendar.materialcalendarview.spans.SideDotSpan;

import java.util.HashSet;

/**
 * Created by bilalahmed on 21/04/2017.
 * bilalahmed.cs@live.com
 */

/**
 * Decorate several days with a dot outlined
 */
public class DisableDatesDecorator implements DayViewDecorator {

    private int colorCircle;
    private int colorStroke;
    private float radius;
    private String strStartDate;
    private HashSet<CalendarDay> dates;

    public DisableDatesDecorator() {
//        this.colorCircle = _colorCircle;
//        this.colorStroke = _colorStroke;
//        this.radius = _radius;
//        this.dates = new HashSet<>(_dates);

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        CalendarDay date = CalendarDay.today();
        Log.d("LOG_TABIB", "date format: " + date + "");

        return (day.isBefore(date)) ? true : false;

    }


    @Override
    public void decorate(DayViewFacade view) {

        view.addSpan(new SideDotSpan(colorCircle, colorStroke, radius));
        //view.setSelectionDrawable(getDrawable(R.drawable.drawable_rectangle));
        view.setDaysDisabled(true);
        view.areDaysDisabled();
    }
}
