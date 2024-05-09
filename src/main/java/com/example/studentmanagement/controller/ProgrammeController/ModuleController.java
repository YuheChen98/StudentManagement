package com.example.studentmanagement.controller.ProgrammeController;

import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing academic modules within the student management system.
 * Provides endpoints for CRUD operations on modules.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@RestController
@CrossOrigin
public class ModuleController {
    @Autowired
    private ModuleMapper moduleMapper;

    /**
     * Retrieves a list of all modules in the system.
     * This endpoint requires admin privileges.
     *
     * @return A list of all modules.
     */
    @Operation(summary = "Retrieve all modules")
    @GetMapping("/admin/module")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Module> listModules() {
        return moduleMapper.selectAllModule();
    }

    /**
     * Retrieves detailed information about a specific module based on its ID.
     * This endpoint requires admin privileges.
     *
     * @param module A module object containing the ID of the module to retrieve.
     * @return The detailed information of the requested module.
     */
    @Operation(summary = "Get module by ID")
    @GetMapping("/admin/moduleInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public Module getModuleById(@RequestBody Module module) {
        return moduleMapper.selectByModuleId(module.getModuleId());
    }

    /**
     * Creates a new module in the database.
     * This endpoint requires admin privileges and returns the created module if successful.
     *
     * @param module The module to be created.
     * @return A Result object containing the created module or an error message.
     */
    @Operation(summary = "Create a module")
    @PostMapping("/admin/module")
    @PreAuthorize("hasRole('ADMIN')")
    public Result addModule(@RequestBody Module module){
        int i = moduleMapper.add(module);
        if (i > 0) {
            return Result.ok().data("module", module);
        } else {
            return Result.error().message("Create module failed");
        }
    }

    /**
     * Updates an existing module in the database.
     * This endpoint requires admin privileges and returns the updated module if successful.
     *
     * @param module The module to be updated.
     * @return A Result object indicating success or failure of the update.
     */
    @Operation(summary = "Update module information")
    @PutMapping("/admin/module")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateModule(@RequestBody Module module){
        int i = moduleMapper.updateById(module);
        if (i > 0) {
            return Result.ok().data("module", module);
        } else {
            return Result.error().message("Update module failed");
        }
    }
}
