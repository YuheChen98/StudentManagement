package com.example.studentmanagement.controller.ProgrammeController;

import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.Programme.ProgrammeModule;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper;
import com.example.studentmanagement.service.ProgrammeService.ProgrammeService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProgrammeController {
    @Autowired
    private ProgrammeMapper programmeMapper;
    @Autowired
    private ProgrammeService programmeService;
    @Autowired
    private ProgrammeModuleMapper programmeModuleMapper;
    @Operation(summary = "查询全部programme列表")
    @GetMapping("/admin/programme")
    @PreAuthorize("hasRole('ADMIN')")
    public List programme() {
        return programmeMapper.selectAllProgramme();
    }
    @Operation(summary = "根据id查询programme")
    @GetMapping("/admin/programmeInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public Programme getProgrammeById(@RequestBody Programme programme) {
        return programmeMapper.selectByProgrammeId(programme.getProgrammeId());
    }

    @Operation(summary = "查询该programme所有module信息")
    @GetMapping("/admin/programme/module")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Module> getProgrammeModules(@RequestBody Programme programme){
       return programmeService.getProgrammeModules(programme.getProgrammeId());
    }

    @Operation(summary = "创建programme")
    @PostMapping("/admin/programme")
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(@RequestBody Programme programme){
        int i = programmeMapper.insert(programme);
        if (i>0) {
            return Result.ok().data("programme",programme);
        } else {
            return Result.error().message("Create programme failed");
        }
    }

    @Operation(summary = "更新programme信息")
    @PutMapping("/admin/programme")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateProgramme(@RequestBody Programme programme){
        int i = programmeMapper.updateById(programme);
        if ( i > 0){
            return Result.ok().data("programme",programme);
        } else {
            return Result.error().message("Update programme failed");
        }
    }

    @Operation(summary = "在programme中添加module")
    @PostMapping("/admin/programme/module")
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(@RequestBody ProgrammeModule programmeModule){
        int i = programmeModuleMapper.insert(programmeModule);
        if (i>0) {
            return Result.ok().data("programmeModule",programmeModule);
        } else {
            return Result.error().message("Select module failed");
        }
    }
}
