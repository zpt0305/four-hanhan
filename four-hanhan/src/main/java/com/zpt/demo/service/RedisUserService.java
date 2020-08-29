package com.zpt.demo.service;

import com.zpt.demo.model.RedisUser;

import java.util.List;

public interface RedisUserService {
    List<RedisUser> findAll();

    void update(RedisUser user);
}
