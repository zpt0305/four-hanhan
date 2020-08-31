package com.zpt.demo.service;

import com.zpt.demo.util.ResponseObjectResult;

public interface ProductService {
    ResponseObjectResult getAll();

    ResponseObjectResult getOne(Integer productNo);
}
