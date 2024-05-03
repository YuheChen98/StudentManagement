package com.example.studentmanagement.entity.Programme;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Represents a student's exam or coursework record within the student management system.
 * This class stores information about a specific exam or coursework assigned to a student, including submission status and grades.
 */
public class StudentExam {
    @TableId
    private String studentId; // Unique identifier for the student
    private ExamCoursework examCoursework; // Exam or coursework associated with this record

    @TableField
    private String ifSubmit; // Indicates whether the student has submitted the exam/coursework
    @TableField
    private String ifSuspend; // Indicates whether the student's exam/coursework has been suspended
    @TableField
    private String mark; // Grade or mark received by the student

    /**
     * Gets the student's unique identifier.
     * @return the unique identifier of the student
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the student's unique identifier.
     * @param studentId the unique identifier to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the exam or coursework associated with the student.
     * @return the associated exam or coursework
     */
    public ExamCoursework getExamCoursework() {
        return examCoursework;
    }

    /**
     * Sets the exam or coursework associated with the student.
     * @param examCoursework the exam or coursework to set
     */
    public void setExamCoursework(ExamCoursework examCoursework) {
        this.examCoursework = examCoursework;
    }

    /**
     * Gets the submission status of the exam or coursework.
     * @return "true" if submitted, otherwise "false"
     */
    public String getIfSubmit() {
        return ifSubmit;
    }

    /**
     * Sets the submission status for the exam or coursework.
     * @param ifSubmit "true" if the student has submitted, otherwise "false"
     */
    public void setIfSubmit(String ifSubmit) {
        this.ifSubmit = ifSubmit;
    }

    /**
     * Gets the suspension status of the exam or coursework.
     * @return "true" if suspended, otherwise "false"
     */
    public String getIfSuspend() {
        return ifSuspend;
    }

    /**
     * Sets the suspension status of the exam or coursework.
     * @param ifSuspend "true" if the exam or coursework is suspended, otherwise "false"
     */
    public void setIfSuspend(String ifSuspend) {
        this.ifSuspend = ifSuspend;
    }

    /**
     * Gets the mark or grade received by the student for the exam or coursework.
     * @return the mark or grade
     */
    public String getMark() {
        return mark;
    }

    /**
     * Sets the mark or grade for the exam or coursework.
     * @param mark the mark or grade to set
     */
    public void setMark(String mark) {
        this.mark = mark;
    }
}
