//package com.ai.bdex.dataexchange.busi.user.controller;
//
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//import java.util.UUID;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.ISmsSendRSV;
//import com.ai.bdex.dataexchange.busi.user.entity.SmsSeccodeInfoVO;
//import com.ai.bdex.dataexchange.exception.BusinessException;
//import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
//import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
//import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.ISmsSeccodeRSV;
//import com.ai.paas.captcha.CaptchaServlet;
//import com.ai.paas.util.CacheUtil;
//import com.alibaba.boot.dubbo.annotation.DubboConsumer;
//import com.alibaba.dubbo.common.logger.Logger;
//import com.alibaba.dubbo.common.logger.LoggerFactory;
//import com.alibaba.dubbo.common.utils.StringUtils;
//
//@Controller
//@RequestMapping(value="/security")
//public class SmsValidController{
//	///日志记录
//	private static Logger logger = LoggerFactory.getLogger(SmsValidController.class);
//	
//	private static final String LAST_SEND_TIME = "SMS_TIME_";
//	
//	// 标识短信验证phoneNo + busiType是否存缓存
//    public static final String CACHE_ITEM_CHECK_ID = "cache_item_check_id";
//    // 短信验证码的信息；
//    public static String SMS_SECURITY_CODE_KEY = "Sms.Security.Code.String.";
//    
//    @DubboConsumer
//    private ISmsSeccodeRSV iSmsSeccodeRSV;
//    
//    @DubboConsumer
//    private IAuthStaffRSV iAuthStaffRSV;
//    
//    @DubboConsumer
//    private ISmsSendRSV smsSendByThreadRSV;
//
//    /**
//     * 发送短信
//     * @param request
//     * @param phoneNo电话号码
//     * @param busiType业务类型
//     * @param lastTocken
//     * @param picVerifyCode
//     * @return
//     */
//	@RequestMapping(value="/sendSecurity")
//	@ResponseBody
//	public Map<String,Object> sendSecurity(HttpServletRequest request,@RequestParam String phoneNo,
//			@RequestParam String busiType,@RequestParam String lastTocken,@RequestParam String picVerifyCode){
//		HttpSession session = request.getSession();
//		Map<String,Object> rMap = new HashMap<String,Object>();
//		
//		/** 图片验证码 */
//		if(!StringUtils.isBlank(picVerifyCode)){
//			if (CaptchaServlet.verifyCaptcha(request, picVerifyCode.trim()) == false) {
//				rMap.put("error_msg","图片验证码不正确，请重新输入！");
//				rMap.put("success",false);
//				return rMap;
//			}
//		}
//		
//		/** 时间间隔限制 80秒 */
//		Long lastSendTime = (Long) CacheUtil.getItem(LAST_SEND_TIME + phoneNo + "_" + busiType);
//		if (lastSendTime != null && ((System.currentTimeMillis() - lastSendTime) < 80 * 1000)) {
//			rMap.put("error_msg","短信验证码已经发送，请在80秒之后再次获取！");
//			rMap.put("success",false);
//			return rMap;
//		}
//		
//		if(StringUtils.isBlank(lastTocken)){
//			rMap.put("error_msg","获取短信验证码失败，请重新刷新页面之后再获取！");
//			rMap.put("success",false);			
//			logger.error("短信发送验证码非正常，入参不对，lastTocken不能为空");
//			return rMap;
//		}
//		
//        try {
//        	if("10".equals(busiType)){
//           	 //注册使用
//           	 AuthStaffDTO arg0 = new AuthStaffDTO();
//           	 arg0.setSerialNumber(phoneNo);
//           	 AuthStaffDTO custinfo = iAuthStaffRSV.findAuthStaffInfo(arg0);
//           	 if(custinfo!=null){
//           		rMap.put("error_msg","该手机号已被使用，请重新输入手机号！");
//        			rMap.put("success",false);
//        			return rMap;
//           	 }
//            }
//        	//获取4位验证码，和tocken ;
//    		String seccode = this.getRandom(6);
//    		String tocken = UUID.randomUUID().toString().replaceAll("-", "");
//    		///将对象加入Redis的缓存；
//    		SmsSeccodeInfoVO info = new SmsSeccodeInfoVO();
//    		info.setPhoneNo(phoneNo);
//    		info.setSecurityCode(seccode);
//    		info.setSendTime(Calendar.getInstance().getTime());
//    		info.setTocken(tocken);
//    		info.setSessionId(session.getId());
//    		info.setBusiType(busiType);
//    		CacheUtil.addItem(SMS_SECURITY_CODE_KEY+tocken+phoneNo, info, 10*60);
//    		CacheUtil.addItem("SMS_RESETPWD_"+phoneNo, seccode,10*60);             
//    		 //发送验证码
//       	     smsSendByThreadRSV.sendVerifyCodeByAlibaba(phoneNo, seccode);
//             rMap.put("error_msg","验证码已经发送到手机，请查收！");
//             rMap.put("success",true);
//             rMap.put("tocken",tocken);
//             // 有效时间10分钟
//             CacheUtil.addItem(LAST_SEND_TIME + phoneNo + "_" + busiType, System.currentTimeMillis(), 1 * 10 * 60);
//        } catch (BusinessException err) {
//            logger.error("发送校验码失败；" + err.getMessage(), err);
//            rMap.put("error_msg",err.getMessage());
// 			rMap.put("success",false);
//        } catch (Exception err) {
//            logger.error("发送校验码失败；" + err.getMessage(), err);
//            rMap.put("error_msg",err.getMessage());
// 			rMap.put("success",false);
//        }
//		return rMap;
//	}
//	
//	/**
//	 * 输入的验证码校验服务；
//	 * @param request
//	 * @param tocken
//	 * @param inputSecurityCode
//	 * @return
//	 */	
//	@RequestMapping(value="/checkSecurityCode")
//	@ResponseBody
//	public Map<String,Object> checkSecurityCode(HttpServletRequest request,@RequestParam String tocken, 
//			@RequestParam String inputSecurityCode,@RequestParam String phoneNo){
//		Map<String,Object> vo = new HashMap<String,Object>();
//		try{			
//			if(StringUtils.isEmpty(tocken)){
//				vo.put("success",false);
//				vo.put("error_msg","校验手机验证码所需的tocken不能为空");
//				return vo;
//			}			
//			if(StringUtils.isEmpty(inputSecurityCode)){
//				vo.put("success",false);
//				vo.put("error_msg","输入的验证码不能为空");
//				return vo;
//			}			
//			SmsSeccodeInfoVO info = (SmsSeccodeInfoVO)CacheUtil.getItem(SMS_SECURITY_CODE_KEY+tocken+phoneNo);
//			if(info == null){
//				vo.put("success",false);
//				vo.put("error_msg","验证码已过期或失效，请重新获取再验证！");
//				return vo;
//			}			
//			///判断验证码是否一致；
//			if(inputSecurityCode.equalsIgnoreCase(info.getSecurityCode())){
//				vo.put("success",true);
//				vo.put("error_msg","验证码正确！");				
//				return vo;
//			} else {
//				vo.put("success",false);
//				vo.put("error_msg","验证码过错误！");
//				return vo;
//			}
//		} catch(Exception err){
//			logger.error("验证校验码异常；"+err.getMessage(),err);
//			vo.put("success",false);
//			vo.put("error_msg","验证码校验失败，请重新获取校验码");
//		}
//		
//		return vo;
//	}
//	
//	/**
//	 * 随机生成N胃数字；
//	 * @param num  生成随机数字的位数；
//	 * @return
//	 * @author
//	 */
//	private String getRandom(int num) {
//	  Random rand = new Random();
//	  StringBuffer passWord = new StringBuffer();
//	  for (int i = 0; i < num; i++) {
//		passWord.append(String.valueOf(rand.nextInt(10)));
//      }
//	  return passWord.toString();
//	}
//}
