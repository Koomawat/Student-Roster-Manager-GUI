package com.example.p2gui;

import java.text.DecimalFormat;

/**
 * Student class to define the Student data type.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class Student {

    /**
     * Student credit hours.
     */
    private int creditHours;
    /**
     * Student's tuition due.
     */
    private double tuitionDue;
    /**
     * Student's most recent payment date.
     */
    private Date paymentDate;
    /**
     * Student status, e.g. resident.
     */
    private String studentStatus;
    /**
     * Total amount payed for tuition.
     */
    private double amountPayed;
    /**
     * Financial aid usage tracker.
     */
    private boolean financialAidUsed;
    /**
     * University fee fulltime.
     */
    private static double universityFee = 3268;
    /**
     * University fee parttime.
     */
    private static double partTimeUniversityFee = 3268 * 0.8;
    /**
     * Credits before extra costs applied.
     */
    private static int creditsBeforeExtraFee = 16;
    /**
     * Credits to qualify as fulltime.
     */
    private static int fullTimeCredits = 12;
    /**
     * Studying abroad tracker.
     */
    private boolean studyingAbroad;
    /**
     * Student's profile containing name and major.
     */
    private Profile profile;

    /**
     * Gets the student profile.
     *
     * @return student profile.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Sets the student profile.
     *
     * @param student student profile to set.
     */
    private void setProfile(Profile student) {
        this.profile = student;
    }

    /**
     * Student constructor, sets their profile and credits.
     *
     * @param name name of student.
     * @param major major of student.
     * @param credits number of credits.
     */
    public Student(String name, Major major, int credits) {
        this.creditHours = credits;
        Profile profile = new Profile(name, major);
        setProfile(profile);
    }

    /**
     * Gets student's credit hours.
     *
     * @return the credit hours.
     */
    public int getCreditHours() {
        return creditHours;
    }

    /**
     * Gets student's amount payed.
     *
     * @return the amount payed.
     */
    public double getAmountPayed() {
        return amountPayed;
    }

    /**
     * Gets the university fee.
     *
     * @return the university fee.
     */
    public double getUniversityFee() {
        return universityFee;
    }

    /**
     * Gets financial aid usage truth value
     *
     * @return truth value of financial aid usage
     */
    public boolean getFinancialAidUsed() {
        return financialAidUsed;
    }

    /**
     * Gets parttime university fee.
     *
     * @return parttime university fee.
     */
    public double getPartTimeUniversityFee() {
        return partTimeUniversityFee;
    }

    /**
     * Gets the number of credits before extra costs are applied.
     *
     * @return number of credits before extra costs added.
     */
    public int getCreditsBeforeExtraFee() {
        return creditsBeforeExtraFee;
    }

    /**
     * Gets the number of credits to be fulltime.
     *
     * @return credits to be considered fulltime.
     */
    public int getFullTimeCredits() {
        return fullTimeCredits;
    }

    /**
     * Gets the current status of a student, e.g. resident.
     *
     * @return student status.
     */
    public String getStudentStatus() {
        return studentStatus;
    }

    /**
     * Gets the tuition due cost.
     *
     * @return tuition due.
     */
    public double getTuitionDue() {
        return tuitionDue;
    }

    /**
     * Gets most recent payment date.
     *
     * @return latest payment date.
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * Gets studying abroad tracker.
     *
     * @return studying abroad status.
     */
    public boolean getStuydingAbroad() {
        return studyingAbroad;
    }

    /**
     * Calls tuition due.
     *
     */
    public void tuitionDue() {
    }

    /**
     * Sets student's tuition due.
     *
     * @param cost amount of tuition.
     */
    public void setTuitionDue(double cost) {
        this.tuitionDue = cost;
    }

    /**
     * Sets latest payment date.
     *
     * @param date date of payment.
     */
    public void setPaymentDate(Date date) {
        this.paymentDate = date;
    }

    /**
     * Updates total amount payed.
     *
     * @param payment amount of payment.
     * @param paid date of payment.
     */
    public void setAmountPayed(double payment, Date paid) {
        this.amountPayed = payment + getAmountPayed();
        this.paymentDate = paid;
    }

    /**
     * Resets amount payed for studying abroad changes.
     *
     */
    public void resetAmountPayed() {
        this.amountPayed = Constants.RESET_PAYMENT;
    }

    /**
     * Updates student status for studying abroad changes.
     *
     * @param status studying abroad status.
     */
    public void setStudentStatus(String status) {
        this.studentStatus = status;
    }

    /**
     * Updates credits for studying abroad changes.
     *
     * @param credits new credit hour number.
     */
    public void setCreditHours(int credits) {
        this.creditHours = credits;
    }

    /**
     * Sets studying abroad status for tuition calculations.
     *
     * @param abroad studying abroad truth value.
     */
    public void setStudyingAbroad(boolean abroad) {
        this.studyingAbroad = abroad;
    }

    /**
     * Financial aid usage tracker.
     *
     * @param used financial aid used status.
     */
    public void setFinancialAidUsed(boolean used) {
        this.financialAidUsed = used;
    }

    /**
     * Sets the decimal format to apply to tuition due and last payment.
     */
    DecimalFormat df = new DecimalFormat("###,##0.00");

    /**
     * Returns a textual representation of an album.
     *
     * @return a string containing the student information.
     */
    @Override
    public String toString() {
        //Example format: John Doe:EE:18 credit hours:tuition due:0.00:last payment:0.00:payment date: --/--/--:resident
        String dateInRoster;

        if (getPaymentDate() == null) {
            dateInRoster = " --/--/--";
        } else {
            dateInRoster = " " + getPaymentDate().formattedDate();
        }

        String printFormat = getProfile().toString() +
                getCreditHours() + " credit hours:tuition due:" + df.format(getTuitionDue()) +
                ":last payment:" + df.format(getAmountPayed()) +
                ":payment date:" + dateInRoster +
                ":" + getStudentStatus();

        return printFormat;
    }
}