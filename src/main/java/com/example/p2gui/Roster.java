package com.example.p2gui;

/**
 * Container class to hold the student roster.
 * Contains operation methods to manage the collections.
 * Operations include: finding students, growing the collection, adding students, removing students, and printing the roster.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class Roster {

    /**
     * Student roster collection array.
     *
     */
    private Student[] roster;
    /**
     * Size of student roster.
     *
     */
    private int size; //keep track of the number of students in the roster

    /**
     * Roster constructor.
     * Sets the default size to 4.
     *
     */
    public Roster() {
        int baseRosterSize = 4;
        Student[] studentRoster = new Student[baseRosterSize];
        int rosterSize = Constants.EMPTY_ROSTER;
        this.roster = studentRoster;
        this.size = rosterSize;
    }

    /**
     * Finds a student in the roster.
     *
     * @param student student to find.
     * @return index of the student if found, NOT_FOUND otherwise.
     */
    private int find(Student student) {
        for (int i = 0; i < getSize(); i++) {
            if (getStudentRoster()[i].equals(student)) {
                return i;
            }
        }

        return Constants.NOT_FOUND;
    }

    /**
     * Gets the student roster.
     *
     * @return roster collection array.
     */
    public Student[] getStudentRoster(){
        return roster;
    }

    /**
     * Size of the collection.
     *
     * @return array size.
     */
    public int getSize(){
        return size;
    }

    /**
     * Grows collection by 4 when full.
     *
     */
    private void grow() {
        Student[] studentCopy = new Student[getSize()];

        for (int i = 0; i < getSize(); i++) {
            studentCopy[i] = getStudentRoster()[i];
        }

        int growSize = 4;

        Student[] studentsExpanded = new Student[getSize() + growSize];
        roster = (studentsExpanded);

        for (int i = 0; i < getSize(); i++) {
            getStudentRoster()[i] = studentCopy[i];
        }
    }

    /**
     * Adds a student to the roster.
     *
     * @param student student to add.
     * @return true if added, false otherwise.
     */
    public boolean add(Student student) {
        for (int i = 0; i < getSize(); i++) {
            if (student.equals(getStudentRoster()[i])) {
                return false;
            }
        }

        getStudentRoster()[getSize()] = student;

        size = (getSize() + Constants.ROSTER_SIZE_CHANGE);

        if (getSize() == getStudentRoster().length) {
            grow();
        }

        return true;
    }

    /**
     * Removes a student from the roster.
     *
     * @param student student to remove.
     * @return true if removed, false otherwise.
     */
    public boolean remove(Student student) {
        boolean studentExists = false;

        for (int i = 0; i < getSize(); i++) {
            if (student.equals(getStudentRoster()[i])) {
                studentExists = true;
            }
        }

        if (studentExists == false) {
            return false;
        }

        int indexOfAlbum = find(student);

        for (int i = indexOfAlbum; i < getSize() - Constants.ROSTER_SIZE_CHANGE; i++) {
            getStudentRoster()[i] = getStudentRoster()[i + Constants.ROSTER_SIZE_CHANGE];
        }

        size = (getSize() - Constants.ROSTER_SIZE_CHANGE);

        return true;
    }

    /**
     * Prints student roster in no specific order.
     *
     */
    public void print() {
        System.out.println("* list of students in the roster **");

        for (int i = 0; i < getSize(); i++) {
            String data = getStudentRoster()[i].toString();
            System.out.println(data);
        }

        System.out.println("* end of roster **");
    }

    /**
     * Prints student roster by name.
     *
     */
    public void printByName() {
        System.out.println("* list of students ordered by name **");

        for (int i = 0; i < getSize() - Constants.ROSTER_SIZE_CHANGE; i++) {
            for (int j = i + Constants.ROSTER_SIZE_CHANGE; j < getSize(); j++) {
                if(getStudentRoster()[i].getProfile().getName().compareTo(getStudentRoster()[j].getProfile().getName()) > 0) {
                    Student temp = getStudentRoster()[i];
                    getStudentRoster()[i] = getStudentRoster()[j];
                    getStudentRoster()[j] = temp;
                }
            }
        }

        for (int i = 0; i < getSize(); i++) {
            String data = getStudentRoster()[i].toString();
            System.out.println(data);
        }


        System.out.println("* end of roster **");
    }

    /**
     * Prints student roster by payment date.
     *
     */
    public void printByDate() {
        System.out.println("* list of students made payments ordered by payment date **");

        int dateCounts = 0;

        for (int i = 0; i < getSize(); i++) {
            if (getStudentRoster()[i].getPaymentDate() != null) {
                dateCounts++;
            }
        }

        Student[] dateRoster = new Student[dateCounts];

        int count = 0;

        for (int i = 0; i < getSize(); i++) {
            if (getStudentRoster()[i].getPaymentDate() != null) {
                dateRoster[count] = getStudentRoster()[i];
                count++;
            }
        }

        int zeroIndex = 0;

        Date earliestDate = dateRoster[zeroIndex].getPaymentDate();
        boolean[] visited = new boolean[dateRoster.length];
        int counter;
        for (int k = 0; k < dateRoster.length; k++) {
            visited[k] = false;
        }
        int earliestDate_Index = 0;

        for (int i = 0; i < dateRoster.length; i++) {
            for (int j = 0; j < dateRoster.length; j++) {
                if ((visited[j] == false && dateRoster[j].getPaymentDate().compareTo(earliestDate) < 0) ||
                        (visited[j] == false && dateRoster[j].getPaymentDate().compareTo(earliestDate) == 0)) { //Looping through to find earliest date that has not already been visited.
                    earliestDate = dateRoster[j].getPaymentDate();
                    earliestDate_Index = j;
                }
            }

            visited[earliestDate_Index] = true;

            for (int j = 0; j < dateRoster.length; j++) {
                if (visited[j] == false) {
                    counter = j;
                    earliestDate = dateRoster[counter].getPaymentDate();
                    break;
                }
            }

            //printing out the earliest date and marking it as visited.
            String data = dateRoster[earliestDate_Index].toString();
            System.out.println(data);

        }

        System.out.println("* end of roster **");
    }
}