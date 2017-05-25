package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseLoginUrlDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.MenuDisPlayDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;

import java.util.List;
import java.util.Map;

public interface IAuthBusiRSV {

	/**
	 * 刷新免登陆URL缓存
	 * @return
	 * @throws BusinessException
     */
	public int loadUnLoginUrls()throws BusinessException;

	/**
	 *刷新角色菜单缓存
	 * @return
	 * @throws BusinessException
     */
	public int loadRoleMenus()throws BusinessException;

	/**
	 * 获取用户菜单
	 * @param staffId
	 * @return
	 * @throws BusinessException
     */
	public List<MenuDisPlayDTO> getStaffAuthMenus(String staffId)throws BusinessException;
}
