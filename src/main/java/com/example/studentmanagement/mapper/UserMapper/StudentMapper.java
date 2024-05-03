package com.example.studentmanagement.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.entity.User.Tutor;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Mapper interface for Student entity providing database interaction operations.
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * Inserts a new student into the database.
     * @param student the student to insert
     * @return the number of rows affected
     */
    @Insert("insert into student values (#{studentId},#{tutor.tutorId},#{ifWithdraw},#{information},#{startTime},#{endTime},#{programme.programmeId},#{password},#{firstName},#{lastName},#{email})")
    int add(Student student);


    /**
     * Updates the tutor associated with a student.
     * @param student the student whose tutor is to be updated
     * @return the number of rows affected
     */
    @Update("update student set tutor_id=#{tutor.tutorId} where student_id=#{studentId}")
    int updateTutor(Student student);


    /**
     * Updates various attributes of a student in the database.
     * @param student the student to update
     * @return the number of rows affected
     */
    @Update("<script>" +
            "update student" +
            "<set>" +
            "<if test='programme.programmeId != null'>programme_id = #{programme.programmeId},</if>" +
            "<if test='ifWithdraw != null'>if_withdraw = #{ifWithdraw},</if>" +
            "<if test='information != null'>information = #{information},</if>" +
            "<if test='startTime != null'>start_time = #{startTime},</if>" +
            "<if test='endTime != null'>end_time = #{endTime},</if>" +
            "<if test='password != null'>password = #{password},</if>" +
            "<if test='firstName != null'>first_name = #{firstName},</if>" +
            "<if test='lastName != null'>last_name = #{lastName},</if>" +
            "<if test='email != null'>email = #{email}</if>" +
            "</set>" +
            "where student_id = #{studentId}" +
            "</script>")
    int updateStudentAndProgramme(Student student);



    /**
     * Retrieves the count of students with a specific student ID to ensure uniqueness.
     * @param studentId the student ID to check in the database
     * @return the count of students with the specified student ID
     */
    @Select("select count(*) from student where student_id = #{studentId}")
    int countStudentById(String studentId);



    /**
     * Fetches a list of students assigned to a specific tutor.
     * @param tutorId the tutor ID whose students are to be retrieved
     * @return a list of students under the specified tutor
     */
    @Select("select * from student where tutor_id = #{tutorId}")
    List<Student> selectByTutorId(String tutorId);



    /**
     * Fetches a list of students enrolled in a specific programme.
     * @param programmeId the programme ID whose students are to be retrieved
     * @return a list of students in the specified programme
     */
    @Select("select * from student where programme_id = #{programmeId}")
    List<Student> selectByProgrammeId(String programmeId);




    /**
     * Fetches a list of all students in the database.
     * @return a comprehensive list of all students
     */
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



    /**
     * Retrieves detailed information about a student based on their student ID, including linked tutor and programme details.
     * @param studentId the student ID to retrieve details for
     * @return detailed student information including related tutor and programme
     */
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

