package com.wang.boot.groovy.compiler;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;

public class GroovyFileUtils {

    private static final String GROOVY_FILE_PACKAGE = "groovy";

    private static final String GROOVY_FILE_SUFFIX = ".groovy";

    public static File loadGroovyScriptFile(String... paths) throws Exception {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        String realPath = String.join(File.separator, paths);
        Resource resource = resourceLoader.getResource("file:" + GROOVY_FILE_PACKAGE + File.separator + realPath + GROOVY_FILE_SUFFIX);
        return resource.getFile();
    }
}
