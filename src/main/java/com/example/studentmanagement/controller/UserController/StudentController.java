package com.example.studentmanagement.controller.UserController;

import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.service.UserService.StudentService;
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
    public List student(){
        List<Student> students = studentMapper.selectList(null);
        return students;
    }

    @Operation(summary = "根据id查询学生信息")
    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable String studentId) {
        Student student = studentMapper.selectById(studentId);
        return student;
    }


    @Operation(summary = "创建学生")
    @PostMapping("/student")
    public Student add(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @Operation(summary = "更新学生信息")
    @PutMapping("/student/{studentId}")
    public Student updateStudent(@RequestBody Student student){
        int i = studentMapper.updateById(student);
        if ( i > 0){
            return student;
        } else {
            return null;
        }
    }

}
