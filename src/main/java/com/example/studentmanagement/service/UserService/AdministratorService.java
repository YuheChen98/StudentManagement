package com.example.studentmanagement.service.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.studentmanagement.entity.User.Administrator;
import com.example.studentmanagement.entity.User.Administrator;
import com.example.studentmanagement.mapper.UserMapper.AdministratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;
    final String ADMINISTRATORINITIAL = "AD";
    public Administrator createAdministrator(Administrator administrator){
        String adminId = generateAdministratorId();
        boolean isUnique = false;
        while (!isUnique) {
            isUnique = !(administratorMapper.countAdministratorById(adminId) > 0);
            if (!isUnique) {
                adminId = generateAdministratorId();
            }
        }
        administrator.setAdminId(adminId);
        int i = administratorMapper.insert(administrator);
        if (i > 0) {
            return administrator;
        } else {
            return null;
        }
    }
    private String generateAdministratorId() {
        Random random = new Random();
        int randomEight_DigitNumber = random.nextInt(100000000);
        return ADMINISTRATORINITIAL + randomEight_DigitNumber;
    }
    public List<Administrator> searchAdministrator(String query) {
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        if (query.contains(" ")) {
            String[] nameParts = query.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            queryWrapper.eq("first_name", firstName).eq("last_name", lastName);
        } else {
            queryWrapper.eq("admin_id", query).or().like("first_name", query).or().like("last_name", query);
        }
        return administratorMapper.selectList(queryWrapper);
    }
}
