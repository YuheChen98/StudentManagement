package com.example.studentmanagement.controller.UserController;


import com.example.studentmanagement.entity.User.Tutor;
import com.example.studentmanagement.mapper.UserMapper.TutorMapper;
import com.example.studentmanagement.service.UserService.TutorService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TutorController {
    @Autowired
    private TutorMapper tutorMapper;
    @Autowired
    private TutorService tutorService;

    @Operation(summary = "创建tutor")
    @PostMapping("/tutor")
    public Result add(@RequestBody Tutor tutor){
        Tutor newTutor = tutorService.createTutor(tutor);
        if (newTutor != null){
            return Result.ok().data("tutor",tutor);
        } else {
            return Result.error().message("Create tutor failed");
        }
    }
    @Operation(summary = "查询全部tutor列表")
    @GetMapping("/tutor")
    public List tutor() {
        return tutorMapper.selectAllTutor();
    }

    @Operation(summary = "根据id或name查询tutor信息列表")
    @GetMapping("/tutor/search/{query}")
    public List<Tutor> searchTutor(@PathVariable String query){
        return tutorService.searchTutor(query);
    }

    @Operation(summary = "查询单个tutor信息")
    @GetMapping("/tutorInfo")
    public Tutor getTutorById(@RequestBody Tutor tutor) {
        return tutorMapper.selectById(tutor.getTutorId());
    }
    @Operation(summary = "更新tutor信息")
    @PutMapping("/tutor")
    public Result updateTutor(@RequestBody Tutor tutor){
        int i = tutorMapper.updateById(tutor);
        if ( i > 0){
            return Result.ok().data("tutor",tutor);
        } else {
            return Result.error().message("Update tutor failed");
        }
    }
    @Operation(summary = "删除tutor")
    @DeleteMapping("/tutor")
    public Result deleteTutor(@RequestBody Tutor tutor){
        int i = tutorMapper.deleteById(tutor);
        if ( i > 0){
            return Result.ok().data("tutor",tutor);
        } else {
            return Result.error().message("Delete tutor failed");
        }
    }

}
