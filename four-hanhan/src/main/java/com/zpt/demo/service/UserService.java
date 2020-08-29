package com.zpt.demo.service;

import com.zpt.demo.model.User;
import com.zpt.demo.util.ResponseObjectResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    //登录
    int login(User user);

    //更新用户
    int updateUser(User user);

    //新增用户
    ResponseObjectResult addUser(User user);

    //查询所有用户
    List<User> getUsers();

    //根据用户名查询用户
    User findByUsername(String userName);
}
