package com.zpt.demo.mapper;

import com.zpt.demo.model.RedisUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RedisUserMapper {

    List<RedisUser> findAll();

    void update(RedisUser redisUser);
}
