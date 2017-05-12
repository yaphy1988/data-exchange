package com.ai.bdex.dataexchange.aipcenter.service.impl;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.DataAccountHisMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.mapper.DataAccountMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.DataAccount;
import com.ai.bdex.dataexchange.aipcenter.dao.model.DataAccountExample;
import com.ai.bdex.dataexchange.aipcenter.dao.model.DataAccountHis;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeReqDTO;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipCenterDataAccountSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IRechargeSV;
import com.ai.bdex.dataexchange.constants.Constants;
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
        dataAccount.setDataAcctType(rechargeDTO.getRechargeType());
        dataAccount.setPeriodType(rechargeDTO.getPeriodType());
        dataAccount.setCreateStaff(rechargeDTO.getCurrentUserId());

        dataAccount.setCreateTime(new Date());
        //充值类型为次数，直接创建新的账户
        if(Constants.Bill.RECHARGE_TYPE_NUM.equals(rechargeDTO.getRechargeType())){
            dataAccount.setTotalNum(rechargeDTO.getTotalNum());
        }else{
            dataAccount.setTotalMoney(rechargeDTO.getTotalMoney());
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
