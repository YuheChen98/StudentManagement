package com.example.studentmanagement.controller.UserController;

import com.example.studentmanagement.entity.User.Lecturer;
import com.example.studentmanagement.mapper.UserMapper.LecturerMapper;
import com.example.studentmanagement.service.UserService.LecturerService;
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
    public Lecturer add(@RequestBody Lecturer lecturer){
        return lecturerService.createLecturer(lecturer);
    }

    @Operation(summary = "查询全部lecturer列表")
    @GetMapping("/lecturer")
    public List lecturer() {
        return lecturerMapper.selectAllLecturer();
    }

    @Operation(summary = "根据id查询lecturer信息")
    @GetMapping("/lecturer/{lecturerId}")
    public Lecturer getLecturerById(@PathVariable String lecturerId) {
        Lecturer lecturer = lecturerMapper.selectById(lecturerId);
        return lecturer;
    }
    @Operation(summary = "更新lecturer信息")
    @PutMapping("/lecturer/{lecturerId}")
    public Lecturer updateLecturer(@RequestBody Lecturer lecturer){
        int i = lecturerMapper.updateById(lecturer);
        if ( i > 0){
            return lecturer;
        } else {
            return null;
        }
    }
    @Operation(summary = "删除lecturer")
    @DeleteMapping("/tutpr/{lecturerId}")
    public Lecturer deleteLecturer(@RequestBody Lecturer lecturer){
        int i = lecturerMapper.deleteById(lecturer);
        if ( i > 0){
            return lecturer;
        } else {
            return null;
        }
    }

}
