package com.example.studentmanagement.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.entity.User.Tutor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TutorMapper extends BaseMapper<Tutor> {

    @Select("select * from tutor")
    @Results(
            {
                    @Result(column = "tutor_id",property = "tutorId"),
                    @Result(column = "information",property = "information"),
                    @Result(column = "password",property = "password"),
                    @Result(column = "first_name",property = "firstName"),
                    @Result(column = "last_name",property = "lastName"),
                    @Result(column = "email",property = "email"),
                    @Result(column = "tutor_id",property = "students",javaType = List.class,
                        many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.StudentMapper.selectByTutorId")
                    )
            }
    )
    List<Tutor> selectTutorAndStudents();
}
