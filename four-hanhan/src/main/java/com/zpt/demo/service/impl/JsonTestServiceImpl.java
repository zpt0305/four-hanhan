package com.zpt.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.zpt.demo.mapper.JsonTestMapper;
import com.zpt.demo.service.JsonTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JsonTestServiceImpl implements JsonTestService {

    @Autowired
    JsonTestMapper jsonTestMapper;

    public void jsontest(){
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(6);
        list.add(11);
       Map<String,Object> jsonMap = new HashMap<>();
       jsonMap.put("list",list);
       jsonMap.put("str","测试下json");
       jsonMap.put("integer",1);

       String str= JSON.toJSONString(list);

       List<Object> list1 = JSON.parseArray(str);

       System.out.println(list1);
    }

}
