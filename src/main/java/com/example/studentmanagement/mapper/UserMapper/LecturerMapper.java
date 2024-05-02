package com.example.studentmanagement.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.User.Lecturer;
import com.example.studentmanagement.entity.User.Tutor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LecturerMapper extends BaseMapper<Lecturer> {

    @Select("select count(*) from lecturer where lecturer_id = #{lecturerId}")
    int countLecturerById(String lecturerId);
    @Select("select * from lecturer")
    @Results(
            {
                    @Result(column = "lecturer_id",property = "lecturerId"),
                    @Result(column = "information",property = "information"),
                    @Result(column = "password",property = "password"),
                    @Result(column = "first_name",property = "firstName"),
                    @Result(column = "last_name",property = "lastName"),
                    @Result(column = "email",property = "email"),
                    @Result(column = "lecturer_id",property = "modules",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper.selectById"))
            }
    )
    List<Lecturer> selectAllLecturer();

    @Select("select * from lecturer where lecturer_id=#{lecturerId}")
    @Results(
            {
                    @Result(column = "lecturer_id",property = "lecturerId"),
                    @Result(column = "information",property = "information"),
                    @Result(column = "password",property = "password"),
                    @Result(column = "first_name",property = "firstName"),
                    @Result(column = "last_name",property = "lastName"),
                    @Result(column = "email",property = "email"),
                    @Result(column = "lecturer_id",property = "modules",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper.selectById"))
            }
    )
    Lecturer selectById(String lecturerId);
}
