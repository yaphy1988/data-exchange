package com.ai.bdex.dataexchange.busi.gds.entity;

import java.io.Serializable;

/**
 * Created by yx on 2017/4/24.
 */
public class GdsDetailTabContent implements Serializable {

    private String tabName;

    private String tabContentType;//tab页内容类型，1普通文本，2.富文本

    private String tabContentId;//tab内容页面ID；

    private String tabContent;//tab内容；

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabContentType() {
        return tabContentType;
    }

    public void setTabContentType(String tabContentType) {
        this.tabContentType = tabContentType;
    }

    public String getTabContentId() {
        return tabContentId;
    }

    public void setTabContentId(String tabContentId) {
        this.tabContentId = tabContentId;
    }

    public String getTabContent() {
        return tabContent;
    }

    public void setTabContent(String tabContent) {
        this.tabContent = tabContent;
    }
}
