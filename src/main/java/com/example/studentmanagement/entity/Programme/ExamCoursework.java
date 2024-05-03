package com.example.studentmanagement.entity.Programme;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;

/**
 * Represents an exam or coursework within the student management system.
 * This class holds details about specific assessments, including deadlines, requirements, and associated modules.
 */
public class ExamCoursework {
    @TableId
    private String examId; // Unique identifier for the exam or coursework
    private String requirementDetails; // Detailed description of the exam or coursework requirements
    private Date deadline; // Deadline for submission of the exam or coursework
    private String ifExam; // Flag indicating whether this is an exam or coursework

    @TableField(exist = false)
    private Module module; // The module associated with this exam or coursework

    /**
     * Gets the unique identifier of the exam or coursework.
     * @return the unique identifier for the exam or coursework
     */
    public String getExamId() {
        return examId;
    }

    /**
     * Sets the unique identifier for the exam or coursework.
     * @param examId the unique identifier to set
     */
    public void setExamId(String examId) {
        this.examId = examId;
    }

    /**
     * Gets the details of the requirements for the exam or coursework.
     * @return the details of the requirements
     */
    public String getRequirementDetails() {
        return requirementDetails;
    }

    /**
     * Sets the details of the requirements for the exam or coursework.
     * @param requirementDetails the details to set
     */
    public void setRequirementDetails(String requirementDetails) {
        this.requirementDetails = requirementDetails;
    }

    /**
     * Gets the deadline for the exam or coursework submission.
     * @return the deadline date
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline for the exam or coursework submission.
     * @param deadline the deadline date to set
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * Gets the flag indicating whether this is an exam or coursework.
     * @return "true" if this is an exam, "false" if coursework
     */
    public String getIfExam() {
        return ifExam;
    }

    /**
     * Sets the flag indicating whether this is an exam or coursework.
     * @param ifExam "true" if this is an exam, otherwise "false"
     */
    public void setIfExam(String ifExam) {
        this.ifExam = ifExam;
    }

    /**
     * Gets the module associated with the exam or coursework.
     * @return the associated module
     */
    public Module getModule() {
        return module;
    }

    /**
     * Sets the module associated with the exam or coursework.
     * @param module the module to associate
     */
    public void setModule(Module module) {
        this.module = module;
    }

}
