package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.ExamCoursework;
import com.example.studentmanagement.entity.Programme.StudentExam;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.util.List;

public interface StudentExamMapper extends BaseMapper<StudentExam> {
    @Insert("insert into student_exam values (#{studentId},#{examCoursework.examId},#{ifSubmit},#{ifSuspend},#{mark})")
    int add(StudentExam studentExam);
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
}
