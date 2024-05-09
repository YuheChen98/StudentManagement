package com.example.studentmanagement.service.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.studentmanagement.entity.User.Lecturer;
import com.example.studentmanagement.mapper.UserMapper.LecturerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


/**
 * Service class for managing lecturers in the Student Management System.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@Service
public class LecturerService {

    private static final String LECTURER_INITIAL = "LC";

    /**
     * Creates a new lecturer with a unique lecturer ID. The uniqueness of the lecturer ID
     * is ensured by repeated checking against existing IDs in the database. If the lecturer
     * is successfully added to the database, it is returned; otherwise, null is returned.
     *
     * @param lecturer the lecturer entity to be added
     * @return the lecturer entity with a unique ID if creation is successful; otherwise, null
     */
    @Autowired
    private LecturerMapper lecturerMapper;
    public Lecturer createLecturer(Lecturer lecturer){
        String lecturerId = generateLecturerId();
        boolean isUnique = false;
        while (!isUnique) {
            isUnique = !(lecturerMapper.countLecturerById(lecturerId) > 0);
            if (!isUnique) {
                lecturerId = generateLecturerId();
            }
        }
        lecturer.setLecturerId(lecturerId);
        int i = lecturerMapper.insert(lecturer);
        if (i > 0) {
            return lecturer;
        } else {
            return null;
        }
    }

    /**
     * Generates a unique ID for a lecturer. The ID is prefixed by 'LC' and followed by a randomly
     * generated eight-digit number, formatted to ensure it is always eight digits long.
     *
     * @return a unique lecturer ID as a String
     */
    private String generateLecturerId() {
        Random random = new Random();
        int randomEightDigitNumber = random.nextInt(100000000);
        return LECTURER_INITIAL + String.format("%08d", randomEightDigitNumber);
    }


    /**
     * Searches for lecturers based on a given query string. The search can be conducted by lecturer ID,
     * first name, or last name. If the query contains a space, it assumes the format is 'First Last'
     * and will split the query to search by first and last name. If no space is present, it searches by ID,
     * first name, or last name.
     *
     * @param query the search query, which can be an ID or a name
     * @return a list of {@link Lecturer} that match the search criteria
     */
    public List<Lecturer> searchLecturer(String query) {
        QueryWrapper<Lecturer> queryWrapper = new QueryWrapper<>();
        if (query.contains(" ")) {
            String[] nameParts = query.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            queryWrapper.eq("first_name", firstName).eq("last_name", lastName);
        } else {
            queryWrapper.eq("lecturer_id", query).or().like("first_name", query).or().like("last_name", query);
        }
        return lecturerMapper.selectList(queryWrapper);
    }
}
