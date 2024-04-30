package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.User.Administrator;
import com.example.studentmanagement.entity.User.Lecturer;
import com.example.studentmanagement.mapper.UserMapper.AdministratorMapper;
import com.example.studentmanagement.mapper.UserMapper.LecturerMapper;
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
    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private LecturerMapper lecturerMapper;
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest){
        String userId = loginRequest.getUserId();
        String password = loginRequest.getPassword();

        if(loginRequest.getRole().equals("STUDENT")){
            Student student = studentMapper.selectById(userId);
            if (student != null && student.getPassword().equals(password)) {
                String token = JwtUtils.generateToken(loginRequest);
                return Result.ok().data("token",token);
            }
        }
        if(loginRequest.getRole().equals("TUTOR")){
            Tutor tutor = tutorMapper.selectById(userId);
            if (tutor != null && tutor.getPassword().equals(password)) {
                String token = JwtUtils.generateToken(loginRequest);
                return Result.ok().data("token",token);
            }
        }
        if(loginRequest.getRole().equals("ADMIN")){
            Administrator administrator = administratorMapper.selectById(userId);
            if (administrator != null && administrator.getPassword().equals(password)) {
                String token = JwtUtils.generateToken(loginRequest);
                return Result.ok().data("token",token);
            }
        }
        if(loginRequest.getRole().equals("LECTURER")){
            Lecturer lecturer = lecturerMapper.selectById(userId);
            if (lecturer != null && lecturer.getPassword().equals(password)) {
                String token = JwtUtils.generateToken(loginRequest);
                return Result.ok().data("token",token);
            }
        }
        return Result.error().message("Invalid userId or password");
    }
//    @GetMapping("/info")
//    public Result info(String token) {
//        String userId = JwtUtils.getClaimsByToken(token).getSubject();
//        return Result.ok().data("userId",userId);
//
//    }

    @PostMapping("/logout")
    public Result logout(){ return Result.ok();}


}
