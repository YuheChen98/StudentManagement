package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.User.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProgrammeMapper extends BaseMapper<Programme> {


    //查询所有programme信息
    @Select("select * from programme")
    @Results(
            {
                    @Result(column = "programme_id",property = "programmeId"),
                    @Result(column = "start_year",property = "startYear"),
                    @Result(column = "end_year",property = "endYear"),
                    @Result(column = "overview",property = "overview"),
                    @Result(column = "structure",property = "structure"),
                    @Result(column = "entry_requirements",property = "entryRequirements"),
                    @Result(column = "fees_and_funding",property = "feesAndFunding"),
                    @Result(column = "programme_id",property = "students",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.StudentMapper.selectByProgrammeId")),
                    @Result(column = "programme_id",property = "tutors",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.TutorMapper.selectByProgrammeId")),
                    @Result(column = "programme_id",property = "programmeModules",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper.selectByProgrammeId")
                    )
            }
    )
    List<Programme> selectAllProgramme();

    //查询单个programme信息
    @Select("select * from programme where programme_id = #{programmeId}")
    @Results(
            {
                    @Result(column = "programme_id",property = "programmeId"),
                    @Result(column = "start_year",property = "startYear"),
                    @Result(column = "end_year",property = "endYear"),
                    @Result(column = "overview",property = "overview"),
                    @Result(column = "structure",property = "structure"),
                    @Result(column = "entry_requirements",property = "entryRequirements"),
                    @Result(column = "fees_and_funding",property = "feesAndFunding"),
                    @Result(column = "programme_id",property = "students",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.StudentMapper.selectByProgrammeId")),
                    @Result(column = "programme_id",property = "tutors",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.UserMapper.TutorMapper.selectByProgrammeId")),
                    @Result(column = "programme_id",property = "programmeModules",javaType = List.class,
                            many = @Many(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper.selectByProgrammeId")
                    )
            }
    )
    Programme selectByProgrammeId(String programmeId);
}
