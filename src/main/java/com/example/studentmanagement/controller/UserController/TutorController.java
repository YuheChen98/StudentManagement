package com.example.studentmanagement.controller.UserController;


import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.entity.User.Tutor;
import com.example.studentmanagement.mapper.UserMapper.TutorMapper;
import com.example.studentmanagement.service.UserService.TutorService;
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
    public Tutor add(@RequestBody Tutor tutor){
        return tutorService.createTutor(tutor);
    }

    @Operation(summary = "查询全部tutor列表")
    @GetMapping("/tutor")
    public List tutor() {
        return tutorMapper.selectAllTutor();
    }

    @Operation(summary = "根据id查询tutor信息")
    @GetMapping("/tutor/{tutorId}")
    public Tutor getTutorById(@PathVariable String tutorId) {
        Tutor tutor = tutorMapper.selectById(tutorId);
        return tutor;
    }
    @Operation(summary = "更新tutor信息")
    @PutMapping("/tutor/{tutorId}")
    public Tutor updateTutor(@RequestBody Tutor tutor){
        int i = tutorMapper.updateById(tutor);
        if ( i > 0){
            return tutor;
        } else {
            return null;
        }
    }
    @Operation(summary = "删除tutor")
    @DeleteMapping("/tutpr/{tutorId}")
    public Tutor deleteTutor(@RequestBody Tutor tutor){
        int i = tutorMapper.deleteById(tutor);
        if ( i > 0){
            return tutor;
        } else {
            return null;
        }
    }

}
