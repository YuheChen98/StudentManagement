package com.example.studentmanagement.controller.UserController;

import com.example.studentmanagement.entity.User.Administrator;
import com.example.studentmanagement.mapper.UserMapper.AdministratorMapper;
import com.example.studentmanagement.service.UserService.AdministratorService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AdministratorController {
    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private AdministratorService administratorService;
    @Operation(summary = "创建administrator")
    @PostMapping("/admin/administrator")
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(@RequestBody Administrator administrator){
        Administrator newAdministrator = administratorService.createAdministrator(administrator);
        if (newAdministrator != null){
            return Result.ok().data("administrator",administrator);
        } else {
            return Result.error().message("Create administrator failed");
        }
    }
    @Operation(summary = "查询全部administrator列表")
    @GetMapping("/admin/administrator")
    @PreAuthorize("hasRole('ADMIN')")
    public List administrator() {
        return administratorMapper.selectList(null);
    }

    @Operation(summary = "根据id或name查询administrator信息列表")
    @GetMapping("/admin/administrator/{query}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Administrator> searchAdministrator(@PathVariable String query){
        return administratorService.searchAdministrator(query);
    }

    @Operation(summary = "查询单个administrator信息")
    @GetMapping("/admin/administratorInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public Administrator getAdministratorById(@RequestBody Administrator administrator) {
        return administratorMapper.selectById(administrator.getAdminId());
    }
    @Operation(summary = "更新administrator信息")
    @PutMapping("/admin/administrator")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateAdministrator(@RequestBody Administrator administrator){
        int i = administratorMapper.updateById(administrator);
        if ( i > 0){
            return Result.ok().data("administrator",administrator);
        } else {
            return Result.error().message("Update administrator failed");
        }
    }
    @Operation(summary = "删除administrator")
    @DeleteMapping("/admin/administrator")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteAdministrator(@RequestBody Administrator administrator){
        int i = administratorMapper.deleteById(administrator);
        if ( i > 0){
            return Result.ok().data("administrator",administrator);
        } else {
            return Result.error().message("Delete administrator failed");
        }
    }


}
