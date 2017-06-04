package com.ai.bdex.dataexchange.usercenter.service.interfaces;

import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRoleReqDTO;

import java.util.List;

/**
 * Created by yx on 2017/6/2.
 */
public interface IAuthRoleSV {

    public List<AuthRole> queryRoleList(AuthRoleReqDTO authRoleReqDTO) throws Exception;

    /**
     * 查询用户已配置角色列表
     * @param authRoleReqDTO
     * @return
     * @throws Exception
     */
    public List<AuthRole> queryAuthRoleListByRole2Staff(AuthRoleReqDTO authRoleReqDTO) throws Exception;
}
