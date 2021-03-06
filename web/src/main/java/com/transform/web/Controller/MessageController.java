package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.entiy.UserInfo;
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
@Api(description = "redis消息队列控制器")
@RestController
@RequestMapping("/v1/rest")
public class MessageController {
    @Reference
    IBaseInfoService baseInfoService;
    @Autowired
    AsyncUtil asyncUtil;

    /**
     * 获取未读消息数量，此方法只提供未读消息数量
     * 故调用此方法不会修改数据库内存放的上一次消息总数量
     * @param key
     * @param userName
     * @return
     * @throws InterruptedException
     */
    @ApiOperation(value = "获取未读消息数量")
    @GetMapping("/getUnReadMessageSize")
    public int getUnReadMessageSize(@RequestParam("key") String key,
                                    @RequestParam("userName") String userName) throws InterruptedException {
        UserInfo userInfo=baseInfoService.getUserInfo(userName);
        int prevSize=Integer.parseInt(userInfo.getRedisMsgSize());
        return asyncUtil.readAllMomentByKey(key, userName).size()-prevSize;
    }

    /**
     * 获取全部消息，大于500条消息就弹出一半，减小内存压力
     * 此方法会获取消息，因此会修改数据库内存放的上一次消息总数量
     * @param key
     * @param userName
     * @return
     * @throws InterruptedException
     */
    @ApiOperation(value = "获取全部消息")
    @GetMapping("/getMessage")
    public List<Object> getMessageByKey(@RequestParam("key") String key,
                                        @RequestParam("userName") String userName) throws InterruptedException {
        int size=Integer.parseInt(baseInfoService.getUserInfo(userName).getRedisMsgSize());
        if (size>500){
            asyncUtil.popMoment(key,size-250);
        }
        asyncUtil.saveCurrentQueneSize(key,userName);
        return asyncUtil.readAllMomentByKey(key, userName);
    }

    /**
     * 获取redis内数据数量，单独写一个方法是为了在获取redis内数据之前先获取数量，
     * 再和获取的数据数量比对判断是否有消息丢失，如果尺寸不一致则重新读取
     * @param key
     * @param userName
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/getMessageSize")
    public int getMessageSize(String key,String userName) throws InterruptedException {
        return asyncUtil.readAllMomentByKey(key, userName).size();
    }
}
