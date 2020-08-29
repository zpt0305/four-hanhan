package com.zpt.demo.mapper;

import com.zpt.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartzTestMapper {
    List<User> getUser();
}
