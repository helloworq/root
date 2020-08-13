package com.transform.web.Controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "feignService")    //指定在注册中心注册的服务名为自己提供服务
public interface DepartService {

    @GetMapping("getUserName")          //必须和提供者的value一致
    String getUserName();

    @GetMapping("/getInfo")
    String getInfo(@RequestParam(value = "ss") String Msg);
}
