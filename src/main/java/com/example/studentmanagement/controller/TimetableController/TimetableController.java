package com.example.studentmanagement.controller.TimetableController;


import com.example.studentmanagement.entity.Timetable.TimeslotModuleRoom;
import com.example.studentmanagement.mapper.TimetableMapper.TimeslotModuleRoomMapper;
import com.example.studentmanagement.service.TimetableService.TimeslotModuleRoomService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST controller for managing timetable of modules within the student management system.
 * Provides endpoints for users to access their own timetable.
 * author: Lingxu Huang
 * modified by Lingxu Huang at May 5th to Result class as return for create and delete timeslot method
 * modified by Lingxu Huang at May 7th to use correct mapping annotation and correct url style
 * date: May 3rd 2024, mod May 5th, mod May 7th
 */
@RestController
public class TimetableController {

    @Autowired
    TimeslotModuleRoomMapper timeslotModuleRoomMapper;

    @Autowired
    TimeslotModuleRoomService timeslotModuleRoomService;

    /**
     * Retrieves the currently authenticated user's ID from the security context.
     * @return The ID of the currently authenticated user.
     */
    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

    /**
     * Create a new timeslot for a module, with the room that uses this timeslot, in the database
     * This endpoint requires admin privileges.
     * @param timeslotModuleRoom A TimeslotModuleRoom object containing the timeslot, moduleId and roomId
     * @return A Result object containing the created TimeslotModuleRoom or an error message
     */
    @Operation(summary = "Add a new timeslot for a module with room")
    @PostMapping("/admin/timetable")
    @PreAuthorize("hasRole('ADMIN')")
    public Result createNewTimeslot(@RequestBody TimeslotModuleRoom timeslotModuleRoom){
        if(timeslotModuleRoomService.createNewTimeslot(timeslotModuleRoom) != null){
            return Result.ok().data("timeslot", timeslotModuleRoom);
        }else{
            return Result.error().message("Create timeslot failed");
        }
    }

    /**
     * Retrieves timetable of all modules
     * This endpoint requires admin privileges.
     * @return A list of TimeslotModuleRoom object represent all modules's timetable
     */
    @Operation(summary = "Get all module's timetable")
    @GetMapping("/admin/timetable")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TimeslotModuleRoom> getAllTimetable(){
        return timeslotModuleRoomMapper.SelectAll();
    }

    /**
     * Retrieves timetable of a lecturer based on the current lecturer's ID.
     * Requires lecturer-level security access.
     * @return A list of TimeslotModuleRoom object represent the lecturer's timetable.
     */
    @Operation(summary = "Get lecturer's timetable")
    @GetMapping("/lecturer/timetable")
    @PreAuthorize("hasRole('LECTURER')")
    public List<TimeslotModuleRoom> getTimetableByLecturerId(){
        String lecturerId = getCurrentUserId();
        return timeslotModuleRoomService.getTimetableByLecturerId(lecturerId);
    }


    /**
     * Retrieves timetable of a student based on the current student's ID.
     * Requires student-level security access.
     * @return A list of TimeslotModuleRoom object represent the student's timetable.
     */
    @Operation(summary = "Get student's timetable")
    @GetMapping("/student/timetable")
    @PreAuthorize("hasRole('STUDENT')")
    public List<TimeslotModuleRoom> getTimetableByStudentId(){
        String studentId = getCurrentUserId();
        return timeslotModuleRoomService.getTimetableByStudentId(studentId);
    }

    /**
     * Deletes a timeslot from the database.
     * This endpoint requires admin privileges.
     * @param timeslotModuleRoom A TimeslotModuleRoom object containing the timeslot, moduleId and roomId, representing the timeslot that the user wants to delete
     * @return a result object indicating the success or failure of the deletion
     */
    @Operation(summary = "Delete a timeslot of a module")
    @DeleteMapping("/admin/timetable")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteTimeslot(@RequestBody TimeslotModuleRoom timeslotModuleRoom){
        int i = timeslotModuleRoomMapper.remove(timeslotModuleRoom);
        if(i>0){
            return Result.ok().data("timeslot",timeslotModuleRoom);
        }else{
            return Result.error().message("Delete timeslot failed");
        }

    }






}
