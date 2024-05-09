package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Programme;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper interface for the Programme entity, providing database interaction methods to manage programmes.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@Mapper
public interface ProgrammeMapper extends BaseMapper<Programme> {

    /**
     * Retrieves a list of all programmes from the database, including related entities like students, tutors, and programme modules.
     * This comprehensive view is useful for displaying complete programme details.
     *
     * @return a list of all programmes with detailed information including students, tutors, and modules associated with each programme.
     */
    @Select("select * from programme")
    @Results({
            @Result(column = "programme_id", property = "programmeId"),
            @Result(column = "start_year", property = "startYear"),
            @Result(column = "end_year", property = "endYear"),
            @Result(column = "overview", property = "overview"),
            @Result(column = "structure", property = "structure"),
            @Result(column = "entry_requirements", property = "entryRequirements"),
            @Result(column = "fees_and_funding", property = "feesAndFunding"),
            @Result(column = "programme_id", property = "students", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.StudentMapper.selectByProgrammeId")),
            @Result(column = "programme_id", property = "tutors", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.TutorMapper.selectByProgrammeId")),
            @Result(column = "programme_id", property = "programmeModules", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper.selectByProgrammeId"))
    })
    List<Programme> selectAllProgramme();

    /**
     * Retrieves a specific programme by its unique identifier, including details such as associated students, tutors, and modules.
     * This method is crucial for fetching comprehensive information about a single programme.
     *
     * @param programmeId the unique identifier of the programme to retrieve
     * @return the Programme object populated with detailed information including related students, tutors, and modules.
     */
    @Select("select * from programme where programme_id = #{programmeId}")
    @Results({
            @Result(column = "programme_id", property = "programmeId"),
            @Result(column = "start_year", property = "startYear"),
            @Result(column = "end_year", property = "endYear"),
            @Result(column = "overview", property = "overview"),
            @Result(column = "structure", property = "structure"),
            @Result(column = "entry_requirements", property = "entryRequirements"),
            @Result(column = "fees_and_funding", property = "feesAndFunding"),
            @Result(column = "programme_id", property = "students", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.StudentMapper.selectByProgrammeId")),
            @Result(column = "programme_id", property = "tutors", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.TutorMapper.selectByProgrammeId")),
            @Result(column = "programme_id", property = "programmeModules", javaType = List.class,
                    many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper.selectByProgrammeId"))
    })
    Programme selectByProgrammeId(String programmeId);
}
