package com.ai.bdex.dataexchange.apigateway.service.impl;

import com.ai.bdex.dataexchange.apigateway.dao.mapper.BillDetailMapper;
import com.ai.bdex.dataexchange.apigateway.dao.mapper.DataAccountHisMapper;
import com.ai.bdex.dataexchange.apigateway.dao.mapper.DataAccountMapper;
import com.ai.bdex.dataexchange.apigateway.dao.mapper.ManualDataAccountMapper;
import com.ai.bdex.dataexchange.apigateway.dao.model.BillDetail;
import com.ai.bdex.dataexchange.apigateway.dao.model.DataAccount;
import com.ai.bdex.dataexchange.apigateway.dao.model.DataAccountExample;
import com.ai.bdex.dataexchange.apigateway.dao.model.DataAccountHis;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DataConsumeDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DataConsumeRespDTO;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiGatewayDataAccountSV;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.exception.BusinessException;
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
import java.util.Date;
import java.util.List;

/**
 * Created by fangyunfeng on 2017/5/10.
 */
@Service("apiGatewayDataAccountSV")
public class ApiGatewayDataAccountSVImpl implements IApiGatewayDataAccountSV {
    private static Logger logger = LoggerFactory.getLogger(ApiGatewayDataAccountSVImpl.class);

    @Autowired
    private DataAccountMapper dataAccountMapper;

    @Autowired
    private DataAccountHisMapper dataAccountHisMapper;

    @Autowired
    private BillDetailMapper billDetailMapper;

    @Autowired
    private ManualDataAccountMapper manualDataAccountMapper;

    @Override
    public DataConsumeRespDTO dealDataCharge(DataConsumeDTO consumeDTO) throws BusinessException {
        DataConsumeRespDTO respDTO = new DataConsumeRespDTO();
        respDTO.setResult(Constants.Bill.CHARGE_RESULE_NA);
        //参数合法性检查
        checkDataConsumeParams(consumeDTO);

        //查询所有可用数据账户
        List<DataAccount> dataAccountList = queryAvailableDataAccountList(consumeDTO);
        if(CollectionUtil.isEmpty(dataAccountList)){
            logger.info("no available data account for user_id:"+consumeDTO.getUserId()+",service_id:"+consumeDTO.getRealServiceId());
            return respDTO;
        }

        DataAccount dataAccountSelected = null;
        //扣减账户
        for(DataAccount dataAccount : dataAccountList){
            if(Constants.Bill.PACKAGE_TYPE_FIX.equals(dataAccount.getPackageType())){
                //10 固定套餐基于次数进行扣减
                if(dataAccount.getLeftNum()>consumeDTO.getConsumeNum()){
                    if(manualDataAccountMapper.updateByConsumeNum(dataAccount,consumeDTO.getConsumeNum()) > 0){
                        dataAccountSelected = dataAccount;
                        respDTO.setResult(Constants.Bill.CHARGE_RESULE_OK);
                        break;
                    }
                }
            }else if(Constants.Bill.PACKAGE_TYPE_CUSTOM.equals(dataAccount.getPackageType())){
                //20 自定义套餐基于次数进行扣减
                if(dataAccount.getLeftNum()>consumeDTO.getConsumeNum()){
                    if(manualDataAccountMapper.updateByConsumeNum(dataAccount,consumeDTO.getConsumeNum()) > 0){
                        dataAccountSelected = dataAccount;
                        respDTO.setResult(Constants.Bill.CHARGE_RESULE_OK);
                        break;
                    }
                }
            }else if(Constants.Bill.PACKAGE_TYPE_MIX.equals(dataAccount.getPackageType())){
                //30 跨类套餐基于金额进行扣减
                if(manualDataAccountMapper.updateByConsumeMoney(dataAccount,consumeDTO.getConsumeMoney()) > 0){
                    dataAccountSelected = dataAccount;
                    respDTO.setResult(Constants.Bill.CHARGE_RESULE_OK);
                    break;
                }
            }
        }


        if(Constants.Bill.CHARGE_RESULE_OK.equals(respDTO.getResult())){
            respDTO.setDataAccountId(dataAccountSelected.getDataAcctId());
            respDTO.setOrderId(dataAccountSelected.getOrderId());
            respDTO.setSubOrder(dataAccountSelected.getSubOrder());
            respDTO.setPackageType(dataAccountSelected.getPackageType());

            //保存扣减历史
            if(Constants.Bill.DATA_ACCT_TYPE_NUM.equals(dataAccountSelected.getDataAcctType())){
                dataAccountSelected.setLeftNum(dataAccountSelected.getLeftNum() - consumeDTO.getConsumeNum());
                dataAccountSelected.setTotalConsumeNum(dataAccountSelected.getTotalConsumeNum() - consumeDTO.getConsumeNum());
                respDTO.setLeftNum(dataAccountSelected.getLeftNum());
            }else{
                dataAccountSelected.setLeftMoney(dataAccountSelected.getLeftMoney() - consumeDTO.getConsumeMoney());
                dataAccountSelected.setTotalConsumeMoney(dataAccountSelected.getTotalConsumeMoney() - consumeDTO.getConsumeMoney());
                respDTO.setLeftMoney(dataAccountSelected.getLeftMoney());
            }
            DataAccountHis dataAccountHis = new DataAccountHis();
            ObjectCopyUtil.copyObjValue(dataAccountSelected,dataAccountHis,null,false);
            dataAccountHis.setHisId(SeqUtil.getLong("SEQ_DATA_ACCOUNT_HIS"));
            dataAccountHis.setRemark(consumeDTO.getRealServiceId()+"接口调用");
            dataAccountHisMapper.insertSelective(dataAccountHis);

            //生成账单
            BillDetail billDetail = new BillDetail();
            billDetail.setBillId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+SeqUtil.getString("SEQ_BILL_DETAIL",8));
            billDetail.setUserId(consumeDTO.getUserId());
            billDetail.setDataAcctId(dataAccountSelected.getDataAcctId());
            billDetail.setDataAcctType(dataAccountSelected.getDataAcctType());
            billDetail.setPackageType(dataAccountSelected.getPackageType());
            billDetail.setRealServiceId(consumeDTO.getRealServiceId());
            if(Constants.Bill.DATA_ACCT_TYPE_NUM.equals(dataAccountSelected.getDataAcctType())){
                billDetail.setConsumeNum(consumeDTO.getConsumeNum());
            }else {
                billDetail.setConsumeMoney(consumeDTO.getConsumeMoney());
            }
            billDetail.setInvokeSeq(consumeDTO.getInvokeSeq());
            billDetail.setConsumeTime(new Date());
            if(billDetailMapper.insertSelective(billDetail)>0){
                respDTO.setBillId(billDetail.getBillId());
            }else{
                logger.error("生成账单失败，账单信息："+ JSON.toJSONString(billDetail));
                throw new BusinessException("生成账单失败，账单id:"+billDetail.getBillId());
            }
        }
        return respDTO;
    }


    private void checkDataConsumeParams(DataConsumeDTO consumeDTO) throws BusinessException{
        if(consumeDTO == null){
            logger.error("consumeDTO 不能为空!");
            throw new BusinessException("consumeDTO 不能为空!");
        }
        if(StringUtil.isBlank(consumeDTO.getUserId())){
            logger.error("用户id不能为空!");
            throw new BusinessException("用户id不能为空!");
        }
        if(StringUtil.isBlank(consumeDTO.getUserId())){
            logger.error("用户id不能为空!");
            throw new BusinessException("用户id不能为空!");
        }
        if(StringUtil.isBlank(consumeDTO.getRealServiceId())){
            logger.error("服务编码不能为空!");
            throw new BusinessException("服务编码不能为空!");
        }
        if(StringUtil.isBlank(consumeDTO.getInvokeSeq())){
            logger.error("调用流水不能为空!");
            throw new BusinessException("调用流水不能为空!");
        }
    }

    private List<DataAccount> queryAvailableDataAccountList(DataConsumeDTO consumeDTO){

        Date now = new Date();
        DataAccountExample dataAccountExample = new DataAccountExample();

        //获取固定套餐，有指定有效期的数据
        DataAccountExample.Criteria fixValidCriteria = dataAccountExample.createCriteria();
        fixValidCriteria.andUserIdEqualTo(consumeDTO.getUserId());
        fixValidCriteria.andPackageTypeEqualTo(Constants.Bill.PACKAGE_TYPE_FIX);
        fixValidCriteria.andPeriodTypeEqualTo(Constants.Bill.DATA_ACCT_PERIOD_VALID);
        fixValidCriteria.andServiceIdEqualTo(consumeDTO.getRealServiceId());
        fixValidCriteria.andStartDateLessThanOrEqualTo(now);
        fixValidCriteria.andEndDateGreaterThanOrEqualTo(now);

        //获取固定套餐，永久有效的数据
        DataAccountExample.Criteria fixPermanentCriteria = dataAccountExample.or();
        fixPermanentCriteria.andUserIdEqualTo(consumeDTO.getUserId());
        fixPermanentCriteria.andPackageTypeEqualTo(Constants.Bill.PACKAGE_TYPE_FIX);
        fixPermanentCriteria.andPeriodTypeEqualTo(Constants.Bill.DATA_ACCT_PERIOD_PERMANENT);
        fixPermanentCriteria.andServiceIdEqualTo(consumeDTO.getRealServiceId());

        //获取自定义套餐，有指定有效期的数据
        DataAccountExample.Criteria customValidCriteria = dataAccountExample.or();
        customValidCriteria.andUserIdEqualTo(consumeDTO.getUserId());
        customValidCriteria.andPackageTypeEqualTo(Constants.Bill.PACKAGE_TYPE_CUSTOM);
        customValidCriteria.andPeriodTypeEqualTo(Constants.Bill.DATA_ACCT_PERIOD_VALID);
        customValidCriteria.andServiceIdEqualTo(consumeDTO.getRealServiceId());
        customValidCriteria.andStartDateLessThanOrEqualTo(now);
        customValidCriteria.andEndDateGreaterThanOrEqualTo(now);

        //获取自定义套餐，永久有效的数据
        DataAccountExample.Criteria customPermanentCriteria = dataAccountExample.or();
        customPermanentCriteria.andUserIdEqualTo(consumeDTO.getUserId());
        customPermanentCriteria.andPackageTypeEqualTo(Constants.Bill.PACKAGE_TYPE_CUSTOM);
        customPermanentCriteria.andPeriodTypeEqualTo(Constants.Bill.DATA_ACCT_PERIOD_PERMANENT);
        customPermanentCriteria.andServiceIdEqualTo(consumeDTO.getRealServiceId());

        //获取跨类套餐，有指定有效期的数据
        DataAccountExample.Criteria mixValidCriteria = dataAccountExample.or();
        mixValidCriteria.andUserIdEqualTo(consumeDTO.getUserId());
        mixValidCriteria.andPackageTypeEqualTo(Constants.Bill.PACKAGE_TYPE_MIX);
        mixValidCriteria.andPeriodTypeEqualTo(Constants.Bill.DATA_ACCT_PERIOD_VALID);
        mixValidCriteria.andStartDateLessThanOrEqualTo(now);
        mixValidCriteria.andEndDateGreaterThanOrEqualTo(now);

        //获取跨类套餐，永久有效的数据
        DataAccountExample.Criteria mixtomPermanentCriteria = dataAccountExample.or();
        mixtomPermanentCriteria.andUserIdEqualTo(consumeDTO.getUserId());
        mixtomPermanentCriteria.andPackageTypeEqualTo(Constants.Bill.PACKAGE_TYPE_MIX);
        mixtomPermanentCriteria.andPeriodTypeEqualTo(Constants.Bill.DATA_ACCT_PERIOD_PERMANENT);

        //把次数账户排在前面，相同有效账户则按创建时间排序
        dataAccountExample.setOrderByClause("period_type asc,end_date");

        return dataAccountMapper.selectByExample(dataAccountExample);
    }
}
