package com.lhw.vue_test2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhw.vue_test2.TestMapper;
import com.lhw.vue_test2.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lhw
 * @date 2023/3/9 16:31
 */
@Slf4j
@RestController
public class Test2Controller {
    @Autowired
    private TestMapper testMapper;

    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public String hello1() {
        User user = testMapper.selectById(4);
        if (user.getUsername().equals("zhangsan")){
            return "没有发生变化";
        }
        log.info(String.valueOf(user));
        return "username发生变化";
    }

    @RequestMapping(value = "/updateName", method = RequestMethod.GET)
    public String updateName() {
        User user = testMapper.selectById(4);
        user.setUsername("zhangsi");
        testMapper.updateById(user);
        log.info(String.valueOf(user));
        return "username发生变化";
    }
}
