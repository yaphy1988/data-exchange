package com.ai.bdex.dataexchange.usercenter;

import java.util.Map;

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
	
	/**
	 * 模拟注册发送邮件激活
	 * @throws BusinessException
	 */
//	@Test
//	public void sendEmalForActiveTest() throws BusinessException{
//		SignInfoDTO info = new SignInfoDTO();
//		info.setStaffId("zouwj");
//		info.setEmail("369697267@qq.com");
//		info.setConfirmPass("hcxyjie2010");
//		info.setSignpass("hcxyjie2010");
//		iAuthStaffRSV.sendEmalForActive(info);
//	}
	
	/**
	 * 模拟激活
	 * @throws BusinessException
	 */
//	@Test
//	public void doActiveByEmailTest()throws BusinessException{
//		//signid=4,code=ba82c1c839b257f314a1c4d7e93b6779
//		Map<String,Object> rMap = iAuthStaffRSV.doActiveByEmail("8", "ba82c1c839b257f314a1c4d7e93b6779");
//		System.out.println(rMap.toString());
//	}
	
	public static void main(String[] args){
		System.out.println("lc，SHA-1加密后的结果：" + SignUtil.SHA1("woego.cn"));
	}
}
