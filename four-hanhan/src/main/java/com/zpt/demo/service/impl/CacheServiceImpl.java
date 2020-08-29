package com.zpt.demo.service.impl;

import com.zpt.demo.mapper.CacheMapper;
import com.zpt.demo.model.User;
import com.zpt.demo.service.CacheService;
import com.zpt.demo.util.ResponseObjectResult;
import com.zpt.demo.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private CacheMapper cacheMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public ResponseObjectResult login(User user) {

        if (user == null || "".equals(user)){
            return new ResponseObjectResult(new ResponseStatus(10001));
        }
        String userName = user.getUserName();
        String password = user.getPassword();

        Map<String,Object> querymap = new HashMap<>();
        querymap.put("userName",userName);
        querymap.put("password",password);

        Object o = redisTemplate.opsForValue().get(userName + password);
        if (o!=null){
            System.out.println("走缓存！");
            return new ResponseObjectResult(new ResponseStatus(200,"登陆成功",true));
        }else {
            User user1 = cacheMapper.login(querymap);
            if (user1 != null){
                System.out.println("走数据库！");
                redisTemplate.opsForValue().set(user1.getUserName() + user1.getPassword(),user1,1, TimeUnit.MINUTES);
                return new ResponseObjectResult(new ResponseStatus(200,"登陆成功",true));
            }else {
                return new ResponseObjectResult(new ResponseStatus(10001));
            }
        }
    }
}
