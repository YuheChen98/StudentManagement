package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.StudentExam;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentExamMapper extends BaseMapper<StudentExam> {

    @Select("select * from student_exam where exam_id = #{examId}")
    List<StudentExam> selectByExamId(String examId);
}
