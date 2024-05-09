package com.example.studentmanagement.mapper.TimetableMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Timetable.Calendar;
import com.example.studentmanagement.entity.Timetable.TimeslotModuleRoom;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * Mapper interface for the Calendar entity, providing database interaction methods to manage meeting timeslot and room for student and tutor.
 * author: Lingxu Huang
 * modified by Limgxu Huang at May 5th to add remove method
 * date: May 3rd 2024, mod May 5th
 */
@Mapper
public interface CalendarMapper extends BaseMapper<Calendar> {

    /**
     * Retrieves all calendar object of a student, according to student ID
     * @param studentId the unique identifier of a student
     * @return a list of calendar associated with the specified student
     */
    @Select("select * from calendar where (student_id = #{studentId})")
    List<Calendar> selectByStudentId(String studentId);

    /**
     * Retrieves all calendar object of a tutor, according to tutor ID
     * @param tutorId the unique identifier of a tutor
     * @return a list of calendar associated with the specified tutor
     */
    @Select("select * from calendar where (tutor_id = #{tutorId})")
    List<Calendar> selectByTutorId(String tutorId);

    /**
     * Retrieves all calendar object of a user, according to user ID
     * @param userId the unique identifier of a user
     * @return a list of calendar associated with the specified user
     */
    @Select("select * from calendar where (tutor_id = #{tutorId} or student_id = #{userId})")
    List<Calendar> selectByUserId(String userId);

    /**
     * Retrieves all calendar object of a room, according to room ID
     * @param roomId the unique identifier of a room
     * @return a list of calendar associated with the specified room
     */
    @Select("select * from calendar where (room_id = #{roomId})")
    List<Calendar> selectByRoomId(String roomId);


    /**
     * Inserts a new calendar record into the database.
     * @param calendar the calendar object to be added
     * @return the number of rows affected by the insert operation
     */
    @Insert("insert into calendar values (#{meetingId},#{startDateTime},#{endDateTime},#{tutorId},#{studentId},#{roomId})")
    int add(Calendar calendar);

    /**
     * Deletes a calendar record from the database.
     * @param calendar the calendar object to be removed
     * @return the number of rows affected by the delete operation
     */
    @Delete("delete from calendar where (start_date_time = #{startDateTime} and end_date_time = #{endDateTime} and student_id = #{studentId} and tutor_id = #{tutorId})")
    int remove(Calendar calendar);


}
