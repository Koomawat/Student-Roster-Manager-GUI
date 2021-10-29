package tuition;

/**
 * Resident class extending student.
 * Performs tuition operations relevant to residents.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class Resident extends Student {

    /**
     * Resident tuition.
     */
    private double baseTuition = 12536;
    /**
     * Resident credit hour rate.
     */
    private double creditHourTuition = 404;

    /**
     * Gets resident tuition cost.
     *
     * @return resident tuition.
     */
    public double getBaseTuition() {
        return baseTuition;
    }

    /**
     * Gets resident credit hour.
     *
     * @return resident credit hour rate.
     */
    public double getCreditHourTuition() {
        return creditHourTuition;
    }

    /**
     * Resident student constructor.
     *
     * @param name name of student.
     * @param major major of student.
     * @param credits number of credits.
     */
    public Resident(String name, Major major, int credits) {
        super(name, major, credits);
        setStudentStatus("resident");
    }

    /**
     * Resident tuition calculator.
     *
     */
    @Override
    public void tuitionDue() {
        double fee;

        if (getCreditHours() >= getFullTimeCredits() && getCreditHours() < getCreditsBeforeExtraFee()) {
            fee = (getBaseTuition() + getUniversityFee()) - (getAmountPayed());

        } else if (getCreditHours() >= getCreditsBeforeExtraFee()) {
            fee = (getBaseTuition() + getUniversityFee() + (getCreditHourTuition() * (getCreditHours() - getCreditsBeforeExtraFee()))) - (getAmountPayed());

        } else {
            fee = (getPartTimeUniversityFee() + getCreditHourTuition() * (getCreditHours())) - (getAmountPayed());
        }

        // getTuition
        //super(num);
        setTuitionDue(fee);
    }

    /**
     * Returns a textual representation of an album.
     *
     * @return a string containing the student information.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}