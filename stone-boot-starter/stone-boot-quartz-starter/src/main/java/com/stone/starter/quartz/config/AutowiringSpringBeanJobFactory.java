package com.stone.starter.quartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * @author rose
 * @date 2022/11/7 18:15
 */
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private transient AutowireCapableBeanFactory beanFactory;

    @Override
    public void setApplicationContext(final ApplicationContext context) {
        beanFactory = context.getAutowireCapableBeanFactory();
    }

    /**
     * 将job实例交给spring ioc托管
     * 我们在job实例实现类内可以直接使用spring注入的调用被spring ioc管理的实例
     *
     * @param bundle
     * @return
     * @throws Exception
     */
    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        /**
         * 将job实例交付给spring ioc
         */
        beanFactory.autowireBean(job);
        return job;
    }
}
