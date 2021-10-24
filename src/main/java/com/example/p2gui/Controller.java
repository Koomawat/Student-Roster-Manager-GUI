package com.example.p2gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;

/**
 * Controller class to control user interaction..
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class Controller {

    /**
     * Roster class;
     */
    Roster studentRoster = new Roster();

    /**
     *
     */
    @FXML
    private TextArea returnText;

    /**
     *
     */
    @FXML
    private TextField studentName;

    /**
     *
     */
    @FXML
    private TextField studentNamePaying;

    /**
     *
     */
    @FXML
    private TextField abroadStudentName;

    /**
     *
     */
    @FXML
    private TextField amountFinanced;

    /**
     *
     */
    @FXML
    private TextField numCredits;

    /**
     *
     */
    @FXML
    private TextField paymentAmount;

    /**
     *
     */
    @FXML
    private TextArea amountDue;

    /**
     *
     */
    @FXML
    private Button addStudentButton;

    /**
     *
     */
    @FXML
    private Button removeStudentButton;

    /**
     *
     */
    @FXML
    private Button tuitionDue;

    /**
     *
     */
    @FXML
    private RadioButton residentButton;

    /**
     *
     */
    @FXML
    private RadioButton nonResidentButton;

    /**
     *
     */
    @FXML
    private ToggleGroup major;

    /**
     *
     */
    @FXML
    private ToggleGroup majorPayment;

    /**
     *
     */
    @FXML
    private ToggleGroup majorAbroad;

    /**
     *
     */
    @FXML
    private ToggleGroup residentOrNot;

    /**
     *
     */
    @FXML
    private ToggleGroup state;

    /**
     *
     */
    @FXML
    private ToggleGroup tristateGroup;

    /**
     *
     */
    @FXML
    private ToggleGroup internationalGroup;

    /**
     *
     */
    @FXML
    private RadioButton nyState;

    /**
     *
     */
    @FXML
    private RadioButton ctState;

    /**
     *
     */
    @FXML
    private RadioButton triState;

    /**
     *
     */
    @FXML
    private RadioButton international;

    /**
     *
     */
    @FXML
    private CheckBox studyAbroad;

    @FXML
    private DatePicker datePicker;

    /**
     *
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
     *
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
     *
     */
    @FXML
    void selectTristate(ActionEvent event) {
        international.setDisable(true);
        studyAbroad.setDisable(true);
        triState.setDisable(false);
        nyState.setDisable(false);
        ctState.setDisable(false);
    }

    /**
     *
     */
    @FXML
    void selectInternational(ActionEvent event) {
        international.setDisable(false);
        studyAbroad.setDisable(false);
        triState.setDisable(true);
        nyState.setDisable(true);
        ctState.setDisable(true);
    }

    /**
     * @param name
     * @param major
     * @param rosterCollection
     * @return
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
     * @param majorButton
     * @return
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
     *
     */
    @FXML
    void add() {
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
                String stateName = statePicked.getText();

                if (stateName == "NY") {
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
        residentButton.setSelected(false);
        nonResidentButton.setSelected(false);
        international.setSelected(false);
        numCredits.clear();

    }

    /**
     *
     */
    @FXML
    void remove() {

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
            residentButton.setSelected(false);
            nonResidentButton.setSelected(false);

        }
    }

    /**
     *
     */
    @FXML
    void calculateTuition() {

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
            studentToRemove.tuitionDue();
            double cost = studentToRemove.getTuitionDue();

            /**
             * Sets the decimal format to apply to tuition due and last payment.
             */
            DecimalFormat df = new DecimalFormat("###,##0.00");
            amountDue.setText(String.valueOf(df.format(cost)) + "\n");
            returnText.appendText("Tuition calculated.\n");
            residentButton.setSelected(false);
            nonResidentButton.setSelected(false);
            return;
        }
    }

    /**
     *
     */
    @FXML
    void paying() {
        String name = studentNamePaying.getText();

        if (name.isEmpty()) {
            returnText.appendText("Missing name.\n");
            return;
        }

        RadioButton majorButton = (RadioButton) majorPayment.getSelectedToggle();
        if (majorButton == null) {
            returnText.appendText("Missing name.\n");
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

        String date = datePicker.getValue().toString();
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
     *
     */
    @FXML
    void setFinancial() {
        String name = studentNamePaying.getText();

        if (name.isEmpty()) {
            returnText.appendText("Missing name.\n");
            return;
        }

        RadioButton majorButton = (RadioButton) majorPayment.getSelectedToggle();
        if (majorButton == null) {
            returnText.appendText("Missing name.\n");
            return;
        }

        Major major = getMajor(majorButton);

        if (uniqueStudent(name, major, studentRoster) == null) {
            returnText.appendText("Student is not in the roster.\n");
            return;
        }

        Student stuToFinance = uniqueStudent(name, major, studentRoster);

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
}