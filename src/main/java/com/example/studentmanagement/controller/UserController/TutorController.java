package com.example.studentmanagement.controller.UserController;


import com.example.studentmanagement.entity.User.Tutor;
import com.example.studentmanagement.mapper.UserMapper.TutorMapper;
import com.example.studentmanagement.service.UserService.TutorService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class providing API endpoints for managing tutors within the student management system.
 */
@RestController
@CrossOrigin
public class TutorController {
    @Autowired
    private TutorMapper tutorMapper;
    @Autowired
    private TutorService tutorService;

    /**
     * Creates a new tutor and adds them to the database.
     *
     * @param tutor the tutor to be added
     * @return a Result object containing the newly created tutor or an error message if creation failed
     */
    @Operation(summary = "Create a tutor")
    @PostMapping("/admin/tutor")
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(@RequestBody Tutor tutor){
        Tutor newTutor = tutorService.createTutor(tutor);
        if (newTutor != null){
            return Result.ok().data("tutor", newTutor);
        } else {
            return Result.error().message("Create tutor failed");
        }
    }

    /**
     * Retrieves a list of all tutors.
     *
     * @return a list of all registered tutors
     */
    @Operation(summary = "List all tutors")
    @GetMapping("/admin/tutor")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Tutor> listTutors() {
        return tutorMapper.selectAllTutor();
    }

    /**
     * Searches for tutors based on the provided query, which could be an ID or a name.
     *
     * @param query the query string used for searching
     * @return a list of tutors matching the search criteria
     */
    @Operation(summary = "Search for tutors by ID or name")
    @GetMapping("/admin/tutor/{query}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Tutor> searchTutor(@PathVariable String query){
        return tutorService.searchTutor(query);
    }

    /**
     * Retrieves details of a single tutor by their tutor ID.
     *
     * @param tutor the tutor whose details are to be retrieved
     * @return the details of the requested tutor
     */
    @Operation(summary = "Get detailed information for one tutor")
    @GetMapping("/admin/tutorInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public Tutor getTutorById(@RequestBody Tutor tutor) {
        return tutorMapper.selectById(tutor.getTutorId());
    }

    /**
     * Updates the information of an existing tutor.
     *
     * @param tutor the tutor object with updated details
     * @return a Result object indicating the success or failure of the update
     */
    @Operation(summary = "Update a tutor's information")
    @PutMapping("/admin/tutor")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateTutor(@RequestBody Tutor tutor){
        int i = tutorMapper.updateTutor(tutor);
        if (i > 0) {
            return Result.ok().data("tutor", tutor);
        } else {
            return Result.error().message("Update tutor failed");
        }
    }

    /**
     * Deletes a tutor from the database.
     *
     * @param tutor the tutor to be deleted
     * @return a Result object indicating the success or failure of the deletion
     */
    @Operation(summary = "Delete a tutor")
    @DeleteMapping("/admin/tutor")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteTutor(@RequestBody Tutor tutor){
        int i = tutorMapper.deleteById(tutor);
        if (i > 0) {
            return Result.ok().data("tutor", tutor);
        } else {
            return Result.error().message("Delete tutor failed");
        }
    }
}
