package com.wang.boot.groovy.compiler;

import com.wang.boot.groovy.model.ClassCache;
import com.wang.boot.groovy.model.GroovyClass;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
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

@Deprecated
@Slf4j
public class GroovyScriptCompiler {

    private static Map<String, GroovyClass<GroovyObject>> GROOVY_CACHE = new ConcurrentHashMap<>();

    private static Map<String, GroovyClass<Script>> GROOVY_SCRIPT_CACHE = new ConcurrentHashMap<>();

    private static Map<String, ClassCache> GROOVY_CLASS_CACHE = new ConcurrentHashMap<>();

    public static GroovyClass<GroovyObject> getGroovyClass(File groovyScriptFile) {
        if (!groovyScriptFile.exists()) {
            return null;
        }
        String key = getFileKey(groovyScriptFile);
        if (GROOVY_CACHE.containsKey(key)) {
            GroovyClass<GroovyObject> groovyClass = GROOVY_CACHE.get(key);
            if (groovyClass.getLastModify() == groovyScriptFile.lastModified()) {
                return groovyClass;
            } else {
                GROOVY_CACHE.remove(key);
            }
        }
        try {
            GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
            Class<?> scriptClass = groovyClassLoader.parseClass(groovyScriptFile);
            if (!scriptClass.isInterface()) {
                GroovyObject groovyObject = (GroovyObject) scriptClass.newInstance();
                GroovyClass<GroovyObject> groovyClass = new GroovyClass<>(groovyObject);
                groovyClass.setLastModify(groovyScriptFile.lastModified());
                GROOVY_CACHE.put(key, groovyClass);
                return groovyClass;
            }
        } catch (Exception e) {
            log.error("getGroovyClass fail,groovyFile:{}", groovyScriptFile.getAbsolutePath(), e);
        }
        return null;
    }

    public static GroovyClass<Script> getGroovyScript(File groovyScriptFile) {
        GroovyClass<Script> groovyScript;
        if (!groovyScriptFile.exists()) {
            return null;
        }
        Binding binding = new Binding(new HashMap<String, Object>());
        String key = getFileKey(groovyScriptFile);
        if (GROOVY_SCRIPT_CACHE.containsKey(key)) {

            groovyScript = GROOVY_SCRIPT_CACHE.get(key);
            if (groovyScript.getLastModify() == groovyScriptFile.lastModified()) {
                return groovyScript;
            } else {
                synchronized (GROOVY_SCRIPT_CACHE){
                    GROOVY_SCRIPT_CACHE.remove(key);
                }
            }
        }
        try {
            GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
            Class aClass = groovyClassLoader.parseClass(groovyScriptFile);
            Script script = InvokerHelper.createScript(aClass, binding);
            groovyScript = new GroovyClass<>(script);
            groovyScript.setLastModify(groovyScriptFile.lastModified());
            GROOVY_SCRIPT_CACHE.put(key, groovyScript);
            return groovyScript;
        } catch (Exception e) {
            log.error("getGroovyScript fail,groovyFile:{}", groovyScriptFile.getAbsolutePath(), e);
        }
        return null;
    }


    public static Script getGroovyScript(File groovyScriptFile, Binding binding) {
        Class aClass = null;
        if (!groovyScriptFile.exists()) {
            return null;
        }
        String key = getFileKey(groovyScriptFile);
        if (GROOVY_CLASS_CACHE.containsKey(key)) {
            ClassCache classCache = GROOVY_CLASS_CACHE.get(key);
            if (classCache.getLastModify() == groovyScriptFile.lastModified()) {
                return InvokerHelper.createScript(classCache.getClass(), binding);
            } else {
                GROOVY_CLASS_CACHE.remove(key);
            }
        }
        try {
            GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
            aClass = groovyClassLoader.parseClass(groovyScriptFile);
            ClassCache classCache = new ClassCache(aClass, groovyScriptFile.lastModified());
            GROOVY_CLASS_CACHE.put(key, classCache);
        } catch (Exception e) {
            log.error("getGroovyScript fail,groovyFile:{}", groovyScriptFile.getAbsolutePath(), e);
        }
        return InvokerHelper.createScript(aClass, binding);
    }

    private static String getFileKey(File groovyScriptFile) {
        try (Stream<String> lines = Files.lines(Paths.get(groovyScriptFile.getAbsolutePath()))) {
            return lines.collect(Collectors.joining()).replaceAll("\\s*", "");
        } catch (Exception e) {
            log.error("getFileKey fail,groovyFile:{}", groovyScriptFile.getAbsolutePath(), e);
        }
        return null;
    }

}
