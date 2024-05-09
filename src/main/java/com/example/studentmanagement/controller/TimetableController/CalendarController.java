package com.example.studentmanagement.controller.TimetableController;

import com.example.studentmanagement.entity.Timetable.Calendar;
import com.example.studentmanagement.mapper.TimetableMapper.CalendarMapper;
import com.example.studentmanagement.service.TimetableService.CalendarService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * REST controller for managing calendar of student and tutor within the student management system.
 * Provides endpoints for users to access their own calendar.
 * author: Lingxu Huang
 * modified by Lingxu Huang at May 4th to move method for adding new calendar to NotificationServiceImpl class
 * modified by Lingxu Huang at May 5th to use Result class as return for deleteMeeting method
 * modified by Lingxu Huang at May 7th to use correct mapping annotation and correct url style
 * date: May 3rd 2024, mod May 4th, mod May 5th, mod May 7th
 */
@RestController
public class CalendarController {

    @Autowired
    CalendarMapper calendarMapper;

    @Autowired
    CalendarService calendarService;

    /**
     * Retrieves the currently authenticated user's ID from the security context.
     * @return The ID of the currently authenticated user.
     */
    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

    /**
     * Retrieves calendar of a user based on the current user's Id.
     * @return A list of Calendar object represented the user's calendar
     */
    @Operation(summary = "Retrieve a list of meeting information according to user Id")
    @GetMapping("/calendar")
    public List<Calendar> getCalendarByUserId() {
        return calendarMapper.selectByUserId(getCurrentUserId());}

    /**
     * Deletes a meeting from the database.
     * This endpoint requires admin privileges.
     * @param calendar A calendar object containing timeslot, studentId, tutorId, representing the meeting that the user wants to delete.
     * @return a result object indicating the success or failure of the deletion
     */
    @Operation(summary = "Delete a meeting in calendar")
    @DeleteMapping("/admin/calendar")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteMeeting(@RequestBody Calendar calendar){
        int i = calendarMapper.remove(calendar);
        if(i>0){
            return Result.ok().data("calendar",calendar);
        }else{
            return Result.error().message("Delete meeting failed");
        }

    }


}
