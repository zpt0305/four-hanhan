package com.zpt.demo.service.impl;

import com.zpt.demo.service.CommonMQService;
import com.zpt.demo.service.InitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class InitServiceImpl implements InitService {

    public final static int THREAD_NUM = 5000;
    private static int phone = 0;

    @Autowired
    private CommonMQService commonMQService;

    @Override
    public void generateMultiThread() {
        log.info("开始初始化线程");

        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            for (int i = 0; i < THREAD_NUM; i++) {
                new Thread(new task(countDownLatch)).start();
            }
            countDownLatch.countDown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class task implements Runnable{
        private CountDownLatch countDownLatch;

        public task(){}

        public task(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                phone++;
                commonMQService.sendRobbingMsg(String.valueOf(phone));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}


