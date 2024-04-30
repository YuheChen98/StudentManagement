package com.example.studentmanagement.controller.UserController;

import com.example.studentmanagement.entity.User.Lecturer;
import com.example.studentmanagement.mapper.UserMapper.LecturerMapper;
import com.example.studentmanagement.service.UserService.LecturerService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LecturerController {
    @Autowired
    private LecturerMapper lecturerMapper;
    @Autowired
    private LecturerService lecturerService;

    @Operation(summary = "创建lecturer")
    @PostMapping("/lecturer")
    public Result add(@RequestBody Lecturer lecturer){
        Lecturer newLecturer = lecturerService.createLecturer(lecturer);
        if (newLecturer != null){
            return Result.ok().data("lecturer",lecturer);
        } else {
            return Result.error().message("Create lecturer failed");
        }
    }
    @Operation(summary = "查询全部lecturer列表")
    @GetMapping("/lecturer")
    public List lecturer() {
        return lecturerMapper.selectAllLecturer();
    }

    @Operation(summary = "根据id或name查询lecturer信息列表")
    @GetMapping("/lecturer/search/{query}")
    public List<Lecturer> searchLecturer(@PathVariable String query){
        return lecturerService.searchLecturer(query);
    }

    @Operation(summary = "查询单个lecturer信息")
    @GetMapping("/lecturerInfo")
    public Lecturer getLecturerById(@RequestBody Lecturer lecturer) {
        return lecturerMapper.selectById(lecturer.getLecturerId());
    }
    @Operation(summary = "更新lecturer信息")
    @PutMapping("/lecturer")
    public Result updateLecturer(@RequestBody Lecturer lecturer){
        int i = lecturerMapper.updateById(lecturer);
        if ( i > 0){
            return Result.ok().data("lecturer",lecturer);
        } else {
            return Result.error().message("Update lecturer failed");
        }
    }
    @Operation(summary = "删除lecturer")
    @DeleteMapping("/lecturer")
    public Result deleteLecturer(@RequestBody Lecturer lecturer){
        int i = lecturerMapper.deleteById(lecturer);
        if ( i > 0){
            return Result.ok().data("lecturer",lecturer);
        } else {
            return Result.error().message("Delete lecturer failed");
        }
    }

}
