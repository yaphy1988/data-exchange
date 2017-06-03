package com.ai.bdex.dataexchange.tradecenter.job;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderMainInfoRSV;
import com.ai.paas.util.Utils;
import com.ai.paas.utils.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by landeng on 2017/6/2.
 */
public class OrderStatusUpdateBaseJob implements Job {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        IOrderMainInfoRSV oderBaseService = Utils.getInstance(IOrderMainInfoRSV.class);
        try {
            //处理已完成的订单
            logger.info("SearchGdsBaseJob fired at " + new Date());
            OrdInfoReqDTO ordInfoReqDTO = new OrdInfoReqDTO ();
            Date orderActiveEndTime   = DateUtil.getNowAsDate();
            ordInfoReqDTO.setActiveEndTime(orderActiveEndTime);
            ordInfoReqDTO.setPageNo(1);
            ordInfoReqDTO.setPageSize(100);
            oderBaseService.updateOrderAndSubOrderInfoByJob(ordInfoReqDTO);
        } catch (Exception e) {
            logger.error("执行 SearchGdsBaseJob 失败： ",e);
        }
    }
}
