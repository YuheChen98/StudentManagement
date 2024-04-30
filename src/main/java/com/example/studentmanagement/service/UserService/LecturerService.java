package com.example.studentmanagement.service.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.studentmanagement.entity.User.Lecturer;
import com.example.studentmanagement.entity.User.Lecturer;
import com.example.studentmanagement.mapper.UserMapper.LecturerMapper;
import com.example.studentmanagement.mapper.UserMapper.LecturerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class LecturerService {
    @Autowired
    private LecturerMapper lecturerMapper;
    final String LECTURERINITIAL = "LC";
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
    private String generateLecturerId() {
        Random random = new Random();
        int randomEight_DigitNumber = random.nextInt(100000000);
        return LECTURERINITIAL + randomEight_DigitNumber;
    }
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
