import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Represents a specific month in a calendar, 
 * providing utility methods to navigate months and get month information.
 */
public class CalendarMonth {
    private int month; // 0-11 (January = 0)
    private int year;
    private Calendar calendar;

    /**
     * Constructs a CalendarMonth initialized to the current month and year.
     */
    public CalendarMonth() {
        calendar = new GregorianCalendar();
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
    }

    /**
     * Moves to the next month. Increments the year if December is exceeded.
     */
    public void nextMonth() {
        month++;
        if (month > 11) {
            month = 0;
            year++;
        }
    }

    /**
     * Moves to the previous month. Decrements the year if January is exceeded.
     */
    public void prevMonth() {
        month--;
        if (month < 0) {
            month = 11;
            year--;
        }
    }

    /**
     * Returns the full name of the current month.
     *
     * @return The month name (e.g., "January")
     */
    public String getMonthName() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                           "July", "August", "September", "October", "November", "December"};
        return months[month];
    }

    /**
     * Returns the current year.
     *
     * @return The year (e.g., 2025)
     */
    public int getYear() { 
        return year; 
    }

    /**
     * Returns the current month index (0 = January, 11 = December).
     *
     * @return Month index
     */
    public int getMonth() { 
        return month; 
    }

    /**
     * Returns the number of days in the current month.
     *
     * @return Days in the month
     */
    public int getDaysInMonth() {
        calendar.set(year, month, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Returns the day of the week for the first day of the month.
     *
     * @return 0 = Sunday, 1 = Monday, ..., 6 = Saturday
     */
    public int getFirstDayOfWeek() {
        calendar.set(year, month, 1);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * Returns the current day of the month if this month is the current month.
     *
     * @return Day of month (1-31), or -1 if not the current month
     */
    public int getCurrentDay() {
        Calendar today = new GregorianCalendar();
        if (today.get(Calendar.MONTH) == month && today.get(Calendar.YEAR) == year) {
            return today.get(Calendar.DAY_OF_MONTH);
        }
        return -1;
    }
}
