package com.zpt.demo.mapper;

import com.zpt.demo.model.LolHero;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PachongMapper {
    void insert(List<LolHero> list);
}
