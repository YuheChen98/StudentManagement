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


/**
 * REST controller for managing Tutor accounts in the Student Management System.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@RestController
@CrossOrigin // Allows cross-origin requests to this controller if needed by the frontend.
public class TutorAccountController {

    @Autowired
    private TutorMapper tutorMapper;

    /**
     * Retrieves the current authenticated tutor's unique identifier from the security context.
     * This method is private as it's a utility function within the controller.
     *
     * @return the current tutor's ID as a {@link String}
     */
    private String getCurrentTutorId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

    /**
     * Endpoint to retrieve the profile information of the currently authenticated tutor.
     * This method ensures that the request is made by an authenticated user with a TUTOR role.
     *
     * <p>Successful invocation of this endpoint returns the tutor's profile data, including
     * personal details stored in the system. It uses the {@link TutorMapper} to query the
     * database based on the tutor ID retrieved from the security context.</p>
     *
     * @return a {@link Result} object containing the tutor's profile data if successful
     * @throws SecurityException if accessed by a user without the TUTOR role
     */
    @Operation(summary = "Retrieve tutor profile")
    @GetMapping("/tutor/profile")
    @PreAuthorize("hasRole('TUTOR')")
    public Result getProfile() {
        String tutorId = getCurrentTutorId();
        Tutor tutor = tutorMapper.selectById(tutorId);
        return Result.ok().data("tutor", tutor);
    }
}
