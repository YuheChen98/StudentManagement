package com.example.studentmanagement.entity.Programme;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.studentmanagement.entity.User.Lecturer;

import java.util.Date;
import java.util.List;

public class Module {
    @TableId
    private String moduleId;
    private String syllabus;
    private Date term;
    private Date timeEveryWeek;
    @TableField(exist = false)
    private Lecturer lecturer;
    public Module(){
    }


    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public Date getTimeEveryWeek() {
        return timeEveryWeek;
    }

    public void setTimeEveryWeek(Date timeEveryWeek) {
        this.timeEveryWeek = timeEveryWeek;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}
