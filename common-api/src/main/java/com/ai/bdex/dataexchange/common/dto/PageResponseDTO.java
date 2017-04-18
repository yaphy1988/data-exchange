package com.ai.bdex.dataexchange.common.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * @author yafei
 * @since 2016/11/15
 */
public class PageResponseDTO<T extends BaseResponseDTO> implements Serializable {
    private static final long serialVersionUID = -3079926609817996775L;
    private Integer pageNo = Integer.valueOf(1);
    private Integer pageSize;
    private List<T> result;
    private long count = 0L;
    private long pageCount;

    public PageResponseDTO(){
    }

    public static <T extends BaseResponseDTO> PageResponseDTO<T> buildByBaseInfo(BaseInfo info, Class<T> clazz) {
        PageResponseDTO t = new PageResponseDTO();
        t.setPageNo(info.getPageNo());
        t.setPageSize(info.getPageSize());
        return t;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPageCount() {
        long quotient = this.getCount() / (long)this.getPageSize().intValue();
        long remainder = this.getCount() % (long)this.getPageSize().intValue();
        this.pageCount = quotient;
        if(remainder > 0L) {
            ++this.pageCount;
        }

        return this.pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
