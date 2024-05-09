package com.example.studentmanagement.service.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.mapper.UserMapper.TutorMapper;
import com.example.studentmanagement.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;

/**
 * Service class for handling student-related business processes.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@Service
public class StudentService {
    private static final String STUDENT_INITIAL = "ST";

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TutorMapper tutorMapper;


    /**
     * Creates a new student with a unique student ID and adds it to the database.
     * @param student the student to be created
     * @return the newly created student if successful, null otherwise
     */
    public Student createStudent(Student student) {
        String studentId = generateStudentId();
        boolean isUnique = false;
        while (!isUnique) {
            isUnique = !(studentMapper.countStudentById(studentId) > 0);
            if (!isUnique) {
                studentId = generateStudentId(); // Generate a new ID if not unique
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


    /**
     * Generates a unique student ID.
     * @return a unique student ID based on a random eight-digit number prefixed by "ST"
     */
    private String generateStudentId() {
        Random random = new Random();
        int randomEightDigitNumber = random.nextInt(100000000);
        return STUDENT_INITIAL + String.format("%08d", randomEightDigitNumber);
    }


    /**
     * Searches for students based on a provided query, which can include names or a student ID.
     * @param query the query string used to search for students
     * @return a list of students matching the search criteria
     */
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



    /**
     * Assigns a tutor to a student if the tutor is not already overseeing the maximum number of students.
     * Also ensures the tutor and student are in the same programme.
     * @param student the student who needs a tutor assigned
     * @return a Result object indicating the success or failure of the tutor assignment
     */
    public Result selectTutor(Student student) {
        int i = tutorMapper.countTutorById(student.getTutor().getTutorId());
        if (student.getProgramme().getProgrammeId().equals(student.getTutor().getProgramme().getProgrammeId())) {
            if (i >= 15) {
                return Result.error().message("A tutor can only have at most 15 students");
            }
            i = studentMapper.updateTutor(student);
            if (i > 0) {
                return Result.ok().data("student", student);
            }
            return Result.error().message("Assigned failed");
        }
        return Result.error().message("Tutor and student must be in the same programme");
    }
}
