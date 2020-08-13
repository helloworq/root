package com.transform.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartController {
    @Autowired
    private DepartService departService;

    @GetMapping("getUserName111")
    public String getUserName111(){
        return departService.getUserName();
    }
    @GetMapping("getMsg111")
    public String getMsg111(){
        return departService.getInfo("hello param");
    }

}