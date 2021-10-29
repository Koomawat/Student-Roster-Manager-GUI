package tuition;

/**
 * International student class extending non-resident.
 * Performs tuition operations relevant to international students.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class International extends NonResident {

    /**
     * International additional fee.
     *
     */
    private static double additionalFee = 2650;

    /**
     * Gets the additional fee.
     *
     * @return additional fee.
     */
    public double getAdditionalFee() {
        return additionalFee;
    }


    /**
     * International student constructor.
     *
     * @param name name of student.
     * @param major major of student.
     * @param credits number of credits.
     */
    public International(String name, Major major, int credits) {
        super(name, major, credits);
        setStudentStatus("non-resident:international");
    }

    /**
     * International student and studying abroad constructor.
     *
     * @param name name of student.
     * @param major major of student.
     * @param credits number of credits.
     * @param abroad studying abroad status.
     */
    public International(String name, Major major, int credits, boolean abroad) {
        super(name, major, credits);
        setStudyingAbroad(abroad);
        if (getStuydingAbroad() == true) {
            setStudentStatus("non-resident:international:study abroad");
        }
        else {
            setStudentStatus("non-resident:international");
        }
    }

    /**
     * International and International studying abroad tuition calculator.
     *
     */
    @Override
    public void tuitionDue() {
        double fee;

        if (getStuydingAbroad() == true && getCreditHours() == Constants.FULL_TIME_MIN_CREDITS) {
            fee = (getUniversityFee() +
                    getAdditionalFee() -
                    getAmountPayed());
            setTuitionDue(fee);

        } else if (getCreditHours() < getCreditsBeforeExtraFee()) {
            fee = (getUniversityFee() +
                    getNrTuition() +
                    getAdditionalFee() -
                    getAmountPayed());
            setTuitionDue(fee);

        } else {
            fee = (getNrTuition() +
                    getUniversityFee() +
                    (getNrCreditHour() * (getCreditHours() - getCreditsBeforeExtraFee()))) +
                    getAdditionalFee() -
                    (getAmountPayed());
            setTuitionDue(fee);
        }
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