package com.zpt.demo.controller;

import com.github.pagehelper.PageInfo;
import com.zpt.demo.model.RedisUser;
import com.zpt.demo.model.User;

import com.zpt.demo.service.RedisUserService;
import com.zpt.demo.service.UserRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/redis")
public class UserRedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUserService redisUserService;

    @RequestMapping("/get")
    public void get(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //redisTemplate.opsForValue().set("name","zpt");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @RequestMapping("/set")
    public void set(){
        RedisUser user = new RedisUser();
        user.setAge(14);
        ca(user);
        System.out.println(user);
    }

    public void ca(RedisUser user){
        user.setId(4);
        user.setUserName("zpt");
        user.setPassword("123456");
    }

    @RequestMapping("/getAll")
    public void getAll(){

        redisUserService.findAll().forEach(u-> System.out.println("u = " + u));
        System.out.println("==================================");
        redisUserService.findAll().forEach(u-> System.out.println("u = " + u));
        List<RedisUser> users = redisUserService.findAll();

        //filter()定义方法，toList()输出为list
        List<RedisUser> list =users.stream().filter(u -> 1 == u.getId()).collect(Collectors.toList());
        //由于id不重复，size只能是0或1
        RedisUser user1 = null;
        if (!CollectionUtils.isEmpty(list)){
            user1 = list.get(0);
        }

    }

    @RequestMapping("/update")
    public void update(){
        System.out.println("==================================");
        RedisUser user = new RedisUser();
        user.setId(1);
        user.setUserName("zp");
        user.setPassword("123456");
        user.setAge(24);
        redisUserService.update(user);
    }

}
