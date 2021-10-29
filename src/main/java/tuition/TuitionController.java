package tuition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.DecimalFormat;

/**
 * Controller class to control user interaction..
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class TuitionController {

    /**
     * Roster initialized through the Roster constructor;
     */
    Roster studentRoster = new Roster();

    /**
     * TextArea to provide output text based on user actions.
     */
    @FXML
    private TextArea returnText;

    /**
     * TextField for the student name in the Student Profiles tab.
     */
    @FXML
    private TextField studentName;

    /**
     * TextField for the student name in the Payments / Financial Aid tab.
     */
    @FXML
    private TextField studentNamePaying;

    /**
     * TextField for the student name in the Change Study Abroad tab.
     */
    @FXML
    private TextField abroadStudentName;

    /**
     * TextField for the amount to finance in the Payments / Financial Aid tab.
     */
    @FXML
    private TextField amountFinanced;

    /**
     * TextField for the number of credits in the Student Profiles tab.
     */
    @FXML
    private TextField numCredits;

    /**
     * TextField for the payment amount in the Payments / Financial Aid tab.
     */
    @FXML
    private TextField paymentAmount;

    /**
     * TextArea to output the tuition due of a given student.
     */
    @FXML
    private TextArea amountDue;

    /**
     * Add student button to add a student.
     */
    @FXML
    private Button addStudentButton;

    /**
     * Remove student button to remove a student.
     */
    @FXML
    private Button removeStudentButton;

    /**
     * Tuition due button to calculate a student's tuition.
     */
    @FXML
    private Button tuitionDue;

    /**
     * Print roster button to print out information regarding students in the roster.
     */
    @FXML
    private Button printRoster;

    /**
     * Print roster by name button to print out information regarding students in the roster sorted by name.
     */
    @FXML
    private Button printRosterByName;

    /**
     * Print students with payment button to print out information regarding students in the roster who have paid sorted by payment date.
     */
    @FXML
    private Button printStudentsWithPayment;

    /**
     * Resident radio button.
     */
    @FXML
    private RadioButton residentButton;

    /**
     * Non-resident radio button.
     */
    @FXML
    private RadioButton nonResidentButton;

    /**
     * Major toggle group in Student Profiles tab.
     */
    @FXML
    private ToggleGroup major;

    /**
     * Major toggle group in Payments / Financial Aid tab.
     */
    @FXML
    private ToggleGroup majorPayment;

    /**
     * Major toggle group in Change Study Abroad tab.
     */
    @FXML
    private ToggleGroup majorAbroad;

    /**
     * Resident or Non-resident toggle group.
     */
    @FXML
    private ToggleGroup residentOrNot;

    /**
     * Tristate state toggle group.
     */
    @FXML
    private ToggleGroup state;

    /**
     * Tristate or International toggle group.
     */
    @FXML
    private ToggleGroup nonResidentGrp;

    /**
     * NY state radio button.
     */
    @FXML
    private RadioButton nyState;

    /**
     * CT state radio button.
     */
    @FXML
    private RadioButton ctState;

    /**
     * Tristate radio button.
     */
    @FXML
    private RadioButton triState;

    /**
     * International radio button.
     */
    @FXML
    private RadioButton international;

    /**
     * Studying abroad check box.
     */
    @FXML
    private CheckBox studyAbroad;

    /**
     * Date picker for Payments / Financial Aid tab.
     */
    @FXML
    private DatePicker datePicker;

    /**
     * Searches the roster to find if a student exists or not.
     *
     * @param name             name of the student.
     * @param major            major of the student.
     * @param rosterCollection the student roster.
     * @return the student if found, null otherwise.
     */
    private Student uniqueStudent(String name, Major major, Roster rosterCollection) {
        Profile comparing = new Profile(name, major);
        for (int i = 0; i < rosterCollection.getSize(); i++) {
            if (rosterCollection.getStudentRoster()[i].getProfile().equals(comparing)) {

                return rosterCollection.getStudentRoster()[i];
            }
        }
        return null;
    }

    /**
     * Gets the chosen major in the major toggle group.
     *
     * @param majorButton the chosen major.
     * @return the major and Major type.
     */
    private Major getMajor(RadioButton majorButton) {
        String majorSelect = majorButton.getText();
        Major major = switch (majorSelect) {
            case "CS" -> Major.CS;
            case "EE" -> Major.EE;
            case "ME" -> Major.ME;
            case "IT" -> Major.IT;
            default -> Major.BA;
        };

        return major;
    }

    /**
     * Resets the options once Add, Remove, or Tuition due is pressed.
     * Resets by unselecting and setting disables.
     */
    private void resetInputs() {
        residentButton.setSelected(false);
        nonResidentButton.setSelected(false);
        triState.setSelected(false);
        triState.setDisable(true);
        nyState.setSelected(false);
        nyState.setDisable(true);
        ctState.setSelected(false);
        ctState.setDisable(true);
        international.setDisable(true);
        international.setSelected(false);
        studyAbroad.setDisable(true);
        studyAbroad.setSelected(false);
    }

    /**
     * Disables all non-resident related options when the resident radio button is selected.
     *
     * @param event the action events.
     */
    @FXML
    void selectResident(ActionEvent event) {
        triState.setDisable(true);
        nyState.setDisable(true);
        ctState.setDisable(true);
        international.setDisable(true);
        studyAbroad.setDisable(true);

        triState.setSelected(false);
        nyState.setSelected(false);
        ctState.setSelected(false);
        international.setSelected(false);
        studyAbroad.setSelected(false);
    }

    /**
     * Disables the resident radio button and extra options for tristate and international radio buttons.
     * The extra options for tristate is the NY and CT radio buttons.
     * The extra options for international is the study abroad checkbox.
     *
     * @param event the action events.
     */
    @FXML
    void selectNonResident(ActionEvent event) {
        triState.setDisable(false);
        nyState.setDisable(true);
        ctState.setDisable(true);
        international.setDisable(false);
        studyAbroad.setDisable(true);
        triState.setSelected(false);
        nyState.setSelected(false);
        ctState.setSelected(false);
        international.setSelected(false);
        studyAbroad.setSelected(false);
    }


    /**
     * Disables international extra options if tristate is selected.
     *
     * @param event the action events.
     */
    @FXML
    void selectTristate(ActionEvent event) {
        international.setSelected(false);
        studyAbroad.setSelected(false);
        studyAbroad.setDisable(true);
        triState.setDisable(false);
        nyState.setDisable(false);
        ctState.setDisable(false);
    }

    /**
     * Disables tristate extra options if international is selected.
     *
     * @param event the action events.
     */
    @FXML
    void selectInternational(ActionEvent event) {
        triState.setSelected(false);
        international.setDisable(false);
        studyAbroad.setDisable(false);
        nyState.setDisable(true);
        nyState.setSelected(false);
        ctState.setDisable(true);
        ctState.setSelected(false);
    }

    /**
     * Event handler for the Add button.
     * Used to add a student.
     *
     * @param event the action events.
     */
    @FXML
    void add(ActionEvent event) {
        String name = studentName.getText();

        if (name.isEmpty()) {
            returnText.appendText("Missing name.\n");
            return;
        }

        int stuCredits = Constants.ZERO_CREDITS;

        try {
            stuCredits = Integer.parseInt(numCredits.getText());
        } catch (IllegalArgumentException exception) {
            returnText.appendText("Invalid credits amount.\n");
            return;
        }

        if (stuCredits < Constants.ZERO_CREDITS) {
            returnText.appendText("Credits can't be negative.\n");
            return;
        } else if (stuCredits < Constants.MIN_CREDITS) {
            returnText.appendText("Minimum credits is 3.\n");
            return;
        } else if (stuCredits > Constants.MAX_CREDITS) {
            returnText.appendText("Maximum credits is 24.\n");
            return;
        }

        RadioButton majorButton = (RadioButton) major.getSelectedToggle();
        if (majorButton == null) {
            returnText.appendText("Missing major.\n");
            return;
        }

        Major major = getMajor(majorButton);

        RadioButton residentStatus = (RadioButton) residentOrNot.getSelectedToggle();

        if (residentStatus == null) {
            returnText.appendText("Missing data.\n");
            return;
        }

        String residentType = residentStatus.getText();


        if (uniqueStudent(name, major, studentRoster) != null) {
            returnText.appendText("Student already exists in the roster.\n");
        } else if (residentType.equals("Resident")) {
            Student ar = new Resident(name, major, stuCredits);
            studentRoster.add(ar);
            returnText.appendText("Student added.\n");
        } else if (residentType.equals("Non-Resident")) {
            boolean tristateSelected = triState.isSelected();
            boolean intlSelected = international.isSelected();

            if (tristateSelected == false && intlSelected == false) {
                Student nr = new NonResident(name, major, stuCredits);
                studentRoster.add(nr);
                returnText.appendText("Student added.\n");
            } else if (tristateSelected == true) {
                RadioButton statePicked = (RadioButton) state.getSelectedToggle();
                if (statePicked == null) {
                    returnText.appendText("No tristate state selected.\n");
                    return;
                }

                String stateName = statePicked.getText();

                if (stateName.equals("New York")) {
                    Student triNr = new TriState(name, major, stuCredits, "NY");
                    studentRoster.add(triNr);
                    returnText.appendText("Student added.\n");
                } else {
                    Student triNr = new TriState(name, major, stuCredits, "CT");
                    studentRoster.add(triNr);
                    returnText.appendText("Student added.\n");
                }
            } else {
                boolean studyingAbroadCheck = studyAbroad.isSelected();

                if (stuCredits < Constants.FULL_TIME_MIN_CREDITS) {
                    returnText.appendText("International students must have 12 credits or more.\n");
                    return;
                }

                if (stuCredits > Constants.FULL_TIME_MIN_CREDITS && studyingAbroadCheck == true) {
                    returnText.appendText("Studying abroad must be 12 credits.\n");
                    return;
                }

                if (studyingAbroadCheck == true) {
                    Student intlStu = new International(name, major, stuCredits, true);
                    studentRoster.add(intlStu);
                    returnText.appendText("Student added.\n");
                } else {
                    Student intlStu = new International(name, major, stuCredits, false);
                    studentRoster.add(intlStu);
                    returnText.appendText("Student added.\n");
                }
            }
        }
        resetInputs();
        numCredits.clear();

    }

    /**
     * Event handler for the Remove button.
     * Used to remove a student.
     *
     * @param event the action events.
     */
    @FXML
    void remove(ActionEvent event) {

        String name = studentName.getText();

        if (name.isEmpty()) {
            returnText.appendText("Missing name.\n");
            return;
        }

        RadioButton majorButton = (RadioButton) major.getSelectedToggle();
        if (majorButton == null) {
            returnText.appendText("Missing major.\n");
            return;
        }

        Major major = getMajor(majorButton);

        if (uniqueStudent(name, major, studentRoster) == null) {
            returnText.appendText("Student is not in the roster.\n");
        } else {
            Student studentToRemove = uniqueStudent(name, major, studentRoster);
            studentRoster.remove(studentToRemove);
            returnText.appendText("Student removed.\n");
            resetInputs();
        }
    }

    /**
     * Event handler for the Tuition Due button.
     * Used to calculate a given student's tuition.
     *
     * @param event the action events.
     */
    @FXML
    void calculateTuition(ActionEvent event) {

        String name = studentName.getText();

        if (name.isEmpty()) {
            returnText.appendText("Missing name.\n");
            return;
        }

        RadioButton majorButton = (RadioButton) major.getSelectedToggle();
        if (majorButton == null) {
            returnText.appendText("Missing major.\n");
            return;
        }

        Major major = getMajor(majorButton);

        if (uniqueStudent(name, major, studentRoster) == null) {
            returnText.appendText("Student is not in the roster.\n");
            amountDue.clear();
        } else {
            Student studentToRemove = uniqueStudent(name, major, studentRoster);
            studentToRemove.tuitionDue();
            double cost = studentToRemove.getTuitionDue();

            /**
             * Sets the decimal format to apply to tuition due and last payment.
             */
            DecimalFormat df = new DecimalFormat("###,##0.00");
            amountDue.setText(String.valueOf(df.format(cost)) + "\n");
            returnText.appendText("Tuition calculated.\n");
            resetInputs();
            return;
        }
    }

    /**
     * Event handler for the Pay button.
     * Adds a payment to a certain student's tuition.
     *
     * @param event the action events.
     */
    @FXML
    void paying(ActionEvent event) {
        String name = studentNamePaying.getText();

        if (name.isEmpty()) {
            returnText.appendText("Missing name.\n");
            return;
        }

        RadioButton majorButton = (RadioButton) majorPayment.getSelectedToggle();
        if (majorButton == null) {
            returnText.appendText("Missing major.\n");
            return;
        }

        Major major = getMajor(majorButton);

        if (uniqueStudent(name, major, studentRoster) == null) {
            returnText.appendText("Student is not in the roster.\n");
            return;
        }

        double paying = Constants.MIN_PAYMENT;

        try {
            paying = Double.valueOf(paymentAmount.getText());
        } catch (IllegalArgumentException exception) {
            returnText.appendText("Invalid payment amount.\n");
            return;
        }

        String date;

        try {
            date = datePicker.getValue().toString();
        } catch (NullPointerException exception) {
            returnText.appendText("Date of payment missing.\n");
            return;
        }

        Date dateOfPayment = new Date(date);

        if (dateOfPayment.isValid() == false) {
            returnText.appendText("Invalid payment date.\n");
            return;
        } else {
            Student studentPaying = uniqueStudent(name, major, studentRoster);
            studentPaying.tuitionDue();
            double remainingTuition = studentPaying.getTuitionDue();

            if (paying > remainingTuition) {
                returnText.appendText("Amount is greater than amount due.\n");
                return;
            } else {
                studentPaying.setAmountPayed(paying, dateOfPayment);
                studentPaying.tuitionDue();
                returnText.appendText("Payment applied.\n");
                return;
            }
        }
    }

    /**
     * Event handler for the Set button.
     * Sets a financial aid amount for a given Resident student.
     *
     * @param event the action events.
     */
    @FXML
    void setFinancial(ActionEvent event) {
        String name = studentNamePaying.getText();

        if (name.isEmpty()) {
            returnText.appendText("Missing name.\n");
            return;
        }

        RadioButton majorButton = (RadioButton) majorPayment.getSelectedToggle();
        if (majorButton == null) {
            returnText.appendText("Missing major.\n");
            return;
        }

        Major major = getMajor(majorButton);

        if (uniqueStudent(name, major, studentRoster) == null) {
            returnText.appendText("Student is not in the roster.\n");
            return;
        }

        Student stuToFinance = uniqueStudent(name, major, studentRoster);
        stuToFinance.tuitionDue();

        double financeRequest = Constants.MIN_PAYMENT;

        try {
            financeRequest = Double.valueOf(amountFinanced.getText());
        } catch (IllegalArgumentException except) {
            returnText.appendText("Invalid finance amount.\n");
            return;
        }

        if (financeRequest > Constants.MAX_FINANCE) {
            returnText.appendText("Finance amount cannot exceed $10000.\n");
            return;
        } else if (financeRequest < Constants.MIN_FINANCE) {
            returnText.appendText("Finance amount can't be negative.\n");
            return;
        } else if (financeRequest > stuToFinance.getTuitionDue()) {
            returnText.appendText("Finance amount can't be more than tuition due.\n");
            return;
        }

        if (stuToFinance.getStudentStatus().equals("resident")) {
            if (stuToFinance.getCreditHours() >= Constants.FULL_TIME_MIN_CREDITS) {
                if (stuToFinance.getFinancialAidUsed() == false) {
                    stuToFinance.setTuitionDue(stuToFinance.getTuitionDue() - financeRequest);
                    stuToFinance.setFinancialAidUsed(true);
                    returnText.appendText("Financial aid applied.\n");
                    return;
                } else {
                    returnText.appendText("Student already used financing once.\n");
                    return;
                }
            } else {
                returnText.appendText("Resident Student must be fulltime to qualify for financing.\n");
                return;
            }
        } else {
            returnText.appendText("Student must be a resident to qualify for financing.\n");
            return;
        }
    }

    /**
     * Event handler for the Change Student to Studying Abroad button.
     * Changes their status to studying abroad if they were International.
     *
     * @param event the action events.
     */
    @FXML
    void changeStudentStatus(ActionEvent event) {
        String name = abroadStudentName.getText();

        if (name.isEmpty()) {
            returnText.appendText("Missing name.\n");
            return;
        }

        RadioButton majorButton = (RadioButton) majorAbroad.getSelectedToggle();
        if (majorButton == null) {
            returnText.appendText("Missing major.\n");
            return;
        }

        Major major = getMajor(majorButton);

        if (uniqueStudent(name, major, studentRoster) == null) {
            returnText.appendText("Student is not in the roster.\n");
            return;
        }

        Student studentToChange = uniqueStudent(name, major, studentRoster);

        if (studentToChange.getStudentStatus().contains("non-resident:international")) {
            if (studentToChange.getStuydingAbroad() == false) {
                studentToChange.setStudentStatus("non-resident:international:study abroad");
                studentToChange.setCreditHours(Constants.FULL_TIME_MIN_CREDITS);
                studentToChange.setStudyingAbroad(true);
                studentToChange.setPaymentDate(null);
                studentToChange.resetAmountPayed();
                studentToChange.tuitionDue();
                returnText.appendText("Student is now a study abroad student.\n");
                return;
            } else {
                returnText.appendText("Student is already a study abroad student.\n");
                return;
            }
        } else {
            returnText.appendText("Student must be international to be a study abroad student.\n");
            return;
        }
    }

    /**
     * Event handler for the Process Roster Tuitions button.
     * Process the tuitions for all students in the roster.
     *
     * @param event the action events.
     */
    @FXML
    void processTuitions(ActionEvent event) {
        if (studentRoster.getSize() == 0) {
            returnText.appendText("Roster is empty.\n");
            return;
        } else {
            for (int i = 0; i < studentRoster.getSize(); i++) {
                studentRoster.getStudentRoster()[i].tuitionDue();
            }
            returnText.appendText("Tuitions calculated.\n");
            return;
        }
    }

    /**
     * Event handler for the Print Roster Button.
     * Prints all students in the roster in current order.
     *
     * @param event the action events.
     */
    @FXML
    void printRoster(ActionEvent event) {
        if (studentRoster.getSize() == 0) {
            returnText.appendText("Roster is empty.\n");
            return;
        } else {
            for (int i = 0; i < studentRoster.getSize(); i++) {
                studentRoster.getStudentRoster()[i].tuitionDue();
            }
            returnText.appendText("------------------------------\n");
            returnText.appendText(studentRoster.print());
            returnText.appendText("------------------------------\n");
            return;
        }
    }

    /**
     * Event handler for the Print Roster By Name Button.
     * Prints all students in the roster by name.
     *
     * @param event the action events.
     */
    @FXML
    void printRosterByName(ActionEvent event) {
        if (studentRoster.getSize() == 0) {
            returnText.appendText("Roster is empty.\n");
            return;
        } else {
            for (int i = 0; i < studentRoster.getSize(); i++) {
                studentRoster.getStudentRoster()[i].tuitionDue();
            }
            returnText.appendText("------------------------------\n");
            returnText.appendText(studentRoster.printByName());
            returnText.appendText("------------------------------\n");
            return;
        }
    }

    /**
     * Event handler for the Print Students With Payment button.
     * Prints all students who have paid in order of payment date.
     *
     * @param event the action events.
     */
    @FXML
    void printStudentsWithPayment(ActionEvent event) {
        if (studentRoster.getSize() == 0) {
            returnText.appendText("Roster is empty.\n");
            return;
        }

        for (int i = 0; i < studentRoster.getSize(); i++) {
            studentRoster.getStudentRoster()[i].tuitionDue();
        }

        int paidStudents = 0;
        Student temp;
        Roster studentPaidRoster = new Roster();
        for (int i = 0; i < studentRoster.getSize(); i++) {
            if (studentRoster.getStudentRoster()[i].getAmountPayed() != 0) {
                temp = studentRoster.getStudentRoster()[i];
                studentPaidRoster.add(temp);
                paidStudents++;
            }
        }
        if (paidStudents == 0) {
            returnText.appendText("No students have paid.\n");
            return;
        } else {
            returnText.appendText("------------------------------\n");
            returnText.appendText(studentPaidRoster.printByDate());
            returnText.appendText("------------------------------\n");
        }
    }
}