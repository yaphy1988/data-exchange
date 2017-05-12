package com.ai.bdex.dataexchange.aipcenter.service.impl;


import com.ai.bdex.dataexchange.aipcenter.dao.mapper.DataAccountMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.mapper.RechargeRecordMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.*;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeReqDTO;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipCenterDataAccountSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IRechargeSV;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.ObjectCopyUtil;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private IAipCenterDataAccountSV aipCenterDataAccountSV;

    @Override
    public void recharge(List<RechargeReqDTO> rechargeDTOList) throws BusinessException {
        if(CollectionUtil.isEmpty(rechargeDTOList)){
            throw new BusinessException("充值列表为空！");
        }

        for(RechargeReqDTO rechargeDTO:rechargeDTOList){
            recharge(rechargeDTO);
        }
    }

    @Override
    public List<RechargeDTO> queryRechargeRecordListByOption(RechargeReqDTO rechargeDTO) throws BusinessException{
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

    private void recharge(RechargeReqDTO rechargeDTO) throws BusinessException{
        checkRecharge(rechargeDTO);

        //先记录充值信息，同时也是进行去重处理
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setRechargeReqId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+SeqUtil.getString("SEQ_RECHARGE_RECORD",8));
        ObjectCopyUtil.copyObjValue(rechargeDTO,rechargeRecord,null,false);
        rechargeRecord.setRechargeStatus(Constants.Bill.RECHARGE_STATUS_GOING);
        rechargeRecord.setCreateStaff(rechargeDTO.getCurrentUserId());
        rechargeRecord.setCreateTime(new Date());
        rechargeRecordMapper.insertSelective(rechargeRecord);

        //创建数据账户
        long newDataAcctId = aipCenterDataAccountSV.createDataAccount(rechargeDTO);

        //将数据账户信息更新到充值记录
        RechargeRecord updateRechargeRecord = new RechargeRecord();
        updateRechargeRecord.setRechargeReqId(rechargeRecord.getRechargeReqId());
        updateRechargeRecord.setDataAccountId(newDataAcctId);
        updateRechargeRecord.setRechargeStatus(Constants.Bill.RECHARGE_STATUS_SUCCESS);
        updateRechargeRecord.setUpdateStaff(rechargeDTO.getCurrentUserId());
        updateRechargeRecord.setUpdateTime(new Date());
        rechargeRecordMapper.updateByPrimaryKeySelective(updateRechargeRecord);

    }

    private void checkRecharge(RechargeReqDTO rechargeDTO) throws BusinessException{
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
        if(Constants.Bill.RECHARGE_TYPE_NUM.equals(rechargeDTO.getRechargeType())&&(rechargeDTO.getTotalNum() == null || rechargeDTO.getTotalNum() <= 0)){
            logger.error("充值类型为次数时,totalNum必须大于0!");
            throw new BusinessException("充值类型为次数时,totalNum必须大于0!");
        }
        if(Constants.Bill.RECHARGE_TYPE_MONEY.equals(rechargeDTO.getRechargeType())&&(rechargeDTO.getTotalMoney() == null || rechargeDTO.getTotalMoney() <= 0)){
            logger.error("充值类型为金额时,totalMoney必须大于0!");
            throw new BusinessException("充值类型为金额时,totalMoney必须大于0!");
        }
        if(StringUtil.isBlank(rechargeDTO.getPeriodType())){
            logger.error("有效期类型不能为空!");
            throw new BusinessException("有效期类型不能为空!");
        }
        if(rechargeDTO.getPeriodType().equals(Constants.Bill.DATA_ACCT_PERIOD_VALID)){
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
}
