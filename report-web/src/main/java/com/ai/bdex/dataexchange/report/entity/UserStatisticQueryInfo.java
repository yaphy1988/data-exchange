package com.ai.bdex.dataexchange.report.entity;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

import java.util.Date;

/**
 * Created by yaphy on 2017/5/24.
 */
public class UserStatisticQueryInfo extends BaseInfo {
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    Date startTime;

    Date endTime;
}
