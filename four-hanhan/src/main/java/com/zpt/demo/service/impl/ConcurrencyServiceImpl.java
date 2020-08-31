package com.zpt.demo.service.impl;

import com.zpt.demo.mapper.OrderMapper;
import com.zpt.demo.mapper.ProductMapper;
import com.zpt.demo.model.Product;
import com.zpt.demo.model.ProductOrder;
import com.zpt.demo.service.ConcurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class ConcurrencyServiceImpl implements ConcurrencyService {
    
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;
    
    @Override
    public synchronized void manageRobbing(String phone) {
        Integer productNo = 1;
        try {
            Product product  = productMapper.getOne(productNo);
            if (product != null && product.getCount() > 0) {
                int result = productMapper.updateCount(product);
                if (result > 0) {
                    ProductOrder order = new ProductOrder();
                    order.setOrderTime(new Date());
                    order.setProductNo(productNo);
                    order.setConsumerPhone(phone);
                    order.setCount(1);
                    order.setAmount(new BigDecimal(3));
                    order.setPayStatus("1");
                    orderMapper.insert(order);
                }
            }
        }catch (Exception e){
            log.error("处理抢单发生异常：phone = {},异常信息：{}",phone,e);
        }
        
    }
}
