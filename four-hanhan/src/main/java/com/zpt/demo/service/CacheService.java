package com.zpt.demo.service;

import com.zpt.demo.model.User;
import com.zpt.demo.util.ResponseObjectResult;

public interface CacheService {

    ResponseObjectResult login(User user);
}
