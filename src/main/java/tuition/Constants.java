package tuition;

/**
 * Constants class to define constants.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class Constants {

    /**
     * Output for find if the target album is not within the collection.
     */
    public static final int NOT_FOUND = -1;
    /**
     * Used for determining if a year is a leap year.
     */
    public static final int QUADRENNIAL = 4;
    /**
     * Used for determining if a year is a leap year.
     */
    public static final int CENTENNIAL = 100;
    /**
     * Used for determining if a year is a leap year.
     */
    public static final int QUARTERCENTENNIAL = 400;
    /**
     * Used for calculations if the date happens on a leap year.
     */
    public static final int LEAP_DAY = 29;
    /**
     * Used for calculations to see if a february day exceed 28 on a non-leap year.
     */
    public static final int MAX_FEBRUARY_DAYS = 28;
    /**
     * Used for calculations to see if a february day exceed 29 on a non-leap year.
     */
    public static final int MAX_FEBRUARY_LEAP_DAYS = 29;
    /**
     * Used to indicate if dates are equal.
     */
    public static final int EQUAL_DATE = 0;
    /**
     * Used to indicate if a date is smaller than another.
     */
    public static final int SMALLER_DATE = -1;
    /**
     * Used to indicate if a date is bigger than another.
     */
    public static final int BIGGER_DATE = 1;
    /**
     * Used to validate max days in a month.
     */
    public static final int MAX_THIRTY_DAYS = 30;
    /**
     * Used to validate max days in a month.
     */
    public static final int MAX_THIRTY_ONE_DAYS = 31;
    /**
     * Used to validate max days in a month.
     */
    public static final int JANUARY = 1;
    /**
     * Used to validate max days in a month.
     */
    public static final int FEBRUARY = 2;
    /**
     * Used to validate max days in a month.
     */
    public static final int MARCH = 3;
    /**
     * Used to validate max days in a month.
     */
    public static final int APRIL = 4;
    /**
     * Used to validate max days in a month.
     */
    public static final int MAY = 5;
    /**
     * Used to validate max days in a month.
     */
    public static final int JUNE = 6;
    /**
     * Used to validate max days in a month.
     */
    public static final int JULY = 7;
    /**
     * Used to validate max days in a month.
     */
    public static final int AUGUST = 8;
    /**
     * Used to validate max days in a month.
     */
    public static final int SEPTEMBER = 9;
    /**
     * Used to validate max days in a month.
     */
    public static final int OCTOBER = 10;
    /**
     * Used to validate max days in a month.
     */
    public static final int NOVEMBER = 11;
    /**
     * Used to validate max days in a month.
     */
    public static final int DECEMBER = 12;
    /**
     * Used to check if a student has 12 credits.
     */
    public static final int FULL_TIME_MIN_CREDITS = 12;
    /**
     * Used to check negative credits.
     */
    public static final int ZERO_CREDITS = 0;
    /**
     * Minimum credits to have.
     */
    public static final int MIN_CREDITS = 3;
    /**
     * Max possible credits.
     */
    public static final int MAX_CREDITS = 24;
    /**
     * Payment reset value.
     */
    public static final int RESET_PAYMENT = 0;
    /**
     * Empty roster value.
     */
    public static final int EMPTY_ROSTER = 0;
    /**
     * Valid year for roster payments.
     */
    public static final int VALID_YEAR = 2021;
    /**
     * Max finance amount.
     */
    public static final int MAX_FINANCE = 10000;
    /**
     * Finance can't be negative.
     */
    public static final int MIN_FINANCE = 0;
    /**
     * Roster size change for add and remove.
     */
    public static final int ROSTER_SIZE_CHANGE = 1;
    /**
     * Used to check for negative payments.
     */
    public static final int MIN_PAYMENT = 0;

}