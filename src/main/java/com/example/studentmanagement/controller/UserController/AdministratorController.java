package com.example.studentmanagement.controller.UserController;

import com.example.studentmanagement.entity.User.Administrator;
import com.example.studentmanagement.mapper.UserMapper.AdministratorMapper;
import com.example.studentmanagement.service.UserService.AdministratorService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller class providing API endpoints for managing administrators within the student management system.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@RestController
@CrossOrigin
public class AdministratorController {
    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private AdministratorService administratorService;



    /**
     * Creates a new administrator and adds it to the database.
     *
     * @param administrator the administrator to be added
     * @return a Result object containing the newly created administrator, or an error message if creation failed
     */
    @Operation(summary = "Create an administrator")
    @PostMapping("/admin/administrator")
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(@RequestBody Administrator administrator){
        Administrator newAdministrator = administratorService.createAdministrator(administrator);
        if (newAdministrator != null){
            return Result.ok().data("administrator",administrator);
        } else {
            return Result.error().message("Create administrator failed");
        }
    }



    /**
     * Retrieves a list of all administrators.
     *
     * @return a list of all registered administrators
     */
    @Operation(summary = "List all administrators")
    @GetMapping("/admin/administrator")
    @PreAuthorize("hasRole('ADMIN')")
    public List administrator() {
        return administratorMapper.selectList(null);
    }



    /**
     * Searches for administrators based on an ID or name.
     *
     * @param query the ID or name to search for
     * @return a list of administrators matching the search criteria
     */
    @Operation(summary = "Search for administrators by ID or name")
    @GetMapping("/admin/administrator/{query}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Administrator> searchAdministrator(@PathVariable String query){
        return administratorService.searchAdministrator(query);
    }



    /**
     * Retrieves information about a specific administrator.
     *
     * @param administrator the administrator whose details are to be retrieved
     * @return an administrator object
     */
    @Operation(summary = "Get information of a single administrator")
    @GetMapping("/admin/administratorInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public Administrator getAdministratorById(@RequestBody Administrator administrator) {
        return administratorMapper.selectById(administrator.getAdminId());
    }



    /**
     * Updates the information of an existing administrator.
     *
     * @param administrator the administrator with updated details
     * @return a Result object indicating success or failure
     */
    @Operation(summary = "Update an administrator's information")
    @PutMapping("/admin/administrator")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateAdministrator(@RequestBody Administrator administrator){
        int i = administratorMapper.updateById(administrator);
        if ( i > 0){
            return Result.ok().data("administrator",administrator);
        } else {
            return Result.error().message("Update administrator failed");
        }
    }



    /**
     * Deletes an administrator from the database.
     *
     * @param administrator the administrator to be deleted
     * @return a Result object indicating success or failure
     */
    @Operation(summary = "Delete an administrator")
    @DeleteMapping("/admin/administrator")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteAdministrator(@RequestBody Administrator administrator){
        int i = administratorMapper.deleteById(administrator);
        if ( i > 0){
            return Result.ok().data("administrator",administrator);
        } else {
            return Result.error().message("Delete administrator failed");
        }
    }

}
