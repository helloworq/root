package com.transform.web.util;

import com.transform.api.model.dto.custom.Message;
import org.hibernate.validator.constraints.EAN;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AsyncUtil {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 发一条动态，需要推送给friends
     */
    @Async
    public void pushMoment(String[] friends, Message message) throws InterruptedException {

        for (String name : friends) {
            redisTemplate.opsForList().leftPush(name, message);
        }
    }

    /**
     * 读取指定key的全部值
     */
    public List<Object> readAllMomentByKey(String key) throws InterruptedException {
        return redisTemplate.opsForList().range(key,0,-1);
    }
}