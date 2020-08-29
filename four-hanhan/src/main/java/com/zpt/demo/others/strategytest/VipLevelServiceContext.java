package com.zpt.demo.others.strategytest;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class VipLevelServiceContext {
    private final Map<Integer,VipLevelService> map = new HashMap<>();

    public VipLevelService getVipLevelService(Integer level) {
        return map.get(level);
    }

    public void setVipLevelService(Integer level,VipLevelService vipLevelService) {
        map.put(level, vipLevelService);
    }
}
