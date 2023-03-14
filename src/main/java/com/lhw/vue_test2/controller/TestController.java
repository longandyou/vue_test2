package com.lhw.vue_test2.controller;

import com.lhw.vue_test2.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhw
 * @date 2023/3/9 16:28
 */
@RestController
public class TestController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String hello1(@RequestBody User user) {
//        if (user.getAccount().equals("admin")&&user.getPassword().equals("123456")) {
//            String msg = "登录成功";
//            System.out.println(msg);
//            return msg;
//        }
        return "登录失败";
    }
}
