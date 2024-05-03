package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.ExamCoursework;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper interface for the ExamCoursework entity, providing database interaction methods to manage exam and coursework records.
 */
@Mapper
public interface ExamCourseworkMapper extends BaseMapper<ExamCoursework> {

    /**
     * Inserts a new ExamCoursework record into the database.
     * This includes linking the record to a specific module and setting details like requirements and deadlines.
     *
     * @param examCoursework The ExamCoursework entity to be added.
     * @return The number of rows affected by the insert operation.
     */
    @Insert("insert into exam_coursework values (#{examId}, #{module.moduleId}, #{requirementDetails}, #{deadline}, #{ifExam})")
    int add(ExamCoursework examCoursework);

    /**
     * Retrieves a list of all exam or coursework records associated with a specific module.
     *
     * @param moduleId The unique identifier of the module whose exams or coursework are to be retrieved.
     * @return A list of ExamCoursework entities associated with the specified module.
     */
    @Select("select * from exam_coursework where module_id = #{moduleId}")
    List<ExamCoursework> selectByModuleId(String moduleId);

    /**
     * Retrieves detailed information about a specific exam or coursework by its unique identifier.
     * This method also fetches associated module details, enhancing the data context for the returned entity.
     *
     * @param examId The unique identifier of the exam or coursework to retrieve.
     * @return An ExamCoursework entity populated with detailed information including the associated module.
     */
    @Select("select * from exam_coursework where exam_id = #{examId}")
    @Results({
            @Result(column = "exam_id", property = "examId"),
            @Result(column = "requirement_details", property = "requirementDetails"),
            @Result(column = "deadline", property = "deadline"),
            @Result(column = "if_exam", property = "ifExam"),
            @Result(column = "module_id", property = "module", javaType = Module.class,
                    one = @One(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper.selectById"))
    })
    ExamCoursework selectByExamId(String examId);
}
