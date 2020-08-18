package com.transform.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageJumpController {
    @RequestMapping("index")
    public String index(){
        System.out.println("进入");
        return "index1";
    }
}
