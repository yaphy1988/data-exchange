package com.ai.bdex.dataexchange.report.service.interfaces;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * Created by xiongqian on 2017/6/3.
 */
public interface IReportSV {

    public <T> PageInfo<T> getRePortData(String reportId, BaseInfo param);

    public <T> PageInfo<T> getRePortData(String reportId, BaseInfo param,IRowForMat format);
}
