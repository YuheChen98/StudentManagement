package com.example.studentmanagement.entity.Programme;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.studentmanagement.entity.User.Lecturer;
import java.util.List;

/**
 * Represents an academic module within the student management system.
 * This class includes details about the module such as its syllabus, timing, associated lecturer, and evaluations.
 */
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

    /**
     * Default constructor.
     */
    public Module() {}

    /**
     * Gets the unique identifier of the module.
     * @return the module ID
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * Sets the unique identifier for the module.
     * @param moduleId the new ID for the module
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * Gets the syllabus of the module.
     * @return the syllabus as a string
     */
    public String getSyllabus() {
        return syllabus;
    }

    /**
     * Sets the syllabus for the module.
     * @param syllabus the new syllabus
     */
    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    /**
     * Gets the semester number during which the module is taught.
     * @return the semester number
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Sets the semester number during which the module is to be taught.
     * @param semester the semester number
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Gets the weekly time commitment required for the module.
     * @return the weekly time commitment as a string
     */
    public String getTimeEveryWeek() {
        return timeEveryWeek;
    }

    /**
     * Sets the weekly time commitment for the module.
     * @param timeEveryWeek the weekly time commitment
     */
    public void setTimeEveryWeek(String timeEveryWeek) {
        this.timeEveryWeek = timeEveryWeek;
    }

    /**
     * Gets the lecturer associated with the module.
     * @return the lecturer teaching the module
     */
    public Lecturer getLecturer() {
        return lecturer;
    }

    /**
     * Sets the lecturer associated with the module.
     * @param lecturer the lecturer to be associated with the module
     */
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    /**
     * Gets the list of exams and coursework associated with the module.
     * @return a list of exam and coursework items
     */
    public List<ExamCoursework> getExamCourseworks() {
        return examCourseworks;
    }

    /**
     * Sets the exams and coursework associated with the module.
     * @param examCourseworks the list of exams and coursework to be associated with the module
     */
    public void setExamCourseworks(List<ExamCoursework> examCourseworks) {
        this.examCourseworks = examCourseworks;
    }
}
