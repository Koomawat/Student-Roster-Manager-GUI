package tuition;

import java.util.Calendar;

/**
 * Date class that takes and handles dates taken in a string format of mm/dd/yyyy.
 * Checks for date validity and has a compare to method for comparing dates.
 * Utilizes Calendar class to get the local system date for further date validation.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class Date implements Comparable<Date> {

    /**
     * Integer of the year in the date.
     */
    private int year;
    /**
     * Integer of the month in the date.
     */
    private int month;
    /**
     * Integer of the day in the date.
     */
    private int day;

    /**
     * Date constructor taking the date in the format of mm/dd/yyyy.
     *
     * @param date the date string.
     */
    public Date(String date) {
        String[] givenDate = date.split("-");

        String givenMonth = givenDate[1];
        String givenDay = givenDate[2];
        String givenYear = givenDate[0];

        int numMonth = Integer.parseInt(givenMonth);
        int numDay = Integer.parseInt(givenDay);
        int numYear = Integer.parseInt(givenYear);

        this.month = numMonth;
        this.day = numDay;
        this.year = numYear;


    } //take �mm/dd/yyyy� and create a Date object

    /**
     * Default date constructor to get the current local system date in the format mm/dd/yyyy.
     */
    public Date() {
        Calendar dateToday = Calendar.getInstance();

        int numMonth = dateToday.get(Calendar.MONTH) + Constants.JANUARY; //Adding january since calendar index from 0 to 11 instead of 1 to 12
        int numDay = dateToday.get(Calendar.DATE);
        int numYear = dateToday.get(Calendar.YEAR);

        this.month = numMonth;
        this.day = numDay;
        this.year = numYear;
    } //create an object with today�s date (see Calendar class)

    /**
     * Gets the year of the album.
     *
     * @return the album's year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the month of the album.
     *
     * @return the album's month.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the day of the album.
     *
     * @return the album's day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Provides the album date in the format mm/dd/yyyy.
     *
     * @return the date in a formatted string.
     */

    public String formattedDate() {
        String date = this.month + "/" + this.day + "/" + this.year;
        return date;
    }

    /**
     * Date validator to check if the provided album date is valid.
     * Compares against the system's local date to check for present or past dates.
     * Calculates if a given year was a leap year to account for albums with a leap day release date.
     *
     * @return true if person was deleted, false otherwise.
     */
    public boolean isValid() {
        boolean isLeapYear = true;
        Date currentDate = new Date();

        int providedYear = getYear();
        int providedMonth = getMonth();
        int providedDay = getDay();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonth();
        int currentDay = currentDate.getDay();

        boolean thirtyDayCheck = false;
        boolean thirtyOneDayCheck = false;

        if (providedMonth == Constants.APRIL
                || providedMonth == Constants.JUNE
                || providedMonth == Constants.SEPTEMBER
                || providedMonth == Constants.NOVEMBER) {
            thirtyDayCheck = true;
        } else if (providedMonth == Constants.JANUARY
                || providedMonth == Constants.MARCH
                || providedMonth == Constants.MAY
                || providedMonth == Constants.JULY
                || providedMonth == Constants.AUGUST
                || providedMonth == Constants.OCTOBER
                || providedMonth == Constants.DECEMBER) {
            thirtyOneDayCheck = true;
        }

        if (thirtyDayCheck && (providedDay > Constants.MAX_THIRTY_DAYS) && (providedMonth != Constants.FEBRUARY)) {
            return false;
        } // Months that are not February can have 30 days.

        if (thirtyOneDayCheck && (providedDay > Constants.MAX_THIRTY_ONE_DAYS) && (providedMonth != Constants.FEBRUARY)) {
            return false;
        } // Months that are not February can have 31 days.

        if (providedMonth > Constants.DECEMBER) {
            return false;
        } // Months are within 12 check.

        if (providedYear < Constants.VALID_YEAR) {
            return false;
        } // Months before 2021 are invalid.

        if (providedYear > currentYear) {
            return false;
        } // Date year is from under 2021.

        if (providedMonth < Constants.JANUARY) {
            return false;
        } // Months under 1 are invalid.

        if (providedDay < Constants.JANUARY) {
            return false;
        } // Days under 1 are invalid.

        if ((providedYear == currentYear) && (providedMonth > currentMonth)) {
            return false;
        } // Current year and current or past month check.

        if ((providedYear == currentYear) && (providedMonth == currentMonth) && (providedDay > currentDay)) {
            return false;
        } // Current year, current month, and current or past day check.

        if (providedMonth == Constants.FEBRUARY && providedDay == Constants.LEAP_DAY) {

            if (providedYear % Constants.QUADRENNIAL == 0) {

                if (providedYear % Constants.CENTENNIAL == 0) {

                    if (providedYear % Constants.QUARTERCENTENNIAL == 0) {
                        isLeapYear = true;
                    } else {
                        isLeapYear = false;
                    }
                } else {
                    isLeapYear = true;
                }
            } else {
                isLeapYear = false;
            }
        } // Leap year checks.

        if (isLeapYear == false) {
            return false;
        }

        if (providedMonth == Constants.FEBRUARY && isLeapYear == true && providedDay > Constants.MAX_FEBRUARY_LEAP_DAYS) {
            return false;
        } // Max days on a leap year February must be 29 or less
        else if (providedMonth == Constants.FEBRUARY && isLeapYear == false && providedDay > Constants.MAX_FEBRUARY_DAYS) {
            return false;
        } // Max days on a non-leap year February must be 28 or less

        return true;
    }

    /**
     * Compares two dates and returns an integer value to signify if a date was equal to, less, or greater than the other.
     *
     * @param date the date being compared.
     * @return 0 if the dates are equal, 1 if the date is beyond, or -1 if the date is under.
     */
    @Override
    public int compareTo(Date date) {
        int primaryYear = getYear();
        int primaryMonth = getMonth();
        int primaryDay = getDay();
        int secondaryYear = date.getYear();
        int secondaryMonth = date.getMonth();
        int secondaryDay = date.getDay();

        if ((primaryYear == secondaryYear) && (primaryMonth == secondaryMonth) && (primaryDay == secondaryDay)) {
            return Constants.EQUAL_DATE;
        } // Both dates have all fields the same.

        if (primaryYear > secondaryYear) {
            return Constants.BIGGER_DATE;
        } // Year is different.

        if ((primaryYear == secondaryYear) && (primaryMonth > secondaryMonth)) {
            return Constants.BIGGER_DATE;
        } // Same years but different month.

        if ((primaryYear == secondaryYear) && (primaryMonth == secondaryMonth) && (primaryDay > secondaryDay)) {
            return Constants.BIGGER_DATE;
        } // Same years, same months, different days

        return Constants.SMALLER_DATE;
    }
}