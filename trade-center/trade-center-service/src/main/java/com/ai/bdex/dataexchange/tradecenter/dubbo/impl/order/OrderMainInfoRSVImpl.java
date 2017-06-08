package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdLog;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderMainInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdMainInfoSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.DateUtil;
@Service("iOrderMainInfoRSV")
public class OrderMainInfoRSVImpl  implements IOrderMainInfoRSV {
	private static final Logger log = LoggerFactory.getLogger(OrderMainInfoRSVImpl.class);

	@Resource
	private IOrdMainInfoSV iOrdMainInfoSV;
	@Resource
	private IOrdInfoSV iOrdInfoSV;
	
	public PageResponseDTO<OrdMainInfoRespDTO> queryOrdMainInfoPage(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception{
		PageResponseDTO<OrdMainInfoRespDTO> ordMainInfoPage = new PageResponseDTO<>();
		try {
			ordMainInfoPage = iOrdMainInfoSV.queryOrdMainInfoPage(ordMainInfoReqDTO);
		} catch (Exception e) {
			log.error("分页获取订单主信息异常:", e);
			throw new Exception(e);
		}
		return ordMainInfoPage;
	}
	public int cancelOrder(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception {
		if (ordMainInfoReqDTO ==null ||StringUtil.isBlank(ordMainInfoReqDTO.getOrderId())){
            throw new Exception("取消订单异常，oderId入参为空");
        }
		if(StringUtil.isBlank(ordMainInfoReqDTO.getOrderStatus())){
			 throw new Exception("取消订单异常，status入参为空");
		}
		int code=0;
		try {
			//取消主订单
			code= iOrdMainInfoSV.cancelOrderMainInfo(ordMainInfoReqDTO);
			if(code>0){
				OrdInfoReqDTO ordInfoReqDTO = new OrdInfoReqDTO();
				ordInfoReqDTO.setOrderId(ordMainInfoReqDTO.getOrderId());
				ordInfoReqDTO.setStatus(ordMainInfoReqDTO.getOrderStatus());
				//取消主订单成功，更新子订单状态
				code=iOrdInfoSV.cancelOrderInfo(ordInfoReqDTO);
			}
		 	OrdLog ordLog = new OrdLog();
			ordLog.setCreateStaff(ordMainInfoReqDTO.getCreateStaff());
			ordLog.setOrderId(ordMainInfoReqDTO.getOrderId());
			ordLog.setNode(Constants.Order.LOG_CODE_CODE_99);
			ordLog.setNodeDesc(Constants.Order.LOG_CODE_DESC_99);
			iOrdMainInfoSV.saveOrderlog(ordLog);
		} catch (Exception e) {
			log.error("取消订单信息异常:", e);
			throw new Exception(e);
		}
		return code;
	}
	public int updateOrderMainInfo(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception {
		if (ordMainInfoReqDTO ==null ||StringUtil.isBlank(ordMainInfoReqDTO.getOrderId())){
            throw new Exception("更新订单异常，oderId入参为空");
        }
		int code=0;
		try {
			code= iOrdMainInfoSV.updateOrderMainInfo(ordMainInfoReqDTO);
		} catch (Exception e) {
			log.error("更新订单信息异常:", e);
			throw new Exception(e);
		}
		return code;
	}
	public List<OrdMainInfoRespDTO> queryOrdMainInfoList(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception {
		List<OrdMainInfoRespDTO> respDTOList = new ArrayList<OrdMainInfoRespDTO>();
		try {
			respDTOList= iOrdMainInfoSV.queryOrdMainInfoList(ordMainInfoReqDTO);
		} catch (Exception e) {
			log.error("获取订单主表信息异常:", e);
			throw new Exception(e);
		}
		return respDTOList;
	}
	/***
	 * 更新主订单和子订单的状态信息
	 * @param ordMainInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public int updateOrderAndSubOrdStatuss(OrdMainInfoReqDTO ordMainInfoReqDTO,OrdInfoReqDTO ordInfo) throws Exception {
		if (ordMainInfoReqDTO ==null ||StringUtil.isBlank(ordMainInfoReqDTO.getOrderId())){
			throw new Exception("更新订单异常，oderId入参为空");
		}
		int code=0;
		Date updateTime =  DateUtil.getNowAsDate();
		ordMainInfoReqDTO.setUpdateTime(updateTime);
		ordInfo.setUpdateTime(updateTime);
		try {
			code= iOrdMainInfoSV.updateOrderMainInfo(ordMainInfoReqDTO);

		} catch (Exception e) {
			log.error("更新主订单信息异常:", e);
			throw new Exception(e);
		}
		try {
			code= iOrdInfoSV.updateOrderStatus(ordInfo);
		} catch (Exception e) {
			log.error("更新子订单信息异常:", e);
			throw new Exception(e);
		}
		return code;
	}
    /***
     * 在线支付成功更新主订单和子订单的状态信息
     * @param ordMainInfoReqDTO
     * @return
     * @throws Exception
     */
    public int setOrderAndSubOrdToPayInline(OrdMainInfoReqDTO ordMainInfoReqDTO,OrdInfoReqDTO ordInfo) throws Exception {
        int docount =  updateOrderAndSubOrdStatuss(ordMainInfoReqDTO,  ordInfo);
        //写支付日志
        OrdLog ordLog = new OrdLog();
        ordLog.setCreateStaff(ordMainInfoReqDTO.getCreateStaff());
        ordLog.setOrderId(ordMainInfoReqDTO.getOrderId());
        ordLog.setNode(Constants.Order.LOG_CODE_02);
        ordLog.setNodeDesc(Constants.Order.LOG_CODE_DESC_02);
        iOrdMainInfoSV.saveOrderlog(ordLog);
        return docount;
    }
    /***
     * 管理员后台设置订单支付
     * @param ordMainInfoReqDTO
     * @return
     * @throws Exception
     */
    public int setOrderAndSubOrdToPayByManager(OrdMainInfoReqDTO ordMainInfoReqDTO,OrdInfoReqDTO ordInfo) throws Exception {
        int docount =  updateOrderAndSubOrdStatuss(ordMainInfoReqDTO,  ordInfo);
        //写支付日志
        OrdLog ordLog = new OrdLog();
        ordLog.setCreateStaff(ordMainInfoReqDTO.getCreateStaff());
        ordLog.setOrderId(ordMainInfoReqDTO.getOrderId());
        ordLog.setNode(Constants.Order.LOG_CODE_020);
        ordLog.setNodeDesc(Constants.Order.LOG_CODE_DESC_020);
        iOrdMainInfoSV.saveOrderlog(ordLog);
        return docount;
    }

	public OrdMainInfoRespDTO queryOrderDetail(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception {
		 OrdMainInfoRespDTO  respOrderDetailDTO = new OrdMainInfoRespDTO();
		OrdInfoRespDTO ordInfoRespDTO  =new OrdInfoRespDTO ();
		try {
			if(StringUtil.isBlank(ordMainInfoReqDTO.getOrderId()))
			{
				throw new Exception("更新订单异常，oderId入参为空");
			}
			respOrderDetailDTO= iOrdMainInfoSV.queryOrdMainInfoOne(ordMainInfoReqDTO);
			//查询子订单信息
			OrdInfoReqDTO ordInfoReqDTO = new OrdInfoReqDTO();
			ordInfoReqDTO.setOrderId(ordMainInfoReqDTO.getOrderId());
			List<OrdInfoRespDTO> 	ordInfoRespDTOList = iOrdInfoSV.queryOrderInfoList(ordInfoReqDTO);
			if(!CollectionUtil.isEmpty(ordInfoRespDTOList)){
				for(OrdInfoRespDTO ordInfo :ordInfoRespDTOList){
					OrdInfoRespDTO respDTO = new OrdInfoRespDTO();
					//只有一个子订单
					ordInfoRespDTO = ordInfo;
					respOrderDetailDTO.setOrdInfoRespDTO(ordInfoRespDTO);
 				}
			}
		} catch (Exception e) {
			log.error("获取订单主表信息异常:", e);
			throw new Exception(e);
		}
		return respOrderDetailDTO;
	}
 	public int updateOrderAndSubOrderInfoByAip(OrdInfoReqDTO ordInfoReqDTO) throws Exception{
		try{
 			//将数据设置为无效的
			// 套餐状态：00 未生效，02 在用，03 有效期已达到，不可再用；
			// 04 套餐已完成（订单调用量量已经达到最大值、或余额已为0，二者中一种情况达到均为订单完成--界面实时计算）
			OrdInfoReqDTO ordInfo = new OrdInfoReqDTO();
			ordInfo.setUpdateStaff(Constants.Order.ORDER_UPDATE_USER_SYS);
			ordInfo.setOrderId(ordInfoReqDTO.getOrderId());
			ordInfo.setSubOrder(ordInfoReqDTO.getSubOrder());
			ordInfo.setPackageStatus(Constants.Order.ORDER_PACKAGE_STATUS_03);
			int code  = iOrdInfoSV.updateOrderStatus(ordInfo);
 		}
		catch (Exception e){
			throw new Exception("updateOrderAndSubOrderInfoByAip：订单完成状态失败："+e.getStackTrace());
		}
		return  1;
	}
	@Override
	public int updateOrderAndSubOrderInfoByJob(OrdInfoReqDTO ordInfoReqDTO) throws Exception{
		// 搜索比当前日期小的，并且状态不是04的订单
		try{
			PageResponseDTO<OrdInfoRespDTO> pageResponseDTO = iOrdInfoSV.queryOutStatusOrdMainInfoPage(ordInfoReqDTO);
			//循环处理数据
			if(pageResponseDTO != null) {
				for (OrdInfoRespDTO resordInfoRespDTO : pageResponseDTO.getResult()) {
					//将数据设置为无效的
					OrdInfoReqDTO ordInfoReqDTO2 = new OrdInfoReqDTO();
					ordInfoReqDTO2.setOrderId(resordInfoRespDTO.getOrderId());
					ordInfoReqDTO2.setSubOrder(resordInfoRespDTO.getSubOrder());
 					updateOrderAndSubOrderInfoByAip(ordInfoReqDTO2);
				}
			}
		}
		catch (Exception e)
		{
			throw new Exception("updateOrderAndSubOrderInfoByJob：定时任务处理 订单完成状态失败："+e.getStackTrace());
		}
		return  1;
	}

}
