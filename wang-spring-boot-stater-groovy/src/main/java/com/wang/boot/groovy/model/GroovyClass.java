package com.wang.boot.groovy.model;

import groovy.lang.GroovyObject;
import groovy.lang.Script;
import lombok.Data;

import java.util.Map;

@Data
public class GroovyClass<T> {

    private T object;

    private long lastModify = 0;

    public GroovyClass(T object) {
        this.object = object;
    }

    public Object invokeMethod(String methodName, Object[] args) {
        if (object instanceof Script) {
            return ((Script) object).invokeMethod(methodName, args);
        }
        if (object instanceof GroovyObject) {
            return ((GroovyObject) object).invokeMethod(methodName, args);
        }
        return null;
    }

    public Object execute(Map bindings) {
        if (object instanceof Script) {
            Script script = (Script) object;
            if (bindings != null) {
                script.getBinding().getVariables().putAll(bindings);
            }
            return script.run();
        }
        return object;
    }
}
