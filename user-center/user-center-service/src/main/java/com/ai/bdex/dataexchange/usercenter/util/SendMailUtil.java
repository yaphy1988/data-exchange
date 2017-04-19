package com.ai.bdex.dataexchange.usercenter.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ai.paas.utils.MD5Util;

/**
 * 创建人： zy
 * 创建时间： 2014年10月22日 下午3:04:11
 * 类描述：发送邮件工具类
 */
public class SendMailUtil {
	
	public static final String HOST = "mail.asiainfo.com";
	public static final String PROTOCOL = "SSL";
	public static final int PORT = 25;
	public static final String SENDER = "zouwj@asiainfo.com";//此处需要与host相同的网站
	public static final String SENDERPWD = "hcxyJIE20190614";
	public static final String SIGNACTIVE = "SIGNACTIVEBYEMAIL";
	
	/**
	 * 获取用于发送邮件的Session
	 * @return
	 */
	public static Session getSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.store.protocol" , PROTOCOL);//设置协议
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth" , true);
        
        Authenticator authenticator = new Authenticator() {
        	@Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER, SENDERPWD);
            }
		};
        Session session = Session.getDefaultInstance(props,authenticator);
        return session;
	}
	
	/**
	 * 发送邮件
	 * @param receiver
	 * @param content
	 */
	public static void send(String receiver, String content) {
		Session session = getSession();
		try {
			System.out.println("-------开始发送-------");
			Message msg = new MimeMessage(session);
			//设置message属性
			msg.setFrom(new InternetAddress(SENDER));
			InternetAddress[] addrs = {new InternetAddress(receiver)};
			msg.setRecipients(Message.RecipientType.TO, addrs);
			msg.setSubject("广州数据交易平台-帐号激活");
			msg.setSentDate(new Date());
			msg.setContent(content,"text/html;charset=utf-8");
			//开始发送
			Transport.send(msg);
            System.out.println("-------发送完成-------");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取注册邮件激活模板
	 * @param uid
	 * @param code
	 * @return
	 */
	public static String getContentBySign(String signid,String staffId,String email){		
		String code = getCode(staffId, email);
		String activeLink = "http://www.fenxi.com?signid="+signid+"&code="+code;
		StringBuffer content = new StringBuffer("<div><a target='_blank' href='http://www.fenxi.com'>");
		content.append("<img src='https://material-ssl.mediav.com/bjjs/mba/2013/images/mail_logo.png'></a></div>")
		.append("<div><p>尊敬的用户，你好：</p><div><p>感谢您注册聚合数据平台，您的邮箱 <a href='mailto:"+email+"' target='_blank'>")
		.append(email+"<wbr></a>将是您的登录帐号，同时也会定时接收报表和提醒。</p>")
		.append("<p>请点击下面的链接完成注册：</p>")
		.append("<a href='"+activeLink+"' target='_blank'>"+activeLink+"</a>")
		.append("<p>如果链接无法点击，请将它拷贝到浏览器的地址栏中。</p></div></div>")
		.append("<b style='color:#F69C6A;'>聚合数据平台<b><br>")
		.append("<a target='_blank' href='http://www.fenxi.com' style='color:#555A56;text-decoration: none;'>www.fenxi.com</a>")
		.append("<p style='color:#555A56;font-size:12px;'>此信由聚合数据平台系统发出，系统不接收回信，请勿回复。</p>")
		.append("<style type='text/css'>.qmbox style, .qmbox script, .qmbox head, .qmbox link,")
		.append(" .qmbox meta {display: none !important;}</style>");
		return content.toString();
	}
	
	public static String getCode(String staffId,String email){
		return MD5Util.getMD5String(staffId+email+SIGNACTIVE);
	}
	public static void main(String[] args){
		String email = "369697267@qq.com";
	  	String content = getContentBySign("123","zouwj2100","369697267@qq.com");
	  	SendMailUtil.send(email, content);//开始发送邮件
	}
}