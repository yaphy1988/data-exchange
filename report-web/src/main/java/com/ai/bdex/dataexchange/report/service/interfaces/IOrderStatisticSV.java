package com.ai.bdex.dataexchange.report.service.interfaces;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.report.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.report.dao.model.OrdMainInfo;
import com.ai.bdex.dataexchange.report.entity.OrdInfoStatisticQueryInfo;
import com.ai.bdex.dataexchange.report.entity.OrdMainStatisticQueryInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by wy on 2017/6/2.
 */
public interface IOrderStatisticSV {

    /**
     * 分页查询我的订单主表信息
     *
     * @param ordMainStatisticQueryInfo
     * @return
     * @throws Exception
     */
    public PageInfo<OrdMainInfo> queryOrdMainInfoPage(OrdMainStatisticQueryInfo ordMainStatisticQueryInfo)
            throws BusinessException;

    public List<OrdMainInfo> queryOrdMainInfoList(OrdMainStatisticQueryInfo ordMainStatisticQueryInfo) throws BusinessException;

    /***
     * 查询单个主订单信息
     * @param ordMainStatisticQueryInfo
     * @return
     * @throws Exception
     */
    public  OrdMainInfo  queryOrdMainInfoOne(OrdMainStatisticQueryInfo ordMainStatisticQueryInfo) 	throws BusinessException;

    /**
     * 分页查询订单
     *
     * @param ordInfoStatisticQueryInfo
     * @return
     */
    public PageInfo<OrdInfo> queryOrdInfoPage(OrdInfoStatisticQueryInfo ordInfoStatisticQueryInfo) throws BusinessException;

    /**
     * 查询子订单List
     * @param ordInfoStatisticQueryInfo
     * @return
     * @throws Exception
     */
    public List<OrdInfo> queryOrderInfoList(OrdInfoStatisticQueryInfo ordInfoStatisticQueryInfo) throws BusinessException;

}
