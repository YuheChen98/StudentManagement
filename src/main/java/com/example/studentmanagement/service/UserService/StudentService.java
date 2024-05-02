package com.example.studentmanagement.service.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.mapper.UserMapper.TutorMapper;
import com.example.studentmanagement.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;
@Service
public class StudentService {
    final String STUDENTINITIAL = "ST";
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
        int i = studentMapper.add(student);
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

    public List<Student> searchStudent(String query) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (query.contains(" ")) {
            String[] nameParts = query.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            queryWrapper.eq("first_name", firstName).eq("last_name", lastName);
        } else {
            queryWrapper.eq("student_id", query).or().like("first_name", query).or().like("last_name",query);
        }
        return studentMapper.selectList(queryWrapper);
    }


    @Autowired
    private TutorMapper tutorMapper;
    public Result selectTutor(Student student) {
        int i = tutorMapper.countTutorById(student.getTutor().getTutorId());
        if(student.getProgramme().getProgrammeId().equals(student.getTutor().getProgramme().getProgrammeId())){
            if (i >= 15) {
                return Result.error().message("A tutor can only have at most 15 students");
            }
            i = studentMapper.updateTutor(student);
            if (i > 0) {
                return Result.ok().data("student",student);
            }
        }
        return Result.error().message("Assigned failed");    }

}
