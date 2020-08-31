package com.zpt.demo.service.impl;

import com.zpt.demo.mapper.OrderMapper;
import com.zpt.demo.model.ProductOrder;
import com.zpt.demo.service.OrderService;
import com.zpt.demo.util.ResponseObjectResult;
import com.zpt.demo.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseObjectResult getAll() {
        List<ProductOrder> productList = orderMapper.getAll();
        Map<String, Object> map = new HashMap<>();
        map.put("list",productList);
        return new ResponseObjectResult(new ResponseStatus(200,"查询成功！",true),map);
    }

    public ResponseObjectResult insert(ProductOrder productOrder){
        int result = orderMapper.insert(productOrder);
        if (result > 0) {
            return new ResponseObjectResult(new ResponseStatus(200,"下单成功！",true));
        }else {
            return new ResponseObjectResult(new ResponseStatus(10001,"下单失败！",false));
        }

    }
}
