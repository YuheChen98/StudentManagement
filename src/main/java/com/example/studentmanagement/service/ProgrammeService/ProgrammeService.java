package com.example.studentmanagement.service.ProgrammeService;

import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.entity.Programme.ProgrammeModule;
import com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgrammeService {
    @Autowired
    private ProgrammeMapper programmeMapper;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private ProgrammeModuleMapper programmeModuleMapper;

    public List<Module> getProgrammeModules(String programmeId) {
        List<ProgrammeModule> programmeModules = programmeModuleMapper.selectByProgrammeId(programmeId);
        List<Module> modules = new ArrayList<>();
        for (ProgrammeModule programmeModule : programmeModules) {
            Module module = moduleMapper.selectByModuleId(programmeModule.getModuleId());
            modules.add(module);
        }
        return modules;
    }

  //  public ProgrammeModule selectModule(String programmeId,String moduleId){
//        ProgrammeModule programmeModule = new ProgrammeModule();
//        programmeModule.setProgrammeId(programmeId);
//        programmeModule.



}
