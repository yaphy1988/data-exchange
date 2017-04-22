package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SmsEntyResDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SmsSeccodeReqDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.ISmsSeccodeRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthStaffSV;
import com.ai.bdex.dataexchange.usercenter.util.HttpRequestUtil;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.utils.SignUtil;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;

@Service("iSmsSeccodeRSV")
public class SmsSeccodeRSVImpl implements ISmsSeccodeRSV {
	private final static Logger logger = LoggerFactory.getLogger(SmsSeccodeRSVImpl.class);
	
	@Resource
	private IAuthStaffSV iAuthStaffSV;
	// 短信验证码的信息；
    public static String SMS_SECURITY_CODE_KEY = "Sms.Security.Code.String.";

	@Override
	public String genSmsSecCode(SmsSeccodeReqDTO seccodeInitVO) throws BusinessException {
		
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
		info.setSecurityCode(seccode);
		info.setSessionId(seccodeInitVO.getSessionId());
		info.setBusiType(seccodeInitVO.getBusiType());
		info.setCreateStaff(seccodeInitVO.getCreateStaff());
		CacheUtil.addItem(SMS_SECURITY_CODE_KEY+tocken+seccodeInitVO.getPhoneNo(), info, 10*60);
		CacheUtil.addItem("SMS_RESETPWD_"+seccodeInitVO.getPhoneNo(), seccode,10*60);
		///发送验证码；,如果是 “1” ，表示启用短信发送；其它的时候，不做发送；
		try{
			iAuthStaffSV.insertSmsSeccodelog(info);
			Map<String,Object> rMap = this.getHttpResult(seccodeInitVO.getPhoneNo(), seccode);			
			if(!("0".equals(rMap.get("result").toString()))){
				throw new BusinessException("desc");
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return tocken;

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
	
	private Map<String, Object> getHttpResult(String phoneNo,String rannum) throws IOException{
		String time =String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000);//生成时间戳
		String validate =  SignUtil.MD5("SCXT"+time+"wochuangfu!@#$%^").toUpperCase();//生成md5
		
		SmsEntyResDTO sms = new SmsEntyResDTO();
		sms.setPhones(phoneNo);
		sms.setUser("SCXT");
		sms.setTimestamp(time);
		sms.setTemplet("SCXT001");//模版编码
		sms.setWords(new String[]{rannum});//模版所需入参
		sms.setValidate(validate);
		Map<String, String> params = new HashMap<String,String>();
		params.put("params", JSONObject.toJSONString(sms).toString());//obj 转json
        Map<String, String> result = HttpRequestUtil.getResultData("", "http://121.31.40.3/EmallAip/SMS001", "UTF-8", params);
        Map<String, Object>respResult = JSONObject.parseObject(result.get("respResult"));
        return respResult;
        }
}
