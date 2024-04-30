package com.example.studentmanagement.controller.UserController;

import com.example.studentmanagement.entity.User.Administrator;
import com.example.studentmanagement.mapper.UserMapper.AdministratorMapper;
import com.example.studentmanagement.service.UserService.AdministratorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdministratorController {
    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private AdministratorService administratorService;

    @Operation(summary = "创建administrator")
    @PostMapping("/administrator")
    public Administrator add(@RequestBody Administrator administrator){
        return administratorService.createAdministrator(administrator);
    }

    @Operation(summary = "查询全部administrator列表")
    @GetMapping("/administrator")
    public List administrator() {
        return administratorMapper.selectList(null);
    }

    @Operation(summary = "根据id查询administrator信息")
    @GetMapping("/administrator/{administratorId}")
    public Administrator getAdministratorById(@PathVariable String administratorId) {
        Administrator administrator = administratorMapper.selectById(administratorId);
        return administrator;
    }
    @Operation(summary = "更新administrator信息")
    @PutMapping("/administrator/{administratorId}")
    public Administrator updateAdministrator(@RequestBody Administrator administrator){
        int i = administratorMapper.updateById(administrator);
        if ( i > 0){
            return administrator;
        } else {
            return null;
        }
    }
    @Operation(summary = "删除administrator")
    @DeleteMapping("/tutpr/{administratorId}")
    public Administrator deleteAdministrator(@RequestBody Administrator administrator){
        int i = administratorMapper.deleteById(administrator);
        if ( i > 0){
            return administrator;
        } else {
            return null;
        }
    }

}
