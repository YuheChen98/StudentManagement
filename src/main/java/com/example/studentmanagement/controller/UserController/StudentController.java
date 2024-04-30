package com.example.studentmanagement.controller.UserController;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.service.UserService.StudentService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @Operation(summary = "查询全部学生列表")
    @GetMapping("/student")
    public List student() {
        return studentMapper.selectAllStudent();
    }

    @Operation(summary = "根据id或name查询学生信息列表")
    @GetMapping("/student/search/{query}")
    public List<Student> searchStudent(@PathVariable String query){
        return studentService.searchStudent(query);
    }
    @Operation(summary = "查询单个学生信息")
    @GetMapping("/studentInfo")
    public Student getStudentById(@RequestBody Student student) {
        return studentMapper.selectByStudentId(student.getStudentId());
    }

    @Operation(summary = "创建学生")
    @PostMapping("/student")
    public Result add(@RequestBody Student student){
        Student newStudent = studentService.createStudent(student);
        if (newStudent != null){
            return Result.ok().data("student",student);
        } else {
            return Result.error().message("Create student failed");
        }
    }

    @Operation(summary = "更新学生信息")
    @PutMapping("/student")
    public Result updateStudent(@RequestBody Student student){
        int i = studentMapper.updateById(student);
        if ( i > 0){
            return Result.ok().data("student",student);
        } else {
            return Result.error().message("Update student failed");
        }
    }
    @Operation(summary = "分配tutor")
    @PutMapping("/student/tutor")
    public Result selectTutor(@RequestBody Student student){
        return studentService.selectTutor(student);
    }


    @Operation(summary = "删除学生")
    @DeleteMapping("/student")
    public Result deleteStudent(@RequestBody Student student){
    int i = studentMapper.deleteById(student);
        if ( i > 0){
            return Result.ok().data("student",student);
        } else {
            return Result.error().message("Delete student failed");
        }
    }
}
