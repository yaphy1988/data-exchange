package com.ai.bdex.dataexchange.aipcenter.service.impl;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.DataAccountHisMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.mapper.DataAccountMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.mapper.ManualDataAccountMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.*;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountRespDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeReqDTO;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipCenterDataAccountSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IRechargeSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.util.ModelDTOConvertUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.ObjectCopyUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by fangyunfeng on 2017/5/11.
 */
@Service("aipCenterDataAccountSV")
public class AipCenterDataAccountSVImpl implements IAipCenterDataAccountSV {

    private static Logger logger = LoggerFactory.getLogger(AipCenterDataAccountSVImpl.class);

    @Autowired
    private DataAccountMapper dataAccountMapper;

    @Autowired
    private ManualDataAccountMapper manualDataAccountMapper;

    @Autowired
    private DataAccountHisMapper dataAccountHisMapper;

    @Autowired
    private IRechargeSV rechargeSV;

    @Override
    public DataAccountDTO queryDataAccountBySubOrder(String subOrder) throws BusinessException{
        if(StringUtil.isBlank(subOrder)){
            return null;
        }
        RechargeReqDTO rechargeDTO = new RechargeReqDTO();
        rechargeDTO.setSubOrder(subOrder);

        List<DataAccountDTO> dataAccountDTOList = queryDataAccountByRechargInfo(rechargeDTO);
        if(!CollectionUtil.isEmpty(dataAccountDTOList)){
            return dataAccountDTOList.get(0);
        }
        return null;
    }


    public List<DataAccountDTO> queryDataAccountByRechargInfo(RechargeReqDTO rechargeDTO) throws BusinessException {

        List<RechargeDTO> rechargeDTOS = rechargeSV.queryRechargeRecordListByOption(rechargeDTO);
        if(!CollectionUtil.isEmpty(rechargeDTOS)){
            List<DataAccountDTO> resultList = new ArrayList<DataAccountDTO>();
            for(RechargeDTO pRecharge : rechargeDTOS){
                DataAccount dataAccount = dataAccountMapper.selectByPrimaryKey(pRecharge.getDataAccountId());
                DataAccountDTO dataAccountDTO = ModelDTOConvertUtil.createCopyObject(dataAccount,DataAccountDTO.class,null,false);
                resultList.add(dataAccountDTO);
            }
            return resultList;
        }
        return null;
    }

    /**
     * 根据充值记录创建数据账户
     * @param rechargeDTO
     * @throws BusinessException
     */
    @Override
    public long createDataAccount(RechargeReqDTO rechargeDTO) throws BusinessException{
        DataAccount dataAccountHis = null;

        DataAccount dataAccount = getDefaultNewDataAccount();
        dataAccount.setUserId(rechargeDTO.getRechargeUserId());
        dataAccount.setServiceId(rechargeDTO.getServiceId());
        dataAccount.setPeriodType(rechargeDTO.getPeriodType());
        dataAccount.setCreateStaff(rechargeDTO.getCurrentUserId());
        dataAccount.setPackageType(rechargeDTO.getPackageType());
        dataAccount.setOrderId(rechargeDTO.getOrderId());
        dataAccount.setSubOrder(rechargeDTO.getSubOrder());
        dataAccount.setGdsId(rechargeDTO.getGdsId());
        dataAccount.setSkuId(rechargeDTO.getSkuId());
        dataAccount.setCatId(rechargeDTO.getCatId());
        dataAccount.setCatFirst(rechargeDTO.getCatFirst());

        dataAccount.setCreateTime(new Timestamp(System.currentTimeMillis()));

        if(Constants.Bill.PACKAGE_TYPE_FIX.equals(dataAccount.getPackageType())){
            //固定套餐是基于次数的
            dataAccount.setTotalNum(rechargeDTO.getTotalNum());
            dataAccount.setLeftNum(rechargeDTO.getTotalNum());
            dataAccount.setDataAcctType(Constants.Bill.DATA_ACCT_TYPE_NUM);
        }else if(Constants.Bill.PACKAGE_TYPE_CUSTOM.equals(dataAccount.getPackageType())){
            //自定义套餐是基于次数的
            dataAccount.setTotalNum(rechargeDTO.getTotalNum());
            dataAccount.setLeftNum(rechargeDTO.getTotalNum());
            dataAccount.setDataAcctType(Constants.Bill.DATA_ACCT_TYPE_NUM);
        }else if(Constants.Bill.PACKAGE_TYPE_MIX.equals(dataAccount.getPackageType())){
            //跨类套餐是基于金额的，rechargeDTO中的单位是分，DataAccount单位是厘，要乘以10
            dataAccount.setTotalMoney(rechargeDTO.getTotalMoney()*10);
            dataAccount.setLeftMoney(rechargeDTO.getTotalMoney()*10);
            dataAccount.setDataAcctType(Constants.Bill.DATA_ACCT_TYPE_MONEY);
        }else {
            logger.error("不支持的套餐类型packageType="+dataAccount.getPackageType());
            throw new BusinessException("不支持的套餐类型packageType="+dataAccount.getPackageType());
        }

        //有有效期类型的账户，需设置生效开始时间和生效结束时间
        if(Constants.Bill.DATA_ACCT_PERIOD_VALID.equals(rechargeDTO.getPeriodType())){
            dataAccount.setStartDate(rechargeDTO.getStartDate());
            dataAccount.setEndDate(rechargeDTO.getEndDate());
        }

        if(dataAccountMapper.insertSelective(dataAccount) <= 0){
            logger.error("创建数据账号异常 rechargeDTO:"+ JSON.toJSONString(rechargeDTO));
            throw new BusinessException("创建数据账号异常");
        }
        dataAccountHis = dataAccount;

        //保存到历史记录表
        saveDataAccountHis(dataAccountHis,"创建数据账户");

        return dataAccount.getDataAcctId();
    }

    @Override
    public void dealDisableDataAccount(DataAccountDTO dataAccountDTO) throws BusinessException {
        DataAccount dataAccount = new DataAccount();
        dataAccount.setDataAcctId(dataAccountDTO.getDataAcctId());
        dataAccount.setDataAcctStatus(Constants.Bill.DATA_ACCT_STATUS_INVALID);
        dataAccount.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        dataAccount.setUpdateStaff(dataAccountDTO.getCurrentUserId());

        DataAccountExample dataAccountExample = new DataAccountExample();
        DataAccountExample.Criteria updateCriteria = dataAccountExample.createCriteria();
        updateCriteria.andDataAcctIdEqualTo(dataAccountDTO.getDataAcctId());
        updateCriteria.andDataAcctStatusEqualTo(Constants.Bill.DATA_ACCT_STATUS_OK);

        if(dataAccountMapper.updateByExampleSelective(dataAccount,dataAccountExample) <= 0){
            String errorMsg = "找不到id:"+dataAccountDTO.getDataAcctId()+" 状态是:"+Constants.Bill.DATA_ACCT_STATUS_OK+" 的记录。";
            logger.error(errorMsg);
            throw new BusinessException(errorMsg);
        }else{
            DataAccount dataAccountHis = dataAccountMapper.selectByPrimaryKey(dataAccountDTO.getDataAcctId());
            saveDataAccountHis(dataAccountHis,"后台人员操作订单失效。");
        }
    }

    @Override
    public void dealEnableDataAccount(DataAccountDTO dataAccountDTO) throws BusinessException {
        DataAccount dataAccount = new DataAccount();
        dataAccount.setDataAcctId(dataAccountDTO.getDataAcctId());
        dataAccount.setDataAcctStatus(Constants.Bill.DATA_ACCT_STATUS_OK);
        dataAccount.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        dataAccount.setUpdateStaff(dataAccountDTO.getCurrentUserId());

        DataAccountExample dataAccountExample = new DataAccountExample();
        DataAccountExample.Criteria updateCriteria = dataAccountExample.createCriteria();
        updateCriteria.andDataAcctIdEqualTo(dataAccountDTO.getDataAcctId());
        updateCriteria.andDataAcctStatusEqualTo(Constants.Bill.DATA_ACCT_STATUS_INVALID);

        if(dataAccountMapper.updateByExampleSelective(dataAccount,dataAccountExample) <= 0){
            String errorMsg = "找不到id:"+dataAccountDTO.getDataAcctId()+" 状态不是:"+Constants.Bill.DATA_ACCT_STATUS_OK+" 的记录。";
            logger.error(errorMsg);
            throw new BusinessException(errorMsg);
        }else{
            DataAccount dataAccountHis = dataAccountMapper.selectByPrimaryKey(dataAccountDTO.getDataAcctId());
            saveDataAccountHis(dataAccountHis,"后台人员操作订单生效。");
        }
    }

    @Override
    public List<DataAccountDTO> queryDataAccountListByOption(DataAccountDTO dataAccountDTO) throws BusinessException{
        DataAccountExample dataAccountExample = new DataAccountExample();
        DataAccountExample.Criteria criteria = dataAccountExample.createCriteria();

        if(dataAccountDTO.getDataAcctId() !=null && !new Integer(0).equals(dataAccountDTO.getDataAcctId())){
            DataAccount singleDataAccount = dataAccountMapper.selectByPrimaryKey(dataAccountDTO.getDataAcctId());
            return Arrays.asList(ModelDTOConvertUtil.createCopyObject(singleDataAccount,DataAccountDTO.class,null,false));
        }

        if(!StringUtil.isBlank(dataAccountDTO.getUserId())){
            criteria.andUserIdEqualTo(dataAccountDTO.getUserId());
        }
        if(!StringUtil.isBlank(dataAccountDTO.getServiceId())){
            criteria.andServiceIdEqualTo(dataAccountDTO.getServiceId());
        }
        if(!StringUtil.isBlank(dataAccountDTO.getDataAcctType())){
            criteria.andDataAcctTypeEqualTo(dataAccountDTO.getDataAcctType());
        }

        if(!StringUtil.isBlank(dataAccountDTO.getPackageType())){
            criteria.andPackageTypeEqualTo(dataAccountDTO.getPackageType());
        }

        if(dataAccountDTO.getCreateStart() != null){
            criteria.andCreateTimeGreaterThanOrEqualTo(dataAccountDTO.getCreateStart());
        }

        if(dataAccountDTO.getCreateEnd() != null){
            criteria.andCreateTimeLessThanOrEqualTo(dataAccountDTO.getCreateEnd());
        }

        if(!StringUtil.isBlank(dataAccountDTO.getCreateStaff())){
            criteria.andCreateStaffEqualTo(dataAccountDTO.getCreateStaff());
        }

        List<DataAccount> dataAccountList = dataAccountMapper.selectByExample(dataAccountExample);

        return ModelDTOConvertUtil.convertModelList2DTOList(dataAccountList,DataAccountDTO.class,null,false);

    }

    @Override
    public DataAccountDTO queryDataAccountById(long dataAcctId) throws BusinessException {
        DataAccount resultDataAccount = dataAccountMapper.selectByPrimaryKey(dataAcctId);
        return ModelDTOConvertUtil.createCopyObject(resultDataAccount,DataAccountDTO.class,null,false);
    }

    @Override
    public PageResponseDTO<DataAccountRespDTO> queryDataAccountStatisticPageByOption(DataAccountDTO dataAccountReqDTO) throws BusinessException {

        DataAccountExample dataAccountExample = new DataAccountExample();
        DataAccountExample.Criteria criteria = dataAccountExample.createCriteria();
        if(!StringUtil.isBlank(dataAccountReqDTO.getUserId())){
            criteria.andUserIdEqualTo(dataAccountReqDTO.getUserId());
        }

        if(!StringUtil.isBlank(dataAccountReqDTO.getPackageType())){
            criteria.andPackageTypeEqualTo(dataAccountReqDTO.getPackageType());
        }
        if(!CollectionUtil.isEmpty(dataAccountReqDTO.getPackageTypeList())){
            criteria.andPackageTypeIn(dataAccountReqDTO.getPackageTypeList());
        }

        if(dataAccountReqDTO.getCreateStart() != null){
            criteria.andCreateTimeGreaterThanOrEqualTo(dataAccountReqDTO.getCreateStart());
        }

        if(dataAccountReqDTO.getCreateEnd() != null){
            criteria.andCreateTimeLessThanOrEqualTo(dataAccountReqDTO.getCreateEnd());
        }

        if(!StringUtil.isBlank(dataAccountReqDTO.getCreateStaff())){
            criteria.andCreateStaffEqualTo(dataAccountReqDTO.getCreateStaff());
        }

        PageHelper.startPage(dataAccountReqDTO.getPageNo(),dataAccountReqDTO.getPageSize());

        List<DataAccount> resultList = manualDataAccountMapper.queryStatisticByExample(dataAccountExample);

        if(CollectionUtil.isEmpty(resultList)){
            return null;
        }else{
            PageResponseDTO<DataAccountRespDTO> respPageDTO = PageResponseFactory.genPageResponse(new PageInfo(resultList),DataAccountRespDTO.class);
            return respPageDTO;
        }
    }

    private DataAccount getDefaultNewDataAccount(){
        DataAccount dataAccount = new DataAccount();
        dataAccount.setDataAcctId(SeqUtil.getLong("SEQ_DATA_ACCOUNT"));
        dataAccount.setTotalMoney(0);
        dataAccount.setLeftMoney(0);
        dataAccount.setTotalConsumeMoney(0);
        dataAccount.setTotalNum(0);
        dataAccount.setLeftNum(0);
        dataAccount.setTotalConsumeNum(0);
        dataAccount.setDataAcctStatus(Constants.Bill.DATA_ACCT_STATUS_OK);
        dataAccount.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return dataAccount;
    }

    /**
     * 保存dataAccount变更记录
     * @param dataAccount
     * @param remark
     */
    private void saveDataAccountHis(DataAccount dataAccount,String remark){
        DataAccountHis dataAccountHis = new DataAccountHis();

        ObjectCopyUtil.copyObjValue(dataAccount,dataAccountHis,null,false);
        dataAccountHis.setHisId(SeqUtil.getLong("SEQ_DATA_ACCOUNT_HIS"));
        dataAccountHis.setRemark(remark);
        dataAccountHis.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if(dataAccount.getUpdateStaff() == null){
            dataAccountHis.setCreateStaff(dataAccount.getCreateStaff());
        }else{
            dataAccount.setCreateStaff(dataAccount.getUpdateStaff());
        }

        dataAccountHisMapper.insertSelective(dataAccountHis);
    }
}
