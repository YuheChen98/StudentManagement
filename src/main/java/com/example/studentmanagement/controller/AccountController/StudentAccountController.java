package com.example.studentmanagement.controller.AccountController;

import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.entity.Programme.StudentExam;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.StudentExamMapper;
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

/**
 * REST controller for managing student accounts and their academic records within the student management system.
 * Provides endpoints for students to access their profiles, modules, and exam results.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@RestController
@CrossOrigin
public class StudentAccountController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ProgrammeService programmeService;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private StudentExamMapper studentExamMapper;

    /**
     * Retrieves the currently authenticated student's ID from the security context.
     * @return The ID of the currently authenticated student.
     */
    private String getCurrentStudentId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

    /**
     * Retrieves the profile of the currently authenticated student.
     * Requires student-level security access.
     *
     * @return A Result object containing the student's profile data.
     */
    @Operation(summary = "Retrieve student profile")
    @GetMapping("/student/profile")
    @PreAuthorize("hasRole('STUDENT')")
    public Result getProfile() {
        String studentId = getCurrentStudentId();
        Student student = studentMapper.selectByStudentId(studentId);
        return Result.ok().data("student", student);
    }

    /**
     * Retrieves all modules associated with the currently authenticated student.
     * Requires student-level security access.
     *
     * @return A Result object containing a list of modules for the student.
     */
    @Operation(summary = "Retrieve all modules for the student")
    @GetMapping("/student/modules")
    @PreAuthorize("hasRole('STUDENT')")
    public Result getAllModules() {
        String studentId = getCurrentStudentId();
        Student student = studentMapper.selectByStudentId(studentId);
        List<Module> modules = programmeService.getProgrammeModules(student.getProgramme().getProgrammeId());
        return Result.ok().data("modules", modules);
    }

    /**
     * Retrieves details about a specific module by its ID.
     * Requires student-level security access.
     *
     * @param module A module object containing the ID of the module to retrieve.
     * @return The module object with full details.
     */
    @Operation(summary = "Retrieve details of a specific module")
    @GetMapping("/student/module")
    @PreAuthorize("hasRole('STUDENT')")
    public Module getModule(@RequestBody Module module) {
        return moduleMapper.selectByModuleId(module.getModuleId());
    }

    /**
     * Retrieves all exam results for the currently authenticated student.
     * Requires student-level security access.
     *
     * @return A list of StudentExam objects representing the student's exams and their outcomes.
     */
    @Operation(summary = "Retrieve all exam results for the student")
    @GetMapping("/student/exam")
    @PreAuthorize("hasRole('STUDENT')")
    public List<StudentExam> getExamList() {
        List<StudentExam> studentExams = studentExamMapper.selectByStudentId(getCurrentStudentId());
        return studentExams;
    }
}
