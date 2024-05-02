package com.example.studentmanagement.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.User.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdministratorMapper extends BaseMapper<Administrator> {
    @Select("select count(*) from administrator where admin_id = #{adminId}")
    int countAdministratorById(String adminId);

}
