package com.zpt.demo.service;

import com.github.pagehelper.PageInfo;
import com.zpt.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRedisService {
    PageInfo<User> findAllUserList(Integer pageNum, Integer pageSize);

    List<String> getList();

    void testMethod();
}
