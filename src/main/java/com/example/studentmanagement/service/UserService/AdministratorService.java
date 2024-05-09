package com.example.studentmanagement.service.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.studentmanagement.entity.User.Administrator;
import com.example.studentmanagement.mapper.UserMapper.AdministratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Service class for handling administrator operations.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@Service
public class AdministratorService {
    private static final String ADMINISTRATOR_INITIAL = "AD";

    @Autowired
    private AdministratorMapper administratorMapper;

    /**
     * Creates a new administrator with a unique ID and persists it to the database.
     * @param administrator the administrator to be created
     * @return the created administrator, or null if the creation failed
     */
    public Administrator createAdministrator(Administrator administrator){
        String adminId = generateAdministratorId();
        boolean isUnique = false;
        while (!isUnique) {
            isUnique = !(administratorMapper.countAdministratorById(adminId) > 0);
            if (!isUnique) {
                // Generate a new ID if the first one is not unique
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

    /**
     * Generates a unique ID for an administrator.
     * @return a unique administrator ID
     */
    private String generateAdministratorId() {
        Random random = new Random();
        int randomEightDigitNumber = random.nextInt(100000000);
        return ADMINISTRATOR_INITIAL + String.format("%08d", randomEightDigitNumber);
    }


    /**
     * Searches for administrators based on a given query, which could be an ID or a name.
     * @param query the query string used to search for administrators
     * @return a list of administrators matching the query
     */
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
