package com.zpt.demo.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface JsonTestMapper {
    List<HashMap<String,String>> jsontest();
}
