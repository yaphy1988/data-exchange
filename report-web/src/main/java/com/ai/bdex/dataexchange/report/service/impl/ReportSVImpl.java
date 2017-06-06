package com.ai.bdex.dataexchange.report.service.impl;

import com.ai.bdex.dataexchange.report.service.interfaces.IRowForMat;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;
import com.ai.bdex.dataexchange.report.service.interfaces.IReportSV;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by xiongqian on 2017/6/3.
 */
@Service("reportSV")
public class ReportSVImpl implements IReportSV {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public <T> Page<T> getRePortData(String reportId, BaseInfo param) {
        //设置分页
        PageHelper.startPage(param.getPageNo().intValue(),param.getPageSize().intValue());

        Page<T> page = (Page<T>)this.sqlSessionTemplate.selectList(reportId, param);

        return page;
    }

    @Override
    public <T> Page<T> getRePortData(String reportId, BaseInfo param,IRowForMat format){
        Page<T> page = this.getRePortData(reportId,param);
        if(format != null && page != null && page.getResult().size()>0){
            for (T vo :page.getResult()) {
                format.forMat(vo);
            }
        }
        return page;
    }
}
