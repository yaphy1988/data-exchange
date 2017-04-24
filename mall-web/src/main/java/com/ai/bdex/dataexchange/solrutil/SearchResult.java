package com.ai.bdex.dataexchange.solrutil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月14日下午2:09:38 <br>
 * Copyright (c) 2015 asia All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class SearchResult<T> implements Serializable {

    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    /**
     * 查询耗时
     */
    private int qTime;

    /**
     * 请求Url地址
     */
    private String requestUrl;

    /**
     * 状态，默认请求失败
     */
    private int status = -1;

    /**
     * 记录结果数或搜索建议结果数
     */
    private long numFound = 0;

    /**
     * 错误或提示信息
     */
    private String message;

    /**
     * 查询/拼写检查/搜索建议结果列表
     */
    private List<T> resultList = new ArrayList<T>();

    /**
     * 重定向类型
     */
    private String redirectType;

    /**
     * 重定向URL
     */
    private String redirectUrl = null;

    /**
     * 分页大小
     */
    private int pageSize;

    /**
     * 当前页码
     */
    private long pageNo;

    /**
     * 总页数
     */
    private long totallyPage;

    public boolean isSuccess() {
        return StringUtils.isNotEmpty(redirectUrl) || status == 0;
    }

    public void setSuccess() {
        this.status = 0;
    }

    public boolean isUrlResult() {
        return StringUtils.isNotEmpty(redirectUrl);
    }

    public int getqTime() {
        return qTime;
    }

    public void setqTime(int qTime) {
        this.qTime = qTime;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getNumFound() {
        return numFound;
    }

    public void setNumFound(long numFound) {
        this.numFound = numFound;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public String getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(String redirectType) {
        this.redirectType = redirectType;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getTotallyPage() {
        return totallyPage;
    }

    public void setTotallyPage(long totallyPage) {
        this.totallyPage = totallyPage;
    }


    @Override
    public String toString() {
        return "SearchResult [qTime=" + qTime + ", requestUrl=" + requestUrl + ", status=" + status + ", numFound=" + numFound + ", message=" + message + ", resultList=" + resultList
                + ", redirectType=" + redirectType + ", redirectUrl=" + redirectUrl + ", pageSize="
                + pageSize + ", pageNo=" + pageNo + ", totallyPage=" + totallyPage + "]";
    }

}
