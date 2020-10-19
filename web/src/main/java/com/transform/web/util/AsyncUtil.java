package com.transform.web.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.dto.custom.Message;
import com.transform.api.model.entiy.UserInfo;
import com.transform.api.service.IBaseInfoService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class AsyncUtil {
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Reference
    IBaseInfoService baseInfoService;
    /**
     * 发一条动态，需要推送给friends
     */
    @Async
    public void pushMoment(String[] friendsIds, Message message) throws InterruptedException {
        for (String id : friendsIds) {
            redisTemplate.opsForList().leftPush(id, message);
            System.out.println("正在推送");
        }
        System.out.println("完成推送");
    }

    /**
     * 根据传入数据弹出信息
     * @throws InterruptedException
     */
    public void popMoment(String key,int size) throws InterruptedException {
        for (int i = 0; i < size; i++) {
           redisTemplate.opsForList().rightPop(key);
        }
    }

    /**
     * 读取指定key的全部消息
     */
    public List<Object> readAllMomentByKey(String key,String userName) throws InterruptedException {
        return redisTemplate.opsForList().range(key,0,-1);
    }

    /**
     * 使用readAllMomentByKey之后，对队列数据读取之后应该调用此方法使得数据库内存储此次队列的尺寸
     */
    public void saveCurrentQueneSize(String key,String userName) throws InterruptedException {
        UserInfo userInfo=baseInfoService.getUserInfo(userName);
        userInfo.setRedisMsgSize(String.valueOf(redisTemplate.opsForList().range(key,0,-1).size()));
        baseInfoService.uploadUserInfo(userInfo);
    }
}