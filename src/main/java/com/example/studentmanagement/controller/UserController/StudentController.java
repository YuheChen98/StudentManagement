package com.example.studentmanagement.controller.UserController;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.service.UserService.StudentService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller class providing API endpoints for managing students within the student management system.
 */
@RestController
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;


    /**
     * Retrieves a list of all students.
     * @return a list of students
     */
    @Operation(summary = "List all students")
    @GetMapping("/admin/student")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> allStudent() {
        return studentMapper.selectAllStudent();
    }


    /**
     * Searches for students based on the provided query, which could be an ID or a name.
     * @param query the query string used for searching
     * @return a list of students matching the criteria
     */
    @Operation(summary = "Search for students by ID or name")
    @GetMapping("/admin/student/{query}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> searchStudent(@PathVariable String query){
        return studentService.searchStudent(query);
    }


    /**
     * Retrieves details of a single student based on the student ID provided in the request body.
     *
     * @param student the student object containing the ID for which details are required
     * @return the details of the requested student
     */
    @Operation(summary = "Get detailed information for one student")
    @GetMapping("/admin/studentInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public Student getStudentById(@RequestBody Student student) {
        return studentMapper.selectByStudentId(student.getStudentId());
    }


    /**
     * Creates a new student and adds them to the database.
     *
     * @param student the student to be created
     * @return a result object containing the newly created student or an error message
     */
    @Operation(summary = "Create a new student")
    @PostMapping("/admin/student")
    @PreAuthorize("hasRole('ADMIN')")
    public Result createStudent(@RequestBody Student student){
        Student newStudent = studentService.createStudent(student);
        if (newStudent != null){
            return Result.ok().data("student",student);
        } else {
            return Result.error().message("Create student failed");
        }
    }



    /**
     * Updates a student's information including potentially changing their program.
     *
     * @param student the student object with updated information
     * @return a result object indicating the success or failure of the update
     */
    @Operation(summary = "Update student details and program selection")
    @PutMapping("/admin/student")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateStudent(@RequestBody Student student){
        int i = studentMapper.updateStudentAndProgramme(student);
        if ( i > 0){
            return Result.ok().data("student",student);
        } else {
            return Result.error().message("Update student failed");
        }
    }



    /**
     * Assigns a tutor to a student and updates the database record accordingly.
     *
     * @param student the student to which the tutor is to be assigned
     * @return a result object indicating success or failure of the assignment
     */
    @Operation(summary = "Assign a tutor to a student")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/student/selectTutor")
    public Result selectTutor(@RequestBody Student student){
        return studentService.selectTutor(student);
    }



    /**
     * Deletes a student from the database.
     *
     * @param student the student to be deleted
     * @return a result object indicating the success or failure of the deletion
     */
    @Operation(summary = "Delete a student")
    @DeleteMapping("/admin/student")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteStudent(@RequestBody Student student){
    int i = studentMapper.deleteById(student);
        if ( i > 0){
            return Result.ok().data("student",student);
        } else {
            return Result.error().message("Delete student failed");
        }
    }
}
