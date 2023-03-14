package com.lhw.vue_test2.controller;

import com.lhw.vue_test2.utils.RedisLockUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhw
 * @date 2023/3/13 9:31
 */
@RestController

public class RedisLockController {
    private static final Logger logger = LoggerFactory.getLogger(RedisLockController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/redisLock")
    public String redisLock() throws InterruptedException {
        logger.info("我进入了方法");

        // 获取锁
        try(RedisLockUtils redisLock = new RedisLockUtils(redisTemplate, "redisKey", 30)) {

            boolean lock = redisLock.getLock();

            if (lock) {
                logger.info("我进入了锁！");
                Thread.sleep(15000);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        logger.info("方法执行完成");
        return "方法执行完成";
    }
}
