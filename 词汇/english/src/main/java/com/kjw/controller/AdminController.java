package com.kjw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kejiawei
 * @create 2018-11-28 12:52
 */
@Controller
public class AdminController
{
    @RequestMapping("/admin")
    public String toAdmin(){
        return "admin";
    }
}
