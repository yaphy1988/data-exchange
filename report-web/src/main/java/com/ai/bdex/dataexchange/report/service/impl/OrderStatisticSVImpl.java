package com.ai.bdex.dataexchange.report.service.impl;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.report.dao.mapper.OrdInfoMapper;
import com.ai.bdex.dataexchange.report.dao.mapper.OrdMainInfoMapper;
import com.ai.bdex.dataexchange.report.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.report.dao.model.OrdInfoExample;
import com.ai.bdex.dataexchange.report.dao.model.OrdMainInfo;
import com.ai.bdex.dataexchange.report.dao.model.OrdMainInfoExample;
import com.ai.bdex.dataexchange.report.entity.OrdInfoStatisticQueryInfo;
import com.ai.bdex.dataexchange.report.entity.OrdMainStatisticQueryInfo;
import com.ai.bdex.dataexchange.report.service.interfaces.IOrderStatisticSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangwy on 2017/6/2.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service("orderStatisticSV")
public class OrderStatisticSVImpl implements IOrderStatisticSV{
    @Autowired
    private OrdMainInfoMapper ordMainInfoMapper;
    @Autowired
    private OrdInfoMapper ordInfoMapper;
    @Override
    public PageInfo<OrdMainInfo> queryOrdMainInfoPage(OrdMainStatisticQueryInfo ordMainStatisticQueryInfo)
            throws BusinessException {
        Integer pageNo = ordMainStatisticQueryInfo.getPageNo();
        Integer pageSize = ordMainStatisticQueryInfo.getPageSize();
        OrdMainInfoExample example = new OrdMainInfoExample();
        OrdMainInfoExample.Criteria criteria = example.createCriteria();
        initOrdMainCriteria(criteria, ordMainStatisticQueryInfo);
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getStaffId())) {
            criteria.andStaffIdEqualTo(ordMainStatisticQueryInfo.getStaffId());
        }
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getShopId())) {
            criteria.andShopIdEqualTo(ordMainStatisticQueryInfo.getShopId());
        }
        if (!CollectionUtil.isEmpty(ordMainStatisticQueryInfo.getordertypeList())) {
            criteria.andOrderTypeIn(ordMainStatisticQueryInfo.getordertypeList());
        }
        if (ordMainStatisticQueryInfo.getStartTime() != null){
            criteria.andOrderTimeGreaterThanOrEqualTo(ordMainStatisticQueryInfo.getStartTime());
        }
        if (ordMainStatisticQueryInfo.getEndTime() != null){
            criteria.andOrderTimeLessThanOrEqualTo(ordMainStatisticQueryInfo.getEndTime());
        }
        if (!StringUtil.isBlank(ordMainStatisticQueryInfo.getPayFlag())){
            criteria.andPayFlagEqualTo(ordMainStatisticQueryInfo.getPayFlag());
        }
        if (!StringUtil.isBlank(ordMainStatisticQueryInfo.getOrderStatus())){
            criteria.andOrderStatusEqualTo(ordMainStatisticQueryInfo.getOrderStatus());
        }
        example.setOrderByClause("ORDER_TIME desc");
        PageHelper.startPage(pageNo, pageSize);
        List<OrdMainInfo> ordMainInfoList = ordMainInfoMapper.selectByExample(example);
        // 使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(ordMainInfoList);

        return pageInfo;
    }

    @Override
    public List<OrdMainInfo> queryOrdMainInfoList(OrdMainStatisticQueryInfo ordMainStatisticQueryInfo) throws BusinessException {
        OrdMainInfoExample example = new OrdMainInfoExample();
        OrdMainInfoExample.Criteria criteria = example.createCriteria();
        initOrdMainCriteria(criteria,ordMainStatisticQueryInfo);
        List<OrdMainInfo> ordMainInfoList = ordMainInfoMapper.selectByExample(example);
        List<OrdMainInfo> respDTOList = new ArrayList<OrdMainInfo>();
        if (!CollectionUtil.isEmpty(ordMainInfoList)) {
            for (OrdMainInfo ordMainInfo : ordMainInfoList) {
                OrdMainInfo ordMainInfoRespDTO = new OrdMainInfo();
                ObjectCopyUtil.copyObjValue(ordMainInfo, ordMainInfoRespDTO, null, false);
                respDTOList.add(ordMainInfoRespDTO);
            }
        }

        return respDTOList;
    }

    @Override
    public OrdMainInfo queryOrdMainInfoOne(OrdMainStatisticQueryInfo ordMainStatisticQueryInfo) throws BusinessException {
        OrdMainInfoExample example = new OrdMainInfoExample();
        if (StringUtil.isBlank(ordMainStatisticQueryInfo.getOrderId())) {
            throw new BusinessException("查询订单信息时，订单ID入参为空");
        }
        OrdMainInfo ordMainInfo  = ordMainInfoMapper.selectByPrimaryKey(ordMainStatisticQueryInfo.getOrderId());
        return ordMainInfo;
    }

    public List<OrdInfo> queryOrderInfoList(OrdInfoStatisticQueryInfo ordMainStatisticQueryInfo) throws BusinessException{
        OrdInfoExample example = new OrdInfoExample();
        OrdInfoExample.Criteria criteria = example.createCriteria();
        initOrdInfoCriteria(criteria, ordMainStatisticQueryInfo);
        example.setOrderByClause("ORDER_TIME desc");
        List<OrdInfo> ordInfoList = ordInfoMapper.selectByExample(example);
        List<OrdInfo> respDTOList = new ArrayList<OrdInfo>();
        if(!CollectionUtil.isEmpty(ordInfoList)){
            for(OrdInfo ordInfo :ordInfoList){
                OrdInfo respDTO = new OrdInfo();
                ObjectCopyUtil.copyObjValue(ordInfo,respDTO,null,false);
                respDTOList.add(respDTO);
            }
        }
        return respDTOList;
    }
    @Override
    public PageInfo<OrdInfo> queryOrdInfoPage(OrdInfoStatisticQueryInfo ordInfoStatisticQueryInfo) throws BusinessException {
        Integer pageNo = ordInfoStatisticQueryInfo.getPageNo();
        Integer pageSize = ordInfoStatisticQueryInfo.getPageSize();
        OrdInfoExample example = new OrdInfoExample();
        OrdInfoExample.Criteria criteria = example.createCriteria();
        initOrdInfoCriteria(criteria, ordInfoStatisticQueryInfo);
        example.setOrderByClause("ORDER_TIME desc");
        PageHelper.startPage(pageNo, pageSize);
        List<OrdInfo> ordInfoList = ordInfoMapper.selectByExample(example);
        // 使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(ordInfoList);
        return pageInfo;
    }

    private void initOrdInfoCriteria( OrdInfoExample.Criteria criteria, OrdInfoStatisticQueryInfo ordInfoStatisticQueryInfo) {
        if (StringUtil.isNotBlank(ordInfoStatisticQueryInfo.getSubOrder())) {
            criteria.andSubOrderEqualTo(ordInfoStatisticQueryInfo.getSubOrder());
        }
        if (StringUtil.isNotBlank(ordInfoStatisticQueryInfo.getOrderId())) {
            criteria.andOrderIdEqualTo(ordInfoStatisticQueryInfo.getOrderId());
        }
        if (StringUtil.isNotBlank(ordInfoStatisticQueryInfo.getShopId())) {
            criteria.andShopIdEqualTo(ordInfoStatisticQueryInfo.getShopId());
        }
        if (StringUtil.isNotBlank(ordInfoStatisticQueryInfo.getStaffId())) {
            criteria.andStaffIdEqualTo(ordInfoStatisticQueryInfo.getStaffId());
        }
        if (ordInfoStatisticQueryInfo.getCatFirst()!= null) {
            criteria.andCatFirstEqualTo(ordInfoStatisticQueryInfo.getCatFirst());
        }
        if (ordInfoStatisticQueryInfo.getCatId() != null) {
            criteria.andCatIdEqualTo(ordInfoStatisticQueryInfo.getCatId());
        }
        if (ordInfoStatisticQueryInfo.getGdsId() != null) {
            criteria.andGdsIdEqualTo(ordInfoStatisticQueryInfo.getGdsId());
        }
        if (StringUtil.isNotBlank(ordInfoStatisticQueryInfo.getGdsName())) {
            criteria.andGdsNameLike("%" + ordInfoStatisticQueryInfo.getGdsName() + "%");
        }
        if (StringUtil.isNotBlank(ordInfoStatisticQueryInfo.getSkuName())) {
            criteria.andSkuNameLike("%" + ordInfoStatisticQueryInfo.getSkuName() + "%");
        }
        if (StringUtil.isNotBlank(ordInfoStatisticQueryInfo.getProvinceCode())) {
            criteria.andProvinceCodeEqualTo(ordInfoStatisticQueryInfo.getProvinceCode());
        }
        if (StringUtil.isNotBlank(ordInfoStatisticQueryInfo.getStatus())) {
            criteria.andStatusEqualTo(ordInfoStatisticQueryInfo.getStatus());
        }
        if (StringUtil.isNotBlank(ordInfoStatisticQueryInfo.getPayFlag())) {
            criteria.andPayFlagEqualTo(ordInfoStatisticQueryInfo.getPayFlag());
        }
    }
    private void initOrdMainCriteria(OrdMainInfoExample.Criteria criteria, OrdMainStatisticQueryInfo ordMainStatisticQueryInfo) {
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getOrderId())) {
            criteria.andOrderIdEqualTo(ordMainStatisticQueryInfo.getOrderId());
        }
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getShopId())) {
            criteria.andShopIdEqualTo(ordMainStatisticQueryInfo.getShopId());
        }
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getStaffId())) {
            criteria.andStaffIdEqualTo(ordMainStatisticQueryInfo.getStaffId());
        }
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getProvinceCode())) {
            criteria.andProvinceCodeEqualTo(ordMainStatisticQueryInfo.getProvinceCode());
        }
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getOrderType())) {
            criteria.andOrderTypeEqualTo(ordMainStatisticQueryInfo.getOrderType());
        }
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getPayFlag())) {
            criteria.andPayFlagEqualTo(ordMainStatisticQueryInfo.getPayFlag());
        }
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getPayWay())) {
            criteria.andPayWayEqualTo(ordMainStatisticQueryInfo.getPayWay());
        }
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getInvoiceModType())) {
            criteria.andInvoiceModTypeEqualTo(ordMainStatisticQueryInfo.getInvoiceModType());
        }
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getInvoiceStatus())) {
            criteria.andInvoiceStatusEqualTo(ordMainStatisticQueryInfo.getInvoiceStatus());
        }
        if (StringUtil.isNotBlank(ordMainStatisticQueryInfo.getSource())) {
            criteria.andSourceEqualTo(ordMainStatisticQueryInfo.getSource());
        }
//        if (!CollectionUtil.isEmpty(ordMainStatisticQueryInfo.getInvoiceModTypeList())) {
//            criteria.andInvoiceModTypeIn(ordMainStatisticQueryInfo.getInvoiceModTypeList());
//        }
    }
}
