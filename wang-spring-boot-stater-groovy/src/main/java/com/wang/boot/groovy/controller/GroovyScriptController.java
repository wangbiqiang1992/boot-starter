package com.wang.boot.groovy.controller;


import com.wang.boot.groovy.compiler.GroovyFileUtils;
import com.wang.boot.groovy.compiler.GroovyScriptFactory;
import com.wang.boot.groovy.context.ContextBeanManager;
import com.wang.common.vo.Response;
import groovy.lang.Script;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/*@ConditionalOnBean*/

/**
 * 使用 @ConditionalOnBean 并不能生效，原因就是spring
 * 容器会优先加载@Componet @Service @Controller 等类
 * @Configuration 优先级较低
 */
@ConditionalOnClass(ContextBeanManager.class)
@RestController
@RequestMapping("${web.groovy.path}")
@Slf4j
public class GroovyScriptController {

    @Resource
    private ContextBeanManager contextBeanManager;

    @Resource
    private GroovyScriptFactory groovyScriptFactory;

    @RequestMapping("unauth/{fileName}")
    public Response invokeGroovyScriptUnAuth(@PathVariable("fileName") String fileName,
                                             HttpServletRequest servletRequest,
                                             HttpServletResponse servletResponse,
                                             @RequestBody(required = false) Object requestBody,
                                             @RequestParam(required = false) Map<String, Object> requestParam) {
        return invokeGroovyScript(servletRequest, servletResponse, requestBody, requestParam, fileName);
    }

    @RequestMapping("unauth/{package1}/{fileName}")
    public Response invokeGroovyScriptUnAuth(@PathVariable("package1") String package1,
                                             @PathVariable("fileName") String fileName,
                                             HttpServletRequest servletRequest,
                                             HttpServletResponse servletResponse,
                                             @RequestBody(required = false) Object requestBody,
                                             @RequestParam(required = false) Map<String, Object> requestParam) {
        return invokeGroovyScript(servletRequest, servletResponse, requestBody, requestParam,
                package1, fileName);
    }

    @RequestMapping("unauth/{package1}/{package2}/{fileName}")
    public Response invokeGroovyScriptUnAuth(@PathVariable("package1") String package1,
                                             @PathVariable("package2") String package2,
                                             @PathVariable("fileName") String fileName,
                                             HttpServletRequest servletRequest,
                                             HttpServletResponse servletResponse,
                                             @RequestBody(required = false) Object requestBody,
                                             @RequestParam(required = false) Map<String, Object> requestParam) {
        return invokeGroovyScript(servletRequest, servletResponse, requestBody, requestParam,
                package1, package2, fileName);
    }

    @RequestMapping("unauth/{package1}/{package2}/{package3}/{fileName}")
    public Response invokeGroovyScriptUnAuth(@PathVariable("package1") String package1,
                                             @PathVariable("package2") String package2,
                                             @PathVariable("package3") String package3,
                                             @PathVariable("fileName") String fileName,
                                             HttpServletRequest servletRequest,
                                             HttpServletResponse servletResponse,
                                             @RequestBody(required = false) Object requestBody,
                                             @RequestParam(required = false) Map<String, Object> requestParam) {
        return invokeGroovyScript(servletRequest, servletResponse, requestBody, requestParam,
                package1, package2, package3, fileName);
    }

    @RequestMapping("unauth/{package1}/{package2}/{package3}/{pacakge4}/{fileName}")
    public Response invokeGroovyScriptUnAuth(@PathVariable("package1") String package1,
                                             @PathVariable("package2") String package2,
                                             @PathVariable("package3") String package3,
                                             @PathVariable("package4") String package4,
                                             @PathVariable("fileName") String fileName,
                                             HttpServletRequest servletRequest,
                                             HttpServletResponse servletResponse,
                                             @RequestBody(required = false) Object requestBody,
                                             @RequestParam(required = false) Map<String, Object> requestParam) {
        return invokeGroovyScript(servletRequest, servletResponse, requestBody, requestParam,
                package1, package2, package3, package4, fileName);
    }

    @RequestMapping("auth/{fileName}")
    public Response invokeGroovyScriptAuth(@PathVariable("fileName") String fileName,
                                           HttpServletRequest servletRequest,
                                           HttpServletResponse servletResponse,
                                           @RequestBody(required = false) Object requestBody,
                                           @RequestParam(required = false) Map<String, Object> requestParam) {
        return invokeGroovyScript(servletRequest, servletResponse, requestBody, requestParam, fileName);
    }

    @RequestMapping("auth/{package1}/{fileName}")
    public Response invokeGroovyScriptAuth(@PathVariable("package1") String package1,
                                           @PathVariable("fileName") String fileName,
                                           HttpServletRequest servletRequest,
                                           HttpServletResponse servletResponse,
                                           @RequestBody(required = false) Object requestBody,
                                           @RequestParam(required = false) Map<String, Object> requestParam) {
        return invokeGroovyScript(servletRequest, servletResponse, requestBody, requestParam,
                package1, fileName);
    }

    @RequestMapping("auth/{package1}/{package2}/{fileName}")
    public Response invokeGroovyScriptAuth(@PathVariable("package1") String package1,
                                           @PathVariable("package2") String package2,
                                           @PathVariable("fileName") String fileName,
                                           HttpServletRequest servletRequest,
                                           HttpServletResponse servletResponse,
                                           @RequestBody(required = false) Object requestBody,
                                           @RequestParam(required = false) Map<String, Object> requestParam) {
        return invokeGroovyScript(servletRequest, servletResponse, requestBody, requestParam,
                package1, package2, fileName);
    }

    @RequestMapping("auth/{package1}/{package2}/{package3}/{fileName}")
    public Response invokeGroovyScriptAuth(@PathVariable("package1") String package1,
                                           @PathVariable("package2") String package2,
                                           @PathVariable("package3") String package3,
                                           @PathVariable("fileName") String fileName,
                                           HttpServletRequest servletRequest,
                                           HttpServletResponse servletResponse,
                                           @RequestBody(required = false) Object requestBody,
                                           @RequestParam(required = false) Map<String, Object> requestParam) {
        return invokeGroovyScript(servletRequest, servletResponse, requestBody, requestParam,
                package1, package2, package3, fileName);
    }

    @RequestMapping("auth/{package1}/{package2}/{package3}/{pacakge4}/{fileName}")
    public Response invokeGroovyScriptAuth(@PathVariable("package1") String package1,
                                           @PathVariable("package2") String package2,
                                           @PathVariable("package3") String package3,
                                           @PathVariable("package4") String package4,
                                           @PathVariable("fileName") String fileName,
                                           HttpServletRequest servletRequest,
                                           HttpServletResponse servletResponse,
                                           @RequestBody(required = false) Object requestBody,
                                           @RequestParam(required = false) Map<String, Object> requestParam) {
        return invokeGroovyScript(servletRequest, servletResponse, requestBody, requestParam,
                package1, package2, package3, package4, fileName);
    }

    /**
     * ... 代码分为两个部分，第一部分是测试使用，第二部分使用auth的部分
     */

    private Response invokeGroovyScript(HttpServletRequest servletRequest,
                                        HttpServletResponse servletResponse,
                                        Object requestBody, Map<String, Object> requestParam,
                                        String... path) {
        Response response = new Response();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("servletRequest", servletRequest);
            map.put("servletResponse", servletResponse);
            map.put("requestBody", requestBody);
            map.put("requestParam", requestParam);
            map.put("response", response);
            map.put("contextManager", contextBeanManager);
            map.put("logger", log);
            File file = GroovyFileUtils.loadGroovyScriptFile(path);
            Script script = groovyScriptFactory.getGroovyScript(file);
            script.getBinding().getVariables().putAll(map);
            return new Response(script.run());
        } catch (Exception e) {
            response.setCode(500);
            response.setMsg("异常");
            log.error("invokeGroovyScript fail,path:{}", String.join(File.separator, path), e);
        }
        return response;
    }
}
