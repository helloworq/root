package com.transform.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    /**
     * GetMapping example with @RequestParam
     * @return userName
     */
    @GetMapping("/getUserName")
    public String getUserName(){
        return "dasdsa";
    }

    @GetMapping("/getInfo")
    public String getInfo(@RequestParam(value = "ss") String Msg) {
        return Msg;
    }
}