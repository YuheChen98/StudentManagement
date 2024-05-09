package com.example.studentmanagement.entity.Notification;


import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
/**
 * Represents a notification within the student management system.
 * This class holds details about specific notification, including send time,senderId,receiverId,related content,
 * booleans for recording accepted or rejected, userId of student, tutor, lecturer and administrator to record who this
 * notification belongs to.
 * The field notificationType could include type of absence,meeting,courseworkextension, suspension,withdrawn to represent
 * five kinds of requests for students, and a type of normal to represent the notification received by student when a request
 * is accepted and rejected.
 * Field absenceDate associate with type absence, meetingStartDateTime ,meetingEndDateTime and meetingRoomId associate with meeting,
 * moduleId and examCourseworkId associate with courseworkextension, programmeId associate with suspension and withdrawn.
 * author: Lingxu Huang
 * modified by Lingxu Huang at May 5th to use LocalDateTime for sendTime and LocalDate for absenceDate
 * date: April 27th 2024,mod May 5th
 */
public class Notification {

    @TableId
    private int notificationId;

    private LocalDateTime sendTime;

    private String senderId;

    private String receiverId;

    private String content;

    private boolean isAccepted;

    private boolean isRejected;

    private String adminId;

    private String studentId;

    private String tutorId;

    private String lecturerId;

    private String examCourseworkId;

    private String moduleId;

    private String programmeId;

    private LocalDate absenceDate;

    private LocalDateTime meetingStartDateTime;

    private LocalDateTime meetingEndDateTime;

    private String meetingRoomId;

    private String notificationType;

    /**
     * Gets the unique identifier of the notification.
     * @return the notification ID
     */
    public int getNotificationId() {
        return notificationId;
    }


    /**
     * Sets the unique identifier of the notification
     * @param notificationId the unique identifier to set
     */
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * Gets the send time of notification
     * @return the send time of notification
     */
    public LocalDateTime getSendTime() {
        return sendTime;
    }

    /**
     * Sets the send time of notification
     * @param sendTime the send time of notification
     */
    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * Gets the sender ID of notification
     * @return the sender ID
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * Sets the sender ID of notification
     * @param senderId the sender ID
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * Gets the receiver ID of notification
     * @return the receiver ID
     */
    public String getReceiverId() {
        return receiverId;
    }


    /**
     * Sets the receiver ID of notification
     * @param receiverId the receiver ID
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * Gets the content of notification
     * @return the notification content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of notification
     * @param content the notification content
     */
    public void setContent(String content) {
        this.content = content;
    }


    /**
     * Gets the accepted status of notification
     * @return true if accepted, false if not
     */
    public boolean getIsAccepted() {
        return isAccepted;
    }

    /**
     * Sets the accepted status of notification
     * @param isAccepted the status to be set
     */
    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
    /**
     * Gets the rejected status of notification
     * @return true if rejected, false if not
     */
    public boolean getIsRejected() {
        return isRejected;
    }

    /**
     * Sets the rejected status of notification
     * @param isRejected the status to be set
     */
    public void setIsRejected(boolean isRejected) {
        this.isRejected = isRejected;
    }

    /**
     * Gets the administrator ID of notification
     * @return the administrator ID
     */
    public String getAdminId() {
        return adminId;
    }


    /**
     * Sets the administrator ID of notification
     * @param adminId the administrator ID
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    /**
     * Gets the student ID of notification
     * @return the student ID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the student ID of notification
     * @param studentId the student ID
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the tutor ID of notification
     * @return the tutor ID
     */
    public String getTutorId() {
        return tutorId;
    }

    /**
     * Sets the tutor ID of notification
     * @param tutorId the tutor ID
     */
    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    /**
     * Gets the lecturer ID of notification
     * @return the lecturer ID
     */
    public String getLecturerId() {
        return lecturerId;
    }

    /**
     * Sets the lecturer ID of notification
     * @param lecturerId the lecturer ID
     */
    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    /**
     * Gets the type of notification
     * @return the type of notification
     */
    public String getNotificationType() {
        return notificationType;
    }

    /**
     * Sets the type of notification
     * @param notificationType the type of notification
     */
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * Gets the ID of exam or coursework
     * @return the ID of exam or coursework
     */
    public String getExamCourseworkId() {
        return examCourseworkId;
    }

    /**
     * Sets the ID of exam or coursework
     * @param examCourseworkId the ID of exam or coursework
     */
    public void setExamCourseworkId(String examCourseworkId) {
        this.examCourseworkId = examCourseworkId;
    }

    /**
     * Gets the module ID for a notification of type "courseworkextension"
     * @return the module ID
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * Sets the module ID for a notification of type "courseworkextension"
     * @param moduleId the module ID
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * Gets the programme ID for a notification of type "suspension" or "withdrawn"
     * @return the programme ID
     */
    public String getProgrammeId() {
        return programmeId;
    }

    /**
     * Sets the programme ID for a notification of type "suspension" or "withdrawn"
     * @param programmeId the programme ID
     */
    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * Gets the date of absence for a notification of type "absence"
     * @return the date of absence
     */
    public LocalDate getAbsenceDate() {
        return absenceDate;
    }

    /**
     * Sets the date of absence for a notification of type "absence"
     * @param absenceDate the date of absence
     */
    public void setAbsenceDate(LocalDate absenceDate) {
        this.absenceDate = absenceDate;
    }

    /**
     * Gets the meeting start time for a notification of type "meeting"
     * @return the start time of meeting
     */
    public LocalDateTime getMeetingStartDateTime() {
        return meetingStartDateTime;
    }

    /**
     * Sets the meeting start time for a notification of type "meeting"
     * @param meetingStartDateTime the start time of meeting
     */
    public void setMeetingStartDateTime(LocalDateTime meetingStartDateTime) {
        this.meetingStartDateTime = meetingStartDateTime;
    }

    /**
     * Gets the meeting end time for a notification of type "meeting"
     * @return the end time of meeting
     */
    public LocalDateTime getMeetingEndDateTime() {
        return meetingEndDateTime;
    }

    /**
     * Sets the meeting end time for a notification of type "meeting"
     * @param meetingEndDateTime the end time of meeting
     */
    public void setMeetingEndDateTime(LocalDateTime meetingEndDateTime) {
        this.meetingEndDateTime = meetingEndDateTime;
    }

    /**
     * Gets the room ID for a notification of type "meeting"
     * @return the room ID
     */
    public String getMeetingRoomId() {
        return meetingRoomId;
    }

    /**
     * Sets the room ID for a notification of type "meeting"
     * @param meetingRoomId the room ID
     */
    public void setMeetingRoomId(String meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }
}
