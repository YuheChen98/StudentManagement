package com.example.studentmanagement.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.entity.User.Tutor;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper interface for the Tutor entity, providing database interaction methods to manage tutors.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@Mapper
public interface TutorMapper extends BaseMapper<Tutor> {

    /**
     * Counts the number of students assigned to a tutor by their tutor ID.
     * @param tutorId the unique identifier of the tutor
     * @return the number of students assigned to the tutor
     */
    @Select("select count(*) from student where tutor_id = #{tutorId}")
    int countTutorById(String tutorId);

    /**
     * Retrieves a list of tutors associated with a specific programme by programme ID.
     * @param programmeId the unique identifier of the programme
     * @return a list of tutors involved in the specified programme
     */
    @Select("select * from tutor where programme_id = #{programmeId}")
    List<Tutor> selectByProgrammeId(String programmeId);

    /**
     * Inserts a new tutor into the database.
     * @param tutor the tutor entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    @Insert("insert into tutor values (#{tutorId}, #{information}, #{programme.programmeId}, #{password}, #{firstName}, #{lastName}, #{email})")
    int insert(Tutor tutor);

    /**
     * Updates the information of an existing tutor in the database.
     * @param tutor the tutor entity with updated information
     * @return the number of rows affected by the update operation
     */
    @Update("<script>" +
            "update tutor" +
            "<set>" +
            "<if test='information != null'>information=#{information},</if>" +
            "<if test='programme.programmeId != null'>programme_id=#{programme.programmeId},</if>" +
            "<if test='password != null'>password=#{password},</if>" +
            "<if test='firstName != null'>first_name=#{firstName},</if>" +
            "<if test='lastName != null'>last_name=#{lastName},</if>" +
            "<if test='email != null'>email=#{email},</if>" +
            "</set>" +
            "where tutor_id = #{tutorId}" +
            "</script>")
    int updateTutor(Tutor tutor);

    /**
     * Retrieves all tutors from the database, along with their associated programmes and students.
     * @return a list of all tutors with detailed information including associated programmes and students
     */
    @Select("select * from tutor")
    @Results(
        {
            @Result(column = "tutor_id", property = "tutorId"),
            @Result(column = "information", property = "information"),
            @Result(column = "password", property = "password"),
            @Result(column = "first_name", property = "firstName"),
            @Result(column = "last_name", property = "lastName"),
            @Result(column = "email", property = "email"),
            @Result(column = "programme_id", property = "programme", javaType = Programme.class,
                    one = @One(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeMapper.selectById")),
            @Result(column = "tutor_id", property = "students", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.StudentMapper.selectByTutorId")
            )
        }
    )
    List<Tutor> selectAllTutor();

    /**
     * Retrieves a specific tutor by their tutor ID, including detailed information about their programme and students.
     * @param tutorId the unique identifier of the tutor
     * @return the tutor with detailed information
     */
    @Select("select * from tutor where tutor_id=#{tutorId}")
    @Results(
        {
            @Result(column = "tutor_id", property = "tutorId"),
            @Result(column = "information", property = "information"),
            @Result(column = "password", property = "password"),
            @Result(column = "first_name", property = "firstName"),
            @Result(column = "last_name", property = "lastName"),
            @Result(column = "email", property = "email"),
            @Result(column = "programme_id", property = "programme", javaType = Programme.class,
                    one = @One(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeMapper.selectById")),
            @Result(column = "tutor_id", property = "students", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.StudentMapper.selectByTutorId")
            )
        }
    )
    Tutor selectById(String tutorId);
}
