package com.wang.boot.groovy.model;

public class ClassCache {
    private Class clazz;
    private long lastModify = 0;


    public ClassCache(Class clazz, long lastModify) {
        this.clazz = clazz;
        this.lastModify = lastModify;
    }

    public ClassCache() {
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public long getLastModify() {
        return lastModify;
    }

    public void setLastModify(long lastModify) {
        this.lastModify = lastModify;
    }
}
