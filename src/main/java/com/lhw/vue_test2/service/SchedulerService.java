package com.lhw.vue_test2.service;

import com.lhw.vue_test2.TestMapper;
import com.lhw.vue_test2.entity.User;
import com.lhw.vue_test2.utils.RedisLockUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author lhw
 * @date 2023/3/13 9:33
 */
@Service
public class SchedulerService {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TestMapper testMapper;


    @Scheduled(cron = "0/5 * * * * ?")
    public void sendSms() {
        try(RedisLockUtils redisLock = new RedisLockUtils(redisTemplate, "autoSms", 30)) {
            boolean lock = redisLock.getLock();
            if (lock) {
                logger.info("检测数据是否发生改变...");
                User user = testMapper.selectById(4);
                logger.info(String.valueOf(user));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
