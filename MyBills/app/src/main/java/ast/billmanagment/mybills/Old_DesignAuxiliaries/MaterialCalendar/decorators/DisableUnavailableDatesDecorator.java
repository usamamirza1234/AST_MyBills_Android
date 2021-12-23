package ast.billmanagment.mybills.Old_DesignAuxiliaries.MaterialCalendar.decorators;

import android.content.Context;
import android.text.style.ForegroundColorSpan;

import ast.billmanagment.mybills.Old_DesignAuxiliaries.MaterialCalendar.materialcalendarview.CalendarDay;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.MaterialCalendar.materialcalendarview.DayViewDecorator;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.MaterialCalendar.materialcalendarview.DayViewFacade;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.MaterialCalendar.materialcalendarview.spans.SideDotSpan;
import ast.billmanagment.mybills.R;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by bilalahmed on 21/04/2017.
 * bilalahmed.cs@live.com
 */

/**
 * Decorate several days with a dot outlined
 */
public class DisableUnavailableDatesDecorator implements DayViewDecorator {

    private int colorCircle;
    private int colorStroke;
    private float radius;
    private String strStartDate;
    private HashSet<CalendarDay> dates;
    private Context context;

    public DisableUnavailableDatesDecorator(Context _context, Collection<CalendarDay> _dates) {
//        this.colorCircle = _colorCircle;
//        this.colorStroke = _colorStroke;
//        this.radius = _radius;
        this.context = _context;
        this.dates = new HashSet<>(_dates);

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);

    }

    @Override
    public void decorate(DayViewFacade view) {

        view.addSpan(new SideDotSpan(colorCircle, colorStroke, radius));
        view.addSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.white)));
        view.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shp_circle_yellow));
        //view.setSelectionDrawable(getDrawable(R.drawable.drawable_rectangle));
        view.setDaysDisabled(true);
        view.areDaysDisabled();
    }
}
