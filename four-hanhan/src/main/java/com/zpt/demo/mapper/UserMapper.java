package com.zpt.demo.mapper;

import com.zpt.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    User login(User user);

    int updateUser(User user);

    int insert(User user);

    List<User> getUsers();

    User findByUsername(String userName);
}
