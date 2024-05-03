package com.example.studentmanagement.entity.User;
import com.example.studentmanagement.entity.Programme.Programme;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.studentmanagement.entity.Programme.ProgrammeModule;

import java.util.Date;
import java.util.List;

/**
 * Represents a student within the student management system.
 */
public class Student {
    @TableId
    private String studentId;
    private String password;

    private String firstName;
    private String lastName;
    private String email;
    private String information;
    private Date startTime;
    private Date endTime;
    private Boolean ifWithdraw;
    @TableField(exist = false)
    private Tutor tutor;
    @TableField(exist = false)
    private Programme programme;
    @TableField(exist = false)
    private List<ProgrammeModule> programmeModules;

    /**
     * Default constructor.
     */
    public Student() {
    }

    /**
     * Gets the student's unique identifier.
     * @return the student's ID
     */
    public String getStudentId() { return studentId; }

    /**
     * Sets the student's unique identifier.
     * @param studentId the student's ID to set
     */
    public void setStudentId(String studentId) { this.studentId = studentId; }

    /**
     * Gets the student's password.
     * @return the student's password
     */
    public String getPassword() { return password; }

    /**
     * Sets the student's password.
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Returns the first name of the student.
     * @return the first name of the student
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the student.
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the student.
     * @return the last name of the student
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the student.
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the email address of the student.
     * @return the email address of the student
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the student.
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the informational description about the student.
     * @return the informational description
     */
    public String getInformation() {
        return information;
    }

    /**
     * Sets the informational description about the student.
     * @param information the informational description to set
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * Returns the start date of the student's enrollment in the programme.
     * @return the start date of enrollment
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets the start date of the student's enrollment in the programme.
     * @param startTime the start date to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the end date of the student's enrollment in the programme.
     * @return the end date of enrollment
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Sets the end date of the student's enrollment in the programme.
     * @param endTime the end date to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns whether the student has withdrawn from the programme.
     * @return true if the student has withdrawn, false otherwise
     */
    public Boolean getIfWithdraw() {
        return ifWithdraw;
    }

    /**
     * Sets whether the student has withdrawn from the programme.
     * @param ifWithdraw the withdrawal status to set
     */
    public void setIfWithdraw(Boolean ifWithdraw) {
        this.ifWithdraw = ifWithdraw;
    }

    /**
     * Returns the assigned tutor for the student.
     * @return the tutor
     */
    public Tutor getTutor() {
        return tutor;
    }

    /**
     * Sets the assigned tutor for the student.
     * @param tutor the tutor to set
     */
    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    /**
     * Returns the programme in which the student is enrolled.
     * @return the programme
     */
    public Programme getProgramme() {
        return programme;
    }

    /**
     * Sets the programme in which the student is enrolled.
     * @param programme the programme to set
     */
    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    /**
     * Returns the list of programme modules the student is enrolled in.
     * @return the list of programme modules
     */
    public List<ProgrammeModule> getProgrammeModules() {
        return programmeModules;
    }

    /**
     * Sets the list of programme modules the student is enrolled in.
     * @param programmeModules the list of programme modules to set
     */
    public void setProgrammeModules(List<ProgrammeModule> programmeModules) {
        this.programmeModules = programmeModules;
    }
}

