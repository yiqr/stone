package com.stone.starter.quartz.helper;

import org.apache.commons.collections4.MapUtils;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author rose
 * @date 2022/11/7 18:28
 */
@Component
public class QuartzJobHelper {

    private Scheduler scheduler;

    public QuartzJobHelper(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

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
    public void addJob(String name, String group, Class<? extends Job> clazz, String cronExpression, String desc, Map<String, ?> jobDataMap) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(clazz).withIdentity(name, group).withDescription(desc).storeDurably().build();
        if (MapUtils.isNotEmpty(jobDataMap)) {
            job.getJobDataMap().putAll(jobDataMap);
        }
        Trigger trg = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        scheduler.scheduleJob(job, trg);
    }

    /**
     * 更新jobDataMap
     */
    public void updateJobDataMap(String name, String group, Map<String, ?> jobDataMap) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (MapUtils.isNotEmpty(jobDataMap)) {
            jobDetail.getJobDataMap().putAll(jobDataMap);
        }
        scheduler.addJob(jobDetail, true);
    }

    /**
     * 删除任务
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public void removeJob(String name, String group) throws SchedulerException {
        TriggerKey tk = TriggerKey.triggerKey(name, group);
        scheduler.pauseTrigger(tk);
        scheduler.unscheduleJob(tk);
        JobKey jobKey = JobKey.jobKey(name, group);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 暂停任务
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复任务
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        scheduler.resumeJob(jobKey);
    }

    /**
     * 修改任务触发时间
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public void modifyTime(String name, String group, String cronExpression) throws SchedulerException {
        // 通过SchedulerFactory构建Scheduler对象
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);     // 通过触发器名和组名获取TriggerKey
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);          // 通过TriggerKey获取CronTrigger
        if (trigger == null) return;
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(trigger.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        ((CronTriggerImpl) trigger).setStartTime(new Date());
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 启动任务
     */
    public void start() throws SchedulerException {
        if (!scheduler.isStarted()) scheduler.start();
    }

    /**
     * 停止任务
     */
    public void shutdown() throws SchedulerException {
        if (!scheduler.isShutdown()) scheduler.shutdown();
    }
}
