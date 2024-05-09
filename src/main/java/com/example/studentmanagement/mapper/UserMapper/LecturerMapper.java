package com.example.studentmanagement.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.User.Lecturer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper interface for the Lecturer entity, providing methods for database operations.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@Mapper
public interface LecturerMapper extends BaseMapper<Lecturer> {

    /**
     * Counts the number of entries for a specific lecturer by their ID.
     * @param lecturerId the unique identifier of the lecturer
     * @return the count of lecturers with the specified ID
     */
    @Select("select count(*) from lecturer where lecturer_id = #{lecturerId}")
    int countLecturerById(String lecturerId);

    /**
     * Retrieves a list of all lecturers in the system, including their associated modules.
     * @return a list of all lecturers with detailed information
     */
    @Select("select * from lecturer")
    @Results({
            @Result(column = "lecturer_id", property = "lecturerId"),
            @Result(column = "information", property = "information"),
            @Result(column = "password", property = "password"),
            @Result(column = "first_name", property = "firstName"),
            @Result(column = "last_name", property = "lastName"),
            @Result(column = "email", property = "email"),
            @Result(column = "lecturer_id", property = "modules", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper.selectByLecturerId"))
    })
    List<Lecturer> selectAllLecturer();

    /**
     * Retrieves detailed information about a specific lecturer by their ID, including the modules they teach.
     * @param lecturerId the unique identifier of the lecturer
     * @return the lecturer object with detailed information
     */
    @Select("select * from lecturer where lecturer_id=#{lecturerId}")
    @Results({
            @Result(column = "lecturer_id", property = "lecturerId"),
            @Result(column = "information", property = "information"),
            @Result(column = "password", property = "password"),
            @Result(column = "first_name", property = "firstName"),
            @Result(column = "last_name", property = "lastName"),
            @Result(column = "email", property = "email"),
            @Result(column = "lecturer_id", property = "modules", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper.selectByLecturerId"))
    })
    Lecturer selectById(String lecturerId);
}
