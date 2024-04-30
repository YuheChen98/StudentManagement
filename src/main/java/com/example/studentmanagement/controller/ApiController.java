package com.example.studentmanagement.controller;

import com.example.studentmanagement.utils.LoginRequest;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.entity.User.Tutor;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.mapper.UserMapper.TutorMapper;
import com.example.studentmanagement.utils.JwtUtils;
import com.example.studentmanagement.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TutorMapper tutorMapper;
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest){
        String userId = loginRequest.getUserId();
        String password = loginRequest.getPassword();

        Student student = studentMapper.selectById(userId);
        Tutor tutor = tutorMapper.selectById(userId);

        if (student != null && student.getPassword().equals(password)) {
            String token = JwtUtils.generateToken(userId);
            return Result.ok().data("token",token).data("role","student");
        }
        if (tutor != null && tutor.getPassword().equals(password)) {
            String token = JwtUtils.generateToken(userId);
            return Result.ok().data("token",token).data("role","tutor");
        }
        else {
            return Result.error().message("Invalid userId or password");
        }
    }
    @GetMapping("/info")
    public Result info(String token) {
        String userId = JwtUtils.getClaimsByToken(token).getSubject();
        return Result.ok().data("userId",userId);
    }

    @PostMapping("/logout")
    public Result logout(){ return Result.ok();}


}
