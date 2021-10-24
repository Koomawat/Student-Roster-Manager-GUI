package com.example.p2gui;

/**
 * Profile class to uniquely identify a student in the roster.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class Profile {

    /**
     * Name of student.
     *
     */
    private String name;
    /**
     * Major of student.
     *
     */
    private Major major; //5 majors and 2-charater each: CS, IT, BA, EE, ME

    /**
     * Profile constructor.
     *
     * @param name name of student.
     * @param major major of student.
     */
    public Profile (String name, Major major) {
        this.name = name;
        this.major = major;
    }

    /**
     * Gets the student name.
     *
     * @return name of student.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the student major.
     *
     * @return major of student.
     */
    public Major getMajor() {
        return major;
    }

    /**
     * Compares names and major to check if 2 students are equal.
     *
     * @param obj the profile object to compare.
     * @return true if the students are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile target_student = (Profile) obj;
            return (target_student.getName().equals(name) && target_student.getMajor() == (major));
        }
        return false;
    }

    /**
     * Returns a textual representation of an album.
     *
     * @return a string containing the student name and major.
     */
    @Override
    public String toString() {

        return getName() + ":" + getMajor() + ":";
    }
}