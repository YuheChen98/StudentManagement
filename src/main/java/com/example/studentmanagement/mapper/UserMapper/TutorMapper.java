package com.example.studentmanagement.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.entity.User.Tutor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TutorMapper extends BaseMapper<Tutor> {
    @Select("select count(*) from student where tutor_id = #{tutorId}")
    int countTutorById(String tutorId);
    @Select("select * from tutor where programme_id = #{programmeId}")
    List<Tutor> selectByProgrammeId(String programmeId);

    @Insert("insert into tutor values (#{tutorId},#{information},#{programme.programmeId},#{password},#{firstName},#{lastName},#{email})")
    int insert(Tutor tutor);

    @Update("update tutor set information=#{information},programme_id=#{programme.programmeId},password=#{password},first_name=#{firstName},last_name=#{lastName},email=#{email} where tutor_id = #{tutorId}")
    int updateTutor(Tutor tutor);
    @Select("select * from tutor")
    @Results(
            {
                    @Result(column = "tutor_id",property = "tutorId"),
                    @Result(column = "information",property = "information"),
                    @Result(column = "password",property = "password"),
                    @Result(column = "first_name",property = "firstName"),
                    @Result(column = "last_name",property = "lastName"),
                    @Result(column = "email",property = "email"),
                    @Result(column = "programme_id",property = "programme",javaType = Programme.class,
                            one = @One(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeMapper.selectById")),
                    @Result(column = "tutor_id",property = "students",javaType = List.class,
                        many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.StudentMapper.selectByTutorId")
                    )
            }
    )
    List<Tutor> selectAllTutor();
    @Select("select * from tutor where tutor_id=#{tutorId}")
    @Results(
            {
                    @Result(column = "tutor_id",property = "tutorId"),
                    @Result(column = "information",property = "information"),
                    @Result(column = "password",property = "password"),
                    @Result(column = "first_name",property = "firstName"),
                    @Result(column = "last_name",property = "lastName"),
                    @Result(column = "email",property = "email"),
                    @Result(column = "programme_id",property = "programme",javaType = Programme.class,
                            one = @One(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeMapper.selectById")),
                    @Result(column = "tutor_id",property = "students",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.StudentMapper.selectByTutorId")
                    )
            }
    )
    Tutor selectById(String tutorId);
}
