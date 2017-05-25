package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import java.util.Map;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;

public interface IAuthStaffRSV {

	/**
	 * 用户点击注册发送激活邮件
	 * @param authStaffDTO
	 */
	public void sendEmalForActive(SignInfoDTO info)throws BusinessException;
	
	/**
	 * 判断用户Id是否存在
	 * @param staffId
	 * @return
	 * @throws Exception
	 */
	public boolean validUserIdExists(String staffId)throws BusinessException;
	
	/**
	 * 账户激活业务实现
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> doActiveByEmail(String signId,String code)throws BusinessException;
	
	/**
	 * 将数据插入到用户表
	 * @return
	 * @throws Exception
	 */
	public int insertInfoToAuthStaff(AuthStaffDTO record)throws BusinessException;
	
	/**
	 * 判断该用户是否已认证
	 * @param staffId
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkStaffAuthen(String staffId)throws BusinessException;
	
	/**
	 * 通过短信验证码注册
	 * @param info
	 * @return
	 * @throws BusinessException
	 */
	public int saveSignInfoBysms(SignInfoDTO info)throws BusinessException;
	
	/**
	 * 根据条件查询用户信息
	 * @return
	 * @throws BusinessException
	 */
	public AuthStaffDTO findAuthStaffInfo(AuthStaffDTO input)throws BusinessException;
	
	/**
	 * 修改用户信息
	 * @param input
	 * @return
	 * @throws BusinessException
	 */
	public int updateAuthStaffInfo(AuthStaffDTO input)throws BusinessException;
	
	/**
	 * 修改手机号
	 * @param input
	 * @return
	 * @throws BusinessException
	 */
	public int updateMobilePhone(AuthStaffDTO input)throws BusinessException;
	
	/**
	 * 校验用户名或别名是否存在
	 * @param name
	 * @param staffId
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkInfoByName(String name,String staffId)throws BusinessException;

	/**
	 * 查询用户信息
	 * @param vo
	 * @return
	 * @throws BusinessException
     */
	public PageResponseDTO<StaffInfoDTO> getStaffInfoPage(AuthStaffDTO vo)throws BusinessException;
}
