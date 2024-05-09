package com.example.studentmanagement.entity.Timetable;

import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

/**
 * Represents a timeslot of a module with the related room.
 * author: Lingxu Huang
 * date: May 3rd 2024
 */
public class TimeslotModuleRoom {

    @TableId
    private int timeslotId;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String moduleId;

    private String roomId;

    /**
     * Gets the unique identifier of a TimeslotModuleRoom entity
     * @return the unique identifier
     */
    public int getTimeslotId() {
        return timeslotId;
    }

    /**
     * Sets the unique identifier of a TimeslotModuleRoom entity
     * @param timeslotId the unique identifier
     */
    public void setTimeslotId(int timeslotId) {
        this.timeslotId = timeslotId;
    }

    /**
     * Gets the start time of a lesson of a module
     * @return the start time
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the start time of a lesson of a module
     * @param startDateTime the start time
     */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * Gets the end time of a lesson of a module
     * @return the end time
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Sets the end time of a lesson of a module
     * @param endDateTime the end time
     */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * Gets the module ID associated with this timeslot and room
     * @return the module ID
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * Sets the module ID associated with this timeslot and room
     * @param moduleId the module ID
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * Gets the room ID associated with this timeslot and module
     * @return the room ID
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Sets the room ID associated with this timeslot and module
     * @param roomId the room ID
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    /**
     * The toString method to print out information of a TimeslotModuleRoom object
     * @return the information
     */
    @Override
    public String toString() {
        return "TimeslotModuleRoom{" +
                "timeslotId=" + timeslotId +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", moduleId='" + moduleId + '\'' +
                ", roomId='" + roomId + '\'' +
                '}'+"\n";
    }
}
