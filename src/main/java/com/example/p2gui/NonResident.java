package com.example.p2gui;

/**
 * Non-resident class extending student.
 * Performs tuition operations relevant to non-residents.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class NonResident extends Student {

    /**
     * Non resident tuition.
     *
     */
    private static double nrTuition = 29737;
    /**
     * Non resident credit hour cost.
     *
     */
    private static double nrCreditHour = 966;


    /**
     * Non resident student constructor.
     *
     * @param name name of student.
     * @param major major of student.
     * @param credits number of credits.
     */
    public NonResident(String name, Major major, int credits) {
        super(name, major, credits);
        setStudentStatus("non-resident");
    }

    /**
     * Gets non resident tuition cost.
     *
     * @return non resident tuition.
     */
    public double getNrTuition() {
        return nrTuition;
    }

    /**
     * Gets non resident credit hour cost.
     *
     * @return non resident credit hour rate.
     */
    public double getNrCreditHour() {
        return nrCreditHour;
    }

    /**
     * Non resident tuition calculator.
     *
     */
    @Override
    public void tuitionDue() {
        double fee;

        if (getCreditHours() >= getFullTimeCredits() && getCreditHours() < getCreditsBeforeExtraFee()) {
            fee = (getNrTuition() + getUniversityFee() - getAmountPayed());

        } else if (getCreditHours() >= getCreditsBeforeExtraFee()) {
            fee = (getNrTuition() + getUniversityFee() + (getNrCreditHour() * (getCreditHours() - getCreditsBeforeExtraFee()))) - (getAmountPayed());

        } else {
            fee = (getPartTimeUniversityFee() + getNrCreditHour() * (getCreditHours())) - (getAmountPayed());
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