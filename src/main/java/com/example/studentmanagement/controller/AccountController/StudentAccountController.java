package com.example.studentmanagement.controller.AccountController;

import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/student")
public class StudentAccountController {

    private StudentMapper studentMapper;
    @Operation(summary = "student profile")
    @GetMapping("/profile")
    @PreAuthorize("hasRole('STUDENT')")
    public Result getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentId = (String) authentication.getPrincipal(); // 从认证信息中获取学生ID

        Student student = studentMapper.selectById(studentId);
        return  Result.ok().data("student",student);
    }
}
