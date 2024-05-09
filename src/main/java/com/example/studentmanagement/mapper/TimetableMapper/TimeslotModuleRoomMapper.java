package com.example.studentmanagement.mapper.TimetableMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Timetable.TimeslotModuleRoom;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for the TimeslotModuleRoom entity, providing database interaction methods
 * to manage module's timeslot and used room.
 * author: Lingxu Huang
 * modified by Lingxu Huang to add method for retrieve all timeslots
 * date: May 3rd 2024, mod May 6th
 */
@Mapper
public interface TimeslotModuleRoomMapper extends BaseMapper<TimeslotModuleRoom> {

    /**
     * Retrieves all timeslot records of modules and rooms
     * @return a list of TimeslotModuleRoom object represent all records
     */
    @Select("select * from timeslot_module_room")
    List<TimeslotModuleRoom> SelectAll();

    /**
     * Retrieves all timeslot records of a room
     * @param roomId the unique identifier of a room
     * @return a list of TimeslotModuleRoom object associate with the specified room
     */
    @Select("select * from timeslot_module_room where (room_id = #{roomId})")
    List<TimeslotModuleRoom> SelectByRoomId(String roomId);

    /**
     * Retrieves all timeslot records of a module
     * @param moduleId the unique identifier of a module
     * @return a list of TimeslotModuleRoom object associate with the specified module
     */
    @Select("select * from timeslot_module_room where (module_id = #{moduleId})")
    List<TimeslotModuleRoom> SelectByModuleId(String moduleId);

    /**
     * Inserts a new timeslot record into the database.
     * @param timeslotModuleRoom the TimeslotModuleRoom object to be added
     * @return the number of rows affected by the insert operation
     */
    @Insert("insert into timeslot_module_room values (#{timeslotId},#{startDateTime},#{endDateTime},#{moduleId},#{roomId})")
    int add(TimeslotModuleRoom timeslotModuleRoom);

    /**
     * Deletes a new timeslot record into the database.
     * @param timeslotModuleRoom the TimeslotModuleRoom object to be removed
     * @return the number of rows affected by the delete operation
     */
    @Delete("delete from timeslot_module_room where (start_date_time = #{startDateTime} and end_date_time = #{endDateTime} and module_id = #{moduleId} and room_id = #{roomId})")
    int remove(TimeslotModuleRoom timeslotModuleRoom);

}
