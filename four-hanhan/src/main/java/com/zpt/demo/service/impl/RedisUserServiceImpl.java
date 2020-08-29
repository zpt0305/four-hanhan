package com.zpt.demo.service.impl;

import com.zpt.demo.mapper.RedisUserMapper;
import com.zpt.demo.model.RedisUser;
import com.zpt.demo.service.RedisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RedisUserServiceImpl implements RedisUserService {

    @Autowired
    private RedisUserMapper redisUserMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RedisUser> findAll() {
        return redisUserMapper.findAll();
    }

    @Override
    public void update(RedisUser redisUser) {
        redisUserMapper.update(redisUser);
    }
}
