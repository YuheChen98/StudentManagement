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

@RestController
@CrossOrigin
public class TutorController {
    @Autowired
    private TutorMapper tutorMapper;
    @Autowired
    private TutorService tutorService;

    @Operation(summary = "创建tutor")
    @PostMapping("/admin/tutor")
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(@RequestBody Tutor tutor){
        Tutor newTutor = tutorService.createTutor(tutor);
        if (newTutor != null){
            return Result.ok().data("tutor",tutor);
        } else {
            return Result.error().message("Create tutor failed");
        }
    }
    @Operation(summary = "查询全部tutor列表")
    @GetMapping("/admin/tutor")
    @PreAuthorize("hasRole('ADMIN')")
    public List tutor() {
        return tutorMapper.selectAllTutor();
    }

    @Operation(summary = "根据id或name查询tutor信息列表")
    @GetMapping("/admin/tutor/{query}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Tutor> searchTutor(@PathVariable String query){
        return tutorService.searchTutor(query);
    }

    @Operation(summary = "查询单个tutor信息")
    @GetMapping("/admin/tutorInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public Tutor getTutorById(@RequestBody Tutor tutor) {
        return tutorMapper.selectById(tutor.getTutorId());
    }
    @Operation(summary = "更新tutor信息")
    @PutMapping("/admin/tutor")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateTutor(@RequestBody Tutor tutor){
        int i = tutorMapper.updateTutor(tutor);
        if ( i > 0){
            return Result.ok().data("tutor",tutor);
        } else {
            return Result.error().message("Update tutor failed");
        }
    }
    @Operation(summary = "删除tutor")
    @DeleteMapping("/admin/tutor")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteTutor(@RequestBody Tutor tutor){
        int i = tutorMapper.deleteById(tutor);
        if ( i > 0){
            return Result.ok().data("tutor",tutor);
        } else {
            return Result.error().message("Delete tutor failed");
        }
    }

}
