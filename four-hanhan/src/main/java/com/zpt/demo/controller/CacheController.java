package com.zpt.demo.controller;

import com.zpt.demo.model.User;
import com.zpt.demo.service.CacheService;
import com.zpt.demo.util.ResponseObjectResult;
import com.zpt.demo.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping("login")
    public ResponseObjectResult login(@RequestBody User user){
        try {
            return cacheService.login(user);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseObjectResult(new ResponseStatus(10001,"操作失败",false));
        }
    }
}
