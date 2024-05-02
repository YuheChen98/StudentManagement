package com.example.studentmanagement.entity.Programme;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class StudentExam {
    @TableId
    private String studentId;
    private ExamCoursework examCoursework;
    private String ifSubmit;
    private String ifSuspend;
    private String mark;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public ExamCoursework getExamCoursework() {
        return examCoursework;
    }

    public void setExamCoursework(ExamCoursework examCoursework) {
        this.examCoursework = examCoursework;
    }

    public String getIfSubmit() {
        return ifSubmit;
    }

    public void setIfSubmit(String ifSubmit) {
        this.ifSubmit = ifSubmit;
    }

    public String getIfSuspend() {
        return ifSuspend;
    }

    public void setIfSuspend(String ifSuspend) {
        this.ifSuspend = ifSuspend;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
