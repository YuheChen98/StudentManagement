package com.example.studentmanagement.controller.ProgrammeController;

import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ModuleController {
    @Autowired
    private ModuleMapper moduleMapper;

    @Operation(summary = "查询全部module列表")
    @GetMapping("/module")
    public List module() {
        return moduleMapper.selectList(null);
    }


    @Operation(summary = "根据id查询module")
    @GetMapping("/module/{moduleId}")
    public Module getModuleById(@PathVariable String moduleId) {
        Module module = moduleMapper.selectById(moduleId);
        return module;
    }

    @Operation(summary = "创建module")
    @PostMapping("/module")
    public Module add(@RequestBody Module module){
        int i = moduleMapper.insert(module);
        if (i>0) {
            return module;
        } else {
            return null;
        }
    }

    @Operation(summary = "更新module信息")
    @PutMapping("/module/{moduleId}")
    public Module updateModule(@RequestBody Module module){
        int i = moduleMapper.updateById(module);
        if ( i > 0){
            return module;
        } else {
            return null;
        }
    }
}
