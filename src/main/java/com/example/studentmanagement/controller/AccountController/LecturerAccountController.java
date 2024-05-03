package com.example.studentmanagement.controller.AccountController;


import com.example.studentmanagement.entity.Programme.ExamCoursework;
import com.example.studentmanagement.entity.Programme.StudentExam;
import com.example.studentmanagement.entity.User.Lecturer;
import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.mapper.ProgrammeMapper.ExamCourseworkMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.StudentExamMapper;
import com.example.studentmanagement.mapper.UserMapper.LecturerMapper;
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
public class LecturerAccountController {
    
    @Autowired
    private LecturerMapper lecturerMapper;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private StudentExamMapper studentExamMapper;
    @Autowired
    private ExamCourseworkMapper examCourseworkMapper;

    private String getCurrentLecturerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
    @Operation(summary = "lecturer profile")
    @GetMapping("/lecturer/profile")
    @PreAuthorize("hasRole('LECTURER')")
    public Result getProfile() {
        String lecturerId = getCurrentLecturerId();
        Lecturer lecturer = lecturerMapper.selectById(lecturerId);
        return  Result.ok().data("lecturer",lecturer);
    }

    @Operation(summary = "all modules of one lecturer")
    @GetMapping("/lecturer/modules")
    @PreAuthorize("hasRole('LECTURER')")
    public Result getAllModules() {
        String lecturerId = getCurrentLecturerId();
        Lecturer lecturer = lecturerMapper.selectById(lecturerId);
        List<Module> modules = lecturer.getModules();
        return  Result.ok().data("modules",modules);
    }

    @Operation(summary = "select one module")
    @GetMapping("/lecturer/module")
    @PreAuthorize("hasRole('LECTURER')")
    public Module getModule(@RequestBody Module module) {
        return moduleMapper.selectByModuleId(module.getModuleId());
    }
    @Operation(summary = "update module")
    @PutMapping("/lecturer/module")
    @PreAuthorize("hasRole('LECTURER')")
    public Result updateModule(@RequestBody Module module){
        int i = moduleMapper.updateById(module);
        if ( i > 0){
            return Result.ok().data("module",module);
        } else {
            return Result.error().message("Update module failed");
        }
    }

    @Operation(summary = "create exam or coursework")
    @PostMapping("/lecturer/exam")
    @PreAuthorize("hasRole('LECTURER')")
    public Result createExam(@RequestBody ExamCoursework examCoursework){
        int i = examCourseworkMapper.add(examCoursework);
        if ( i > 0){
            return Result.ok().data("examCoursework",examCoursework);
        } else {
            return Result.error().message("create exam or Coursework failed");
        }
    }

    @Operation(summary = "get exam of student")
    @GetMapping("/lecturer/examInfo")
    @PreAuthorize("hasRole('LECTURER')")
    public List<StudentExam> getStudentExam(@RequestBody StudentExam studentExam) {
        return studentExamMapper.selectByExamId(studentExam.getExamCoursework().getExamId());
    }

    @Operation(summary = "set exam status of student")
    @PostMapping("/lecturer/examInfo")
    @PreAuthorize("hasRole('LECTURER')")
    public Result setStudentExam(@RequestBody StudentExam studentExam){
        int i = studentExamMapper.add(studentExam);
        if ( i > 0){
            return Result.ok().data("studentExam",studentExam);
        } else {
            return Result.error().message("set exam status of student failed");
        }
    }

    @Operation(summary = "update exam status of student")
    @PutMapping("/lecturer/examInfo")
    @PreAuthorize("hasRole('LECTURER')")
    public Result updateStudentExam(@RequestBody StudentExam studentExam){
        int i = studentExamMapper.update(studentExam);
        if ( i > 0){
            return Result.ok().data("studentExam",studentExam);
        } else {
            return Result.error().message("update exam status of student failed");
        }
    }

}
