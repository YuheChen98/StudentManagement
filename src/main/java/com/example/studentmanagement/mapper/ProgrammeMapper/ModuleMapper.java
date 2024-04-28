package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.Module;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ModuleMapper extends BaseMapper<Module> {
//    @Select("select * from module")
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
//    List<Module> selectAllModule();
}
