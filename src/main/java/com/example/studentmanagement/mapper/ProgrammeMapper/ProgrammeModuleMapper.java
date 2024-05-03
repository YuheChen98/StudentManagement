package com.example.studentmanagement.mapper.ProgrammeMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Programme.ProgrammeModule;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper interface for the ProgrammeModule entity, which provides methods for database operations related to linking programmes with their respective modules.
 */
@Mapper
public interface ProgrammeModuleMapper extends BaseMapper<ProgrammeModule> {

    /**
     * Retrieves a list of programme modules based on the programme ID. This method is used to find all modules associated with a specific programme.
     *
     * @param programmeId The unique identifier of the programme whose modules are to be retrieved.
     * @return A list of ProgrammeModule entities associated with the specified programme ID.
     */
    @Select("select * from programme_module where programme_id = #{programmeId}")
    List<ProgrammeModule> selectByProgrammeId(String programmeId);

    /**
     * Retrieves a list of programme modules based on the module ID. This method is useful for fetching all programme associations for a specific module.
     *
     * @param moduleId The unique identifier of the module whose programme associations are to be retrieved.
     * @return A list of ProgrammeModule entities associated with the specified module ID.
     */
    @Select("select * from programmeModule where module_id = #{moduleId}")
    List<ProgrammeModule> selectByModuleId(String moduleId);

}
