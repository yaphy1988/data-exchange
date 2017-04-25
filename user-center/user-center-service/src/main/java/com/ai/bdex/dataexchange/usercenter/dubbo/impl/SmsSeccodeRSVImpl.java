package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SmsSeccodeReqDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.ISmsSeccodeRSV;
import com.ai.paas.util.CacheUtil;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.StringUtils;

@Service("iSmsSeccodeRSV")
public class SmsSeccodeRSVImpl implements ISmsSeccodeRSV {
	private final static Logger logger = LoggerFactory.getLogger(SmsSeccodeRSVImpl.class);
	
	// 短信验证码的信息；
    public static String SMS_SECURITY_CODE_KEY = "Sms.Security.Code.String.";

	@Override
	public SmsSeccodeReqDTO genSmsSecCode(SmsSeccodeReqDTO seccodeInitVO) throws BusinessException {
		
		if(seccodeInitVO == null){
			throw new BusinessException("入参对象");
		}
		
		if(StringUtils.isBlank(seccodeInitVO.getPhoneNo())){
			throw new BusinessException("发送手机验证码所需要的手机号码不能为空");
		}
		
		if(StringUtils.isEmpty(seccodeInitVO.getLastTocken())){
			throw new BusinessException("短信验证码的参数token不正确，请刷新页面");
		}
		
		if(StringUtils.isEmpty(seccodeInitVO.getBusiType())){
		    throw new BusinessException("短信验证码的参数busiType不能为空");
		}  
		//获取4位验证码，和tocken ;
		String seccode = this.getRandom(4);
		String tocken = UUID.randomUUID().toString().replaceAll("-", "");
		///将对象加入Redis的缓存；
		SmsSeccodeReqDTO info = new SmsSeccodeReqDTO();
		info.setPhoneNo(seccodeInitVO.getPhoneNo());
		info.setSecurityCode(seccode);
		info.setSendTime(Calendar.getInstance().getTime());
		info.setTocken(tocken);
		info.setSessionId(seccodeInitVO.getSessionId());
		info.setBusiType(seccodeInitVO.getBusiType());
		info.setCreateStaff(seccodeInitVO.getCreateStaff());
		CacheUtil.addItem(SMS_SECURITY_CODE_KEY+tocken+seccodeInitVO.getPhoneNo(), info, 10*60);
		CacheUtil.addItem("SMS_RESETPWD_"+seccodeInitVO.getPhoneNo(), seccode,10*60);
		///发送验证码；,如果是 “1” ，表示启用短信发送；其它的时候，不做发送；
		try{
			return info;		
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;

	}

	@Override
	public boolean checkSmsSecCode(SmsSeccodeReqDTO smsSecurityCheckVO) throws BusinessException {
		
		if(smsSecurityCheckVO == null){
			throw new BusinessException("校验手机验证码所需要的信息不能为空");
		}
		
		if(StringUtils.isEmpty(smsSecurityCheckVO.getTocken())){
			throw new BusinessException("校验手机验证码所需的tocken不能为空");
		}
		
		if(StringUtils.isEmpty(smsSecurityCheckVO.getInputSecurityCode())){
			throw new BusinessException("输入的验证码不能为空");
		}		
		
		SmsSeccodeReqDTO info = (SmsSeccodeReqDTO)CacheUtil.getItem(SMS_SECURITY_CODE_KEY+smsSecurityCheckVO.getTocken()+smsSecurityCheckVO.getPhoneNo());
		if(info == null){
			throw new BusinessException("验证码过期，请重新获取再验证！");
		}
		
		///判断验证码是否一致；
		if(smsSecurityCheckVO.getInputSecurityCode().equalsIgnoreCase(info.getSecurityCode())){
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * 随机生成N胃数字；
	 * @param num  生成随机数字的位数；
	 * @return
	 * @author
	 */
	private String getRandom(int num) {
	  Random rand = new Random();
	  StringBuffer passWord = new StringBuffer();
	  for (int i = 0; i < num; i++) {
		passWord.append(String.valueOf(rand.nextInt(10)));
      }
	  return passWord.toString();
	}
	
	public static void main(String[] args){
		Date nowTime = new Date(System.currentTimeMillis());
		System.out.println(nowTime);
		Date nowTimeago = new Date(System.currentTimeMillis()-60*1000*10);
		System.out.println(nowTimeago);
	}
}
