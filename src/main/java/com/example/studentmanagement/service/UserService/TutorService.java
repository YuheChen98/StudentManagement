package com.example.studentmanagement.service.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.studentmanagement.entity.User.Tutor;
import com.example.studentmanagement.mapper.UserMapper.TutorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Service class for handling tutor-related operations within the student management system.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@Service
public class TutorService {
    private static final String TUTOR_INITIAL = "PT";

    @Autowired
    private TutorMapper tutorMapper;

    /**
     * Creates and saves a new tutor with a unique tutor ID in the database.
     * The tutor ID is generated to ensure it does not already exist in the database.
     *
     * @param tutor the tutor object to be created and saved
     * @return the newly created tutor if successful, null if the creation failed
     */
    public Tutor createTutor(Tutor tutor){
        String tutorId = generateTutorId();
        boolean isUnique = false;
        while (!isUnique) {
            isUnique = !(tutorMapper.countTutorById(tutorId) > 0);
            if (!isUnique) {
                tutorId = generateTutorId(); // Regenerate the ID if it's not unique
            }
        }
        tutor.setTutorId(tutorId);
        int i = tutorMapper.insert(tutor);
        if (i > 0) {
            return tutor;
        } else {
            return null;
        }
    }


    /**
     * Generates a unique tutor ID using a random eight-digit number prefixed by a defined initial.
     *
     * @return a unique tutor ID
     */
    private String generateTutorId() {
        Random random = new Random();
        int randomEightDigitNumber = random.nextInt(100000000);
        return TUTOR_INITIAL + String.format("%08d", randomEightDigitNumber);
    }



    /**
     * Searches for tutors based on a given query. The query can be a tutor ID or a name.
     * If the query contains a space, it assumes the format "firstName lastName".
     *
     * @param query the query string used to search for tutors
     * @return a list of tutors that match the search criteria
     */
    public List<Tutor> searchTutor(String query) {
        QueryWrapper<Tutor> queryWrapper = new QueryWrapper<>();
        if (query.contains(" ")) {
            String[] nameParts = query.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            queryWrapper.eq("first_name", firstName).eq("last_name", lastName);
        } else {
            queryWrapper.eq("tutor_id", query).or().like("first_name", query).or().like("last_name", query);
        }
        return tutorMapper.selectList(queryWrapper);
    }
}
