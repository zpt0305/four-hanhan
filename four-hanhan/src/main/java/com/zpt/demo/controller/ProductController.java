package com.zpt.demo.controller;

import com.zpt.demo.service.ProductService;
import com.zpt.demo.util.ResponseObjectResult;
import com.zpt.demo.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/product/getAll")
    private ResponseObjectResult getAll(){
        try {
            return productService.getAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseObjectResult(new ResponseStatus(10001,"操作失败！",false));
        }
    }

    @RequestMapping("/product/getOne")
    private ResponseObjectResult getOne(Integer productNo){
        try {
            return productService.getOne(productNo);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseObjectResult(new ResponseStatus(10001,"操作失败！",false));
        }
    }
}
