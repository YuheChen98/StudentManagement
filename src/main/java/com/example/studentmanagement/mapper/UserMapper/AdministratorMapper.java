package com.example.studentmanagement.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.User.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * Mapper interface for the Administrator entity.
 * Provides SQL operations through MyBatis for Administrator data manipulation.
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@Mapper
public interface AdministratorMapper extends BaseMapper<Administrator> {

    /**
     * Counts the number of administrators with a specified admin ID.
     *
     * @param adminId the administrator ID to check for uniqueness
     * @return the count of administrators with the specified ID
     */
    @Select("select count(*) from administrator where admin_id = #{adminId}")
    int countAdministratorById(String adminId);

}
