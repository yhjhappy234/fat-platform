package com.yhj.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by YHJ on 2016/12/11.
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static <T extends Object> T getBean(Class<T> t){
        Object obj = applicationContext.getBean(t);
        return (T) obj;
    }
}
