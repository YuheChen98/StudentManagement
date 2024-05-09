package com.example.studentmanagement.entity.User;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.studentmanagement.entity.Programme.Programme;

import java.util.List;


/**
 * Represents a tutor within the student management system.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
public class Tutor {
    @TableId
    private String tutorId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String information;

    @TableField(exist = false)
    private Programme programme;

    @TableField(exist = false)
    private List<Student> students;

    /**
     * Default constructor for Tutor.
     */
    public Tutor() {}

    /**
     * Gets the unique identifier of the tutor.
     * @return the unique identifier for the tutor
     */
    public String getTutorId() {
        return tutorId;
    }

    /**
     * Sets the unique identifier of the tutor.
     * @param tutorId the unique identifier to set
     */
    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    /**
     * Gets the password of the tutor.
     * @return the password of the tutor
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the tutor.
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the first name of the tutor.
     * @return the first name of the tutor
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the tutor.
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the tutor.
     * @return the last name of the tutor
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the tutor.
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the tutor.
     * @return the email of the tutor
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the tutor.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the informational description of the tutor.
     * @return the informational description of the tutor
     */
    public String getInformation() {
        return information;
    }

    /**
     * Sets the informational description of the tutor.
     * @param information the informational description to set
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * Gets the programme associated with the tutor.
     * @return the programme in which the tutor teaches
     */
    public Programme getProgramme() {
        return programme;
    }

    /**
     * Sets the programme associated with the tutor.
     * @param programme the programme to associate with the tutor
     */
    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    /**
     * Gets the list of students mentored by the tutor.
     * @return a list of students under the tutor's mentorship
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets the list of students mentored by the tutor.
     * @param students the list of students to be mentored by the tutor
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}