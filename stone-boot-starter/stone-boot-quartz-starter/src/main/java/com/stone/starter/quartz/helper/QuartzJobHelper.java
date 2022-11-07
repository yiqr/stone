package com.stone.starter.quartz.helper;

import com.stone.commons.core.SpringContextHelper;
import com.stone.starter.quartz.config.QuartzConfiguration;
import org.apache.commons.collections4.MapUtils;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author rose
 * @date 2022/11/7 18:28
 */
@Component
@AutoConfigureAfter({QuartzConfiguration.class})
public class QuartzJobHelper {
    /**
     * 添加任务
     *
     * @param name           任务名
     * @param group          任务组
     * @param clazz          任务class
     * @param cronExpression 克隆表达式
     * @param jobDataMap     任务附加参数
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public static void addJob(String name, String group, Class<? extends Job> clazz, String cronExpression, String desc, Map<String, ?> jobDataMap) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(clazz).withIdentity(name, group).withDescription(desc).storeDurably().build();
        if (MapUtils.isNotEmpty(jobDataMap)) {
            job.getJobDataMap().putAll(jobDataMap);
        }
        Trigger trg = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        getScheduler().scheduleJob(job, trg);
    }

    /**
     * 更新jobDataMap
     */
    public static void updateJobDataMap(String name, String group, Map<String, ?> jobDataMap) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        JobDetail jobDetail = getScheduler().getJobDetail(jobKey);
        if (MapUtils.isNotEmpty(jobDataMap)) {
            jobDetail.getJobDataMap().putAll(jobDataMap);
        }
        getScheduler().addJob(jobDetail, true);
    }

    /**
     * 删除任务
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public static void removeJob(String name, String group) throws SchedulerException {
        TriggerKey tk = TriggerKey.triggerKey(name, group);
        getScheduler().pauseTrigger(tk);
        getScheduler().unscheduleJob(tk);
        JobKey jobKey = JobKey.jobKey(name, group);
        getScheduler().deleteJob(jobKey);
    }

    /**
     * 暂停任务
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public static void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        getScheduler().pauseJob(jobKey);
    }

    /**
     * 恢复任务
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public static void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        getScheduler().resumeJob(jobKey);
    }

    /**
     * 修改任务触发时间
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public static void modifyTime(String name, String group, String cronExpression) throws SchedulerException {
        // 通过SchedulerFactory构建Scheduler对象
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);     // 通过触发器名和组名获取TriggerKey
        CronTrigger trigger = (CronTrigger) getScheduler().getTrigger(triggerKey);          // 通过TriggerKey获取CronTrigger
        if (trigger == null) return;
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(trigger.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        ((CronTriggerImpl) trigger).setStartTime(new Date());
        getScheduler().rescheduleJob(triggerKey, trigger);
    }

    /**
     * 启动任务
     */
    public static void start() throws SchedulerException {
        if (!getScheduler().isStarted()) getScheduler().start();
    }

    /**
     * 停止任务
     */
    public static void shutdown() throws SchedulerException {
        if (!getScheduler().isShutdown()) getScheduler().shutdown();
    }

    /**
     * 获取任务上下文
     *
     * @return SchedulerContext
     * @throws SchedulerException 获取上下文失败抛出异常
     */
    public static SchedulerContext getContext() throws SchedulerException {
        return getScheduler().getContext();
    }

    private static Scheduler getScheduler() {
        return SpringContextHelper.getBean(Scheduler.class);
    }
}
