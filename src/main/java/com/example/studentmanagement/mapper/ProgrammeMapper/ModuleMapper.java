package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.entity.User.Lecturer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ModuleMapper extends BaseMapper<Module> {
    @Select("select * from module")
    @Results(
            {
                    @Result(column = "module_id",property = "moduleId"),
                    @Result(column = "syllabus",property = "syllabus"),
                    @Result(column = "term",property = "term"),
                    @Result(column = "time_every_week",property = "timeEveryWeek"),
                    @Result(column = "lecturer_id",property = "lecturer",javaType = Lecturer.class,
                            one = @One(select = "com.example.studentmanagement.mapper.UserMapper.LecturerMapper.selectById")),
                    @Result(column = "module_id",property = "examCourseworks",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ExamCourseworkMapper.selectByModuleId")
                    )
            }
    )
    List<Module> selectAllModule();

    @Select("select * from module where module_id = #{moduleId}")
    @Results(
            {
                    @Result(column = "module_id",property = "moduleId"),
                    @Result(column = "syllabus",property = "syllabus"),
                    @Result(column = "semester",property = "semester"),
                    @Result(column = "time_every_week",property = "timeEveryWeek"),
                    @Result(column = "lecturer_id",property = "lecturer",javaType = Lecturer.class,
                            one = @One(select = "com.example.studentmanagement.mapper.UserMapper.LecturerMapper.selectById")),
                    @Result(column = "module_id",property = "examCourseworks",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ExamCourseworkMapper.selectByModuleId")
                    )
            }
    )
    Module selectByModuleId(String moduleId);

//    @Select("select * from Module where programme_id = #{programmeId}")
//    @Results(
//            {
//                    @Result(column = "module_id",property = "moduleId"),
//                    @Result(column = "syllabus",property = "syllabus"),
//                    @Result(column = "term",property = "term"),
//                    @Result(column = "time_every_week",property = "timeEveryWeek"),
//                    @Result(column = "module_id",property = "programmeModules",javaType = List.class,
//                            many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper.selectByModuleId")
//                    )
//            }
//    )
//    List<Module> selectByProgrammeId(String programmeId);
}
