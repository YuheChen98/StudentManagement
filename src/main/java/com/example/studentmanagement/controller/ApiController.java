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

/**
 * Provides RESTful API endpoints for managing user authentication and information retrieval
 * in a student management system.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TutorMapper tutorMapper;
    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private LecturerMapper lecturerMapper;


    /**
     * Processes login requests for various user roles and returns authentication tokens.
     *
     * @param loginRequest the login details including user ID, password, and user role
     * @return a Result object containing a JWT token if authentication is successful; otherwise, an error message
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest){
        String userId = loginRequest.getUserId();
        String password = loginRequest.getPassword();

        // Handle login based on role
        if(loginRequest.getRole().equals("ROLE_STUDENT")){
            Student student = studentMapper.selectById(userId);
            if (student != null && student.getPassword().equals(password)) {
                String token = JwtUtils.generateToken(loginRequest);
                return Result.ok().data("token",token);
            }
        }
        if(loginRequest.getRole().equals("ROLE_TUTOR")){
            Tutor tutor = tutorMapper.selectById(userId);
            if (tutor != null && tutor.getPassword().equals(password)) {
                String token = JwtUtils.generateToken(loginRequest);
                return Result.ok().data("token",token);
            }
        }
        if(loginRequest.getRole().equals("ROLE_ADMIN")){
            Administrator administrator = administratorMapper.selectById(userId);
            if (administrator != null && administrator.getPassword().equals(password)) {
                String token = JwtUtils.generateToken(loginRequest);
                return Result.ok().data("token",token);
            }
        }
        if(loginRequest.getRole().equals("ROLE_LECTURER")){
            Lecturer lecturer = lecturerMapper.selectById(userId);
            if (lecturer != null && lecturer.getPassword().equals(password)) {
                String token = JwtUtils.generateToken(loginRequest);
                return Result.ok().data("token",token);
            }
        }
        return Result.error().message("Invalid userId or password");
    }


    /**
     * Retrieves user information based on the provided JWT token.
     *
     * @param token the JWT token used for authenticating user requests
     * @return a Result object containing the user ID associated with the provided token
     */
    @GetMapping("/info")
    public Result info(String token) {
        String userId = JwtUtils.getClaimsByToken(token).getSubject();
        return Result.ok().data("userId",userId);

    }

    /**
     * Ends the user's session and invalidates the authentication token.
     *
     * @return a Result object indicating successful logout
     */
    @PostMapping("/logout")
    public Result logout(){ return Result.ok();}


}
