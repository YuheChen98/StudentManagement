package com.example.studentmanagement.controller.ProgrammeController;

import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ModuleController {
    @Autowired
    private ModuleMapper moduleMapper;

    @Operation(summary = "查询全部module列表")
    @GetMapping("/module")
    public List module() {
        return moduleMapper.selectAllModule();
    }


    @Operation(summary = "根据id查询module")
    @GetMapping("/module/{moduleId}")
    public Module getModuleById(@PathVariable String moduleId) {
        Module module = moduleMapper.selectByModuleId(moduleId);
        return module;
    }

    @Operation(summary = "创建module")
    @PostMapping("/module")
    public Result add(@RequestBody Module module){
        int i = moduleMapper.insert(module);
        if (i>0) {
            return Result.ok().data("module",module);
        } else {
            return Result.error().message("Create module failed");
        }
    }

    @Operation(summary = "更新module信息")
    @PutMapping("/module")
    public Result updateModule(@RequestBody Module module){
        int i = moduleMapper.updateById(module);
        if ( i > 0){
            return Result.ok().data("module",module);
        } else {
            return Result.error().message("Update module failed");
        }
    }
}
