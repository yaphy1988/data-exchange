package com.ai.bdex.dataexchange.busi.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.ISmsSendRSV;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SmsSeccodeReqDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.ISmsSeccodeRSV;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.captcha.CaptchaServlet;
import com.ai.paas.util.CacheUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.StringUtils;

@Controller
@RequestMapping(value="/security")
public class SmsValidController{
	///日志记录
	private static Logger logger = LoggerFactory.getLogger(SmsValidController.class);
	
	private static final String LAST_SEND_TIME = "SMS_TIME_";
	
	// 标识短信验证phoneNo + busiType是否存缓存
    public static final String CACHE_ITEM_CHECK_ID = "cache_item_check_id";
    
    @DubboConsumer
    private ISmsSeccodeRSV smsSeccodeRSV;
    
    @DubboConsumer
    private IAuthStaffRSV iAuthStaffRSV;
    
    @DubboConsumer
    private ISmsSendRSV iSmsSendRSV;

    /**
     * 发送短信
     * @param request
     * @param phoneNo电话号码
     * @param busiType业务类型
     * @param lastTocken
     * @param picVerifyCode
     * @return
     */
	@RequestMapping(value="/sendSecurity")
	@ResponseBody
	public Map<String,Object> sendSecurity(HttpServletRequest request,@RequestParam String phoneNo,
			@RequestParam String busiType,@RequestParam String lastTocken,@RequestParam String picVerifyCode){
		HttpSession session = request.getSession();
		Map<String,Object> rMap = new HashMap<String,Object>();
		
		/** 图片验证码 */
		if(!StringUtils.isBlank(picVerifyCode)){
			if (CaptchaServlet.verifyCaptcha(request, picVerifyCode.trim()) == false) {
				rMap.put("error_msg","图片验证码不正确，请重新输入！");
				rMap.put("success",false);
				return rMap;
			}
		}
		
		/** 时间间隔限制 80秒 */
		Long lastSendTime = (Long) CacheUtil.getItem(LAST_SEND_TIME + phoneNo + "_" + busiType);
		if (lastSendTime != null && ((System.currentTimeMillis() - lastSendTime) < 80 * 1000)) {
			rMap.put("error_msg","短信验证码已经发送，请在80秒之后再次获取！");
			rMap.put("success",false);
			return rMap;
		}
		
		if(StringUtils.isBlank(lastTocken)){
			rMap.put("error_msg","获取短信验证码失败，请重新刷新页面之后再获取！");
			rMap.put("success",false);			
			logger.error("短信发送验证码非正常，入参不对，lastTocken不能为空");
			return rMap;
		}
		
        try {
        	 SmsSeccodeReqDTO seccodeInitVO = new SmsSeccodeReqDTO();
             seccodeInitVO.setBusiType(busiType);
             seccodeInitVO.setPhoneNo(phoneNo);
             seccodeInitVO.setLastTocken(lastTocken);
             seccodeInitVO.setSessionId(request.getSession().getId());             
             if(null!=StaffUtil.getStaffVO(session)){
            	 seccodeInitVO.setCreateStaff(StaffUtil.getStaffId(session));
             }
             if("10".equals(busiType)){
            	 //注册使用
            	 AuthStaffDTO arg0 = new AuthStaffDTO();
            	 arg0.setSerialNumber(phoneNo);
            	 AuthStaffDTO custinfo = iAuthStaffRSV.findAuthStaffInfo(arg0);
            	 if(custinfo!=null){
            		rMap.put("error_msg","该手机号已被使用，请重新输入手机号！");
         			rMap.put("success",false);
         			return rMap;
            	 }
             }
             //保存到验证码发送表
             SmsSeccodeReqDTO tockenInfo = smsSeccodeRSV.genSmsSecCode(seccodeInitVO);
             if(tockenInfo!=null){
            	 //发送验证码
            	 iSmsSendRSV.sendVerifyCodeByAlibaba(phoneNo, tockenInfo.getSecurityCode());
             }
             rMap.put("error_msg","验证码已经发送到手机，请查收！");
             rMap.put("success",true);
             rMap.put("tocken",tockenInfo.getTocken());
             // 有效时间10分钟
             CacheUtil.addItem(LAST_SEND_TIME + phoneNo + "_" + busiType, System.currentTimeMillis(), 1 * 10 * 60);
        } catch (BusinessException err) {
            logger.error("发送校验码失败；" + err.getMessage(), err);
            rMap.put("error_msg",err.getMessage());
 			rMap.put("success",false);
        } catch (Exception err) {
            logger.error("发送校验码失败；" + err.getMessage(), err);
            rMap.put("error_msg",err.getMessage());
 			rMap.put("success",false);
        }
		return rMap;
	}
	
	/**
	 * 输入的验证码校验服务；
	 * @param request
	 * @param tocken
	 * @param inputSecurityCode
	 * @return
	 */	
	@RequestMapping(value="/checkSecurityCode")
	@ResponseBody
	public Map<String,Object> checkSecurityCode(HttpServletRequest request,@RequestParam String tocken, 
			@RequestParam String inputSecurityCode,@RequestParam String phoneNo){
		
		SmsSeccodeReqDTO smsSecurityCheckVO = new SmsSeccodeReqDTO();
		smsSecurityCheckVO.setInputSecurityCode(inputSecurityCode);
		smsSecurityCheckVO.setTocken(tocken);
		smsSecurityCheckVO.setPhoneNo(phoneNo);
		Map<String,Object> vo = new HashMap<String,Object>();
		try{
			boolean checkResult = smsSeccodeRSV.checkSmsSecCode(smsSecurityCheckVO);
			if(checkResult){
				vo.put("success",true);
				vo.put("error_msg","验证码校验通过");
			} else {
				vo.put("success",false);
				vo.put("error_msg","验证码输入有误，请重新输入");
			}
		} catch(Exception err){
			logger.error("验证校验码异常；"+err.getMessage(),err);
			vo.put("success",false);
			vo.put("error_msg","验证码校验失败，请重新获取校验码");
		}
		
		return vo;
	}
}
