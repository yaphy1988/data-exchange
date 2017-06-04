package com.ai.bdex.dataexchange.report.service.interfaces;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;
import com.github.pagehelper.Page;

/**
 * Created by xiongqian on 2017/6/3.
 */
public interface IReportSv {
    public <T> Page<T> getRePortData(String reportId, BaseInfo param);
}
