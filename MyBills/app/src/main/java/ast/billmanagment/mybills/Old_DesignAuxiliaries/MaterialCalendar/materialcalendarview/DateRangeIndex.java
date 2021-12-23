package ast.billmanagment.mybills.Old_DesignAuxiliaries.MaterialCalendar.materialcalendarview;

/**
 * Use math to calculate first days of months by postion from a minium date
 */
interface DateRangeIndex {

    int getCount();

    int indexOf(CalendarDay day);

    CalendarDay getItem(int position);
}
