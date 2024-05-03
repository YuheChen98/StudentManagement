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
/**
 * REST controller for managing lecturer accounts and their academic activities within the student management system.
 * Provides endpoints for lecturers to access their profiles, manage modules, and oversee student assessments.
 */
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

    /**
     * Retrieves the currently authenticated lecturer's ID from the security context.
     * @return The ID of the currently authenticated lecturer.
     */
    private String getCurrentLecturerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

    /**
     * Retrieves the profile of the currently authenticated lecturer.
     * Requires lecturer-level security access.
     *
     * @return A Result object containing the lecturer's profile data.
     */
    @Operation(summary = "Retrieve lecturer profile")
    @GetMapping("/lecturer/profile")
    @PreAuthorize("hasRole('LECTURER')")
    public Result getProfile() {
        String lecturerId = getCurrentLecturerId();
        Lecturer lecturer = lecturerMapper.selectById(lecturerId);
        return Result.ok().data("lecturer", lecturer);
    }

    /**
     * Retrieves all modules taught by the currently authenticated lecturer.
     * Requires lecturer-level security access.
     *
     * @return A Result object containing a list of modules taught by the lecturer.
     */
    @Operation(summary = "Retrieve all modules taught by the lecturer")
    @GetMapping("/lecturer/modules")
    @PreAuthorize("hasRole('LECTURER')")
    public Result getAllModules() {
        String lecturerId = getCurrentLecturerId();
        Lecturer lecturer = lecturerMapper.selectById(lecturerId);
        List<Module> modules = lecturer.getModules();
        return Result.ok().data("modules", modules);
    }

    /**
     * Retrieves details about a specific module by its ID.
     * Requires lecturer-level security access.
     *
     * @param module A module object containing the ID of the module to retrieve.
     * @return The module object with full details.
     */
    @Operation(summary = "Retrieve details of a specific module")
    @GetMapping("/lecturer/module")
    @PreAuthorize("hasRole('LECTURER')")
    public Module getModule(@RequestBody Module module) {
        return moduleMapper.selectByModuleId(module.getModuleId());
    }
    /**
     * Updates an existing module with new details provided by the lecturer.
     * Requires lecturer-level security access.
     *
     * @param module The module containing updated information.
     * @return A Result object indicating success or failure of the update operation.
     */
    @Operation(summary = "Update module information")
    @PutMapping("/lecturer/module")
    @PreAuthorize("hasRole('LECTURER')")
    public Result updateModule(@RequestBody Module module) {
        int i = moduleMapper.updateById(module);
        if (i > 0) {
            return Result.ok().data("module", module);
        } else {
            return Result.error().message("Update module failed");
        }
    }

    /**
     * Creates a new exam or coursework for a module managed by the lecturer.
     * Requires lecturer-level security access.
     *
     * @param examCoursework The exam or coursework to be created.
     * @return A Result object indicating success or failure of the creation operation.
     */
    @Operation(summary = "Create exam or coursework")
    @PostMapping("/lecturer/exam")
    @PreAuthorize("hasRole('LECTURER')")
    public Result createExam(@RequestBody ExamCoursework examCoursework) {
        int i = examCourseworkMapper.add(examCoursework);
        if (i > 0) {
            return Result.ok().data("examCoursework", examCoursework);
        } else {
            return Result.error().message("Create exam or coursework failed");
        }
    }

    /**
     * Retrieves all exams associated with a student under the lecturer's purview.
     * Requires lecturer-level security access.
     *
     * @param studentExam A StudentExam object containing details to identify the student's exams.
     * @return A list of StudentExam objects detailing the student's assessments.
     */
    @Operation(summary = "Retrieve exams of a student")
    @GetMapping("/lecturer/examInfo")
    @PreAuthorize("hasRole('LECTURER')")
    public List<StudentExam> getStudentExam(@RequestBody StudentExam studentExam) {
        return studentExamMapper.selectByExamId(studentExam.getExamCoursework().getExamId());
    }

    /**
     * Updates the exam status of a student, marking it as submitted, graded, or failed based on the input provided.
     * Requires lecturer-level security access.
     *
     * @param studentExam The StudentExam object containing the new status to be updated.
     * @return A Result object indicating success or failure of the update operation.
     */
    @Operation(summary = "Update exam status of a student")
    @PutMapping("/lecturer/examInfo")
    @PreAuthorize("hasRole('LECTURER')")
    public Result updateStudentExam(@RequestBody StudentExam studentExam) {
        int i = studentExamMapper.update(studentExam);
        if (i > 0) {
            return Result.ok().data("studentExam", studentExam);
        } else {
            return Result.error().message("Update exam status of student failed");
        }
    }

    /**
     * Sets or changes the exam status for a student under the lecturer's purview, such as marking an exam as complete or incomplete.
     * Requires lecturer-level security access.
     *
     * @param studentExam The StudentExam object containing exam details and the new status.
     * @return A Result object indicating success or failure of the operation.
     */
    @Operation(summary = "Set exam status of a student")
    @PostMapping("/lecturer/examInfo")
    @PreAuthorize("hasRole('LECTURER')")
    public Result setStudentExam(@RequestBody StudentExam studentExam) {
        int i = studentExamMapper.add(studentExam);
        if (i > 0) {
            return Result.ok().data("studentExam", studentExam);
        } else {
            return Result.error().message("Set exam status of student failed");
        }
    }
}
