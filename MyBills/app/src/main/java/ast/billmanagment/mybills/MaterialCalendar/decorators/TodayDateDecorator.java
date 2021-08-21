package ast.billmanagment.mybills.MaterialCalendar.decorators;

import android.content.Context;
import android.text.style.ForegroundColorSpan;

import ast.billmanagment.mybills.MaterialCalendar.materialcalendarview.CalendarDay;
import ast.billmanagment.mybills.MaterialCalendar.materialcalendarview.DayViewDecorator;
import ast.billmanagment.mybills.MaterialCalendar.materialcalendarview.DayViewFacade;
import ast.billmanagment.mybills.R;

import java.util.Date;

/**
 * Created by Muhammad Danish(danish.andpercent@gmail.com) on 31/01/2020.
 */
public class TodayDateDecorator implements DayViewDecorator {

    private CalendarDay date;
    private Context context;

    public TodayDateDecorator(Context context) {
        date = CalendarDay.today();
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.white)));
        view.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shp_circle_green));
    }

    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}