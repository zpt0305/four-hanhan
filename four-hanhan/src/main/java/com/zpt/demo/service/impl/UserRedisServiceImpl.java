package com.zpt.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpt.demo.mapper.UserRedisMapper;
import com.zpt.demo.model.User;
import com.zpt.demo.service.UserRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * User ServiceImpl
 *
 * @author Leon
 * @version 2018/6/14 17:12
 */
@Service
public class UserRedisServiceImpl implements UserRedisService {

    @Autowired
    UserRedisMapper userRedisMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 新增
     *
     * @param user
     * @return
     */
    @Transactional
    public int save(User user) {
        userRedisMapper.insert(user);
        return user.getId();
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public PageInfo<User> findAllUserList(Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userRedisMapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        // 具体使用
        int i = 0;
        for (User user1:list) {
            redisTemplate.opsForList().leftPush("user: "+ i,JSON.toJSONString(user1));
            /*redisTemplate.opsForList().leftPop(i);*/
            i++;
        }

        //redisTemplate.opsForList().leftPush("user:list", JSON.toJSONString(list));
        stringRedisTemplate.opsForValue().set("user:name", "张三");
        stringRedisTemplate.opsForValue().set("user:rubbish","小鹏子");
        return pageInfo;
    }

    @Override
    public List<String> getList(){
        List<User> list = userRedisMapper.selectAll();

        redisTemplate.opsForList().leftPush("zpt",list);
        Object str = redisTemplate.opsForList().leftPop("zpt");
       /* List<String> list2 = JSON.parseArray(str.toString(),String.class);
        for (String str12:list2
             ) {
            System.out.println(str12);
        }*/
       System.out.println(str);
        return null;
    }

    @Override
    public void testMethod(){
        /*redisTemplate.opsForValue().set("rubbish","小鹏子");
        redisTemplate.opsForValue().set("a","小鹏子a");
        redisTemplate.opsForValue().set("b","小鹏子b");
        redisTemplate.opsForValue().set("c","小鹏子c");
        redisTemplate.opsForValue().set("d","小鹏子d");*/
        //System.out.println(redisTemplate.hasKey("rubbish"));
        //System.out.println(redisTemplate.opsForValue().get("rubbish"));
        //System.out.println(redisTemplate.delete("rubbish"));
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        redisTemplate.opsForList().rightPushAll("list",list);
        System.out.println(redisTemplate.opsForList().index("list",1));
        System.out.println(redisTemplate.opsForList().leftPush("list","a","e"));
        System.out.println(redisTemplate.opsForList().leftPush("list",4));
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
        long size = redisTemplate.opsForList().size("list");
        System.out.println(size);
        for(int i = 0; i< size; i++){
            System.out.println(redisTemplate.opsForList().leftPop("list"));
        }
    }
}
