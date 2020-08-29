package com.zpt.demo.others.strategytest;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VipLevelServiceListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        /*Map<String, Object> beans = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(LevelHandler.class);
        VipLevelServiceContext vipLevelServiceContext = contextRefreshedEvent.getApplicationContextz().getBean(VipLevelServiceContext.class);
        beans.forEach((name,bean)->{
            LevelHandler levelHandler = bean.getClass().getAnnotation(LevelHandler.class);
            vipLevelServiceContext.setVipLevelService(levelHandler.value().getCode(),(VipLevelService)bean);
        });*/
        Map<String, Object> beans = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(LevelHandler.class);
        VipLevelServiceContext vipLevelServiceContext = new VipLevelServiceContext();
        for (Object value : beans.values()) {
            LevelHandler levelHandler = value.getClass().getAnnotation(LevelHandler.class);
            vipLevelServiceContext.setVipLevelService(levelHandler.value().getCode(),(VipLevelService)value);
        }
    }
}
