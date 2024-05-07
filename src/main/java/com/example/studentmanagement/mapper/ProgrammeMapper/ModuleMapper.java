package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.entity.User.Lecturer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper interface for the Module entity, providing methods for database operations related to academic modules.
 */
@Mapper
public interface ModuleMapper extends BaseMapper<Module> {

    /**
     * Inserts a new Module into the database.
     * @param module the Module to be added
     * @return the number of rows affected by the insert operation
     */
    @Insert("insert into module values (#{moduleId}, #{syllabus}, #{semester}, #{timeEveryWeek}, #{lecturer.lecturerId})")
    int add(Module module);

    /**
     * Retrieves all modules taught by a specific lecturer.
     * @param lecturerId the unique identifier of the lecturer
     * @return a list of modules associated with the specified lecturer
     */
    @Select("select * from module where lecturer_id = #{lecturerId}")
    List<Module> selectByLecturerId(String lecturerId);

    /**
     * Retrieves all modules in the database, including their associated lecturer and exam/coursework details.
     * @return a list of all modules with detailed information
     */
    @Select("select * from module")
    @Results({
            @Result(column = "module_id", property = "moduleId"),
            @Result(column = "syllabus", property = "syllabus"),
            @Result(column = "semester", property = "semester"),
            @Result(column = "time_every_week", property = "timeEveryWeek"),
            @Result(column = "lecturer_id", property = "lecturer", javaType = Lecturer.class,
                    one = @One(select = "com.example.studentmanagement.mapper.UserMapper.LecturerMapper.selectById")),
            @Result(column = "module_id", property = "examCourseworks", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ExamCourseworkMapper.selectByModuleId"))
    })
    List<Module> selectAllModule();

    /**
     * Retrieves a specific module by its module ID, including details about the lecturer and associated exam/coursework.
     * @param moduleId the unique identifier of the module
     * @return the module with detailed information
     */
    @Select("select * from module where module_id = #{moduleId}")
    @Results({
            @Result(column = "module_id", property = "moduleId"),
            @Result(column = "syllabus", property = "syllabus"),
            @Result(column = "semester", property = "semester"),
            @Result(column = "time_every_week", property = "timeEveryWeek"),
            @Result(column = "lecturer_id", property = "lecturer", javaType = Lecturer.class,
                    one = @One(select = "com.example.studentmanagement.mapper.UserMapper.LecturerMapper.selectById")),
            @Result(column = "module_id", property = "examCourseworks", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ExamCourseworkMapper.selectByModuleId"))
    })
    Module selectByModuleId(String moduleId);


    /**
     * Retrieves all modules that are within same year and same programme of the module that represented by provided module Id.
     * @param moduleId the unique identifier of the module
     * @return a list of all same year and same programme modules
     */
    @Select("select * from module where SUBSTRING_INDEX(module_id,'-',2) = SUBSTRING_INDEX(#{moduleId},'-',2)")
    List<Module> selectSameYearModuleByModuleId(String moduleId);
}
