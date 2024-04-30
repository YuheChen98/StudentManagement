package com.example.studentmanagement.service.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.studentmanagement.entity.User.Tutor;
import com.example.studentmanagement.mapper.UserMapper.TutorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TutorService {
    @Autowired
    private TutorMapper tutorMapper;
    final String TUTORINITIAL = "TU";
    public Tutor createTutor(Tutor tutor){
        String tutorId = generateTutorId();
        boolean isUnique = false;
        while (!isUnique) {
            isUnique = !(tutorMapper.countTutorById(tutorId) > 0);
            if (!isUnique) {
                tutorId = generateTutorId();
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
    private String generateTutorId() {
        Random random = new Random();
        int randomEight_DigitNumber = random.nextInt(100000000);
        return TUTORINITIAL + randomEight_DigitNumber;
    }

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
