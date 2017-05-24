package com.ai.bdex.dataexchange.usercenter.dao.mapper;

import com.ai.bdex.dataexchange.usercenter.dao.model.AuthMenu;

import java.util.List;

public interface AuthMenuExtMapper {

    /**
     * 根据角色ID查询菜单
     * @param roleId
     * @return
     */
    List<AuthMenu> selectByRoleId(Integer roleId);
}