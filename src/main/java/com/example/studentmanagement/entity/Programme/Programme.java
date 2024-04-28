package com.example.studentmanagement.entity.Programme;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.entity.User.Tutor;

import java.util.Date;
import java.util.List;

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
    public Programme(){

    }
    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public Date getStartYear() {
        return startYear;
    }

    public void setStartYear(Date startYear) {
        this.startYear = startYear;
    }

    public Date getEndYear() {
        return endYear;
    }

    public void setEndYear(Date endYear) {
        this.endYear = endYear;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getEntryRequirements() {
        return entryRequirements;
    }

    public void setEntryRequirements(String entryRequirements) {
        this.entryRequirements = entryRequirements;
    }

    public String getFeesAndFunding() {
        return feesAndFunding;
    }

    public void setFeesAndFunding(String feesAndFunding) {
        this.feesAndFunding = feesAndFunding;
    }

    public List<ProgrammeModule> getProgrammeModules() {
        return programmeModules;
    }

    public void setProgrammeModules(List<ProgrammeModule> programmeModules) {
        this.programmeModules = programmeModules;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }
}

