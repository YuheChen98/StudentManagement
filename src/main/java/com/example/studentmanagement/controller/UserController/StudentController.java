package com.example.studentmanagement.controller.UserController;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.service.UserService.StudentService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @Operation(summary = "query all student list")
    @GetMapping("/admin/student")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> allStudent() {
        return studentMapper.selectAllStudent();
    }

    @Operation(summary = "query student by ID or name")
    @GetMapping("/admin/student/{query}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> searchStudent(@PathVariable String query){
        return studentService.searchStudent(query);
    }
    @Operation(summary = "query one student")
    @GetMapping("/admin/studentInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public Student getStudentById(@RequestBody Student student) {
        return studentMapper.selectByStudentId(student.getStudentId());
    }

    @Operation(summary = "create student")
    @PostMapping("/admin/student")
    @PreAuthorize("hasRole('ADMIN')")
    public Result createStudent(@RequestBody Student student){
        Student newStudent = studentService.createStudent(student);
        if (newStudent != null){
            return Result.ok().data("student",student);
        } else {
            return Result.error().message("Create student failed");
        }
    }

    @Operation(summary = "update student and select programme")
    @PutMapping("/admin/student")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateStudent(@RequestBody Student student){
        int i = studentMapper.updateStudentAndProgramme(student);
        if ( i > 0){
            return Result.ok().data("student",student);
        } else {
            return Result.error().message("Update student failed");
        }
    }

    @Operation(summary = "assign tutor to student")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/student/selectTutor")
    public Result selectTutor(@RequestBody Student student){
        return studentService.selectTutor(student);
    }


    @Operation(summary = "delete student")
    @DeleteMapping("/admin/student")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteStudent(@RequestBody Student student){
    int i = studentMapper.deleteById(student);
        if ( i > 0){
            return Result.ok().data("student",student);
        } else {
            return Result.error().message("Delete student failed");
        }
    }
}
