package com.example.p2gui;

/**
 * Tri-state class extending non-resident.
 * Performs tuition operations relevant to tri-state students.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class TriState extends NonResident {

    /**
     * New york discount.
     *
     */
    private double newYorkResidentDiscount = 4000;
    /**
     * Connecticut discount.
     *
     */
    private double connecticutResidentDiscount = 5000;
    /**
     * Tristate state.
     *
     */
    private String triState;

    /**
     * Gets the New York discount.
     *
     * @return discount amount.
     */
    public double getNewYorkResidentDiscount() {
        return newYorkResidentDiscount;
    }

    /**
     * Gets the Conneticut discount.
     *
     * @return discount amount.
     */
    public double getConnecticutResidentDiscount() {
        return connecticutResidentDiscount;
    }

    /**
     * Sets the tristate.
     *
     * @param state state to set.
     */
    public void setTriState(String state) {
        this.triState = state;
    }

    /**
     * Gets the tristate.
     *
     * @return tristate.
     */
    public String getTriState() {
        return triState;
    }

    /**
     * Tristate student constructor.
     *
     * @param name name of student.
     * @param major major of student.
     * @param credits number of credits.
     * @param state tristate state (NY, CT are valid)
     */
    public TriState(String name, Major major, int credits, String state) {
        super(name, major, credits);
        setTriState(state);
        setStudentStatus("non-resident(tri-state):"+getTriState());
    }

    /**
     * Tristate tuition calculator.
     *
     */
    @Override
    public void tuitionDue() {
        double fee;

        if (getTriState().equalsIgnoreCase("NY")) {
            if (getCreditHours() >= getFullTimeCredits() && getCreditHours() < getCreditsBeforeExtraFee()) {
                fee = ((getNrTuition() - getNewYorkResidentDiscount()) + getUniversityFee() - getAmountPayed());

            } else if (getCreditHours() >= getCreditsBeforeExtraFee()) {
                fee = (getNrTuition() + getUniversityFee() - getNewYorkResidentDiscount() + (getNrCreditHour() * (getCreditHours() - getCreditsBeforeExtraFee()))) - (getAmountPayed());

            } else {
                fee = (getPartTimeUniversityFee() + (getNrCreditHour() * getCreditHours())) - (getAmountPayed());
            }
        } else {
            if (getCreditHours() >= getFullTimeCredits()) {
                fee = ((getNrTuition() - getConnecticutResidentDiscount()) + getUniversityFee() - getAmountPayed());

            } else if (getCreditHours() >= getCreditsBeforeExtraFee()) {
                fee = (getNrTuition() + getUniversityFee() - getConnecticutResidentDiscount() + (getNrCreditHour() * (getCreditHours() - getCreditsBeforeExtraFee()))) - (getAmountPayed());

            } else {
                fee = (getPartTimeUniversityFee() + (getNrCreditHour() * getCreditHours())) - (getAmountPayed());
            }
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