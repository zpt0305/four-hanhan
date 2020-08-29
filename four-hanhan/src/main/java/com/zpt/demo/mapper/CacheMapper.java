package com.zpt.demo.mapper;

import com.zpt.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface CacheMapper {
    User login(Map<String, Object> querymap);
}
