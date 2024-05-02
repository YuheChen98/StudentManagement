package com.example.studentmanagement.entity.Programme;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.studentmanagement.entity.User.Student;

import java.util.Date;
import java.util.List;

public class ExamCoursework {
    @TableId
    private String examId;
    private String requirementDetails;
    private Date deadline;
    private String ifExam;
    @TableField(exist = false)
    private Module module;


    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getRequirementDetails() {
        return requirementDetails;
    }

    public void setRequirementDetails(String requirementDetails) {
        this.requirementDetails = requirementDetails;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getIfExam() {
        return ifExam;
    }

    public void setIfExam(String ifExam) {
        this.ifExam = ifExam;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

}
