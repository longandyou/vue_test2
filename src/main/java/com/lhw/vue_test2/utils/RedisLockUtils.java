package com.lhw.vue_test2.utils;

import com.lhw.vue_test2.test.FuncReportScheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author lhw
 * @date 2023/3/13 9:24
 */
public class RedisLockUtils implements AutoCloseable{
    private static final Logger logger = LoggerFactory.getLogger(RedisLockUtils.class);
    private RedisTemplate redisTemplate;
    private String key;
    private String value;
    // 单位：秒
    private int expireTime;

    public RedisLockUtils(RedisTemplate redisTemplate, String key, int expireTime) {
        this.redisTemplate = redisTemplate;
        this.key = key;
        this.value = UUID.randomUUID().toString();
        this.expireTime = expireTime;
    }

    /**
     * 获取分布式锁
     * @return
     */
    public boolean getLock() {
        RedisCallback<Boolean> redisCallback = redisConnection -> {
            // 设置NX
            RedisStringCommands.SetOption setOption = RedisStringCommands.SetOption.ifAbsent();
            // 设置过期时间
            Expiration expiration = Expiration.seconds(expireTime);

            // 序列化key和value
            byte[] redisKey = redisTemplate.getKeySerializer().serialize(key);
            byte[] redisValue = redisTemplate.getValueSerializer().serialize(value);

            // 执行set nx 操作
            Boolean result = redisConnection.set(redisKey, redisValue, expiration, setOption);
            return result;
        };

        // 获取分布式锁
        Boolean lock = (Boolean) redisTemplate.execute(redisCallback);

        return lock;
    }

    /**
     * 释放锁
     * @return
     */
    public boolean unLock() {
        String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";
        RedisScript<Boolean> redisScript = RedisScript.of(script, Boolean.class);
        List<String> keys = Arrays.asList(key);

        Boolean result = (Boolean) redisTemplate.execute(redisScript, keys, value);
        logger.info("释放锁的结果：" + result);
        return result;
    }

    @Override
    public void close() throws Exception {
        // 释放锁
        unLock();
    }
}
