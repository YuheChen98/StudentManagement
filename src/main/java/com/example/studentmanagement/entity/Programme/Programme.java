package com.example.studentmanagement.entity.Programme;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.entity.User.Tutor;

import java.util.Date;
import java.util.List;

/**
 * Represents an academic programme within the student management system.
 * Includes comprehensive details about the programme such as its duration, content, entry requirements, and associated fees.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
public class Programme {
    @TableId
    private String programmeId;
    private Date startYear;
    private Date endYear;
    private String overview;
    private String structure;
    private String entryRequirements;
    private String feesAndFunding;

    @TableField(exist = false)
    private List<ProgrammeModule> programmeModules;

    @TableField(exist = false)
    private List<Student> students;

    @TableField(exist = false)
    private List<Tutor> tutors;

    /**
     * Default constructor for Programme.
     */
    public Programme() {}

    /**
     * Gets the unique identifier for the programme.
     * @return the programme ID
     */
    public String getProgrammeId() {
        return programmeId;
    }

    /**
     * Sets the unique identifier for the programme.
     * @param programmeId the new ID for the programme
     */
    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * Gets the start date of the programme.
     * @return the start date
     */
    public Date getStartYear() {
        return startYear;
    }

    /**
     * Sets the start date of the programme.
     * @param startYear the start date to set
     */
    public void setStartYear(Date startYear) {
        this.startYear = startYear;
    }

    /**
     * Gets the end date of the programme.
     * @return the end date
     */
    public Date getEndYear() {
        return endYear;
    }

    /**
     * Sets the end date of the programme.
     * @param endYear the end date to set
     */
    public void setEndYear(Date endYear) {
        this.endYear = endYear;
    }

    /**
     * Gets an overview of the programme.
     * @return the programme overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Sets an overview of the programme.
     * @param overview the overview to set
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * Gets the structural outline of the programme.
     * @return the programme structure
     */
    public String getStructure() {
        return structure;
    }

    /**
     * Sets the structural outline of the programme.
     * @param structure the structure to set
     */
    public void setStructure(String structure) {
        this.structure = structure;
    }

    /**
     * Gets the entry requirements for the programme.
     * @return the entry requirements
     */
    public String getEntryRequirements() {
        return entryRequirements;
    }

    /**
     * Sets the entry requirements for the programme.
     * @param entryRequirements the entry requirements to set
     */
    public void setEntryRequirements(String entryRequirements) {
        this.entryRequirements = entryRequirements;
    }

    /**
     * Gets the fees and funding details for the programme.
     * @return the fees and funding details
     */
    public String getFeesAndFunding() {
        return feesAndFunding;
    }

    /**
     * Sets the fees and funding details for the programme.
     * @param feesAndFunding the fees and funding details to set
     */
    public void setFeesAndFunding(String feesAndFunding) {
        this.feesAndFunding = feesAndFunding;
    }

    /**
     * Gets the list of modules associated with the programme.
     * @return a list of ProgrammeModules
     */
    public List<ProgrammeModule> getProgrammeModules() {
        return programmeModules;
    }

    /**
     * Sets the list of modules associated with the programme.
     * @param programmeModules the list of modules to set
     */
    public void setProgrammeModules(List<ProgrammeModule> programmeModules) {
        this.programmeModules = programmeModules;
    }

    /**
     * Gets the list of students enrolled in the programme.
     * @return a list of students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets the list of students enrolled in the programme.
     * @param students the list of students to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Gets the list of tutors teaching in the programme.
     * @return a list of tutors
     */
    public List<Tutor> getTutors() {
        return tutors;
    }

    /**
     * Sets the list of tutors teaching in the programme.
     * @param tutors the list of tutors to set
     */
    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }
}
