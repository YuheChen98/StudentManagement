package com.example.studentmanagement.controller.ProgrammeController;

import com.example.studentmanagement.entity.Programme.ProgrammeModule;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgrammeModuleController {

    @Autowired
    private ProgrammeModuleMapper programmeModuleMapper;
    @Operation(summary = "在programme中添加module")
    @PostMapping("/programme/module")
    public ProgrammeModule add(@RequestBody ProgrammeModule programmeModule){
        int i = programmeModuleMapper.insert(programmeModule);
        if (i>0) {
            return programmeModule;
        } else {
            return null;
        }
    }
}
