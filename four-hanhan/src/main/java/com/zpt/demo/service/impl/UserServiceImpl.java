package com.zpt.demo.service.impl;

import com.zpt.demo.mapper.UserMapper;
import com.zpt.demo.model.User;
import com.zpt.demo.service.UserService;
import com.zpt.demo.util.ResponseObjectResult;
import com.zpt.demo.util.ResponseStatus;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int login(User user){
        User user1 = userMapper.login(user);
        if (user1 == null){
            return 0;
        }
        return 1;
    }

    @Override
    public ResponseObjectResult addUser(User user){
        if (user == null){
            return new ResponseObjectResult(new ResponseStatus(10001,"请输入用户信息",false));
        }
        String username = user.getUserName();
        String password = user.getPassword();
        user.setPassword(MD5Pwd(username,password));
        try {
            userMapper.insert(user);
            return new ResponseObjectResult(new ResponseStatus(200,"新增用户成功！",true));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseObjectResult(new ResponseStatus(10001,"插入失败",false));
        }
    }

    @Override
    public List<User> getUsers(){
        List<User> users = userMapper.getUsers();
        return users;
        /*Map<String,Object> result = new HashMap<>();
        result.put("users",users);
        return new ResponseObjectResult(new ResponseStatus(10001,"操作成功",true),result);*/
    }

    @Override
    public int updateUser(User user){
        int index = 0;
        try {
            index = userMapper.updateUser(user);
        }catch (Exception e){
            return 0;
        }

        return index;
    }

    @Override
    public User findByUsername(String userName){
        if (userName == null || userName == ""){
            return null;
        }
        return userMapper.findByUsername(userName);
    }

    public String MD5Pwd(String username, String pwd) {
        // 加密算法MD5,salt盐 username + salt,迭代次数
        String md5Pwd = new SimpleHash("MD5", pwd,
                ByteSource.Util.bytes(username + "salt"), 2).toHex();
        return md5Pwd;
    }
}
