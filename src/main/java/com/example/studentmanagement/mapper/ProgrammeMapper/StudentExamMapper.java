package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.ExamCoursework;
import com.example.studentmanagement.entity.Programme.StudentExam;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper interface for managing interactions with the student_exam table in the database.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
public interface StudentExamMapper extends BaseMapper<StudentExam> {

    /**
     * Inserts a new {@link StudentExam} record into the student_exam table.
     * @param studentExam the student exam to add
     * @return the number of rows affected
     */
    @Insert("insert into student_exam values (#{studentId},#{examCoursework.examId},#{ifSubmit},#{ifSuspend},#{mark})")
    int add(StudentExam studentExam);


    /**
     * Updates an existing {@link StudentExam} record in the student_exam table.
     * Uses a dynamic SQL script to update fields if they are not null.
     * @param studentExam the student exam to update
     * @return the number of rows affected
     */
    @Update("<script>" +
            "update student_exam " +
            "<set>" +
            "<if test='ifSubmit !=null'> if_submit=#{ifSubmit},</if>" +
            "<if test='ifSuspend !=null'> if_suspend=#{ifSuspend},</if>" +
            "<if test='mark !=null'> mark=#{mark}</if>" +
            "</set>" +
            " where student_id=#{studentId} and exam_id=#{examCoursework.examId}" +
            "</script>")
    int update(StudentExam studentExam);


    /**
     * Retrieves a list of {@link StudentExam} records by the exam ID.
     * This method includes a join to fetch related {@link ExamCoursework} details.
     * @param examId the ID of the exam
     * @return a list of student exams associated with the specified exam ID
     */
    @Select("select * from student_exam where exam_id = #{examId}")
    @Results(
            {
                    @Result(column = "student_id",property = "studentId"),
                    @Result(column = "if_submit",property = "ifSubmit"),
                    @Result(column = "if_suspend",property = "ifSuspend"),
                    @Result(column = "mark",property = "mark"),
                    @Result(column = "exam_id",property = "examCoursework",javaType = ExamCoursework.class,
                            one = @One(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ExamCourseworkMapper.selectByExamId")
                    )
            }
    )
    List<StudentExam> selectByExamId(String examId);


    /**
     * Retrieves a list of {@link StudentExam} records by the student ID.
     * This method includes a join to fetch related {@link ExamCoursework} details.
     * @param studentId the ID of the student
     * @return a list of student exams associated with the specified student ID
     */
    @Select("select * from student_exam where student_id = #{studentId}")
    @Results(
            {
                    @Result(column = "student_id",property = "studentId"),
                    @Result(column = "if_submit",property = "ifSubmit"),
                    @Result(column = "if_suspend",property = "ifSuspend"),
                    @Result(column = "mark",property = "mark"),
                    @Result(column = "exam_id",property = "examCoursework",javaType = ExamCoursework.class,
                            one = @One(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ExamCourseworkMapper.selectByExamId")
                    )
            }
    )
    List<StudentExam> selectByStudentId(String studentId);
}
