package com.wang.boot.groovy.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextBeanManager implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @SuppressWarnings("uncheck")
    public <T> T getBean(String name) {
        if (applicationContext != null) {
            return (T) applicationContext.getBean(name);
        } else {
            return null;
        }
    }

    public String getProperty(String name) {
        if (applicationContext != null) {
            return applicationContext.getEnvironment().getProperty(name);
        } else {
            return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.applicationContext == null) {
            this.applicationContext = applicationContext;
        }
    }
}
