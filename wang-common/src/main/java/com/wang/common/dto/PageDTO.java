package com.wang.common.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class PageDTO implements Serializable {

    private static final long serialVersionUID = 3893101648401625778L;

    private Integer curPageNum;

    private Integer pageSize;

    private Integer totalPages;

    private Long totalRecordNum;

    public Integer getCurPageNum() {
        return curPageNum;
    }

    public void setCurPageNum(Integer curPageNum) {
        this.curPageNum = curPageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalRecordNum() {
        return totalRecordNum;
    }

    public void setTotalRecordNum(Long totalRecordNum) {
        this.totalRecordNum = totalRecordNum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
