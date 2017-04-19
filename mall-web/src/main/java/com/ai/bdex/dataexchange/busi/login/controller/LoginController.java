//package com.ai.bdex.dataexchange.busi.login.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping(value = "/login")
//public class LoginController {
//	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
//	
//	@RequestMapping(value="/pageInit")
//	public String pageInit(HttpServletRequest request,
//			HttpServletResponse response){
//		return "/login/login";
//	}
//	
//	@RequestMapping(value="/dologin")
//	public void dologin(HttpServletRequest request,
//			HttpServletResponse response){
//		String token = this.getParam("token");
//		LoginInfoDTO loginInfo = null;
//		String isWeak = "0";
//		String staffId = null;
//		String ip = InetTool.getClientAddr(request);
//		if ("QRCODE".equals(token)) { // 使用二维码登录的情况
//			Object qrCodeLoginCheck = session.getAttribute(QRCodeLoginCheck);
//			if (qrCodeLoginCheck != null && Constants.Login.QRCODE_SUCCESS.equals(qrCodeLoginCheck.toString())) { // 判断是否通过qrCodeLoginCheck的校验
//				staffId = session.getAttribute(QRCodeStaffId).toString();
//				loginInfo = iLoginSIDSV.getLoginInfoshare(staffId);
//				loginInfo.setLoginIp(ip);
//			    loginInfo.setAppName("web");
//			    session.removeAttribute(QRCodeLoginCheck);
//			    session.removeAttribute(QRCodeStaffId);
//			} else { // 如果不是通过qrCodeLoginCheck的校验而直接尝试登录的将被认为是非法攻击，页面将被重定向
//				this.output(response, R.failure(Constants.Login.QRCODE_ATTACK));
//				return;
//			}
//		} else {
//			staffId = this.getParam("staffId");
//			String password = this.getParam("password");
//			String verifyCode = this.getParam("verifyCode");
//			isWeak = this.getParam("isWeak");
//			logger.info("pwd:" + password + "______rpd:" + SignUtil.SHA1(SignUtil.SHA1("ok")));
//			String veriCodeInSession = CaptchaServlet.getCaptchaCode(request);
//			//验证码注释
//			if (!CaptchaServlet.verifyCaptcha(request, verifyCode)) {
//				this.output(response, R.failure("验证码输入错误"));
//				return;
//			}
//	
//			loginInfo = new LoginInfo();
//			loginInfo.setLoginName(staffId);
//			loginInfo.setLoginPwd(password);
//			loginInfo.setInputVerifyCode(verifyCode);
//			loginInfo.setSessionVerifyCode(veriCodeInSession);
//			loginInfo.setLoginIp(ip);
//		    loginInfo.setAppName("web");
//		}
//		//StaffInfoVO staffInfoVO = null;
//	    session.setAttribute("systype", "web");
//	    System.out.println(session.getAttribute("systype").toString());
//		try {
//			//先校验登录，登录成功再进行其他业务逻辑判断
//            Map<String,Object> map = loginVerify_new(loginInfo,response,isWeak);
//            
//            
//            if (null != map.get("loginVerifyUrl")||null!=map.get("isFirst")) {
//				this.output(response,map);
//			} else {
//				StaffInfoVO staffInfoVO = (StaffInfoVO) map.get("staffInfoVO");
//				String canLottery = (String)map.get("canLottery");
//				//跳转
//				Map<String,Object> m = new HashMap<String,Object>();
//				m.put("provinceCode", staffInfoVO.getProvinceCode());
//				m.put("fairFlag", staffInfoVO.isFairFlag());//是否开启交易会
//		        m.put("iphone6Flag",staffInfoVO.isIphone6());//是否Iphone6
//				m.put("chnlAgreementFlag", staffInfoVO.getChnlAgreementFlag());//体验渠道是否协议签署  （空：非体验渠道，1：已签署，0：未签署）
//				m.put("isLottery", canLottery);
//				this.output(response,m);
//			}
//		} catch (Exception e) {
//			if (e instanceof BusinessException) {
//				BusinessException ee = (BusinessException) e;
//				logger.error("用户名:" + staffId + ",IP:" + ip + ",异常编码:"+ ee.getErrorCode() + ",异常信息:" , ee);
//				this.output(response, R.failure(ee.getErrorMessage()));
//				return;
//			} else {
//				logger.error("",e);
//			}
//			this.output(response, R.failure("系统错误,请联系管理员"));
//		}
//	}
//
//}
