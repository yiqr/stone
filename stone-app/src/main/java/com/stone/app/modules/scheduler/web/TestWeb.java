package com.stone.app.modules.scheduler.web;

import com.stone.app.modules.scheduler.job.TestJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author rose
 * @date 2022/11/8 18:53
 */
@RestController
public class TestWeb {
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @GetMapping("/test")
    public String test() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        // 任务名称
        String name = UUID.randomUUID().toString();
        // 任务所属分组
        String group = TestJob.class.getName();

        JobDetail jobDetail = JobBuilder
                .newJob(TestJob.class)
                .withIdentity(name, group)
                .build();

        // 设置任务传递商品编号参数
        jobDetail.getJobDataMap().put("test", UUID.randomUUID().toString());
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
        // 将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
        return "ok";
    }
}
