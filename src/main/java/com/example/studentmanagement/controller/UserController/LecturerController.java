package com.example.studentmanagement.controller.UserController;

import com.example.studentmanagement.entity.User.Lecturer;
import com.example.studentmanagement.mapper.UserMapper.LecturerMapper;
import com.example.studentmanagement.service.UserService.LecturerService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class providing API endpoints for managing lecturers within the student management system.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@RestController
@CrossOrigin
public class LecturerController {
    @Autowired
    private LecturerMapper lecturerMapper;
    @Autowired
    private LecturerService lecturerService;

    /**
     * Creates and adds a new lecturer to the database.
     *
     * @param lecturer the lecturer to be added
     * @return a Result object containing the newly created lecturer or an error message if creation failed
     */
    @Operation(summary = "Create a lecturer")
    @PostMapping("/admin/lecturer")
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(@RequestBody Lecturer lecturer){
        Lecturer newLecturer = lecturerService.createLecturer(lecturer);
        if (newLecturer != null){
            return Result.ok().data("lecturer", newLecturer);
        } else {
            return Result.error().message("Create lecturer failed");
        }
    }

    /**
     * Retrieves a list of all lecturers.
     *
     * @return a list of all registered lecturers
     */
    @Operation(summary = "List all lecturers")
    @GetMapping("/admin/lecturer")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Lecturer> listLecturers() {
        return lecturerMapper.selectAllLecturer();
    }

    /**
     * Searches for lecturers based on the provided query, which could be an ID or a name.
     *
     * @param query the query string used for searching
     * @return a list of lecturers matching the search criteria
     */
    @Operation(summary = "Search for lecturers by ID or name")
    @GetMapping("/admin/lecturer/{query}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Lecturer> searchLecturer(@PathVariable String query){
        return lecturerService.searchLecturer(query);
    }

    /**
     * Retrieves detailed information about a specific lecturer by their lecturer ID.
     *
     * @param lecturer the lecturer whose details are to be retrieved
     * @return the detailed information of the requested lecturer
     */
    @Operation(summary = "Get detailed information for one lecturer")
    @GetMapping("/admin/lecturerInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public Lecturer getLecturerById(@RequestBody Lecturer lecturer) {
        return lecturerMapper.selectById(lecturer.getLecturerId());
    }

    /**
     * Updates the information of an existing lecturer.
     *
     * @param lecturer the lecturer object with updated details
     * @return a Result object indicating the success or failure of the update
     */
    @Operation(summary = "Update a lecturer's information")
    @PutMapping("/admin/lecturer")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateLecturer(@RequestBody Lecturer lecturer){
        int i = lecturerMapper.updateById(lecturer);
        if (i > 0) {
            return Result.ok().data("lecturer", lecturer);
        } else {
            return Result.error().message("Update lecturer failed");
        }
    }

    /**
     * Deletes a lecturer from the database.
     *
     * @param lecturer the lecturer to be deleted
     * @return a Result object indicating the success or failure of the deletion
     */
    @Operation(summary = "Delete a lecturer")
    @DeleteMapping("/admin/lecturer")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteLecturer(@RequestBody Lecturer lecturer){
        int i = lecturerMapper.deleteById(lecturer);
        if (i > 0) {
            return Result.ok().data("lecturer", lecturer);
        } else {
            return Result.error().message("Delete lecturer failed");
        }
    }
}
