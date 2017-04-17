package com.ai.bdex.dataexchange.common.dto;

import com.ai.bdex.dataexchange.util.LocaleUtil;
import com.ai.bdex.dataexchange.util.SiteLocaleUtil;
import com.ai.bdex.dataexchange.util.StaffLocaleUtil;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by fangyunfeng on 2017/4/17.
 */
public class BaseInfo implements Serializable {
    private static final long serialVersionUID = 7849408114582193366L;
    private Integer pageNo = Integer.valueOf(1);
    private Integer pageSize = Integer.valueOf(1);
    private String gridQuerySortName = null;
    private String gridQuerySortOrder = null;

    private BaseStaff staff = null;
    private Locale locale = null;
    private Long currentSiteId = Long.valueOf(0L);

    public BaseInfo(){
        this.staff = StaffLocaleUtil.getStaff();
        this.locale = LocaleUtil.getLocale();
        this.currentSiteId = SiteLocaleUtil.getSite();
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

    public String getGridQuerySortName() {
        return gridQuerySortName;
    }

    public void setGridQuerySortName(String gridQuerySortName) {
        this.gridQuerySortName = gridQuerySortName;
    }

    public String getGridQuerySortOrder() {
        return gridQuerySortOrder;
    }

    public void setGridQuerySortOrder(String gridQuerySortOrder) {
        this.gridQuerySortOrder = gridQuerySortOrder;
    }
}
