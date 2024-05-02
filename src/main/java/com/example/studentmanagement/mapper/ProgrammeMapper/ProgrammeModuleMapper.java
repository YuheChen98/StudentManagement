package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.Programme.ProgrammeModule;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProgrammeModuleMapper extends BaseMapper<ProgrammeModule> {

    @Select("select * from programme_module where programme_id = #{programmeId}")
    List<ProgrammeModule> selectByProgrammeId(String programmeId);

    @Select("select * from programmeModule where module_id = #{moduleId}")
    List<ProgrammeModule> selectByModuleId(String moduleId);

//    @Insert("insert into programmeModule values ({module.moduleId},{programme.programmeId})")
//    int addProgrammeModule(ProgrammeModule programmeModule);

//    @Select("select * from programmeModule")
//    @Results(
//            {
//                    @Result(column = "programme_id",property = "programme",javaType = Programme.class,
//                            one = @One(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeMapper.selectById")),
//                    @Result(column = "module_id",property = "module",javaType = Module.class,
//                            one = @One(select = "com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper.selectById")
//                    )
//            }
//    )
//    List<ProgrammeModule> selectProgrammeModule();
}
