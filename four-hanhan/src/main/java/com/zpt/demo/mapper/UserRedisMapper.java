package com.zpt.demo.mapper;

import com.zpt.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRedisMapper {

    void insert(User user);

    List<User> selectAll();
}
