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
			msg.setSubject("开心网-帐号激活");
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
	
	public static void main(String[] args){
		String email = "369697267@qq.com";
	  	StringBuffer content = new StringBuffer("<h2>请点击下面的链接激活帐号，链接只能使用一次，请尽快激活！</h2>");
	  	String nameMd5 = MD5Util.encodeToHex("邹文杰"+"审核通过");//创建加密字符串
	  	content.append("<a style='font-size:16px;' href='http://localhost:8080/czfc/register.reg?doType=activate&")
	  	.append("email=" + email + "&name=" + nameMd5 +"'>")
	  	.append("http://localhost:8080/czfc/register.reg?doType=activate&")
	  	.append("email=" + email + "&name=" + nameMd5 + "</a><br/><br/>")
	  	.append("<span style='color:blue;font-size:20px;font-weight:bold;'>———浙江常山欢迎您！<span>");
	  	SendMailUtil.send(email, content.toString());//开始发送邮件
	}
}