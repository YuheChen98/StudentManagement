package com.example.studentmanagement.controller.AccountController;

import com.example.studentmanagement.entity.User.Tutor;
import com.example.studentmanagement.mapper.UserMapper.TutorMapper;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TutorAccountController {

    @Autowired
    private TutorMapper tutorMapper;
    private String getCurrentTutorId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

    @Operation(summary = "Retrieve tutor profile")
    @GetMapping("/tutor/profile")
    @PreAuthorize("hasRole('TUTOR')")
    public Result getProfile() {
        String tutorId = getCurrentTutorId();
        Tutor tutor = tutorMapper.selectById(tutorId);
        return Result.ok().data("tutor", tutor);
    }


}
