package com.wang.boot.groovy.compiler;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.runtime.InvokerHelper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class GroovyScriptFactory {

    private final Map<String,Script> singleScriptObject = new ConcurrentHashMap<>(256);

    private final GroovyClassLoader groovyClassLoader = new GroovyClassLoader(GroovyScriptFactory.class.getClassLoader());

    public Script getGroovyScript(File groovyScriptFile){
        if (!groovyScriptFile.exists()) {
            return null;
        }
        Binding binding = new Binding(new HashMap<String, Object>());
        String key = getFileContentKey(groovyScriptFile);
        if (singleScriptObject.containsKey(key)) {
            return singleScriptObject.get(key);
        }
        try {
            synchronized (singleScriptObject){
                if(!singleScriptObject.containsKey(key)){
                    Class aClass = groovyClassLoader.parseClass(groovyScriptFile);
                    Script script = InvokerHelper.createScript(aClass, binding);
                    singleScriptObject.put(key,script);
                    return script;
                }
            }
            return singleScriptObject.get(key);
        } catch (Exception e) {
            log.error("getGroovyScript fail,groovyFile:{}", groovyScriptFile.getAbsolutePath(), e);
        }
        return null;
    }

    private static String getFileContentKey(File groovyScriptFile) {
        try (Stream<String> lines = Files.lines(Paths.get(groovyScriptFile.getAbsolutePath()))) {
            return lines.collect(Collectors.joining()).replaceAll("\\s*", "");
        } catch (Exception e) {
            log.error("getFileKey fail,groovyFile:{}", groovyScriptFile.getAbsolutePath(), e);
        }
        return null;
    }
}
