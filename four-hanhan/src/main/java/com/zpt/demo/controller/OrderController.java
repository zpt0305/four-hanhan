package com.zpt.demo.controller;

import com.zpt.demo.service.InitService;
import com.zpt.demo.service.OrderService;
import com.zpt.demo.service.ProductService;
import com.zpt.demo.util.ResponseObjectResult;
import com.zpt.demo.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private InitService initService;

    @RequestMapping("/order/getAll")
    private ResponseObjectResult getAll(){
        try {
            return orderService.getAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseObjectResult(new ResponseStatus(10001,"操作失败！",false));
        }
    }

    @RequestMapping("/order/robbing")
    private ResponseObjectResult robbing(){
        try {
            initService.generateMultiThread();
            return new ResponseObjectResult(new ResponseStatus(200,"操作成功！",true));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseObjectResult(new ResponseStatus(10001,"操作失败！",false));
        }
    }
}
