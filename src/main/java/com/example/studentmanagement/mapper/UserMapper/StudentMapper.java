package com.example.studentmanagement.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.entity.User.Tutor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    @Insert("insert into Student values (#{studentId},#{tutor.tutorId},#{ifWithdraw},#{information},#{startTime},#{endTime},#{programme.programmeId},#{password},#{firstName},#{lastName},#{email})")
    int add(Student student);

    @Select("select count(*) from student where student_id = #{studentId}")
    int countStudentById(String studentId);

    @Select("select * from student where tutorId = #{tutorId}")
    List<Student> selectByTutorId(String tutorId);
    @Select("select * from student where programme_id = #{programmeId}")
    List<Student> selectByProgrammeId(String programmeId);


    @Select("select * from student")
    @Results(
            {
                    @Result(column = "student_id",property = "studentId"),
                    @Result(column = "information",property = "information"),
                    @Result(column = "password",property = "password"),
                    @Result(column = "first_name",property = "firstName"),
                    @Result(column = "last_name",property = "lastName"),
                    @Result(column = "start_time",property = "startTime"),
                    @Result(column = "end_time",property = "endTime"),
                    @Result(column = "if_withdraw",property = "ifWithdraw"),
                    @Result(column = "programme_id",property = "programme",javaType = Programme.class,
                            one = @One(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeMapper.selectById")),
                    @Result(column = "tutor_id",property = "tutor",javaType = Tutor.class,
                            one = @One(select = "com.example.studentmanagement.mapper.UserMapper.TutorMapper.selectById")
                    )
            }
    )
    List<Student> selectAllStudentProgrammeTutor();
}

