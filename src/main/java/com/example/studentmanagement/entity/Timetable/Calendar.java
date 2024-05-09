package com.example.studentmanagement.entity.Timetable;

import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

/**
 * Represents a timeslot of a meeting for student and tutor, with related room.
 * author: Lingxu Huang
 * date: May 3rd 2024
 */
public class Calendar {

    @TableId
    private int meetingId;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String tutorId;

    private String studentId;

    private String roomId;

    /**
     * Gets the unique identifier of the calendar
     * @return the unique identifier
     */
    public int getMeetingId() {
        return meetingId;
    }

    /**
     * Sets the unique identifier of the calendar
     * @param meetingId the unique identifier
     */
    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    /**
     * Gets the start time of a meeting
     * @return the start time
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the start time of a meeting
     * @param startDateTime the start time
     */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * Gets the end time of a meeting
     * @return the end time
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Sets the end time of a meeting
     * @param endDateTime the end time
     */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * Gets the tutor ID of a meeting
     * @return the tutor ID
     */
    public String getTutorId() {
        return tutorId;
    }

    /**
     * Sets the tutor ID of a meeting
     * @param tutorId the tutor ID
     */
    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    /**
     * Gets the student ID of a meeting
     * @return the student ID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the student ID of a meeting
     * @param studentId the student ID
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the room ID of a meeting
     * @return the room ID
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Sets the room ID of a meeting
     * @param roomId the room ID
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * The toString method to print out information of a Calendar object
     * @return the information
     */
    @Override
    public String toString() {
        return "Calendar{" +
                "meetingId=" + meetingId +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", tutorId='" + tutorId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
