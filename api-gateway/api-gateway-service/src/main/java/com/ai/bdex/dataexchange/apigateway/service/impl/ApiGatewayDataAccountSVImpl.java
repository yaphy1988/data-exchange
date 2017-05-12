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
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiGatewayDataAccountSV;
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
    public String dataCharge(DataConsumeDTO consumeDTO) throws BusinessException {
        //参数合法性检查
        checkDataConsumeParams(consumeDTO);

        //查询所有可用数据账户
        List<DataAccount> dataAccountList = queryAvailableDataAccountList(consumeDTO);
        if(CollectionUtil.isEmpty(dataAccountList)){
            logger.info("no available data account for user_id:"+consumeDTO.getUserId()+",service_id:"+consumeDTO.getRealServiceId());
            return Constants.Bill.CHARGE_RESULE_NA;
        }

        DataAccount dataAccountSelected = null;
        String result = Constants.Bill.CHARGE_RESULE_NA;
        //扣减账户
        for(DataAccount dataAccount : dataAccountList){
            if(Constants.Bill.DATA_ACCT_TYPE_NUM.equals(dataAccount.getDataAcctType())){
                if(manualDataAccountMapper.updateByConsumeNum(dataAccount,consumeDTO.getConsumeNum()) > 0){
                    dataAccountSelected = dataAccount;
                    result = Constants.Bill.CHARGE_RESULE_OK;
                    break;
                }
            }else{
                if(manualDataAccountMapper.updateByConsumeMoney(dataAccount,consumeDTO.getConsumeMoney()) > 0){
                    dataAccountSelected = dataAccount;
                    result = Constants.Bill.CHARGE_RESULE_OK;
                    break;
                }
            }
        }


        if(Constants.Bill.CHARGE_RESULE_OK.equals(result)){
            //保存扣减历史
            if(Constants.Bill.DATA_ACCT_TYPE_NUM.equals(dataAccountSelected.getDataAcctType())){
                dataAccountSelected.setLeftNum(dataAccountSelected.getLeftNum() - consumeDTO.getConsumeNum());
                dataAccountSelected.setTotalConsumeNum(dataAccountSelected.getTotalConsumeNum() - consumeDTO.getConsumeNum());
            }else{
                dataAccountSelected.setLeftMoney(dataAccountSelected.getLeftMoney() - consumeDTO.getConsumeMoney());
                dataAccountSelected.setTotalConsumeMoney(dataAccountSelected.getTotalConsumeMoney() - consumeDTO.getConsumeMoney());
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
            billDetail.setRealServiceId(consumeDTO.getRealServiceId());
            if(Constants.Bill.DATA_ACCT_TYPE_NUM.equals(dataAccountSelected.getDataAcctType())){
                billDetail.setConsumeNum(consumeDTO.getConsumeNum());
            }else {
                billDetail.setConsumeMoney(consumeDTO.getConsumeMoney());
            }
            billDetail.setInvokeSeq(consumeDTO.getInvokeSeq());
            billDetail.setConsumeTime(new Date());
            billDetailMapper.insertSelective(billDetail);
        }
        return result;
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

        //查找次数类型账户，次数类型账户都是有有效期的，且具体到特定的服务编码
        DataAccountExample.Criteria criteria1 = dataAccountExample.createCriteria();
        criteria1.andUserIdEqualTo(consumeDTO.getUserId());
        criteria1.andDataAcctTypeEqualTo(Constants.Bill.DATA_ACCT_TYPE_NUM);
        criteria1.andPeriodTypeEqualTo(Constants.Bill.DATA_ACCT_PERIOD_VALID);
        criteria1.andServiceIdEqualTo(consumeDTO.getRealServiceId());
        criteria1.andStartDateLessThanOrEqualTo(now);
        criteria1.andEndDateGreaterThanOrEqualTo(now);
        criteria1.andLeftNumGreaterThanOrEqualTo(consumeDTO.getConsumeNum());

        //查找余额类型账户，余额类型账户是没有有效期的，且对所有接口有效
        DataAccountExample.Criteria criteria2 = dataAccountExample.createCriteria();
        criteria2.andUserIdEqualTo(consumeDTO.getUserId());
        criteria2.andDataAcctTypeEqualTo(Constants.Bill.DATA_ACCT_TYPE_MONEY);
        criteria2.andPeriodTypeEqualTo(Constants.Bill.DATA_ACCT_PERIOD_PERMANENT);
        criteria2.andLeftMoneyGreaterThanOrEqualTo(consumeDTO.getConsumeMoney());

        //把次数账户排在前面，相同有效账户则按创建时间排序
        dataAccountExample.setOrderByClause("data_acct_type,create_time");

        return dataAccountMapper.selectByExample(dataAccountExample);
    }
}
