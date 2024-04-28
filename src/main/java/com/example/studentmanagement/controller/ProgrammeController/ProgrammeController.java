package com.example.studentmanagement.controller.ProgrammeController;

import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProgrammeController {
    @Autowired
    private ProgrammeMapper programmeMapper;

    @Operation(summary = "查询全部programme列表")
    @GetMapping("/programme")
    public List programme() {
        return programmeMapper.selectAllProgramme();
    }
    @Operation(summary = "根据id查询programme")
    @GetMapping("/programme/{programmeId}")
    public Programme getProgrammeById(@PathVariable String programmeId) {
        Programme programme = programmeMapper.selectById(programmeId);
        return programme;
    }

    @Operation(summary = "创建programme")
    @PostMapping("/programme")
    public Programme add(@RequestBody Programme programme){
        int i = programmeMapper.insert(programme);
        if (i>0) {
            return programme;
        } else {
            return null;
        }
    }

    @Operation(summary = "更新programme信息")
    @PutMapping("/programme/{programmeId}")
    public Programme updateProgramme(@RequestBody Programme programme){
        int i = programmeMapper.updateById(programme);
        if ( i > 0){
            return programme;
        } else {
            return null;
        }
    }

}