package com.ai.bdex.dataexchange.aipcenter.service.impl;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.DataAccountHisMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.mapper.DataAccountMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.mapper.ManualDataAccountMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.mapper.RechargeRecordMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.*;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IRechargeSV;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.util.ModelDTOConvertUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.ObjectCopyUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by fangyunfeng on 2017/5/4.
 */
@Service("rechargeSV")
public class RechargeSVImpl implements IRechargeSV {

    private static Logger logger = LoggerFactory.getLogger(RechargeSVImpl.class);

    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;

    @Autowired
    private DataAccountMapper dataAccountMapper;

    @Autowired
    private DataAccountHisMapper dataAccountHisMapper;

    @Override
    public void recharge(List<RechargeDTO> rechargeDTOList) throws BusinessException {
        if(CollectionUtil.isEmpty(rechargeDTOList)){
            throw new BusinessException("充值列表为空！");
        }

        for(RechargeDTO rechargeDTO:rechargeDTOList){
            recharge(rechargeDTO);
        }
    }

    public DataAccountDTO queryDataAccountByOrderInfo(String subOrder) throws BusinessException{
        if(StringUtil.isBlank(subOrder)){
            return null;
        }
        RechargeDTO rechargeDTO = new RechargeDTO();
        rechargeDTO.setSubOrder(subOrder);

        List<DataAccountDTO> dataAccountDTOList = queryDataAccountByRechargInfo(rechargeDTO);
        if(!CollectionUtil.isEmpty(dataAccountDTOList)){
            return dataAccountDTOList.get(0);
        }
        return null;
    }


    public List<DataAccountDTO> queryDataAccountByRechargInfo(RechargeDTO rechargeDTO) throws BusinessException {

        List<RechargeDTO> rechargeDTOS = queryRechargeRecordListByOption(rechargeDTO);
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

    public List<RechargeDTO> queryRechargeRecordListByOption(RechargeDTO rechargeDTO){
        RechargeRecordExample rechargeRecordExample = new RechargeRecordExample();
        RechargeRecordExample.Criteria criteria = rechargeRecordExample.createCriteria();
        if(!StringUtil.isBlank(rechargeDTO.getRechargeUserId())){
            criteria.andRechargeUserIdEqualTo(rechargeDTO.getRechargeUserId());
        }
        if(!StringUtil.isBlank(rechargeDTO.getOrderId())){
            criteria.andOrderIdEqualTo(rechargeDTO.getOrderId());
        }
        if(!StringUtil.isBlank(rechargeDTO.getSubOrder())){
            criteria.andSubOrderEqualTo(rechargeDTO.getSubOrder());
        }
        if(!StringUtil.isBlank(rechargeDTO.getServiceId())){
            criteria.andServiceIdEqualTo(rechargeDTO.getServiceId());
        }

        List<RechargeRecord> resultList = rechargeRecordMapper.selectByExample(rechargeRecordExample);
        if(CollectionUtil.isEmpty(resultList)){
            return null;
        }else{
            List<RechargeDTO> dtos = new ArrayList<RechargeDTO>();
            for(RechargeRecord rechargeRecord:resultList){
                RechargeDTO rechargeDTO1 = new RechargeDTO();
                ObjectCopyUtil.copyObjValue(rechargeRecord,rechargeDTO1,null,false);
                dtos.add(rechargeDTO1);
            }
            return dtos;
        }
    }

    private void recharge(RechargeDTO rechargeDTO) throws BusinessException{
        checkRecharge(rechargeDTO);

        //先记录充值信息，同时也是进行去重处理
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setRechargeReqId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+SeqUtil.getString("SEQ_RECHARGE_RECORD",8));
        ObjectCopyUtil.copyObjValue(rechargeDTO,rechargeRecord,null,false);
        rechargeRecord.setRechargeStatus("1");
        rechargeRecord.setCreateStaff(rechargeDTO.getCurrentUserId());
        rechargeRecord.setCreateTime(new Date());
        rechargeRecordMapper.insertSelective(rechargeRecord);

        //创建数据账户
        long newDataAcctId = createDataAccount(rechargeDTO);

        //将数据账户信息更新到充值记录
        RechargeRecord updateRechargeRecord = new RechargeRecord();
        updateRechargeRecord.setRechargeReqId(rechargeRecord.getRechargeReqId());
        updateRechargeRecord.setDataAccountId(newDataAcctId);
        updateRechargeRecord.setRechargeStatus("2");
        updateRechargeRecord.setUpdateStaff(rechargeDTO.getCurrentUserId());
        updateRechargeRecord.setUpdateTime(new Date());
        rechargeRecordMapper.updateByPrimaryKeySelective(updateRechargeRecord);

    }

    /**
     * 根据充值记录创建数据账户
     * @param rechargeDTO
     * @throws BusinessException
     */
    private long createDataAccount(RechargeDTO rechargeDTO) throws BusinessException{
        DataAccount dataAccountHis = null;

        DataAccount dataAccount = getDefaultNewDataAccount();
        dataAccount.setUserId(rechargeDTO.getRechargeUserId());
        dataAccount.setDataAcctType(rechargeDTO.getRechargeType());
        dataAccount.setPeriodType(rechargeDTO.getPeriodType());
        dataAccount.setCreateStaff(rechargeDTO.getCurrentUserId());
        dataAccount.setCreateTime(new Date());
        //充值类型为次数，直接创建新的账户
        if("1".equals(rechargeDTO.getRechargeType())){
            dataAccount.setTotalNum(rechargeDTO.getTotalNum());
        }else{
            dataAccount.setTotalMoney(rechargeDTO.getTotalMoney());
        }

        if(dataAccountMapper.insertSelective(dataAccount) <= 0){
            logger.error("创建数据账号异常 rechargeDTO:"+ JSON.toJSONString(rechargeDTO));
            throw new BusinessException("创建数据账号异常");
        }
        dataAccountHis = dataAccount;

        //保存到历史记录表
        saveDataAccountHis(dataAccountHis,"用户下单");

        return dataAccount.getDataAcctId();
    }

    private List<DataAccountDTO> queryDataAccountListByOption(DataAccountDTO dataAccountDTO){
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

        List<DataAccount> dataAccountList = dataAccountMapper.selectByExample(dataAccountExample);

        return ModelDTOConvertUtil.convertModelList2DTOList(dataAccountList,DataAccountDTO.class,null,false);

    }

    private void checkRecharge(RechargeDTO rechargeDTO) throws BusinessException{
        if(rechargeDTO == null){
            logger.error("入参对象为空!");
            throw new BusinessException("入参对象为空!");
        }
        if(StringUtil.isBlank(rechargeDTO.getRechargeUserId())){
            logger.error("用户id不能为空!");
            throw new BusinessException("用户id不能为空!");
        }
        if(StringUtil.isBlank(rechargeDTO.getRechargeType())){
            logger.error("充值类型不能为空!");
            throw new BusinessException("充值类型不能为空!");
        }
        if(StringUtil.isBlank(rechargeDTO.getPeriodType())){
            logger.error("有效期类型不能为空!");
            throw new BusinessException("有效期类型不能为空!");
        }
        if(rechargeDTO.getPeriodType().equals("1")){
            if(rechargeDTO.getStartDate() == null){
                logger.error("非永久有效类型，生效开始日期不能为空!");
                throw new BusinessException("非永久有效类型，生效开始日期不能为空!");
            }
            if(rechargeDTO.getEndDate() == null){
                logger.error("非永久有效类型，生效结束日期不能为空!");
                throw new BusinessException("非永久有效类型，生效结束日期不能为空!");
            }
            if(StringUtil.isBlank(rechargeDTO.getServiceId())){
                logger.error("非永久有效类型，服务编码不能为空!");
                throw new BusinessException("非永久有效类型，服务编码不能为空!");
            }
        }
        if(StringUtil.isBlank(rechargeDTO.getSubOrder())){
            logger.error("子订单编码不能为空!");
            throw new BusinessException("子订单编码不能为空!");
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
        dataAccount.setDataAcctStatus("1");
        dataAccount.setCreateTime(new Date());

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
        dataAccountHis.setCreateTime(new Date());
        if(dataAccount.getUpdateStaff() == null){
            dataAccountHis.setCreateStaff(dataAccount.getCreateStaff());
        }else{
            dataAccount.setCreateStaff(dataAccount.getUpdateStaff());
        }

        dataAccountHisMapper.insertSelective(dataAccountHis);
    }

}
