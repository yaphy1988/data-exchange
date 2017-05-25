package com.ai.bdex.dataexchange.usercenter.service.interfaces;

import com.ai.bdex.dataexchange.usercenter.dao.model.AuthMenu;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole2Menu;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.MenuDisPlayDTO;

import java.util.List;

public interface IAuthBusiSV {

    public List<MenuDisPlayDTO> getValidMenus()throws Exception;
    public List<AuthRole2Menu> getValidRoles()throws Exception;

    /**
     * 通过用户ID查询角色
     * @param staffId
     * @return
     * @throws Exception
     */
    public List<AuthRole> getRoleByStaffId(String staffId)throws Exception;

    /**
     * 根据用ID查询用户菜单
     * @return
     * @throws Exception
     */
    public List<MenuDisPlayDTO> getMenusByStaffId(String staffId)throws Exception;


}
