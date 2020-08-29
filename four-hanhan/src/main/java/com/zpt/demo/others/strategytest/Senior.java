package com.zpt.demo.others.strategytest;

import org.springframework.stereotype.Service;

@Service(value = "Senior")
@LevelHandler(value = VIP_LEVEL.SENIOR)
public class Senior implements VipLevelService {
    @Override
    public String getDiscount(int level) {
        return "7折";
    }
}
