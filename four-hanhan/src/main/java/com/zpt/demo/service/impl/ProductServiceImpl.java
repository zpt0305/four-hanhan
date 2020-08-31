package com.zpt.demo.service.impl;

import com.zpt.demo.mapper.ProductMapper;
import com.zpt.demo.model.Product;
import com.zpt.demo.service.ProductService;
import com.zpt.demo.util.ResponseObjectResult;
import com.zpt.demo.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseObjectResult getAll() {
        List<Product> productList = productMapper.getAll();
        Map<String, Object> map = new HashMap<>();
        map.put("list",productList);
        return new ResponseObjectResult(new ResponseStatus(200,"查询成功！",true),map);
    }

    @Override
    public ResponseObjectResult getOne(Integer productNo) {
        Product product = productMapper.getOne(productNo);
        Map<String, Object> map = new HashMap<>();
        map.put("product",product);
        return new ResponseObjectResult(new ResponseStatus(200,"查询成功！",true),map);
    }
}
