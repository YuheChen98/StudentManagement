package com.example.studentmanagement.service.UserService;

import com.example.studentmanagement.entity.User.Student;

public interface StudentService {
    final String STUDENTINITIAL = "SD";
    Student createStudent(Student student);
}
