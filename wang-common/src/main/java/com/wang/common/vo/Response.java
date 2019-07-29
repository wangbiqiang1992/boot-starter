package com.wang.common.vo;

/**
 * 返回给前端的
 */
public class Response {

    private int code = 0;
    private String msg = "SUCCESS";
    private Object resultData;

    public Response() {
    }

    public Response(Object resultData) {
        this.resultData = resultData;
    }

    public Response(int code, String msg, Object resultData) {
        this.code = code;
        this.msg = msg;
        this.resultData = resultData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }
}
