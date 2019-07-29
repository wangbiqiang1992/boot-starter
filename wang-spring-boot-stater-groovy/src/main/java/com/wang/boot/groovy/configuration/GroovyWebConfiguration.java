package com.wang.boot.groovy.configuration;

import com.wang.boot.groovy.compiler.GroovyScriptFactory;
import com.wang.boot.groovy.context.ContextBeanManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroovyWebConfiguration {

    @Bean
    public GroovyScriptFactory groovyScriptFactory(){
        return new GroovyScriptFactory();
    }

    @Bean("contextBeanManager")
    @ConditionalOnProperty(value = {"web.groovy.path"} )
    public ContextBeanManager contextBeanManager(){
        return new ContextBeanManager();
    }
}
