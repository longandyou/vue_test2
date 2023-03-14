package com.lhw.vue_test2.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lhw
 * @date 2023/3/10 14:28
 */
@Component
public class FuncReportScheduled {
    private static final Logger logger = LoggerFactory.getLogger(FuncReportScheduled.class);

//    @Scheduled(cron = "*/5 * * * * ?")
    public void funcReportInit() {
        logger.info(Thread.currentThread().getName()+"hello,我是定时器，5秒后再见！");
//        System.out.println("hello,我是定时器，5秒后再见！");
    }
}
