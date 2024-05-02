package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.ExamCoursework;
import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.entity.User.Lecturer;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ExamCourseworkMapper extends BaseMapper<ExamCoursework> {
    @Select("select * from exam_coursework where module_id = #{moduleId}")
    List<ExamCoursework> selectByModuleId(String moduleId);

    @Select("select * from exam_coursework where exam_id = #{examId}")
    @Results(
            {
                    @Result(column = "exam_id",property = "examId"),
                    @Result(column = "requirement_details",property = "requirementDetails"),
                    @Result(column = "deadline",property = "deadline"),
                    @Result(column = "if_exam",property = "ifExam"),
                    @Result(column = "module_id",property = "module",javaType = Module.class,
                            one = @One(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper.selectById")
                    )
            }
    )
    ExamCoursework selectByExamId(String examId);
}
