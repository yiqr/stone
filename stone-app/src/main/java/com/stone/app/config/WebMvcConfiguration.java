package com.stone.app.config;

import com.stone.commons.core.config.AbstractWebMvcConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * @author rose
 * @date 2022/11/8 15:38
 */
@Slf4j
@Configuration
public class WebMvcConfiguration extends AbstractWebMvcConfiguration {

    private static final String MESSAGE_BASE_NAMES = "classpath:i18n/messages";
    private static final String DEFAULT_ENCODING = "UTF-8";


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(super.localeChangeInterceptor());
    }

    @Override
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_BASE_NAMES);
        messageSource.setDefaultEncoding(DEFAULT_ENCODING);
        return messageSource;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }

    @Override
    public jakarta.validation.Validator beanValidator() {
        CustomValidatorBean validatorBean = new CustomValidatorBean();
        validatorBean.setMessageInterpolator(new ResourceBundleMessageInterpolator(new MessageSourceResourceBundleLocator(messageSource())));
        return validatorBean;
    }

    @Override
    public MethodValidationPostProcessor methodValidationPostProcessor(Environment environment) {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setProxyTargetClass(determineProxyTargetClass(environment));
        processor.setValidator(beanValidator());
        return processor;
    }

    private static boolean determineProxyTargetClass(Environment environment) {
        return environment.getProperty("spring.aop.proxy-target-class", Boolean.class, true);
    }

    @Override
    public MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "OPTIONS", "PUT", "DELETE")
                .allowedHeaders("x-requested-with", "authorization", "Content-Type", "Authorization", "X-Auth-Token", "x-auth-token");
    }
}
