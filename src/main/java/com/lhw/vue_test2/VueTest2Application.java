package com.lhw.vue_test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VueTest2Application {

    public static void main(String[] args) {
        SpringApplication.run(VueTest2Application.class, args);
        System.out.println("项目启动成功..........");
    }

}
