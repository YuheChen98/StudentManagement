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
    public List student() {
        return studentMapper.selectAllStudent();
    }

    @Operation(summary = "根据id或name查询学生信息列表")
    @GetMapping("/student/search/{query}")
    public List<Student> searchStudent(@PathVariable String query){
        return studentService.searchStudent(query);
    }
    @Operation(summary = "根据id查询学生信息")
    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable String studentId) {
        Student student = studentMapper.selectByStudentId(studentId);
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
    @Operation(summary = "分配tutor")
    @PutMapping("/student/tutor")
    public Student selectTutor(@RequestBody Student student){
        return studentService.selectTutor(student);
    }


    @Operation(summary = "删除学生")
    @DeleteMapping("/student/{studentId}")
    public Student deleteStudent(@RequestBody Student student){
    int i = studentMapper.deleteById(student);
        if ( i > 0){
            return student;
        } else {
            return null;
        }
    }
}
