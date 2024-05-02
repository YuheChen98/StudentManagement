package com.example.studentmanagement.controller.AccountController;

import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.service.ProgrammeService.ProgrammeService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StudentAccountController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ProgrammeService programmeService;
    @Autowired
    private ModuleMapper moduleMapper;
    private String getCurrentStudentId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

    @Operation(summary = "student profile")
    @GetMapping("/student/profile")
    @PreAuthorize("hasRole('STUDENT')")
    public Result getProfile() {
        String studentId = getCurrentStudentId();
        Student student = studentMapper.selectByStudentId(studentId);
        return Result.ok().data("student",student);
    }

    @Operation(summary = "all modules of one student")
    @GetMapping("/student/modules")
    @PreAuthorize("hasRole('STUDENT')")
    public Result getAllModules() {
        String studentId = getCurrentStudentId();
        Student student = studentMapper.selectByStudentId(studentId);
        List<Module> modules = programmeService.getProgrammeModules(student.getProgramme().getProgrammeId());
        return  Result.ok().data("modules",modules);
    }

    @Operation(summary = "select one module")
    @GetMapping("/student-module")
    @PreAuthorize("hasRole('STUDENT')")
    public Module getModule(@RequestBody Module module) {
        return moduleMapper.selectByModuleId(module.getModuleId());
    }

}
