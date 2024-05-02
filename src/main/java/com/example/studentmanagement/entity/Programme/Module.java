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
    private int semester;
    private String timeEveryWeek;
    @TableField(exist = false)
    private Lecturer lecturer;
    @TableField(exist = false)
    private List<ExamCoursework> examCourseworks;
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

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getTimeEveryWeek() {
        return timeEveryWeek;
    }

    public void setTimeEveryWeek(String timeEveryWeek) {
        this.timeEveryWeek = timeEveryWeek;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public List<ExamCoursework> getExamCourseworks() {
        return examCourseworks;
    }

    public void setExamCourseworks(List<ExamCoursework> examCourseworks) {
        this.examCourseworks = examCourseworks;
    }
}
