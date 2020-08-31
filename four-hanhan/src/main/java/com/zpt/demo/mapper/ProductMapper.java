package com.zpt.demo.mapper;

import com.zpt.demo.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    List<Product> getAll();

    Product getOne(Integer productNo);

    int updateCount(Product product);
}
