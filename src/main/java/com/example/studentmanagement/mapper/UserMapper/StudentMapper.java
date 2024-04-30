package com.example.studentmanagement.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.Programme.ProgrammeModule;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.entity.User.Tutor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    //插入新学生
    @Insert("insert into student values (#{studentId},#{tutor.tutorId},#{ifWithdraw},#{information},#{startTime},#{endTime},#{programme.programmeId},#{password},#{firstName},#{lastName},#{email})")
    int add(Student student);

    //根据id查找该学生
    @Select("select count(*) from student where student_id = #{studentId}")
    int countStudentById(String studentId);

    //查找该tutor的学生列表
    @Select("select * from student where tutor_id = #{tutorId}")
    List<Student> selectByTutorId(String tutorId);

    //查找该programme的学生列表
    @Select("select * from student where programme_id = #{programmeId}")
    List<Student> selectByProgrammeId(String programmeId);

    //查找所有学生信息
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
                            one = @One(select = "com.example.studentmanagement.mapper.UserMapper.TutorMapper.selectById"))
            }
    )
    List<Student> selectAllStudent();

    //查找单个学生信息、programme、tutor
    @Select("select * from student where student_id = #{studentId}")
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
                            one = @One(select = "com.example.studentmanagement.mapper.UserMapper.TutorMapper.selectById")),
                    @Result(column = "programme_id",property = "programmeModules",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper.selectByProgrammeId")
                    )
            }
    )
    Student selectByStudentId(String studentId);

}

