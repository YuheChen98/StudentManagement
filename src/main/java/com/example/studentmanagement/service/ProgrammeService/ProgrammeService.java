package com.example.studentmanagement.service.ProgrammeService;

import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.entity.Programme.ProgrammeModule;
import com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for handling operations related to academic programmes and modules within the student management system.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@Service
public class ProgrammeService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private ProgrammeModuleMapper programmeModuleMapper;

    /**
     * Retrieves a list of Modules associated with a given programme ID. This method leverages the ProgrammeModuleMapper
     * to fetch all ProgrammeModule entries by programme ID, then retrieves each corresponding Module via the ModuleMapper.
     *
     * @param programmeId The unique identifier of the programme for which modules are to be retrieved.
     * @return A list of Module objects associated with the specified programme ID.
     */
    public List<Module> getProgrammeModules(String programmeId) {
        List<ProgrammeModule> programmeModules = programmeModuleMapper.selectByProgrammeId(programmeId);
        List<Module> modules = new ArrayList<>();
        for (ProgrammeModule programmeModule : programmeModules) {
            Module module = moduleMapper.selectByModuleId(programmeModule.getModuleId());
            modules.add(module);
        }
        return modules;
    }

}
