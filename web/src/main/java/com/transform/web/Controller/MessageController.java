package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.service.IBaseInfoService;
import com.transform.web.util.AsyncUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 消息推送接口v1.0
 * 接口目前采取简单的redis列表实现，后期考虑采用专业的消息队列中间件实现
 * 考虑到消息如果人数比较多会很费时间，所以新建一个异步类，将任务委托给它处理
 */
@Api("消息队列控制器")
@RestController
@RequestMapping("/v1/rest")
public class MessageController {
    @Reference
    IBaseInfoService baseInfoService;
    @Autowired
    AsyncUtil asyncUtil;

    @GetMapping("/getMessage")
    public List<Object> getMessage(@RequestParam("key")String key) throws InterruptedException {
        return asyncUtil.readAllMomentByKey(key);
    }
}
