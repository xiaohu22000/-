package com.example.mapper;


import com.example.entity.Account;
import com.example.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where username =#{username}")
    User selectByUserName(String username);


    void insert(User user);

    void deleteById(Integer id);

    User selectById(Integer id);

    void updateById(User user);

    List<User> selectAll(User user);

    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);
}
