package com.ai.bdex.dataexchange.usercenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffPassRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
import com.ai.paas.utils.SignUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ImportResource({"classpath:/dubbo/dubboContext.xml"})
public class UserServiceTest {

	@Autowired
	private IAuthStaffPassRSV iAuthStaffPassRSV;
	
	@Autowired
	private IAuthStaffRSV iAuthStaffRSV;
	
//	@Test
//	public void sendEmalForActiveTest() throws BusinessException{
//		SignInfoDTO info = new SignInfoDTO();
//		info.setStaffId("zouwj");
//		info.setEmail("369697267@qq.com");
//		info.setConfirmPass("hcxyjie2010");
//		info.setSignpass("hcxyjie2010");
//		iAuthStaffRSV.sendEmalForActive(info);
//	}
	
	public static void main(String[] args){
		System.out.println("lc，SHA-1加密后的结果：" + SignUtil.SHA1("woego.cn"));
	}
}
