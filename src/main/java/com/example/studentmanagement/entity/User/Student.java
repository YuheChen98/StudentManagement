package com.example.studentmanagement.entity.User;
import com.example.studentmanagement.entity.Programme.Programme;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.studentmanagement.entity.Programme.ProgrammeModule;

import java.util.Date;
import java.util.List;

@TableName("Student")
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

    public Student() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getIfWithdraw() {
        return ifWithdraw;
    }

    public void setIfWithdraw(Boolean ifWithdraw) {
        this.ifWithdraw = ifWithdraw;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public List<ProgrammeModule> getProgrammeModules() {
        return programmeModules;
    }

    public void setProgrammeModules(List<ProgrammeModule> programmeModules) {
        this.programmeModules = programmeModules;
    }
}

