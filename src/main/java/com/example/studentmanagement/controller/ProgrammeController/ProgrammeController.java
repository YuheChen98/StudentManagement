package com.example.studentmanagement.controller.ProgrammeController;

import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.Programme.ProgrammeModule;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper;
import com.example.studentmanagement.service.ProgrammeService.ProgrammeService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing academic programmes within the student management system.
 * Provides endpoints for CRUD operations on programmes and related entities.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@RestController
@CrossOrigin
public class ProgrammeController {
    @Autowired
    private ProgrammeMapper programmeMapper;
    @Autowired
    private ProgrammeService programmeService;
    @Autowired
    private ProgrammeModuleMapper programmeModuleMapper;

    /**
     * Retrieves a list of all academic programmes.
     * Requires admin privileges.
     *
     * @return A list of all programmes.
     */
    @Operation(summary = "Retrieve all programmes")
    @GetMapping("/admin/programme")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Programme> listProgrammes() {
        return programmeMapper.selectAllProgramme();
    }

    /**
     * Retrieves detailed information about a specific programme by its ID.
     * Requires admin privileges.
     *
     * @param programme A programme object containing the ID of the programme to retrieve.
     * @return The detailed information of the requested programme.
     */
    @Operation(summary = "Get programme by ID")
    @GetMapping("/admin/programmeInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public Programme getProgrammeById(@RequestBody Programme programme) {
        return programmeMapper.selectByProgrammeId(programme.getProgrammeId());
    }

    /**
     * Retrieves all modules associated with a specific programme ID.
     * Requires admin privileges.
     *
     * @param programme A programme object containing the ID for which modules are to be retrieved.
     * @return A list of modules associated with the programme.
     */
    @Operation(summary = "Retrieve all modules for a specific programme")
    @GetMapping("/admin/programme/module")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Module> getProgrammeModules(@RequestBody Programme programme) {
        return programmeService.getProgrammeModules(programme.getProgrammeId());
    }

    /**
     * Creates a new programme in the database.
     * Requires admin privileges and returns the created programme if successful.
     *
     * @param programme The programme to be created.
     * @return A Result object containing the created programme or an error message.
     */
    @Operation(summary = "Create a programme")
    @PostMapping("/admin/programme")
    @PreAuthorize("hasRole('ADMIN')")
    public Result addProgramme(@RequestBody Programme programme) {
        int i = programmeMapper.insert(programme);
        if (i > 0) {
            return Result.ok().data("programme", programme);
        } else {
            return Result.error().message("Create programme failed");
        }
    }

    /**
     * Updates an existing programme in the database.
     * Requires admin privileges and returns the updated programme if successful.
     *
     * @param programme The programme to update.
     * @return A Result object indicating success or failure of the update.
     */
    @Operation(summary = "Update programme information")
    @PutMapping("/admin/programme")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateProgramme(@RequestBody Programme programme) {
        int i = programmeMapper.updateById(programme);
        if (i > 0) {
            return Result.ok().data("programme", programme);
        } else {
            return Result.error().message("Update programme failed");
        }
    }

    /**
     * Adds a module to a programme, effectively creating a link between the programme and a module.
     * Requires admin privileges.
     *
     * @param programmeModule The programme module linkage to create.
     * @return A Result object indicating success or failure of the operation.
     */
    @Operation(summary = "Add a module to a programme")
    @PostMapping("/admin/programme/module")
    @PreAuthorize("hasRole('ADMIN')")
    public Result addModuleToProgramme(@RequestBody ProgrammeModule programmeModule) {
        int i = programmeModuleMapper.insert(programmeModule);
        if (i > 0) {
            return Result.ok().data("programmeModule", programmeModule);
        } else {
            return Result.error().message("Add module to programme failed");
        }
    }
}
