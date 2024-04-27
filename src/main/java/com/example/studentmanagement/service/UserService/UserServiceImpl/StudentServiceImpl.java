package com.example.studentmanagement.service.UserService.UserServiceImpl;

import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.service.UserService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    public Student createStudent(Student student) {
        String studentId = generateStudentId();
        boolean isUnique = false;
        while (!isUnique) {
            isUnique = !(studentMapper.countStudentById(studentId) > 0);
            if (!isUnique) {
                studentId = generateStudentId();
            }
        }
        student.setStudentId(studentId);
        int i = studentMapper.insert(student);
        if (i > 0) {
            return student;
        } else {
            return null;
        }
    }


    private String generateStudentId() {
        Random random = new Random();
        int randomEight_DigitNumber = random.nextInt(100000000);
        return STUDENTINITIAL + randomEight_DigitNumber;
    }

}
