package com.zpt.demo.mapper;

import com.zpt.demo.model.ProductOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    List<ProductOrder> getAll();

    int insert(ProductOrder productOrder);
}
