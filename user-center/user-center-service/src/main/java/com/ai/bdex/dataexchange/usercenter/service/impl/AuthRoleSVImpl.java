package com.ai.bdex.dataexchange.usercenter.service.impl;

import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthRole2StaffMapper;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthRoleMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole2Staff;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole2StaffExample;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRoleExample;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRoleReqDTO;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthRoleSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/6/2.
 */
@Service("iAuthRoleSV")
public class AuthRoleSVImpl implements IAuthRoleSV {

    @Autowired
    private AuthRoleMapper authRoleMapper;
    @Autowired
    private AuthRole2StaffMapper authRole2StaffMapper;

    @Override
    public List<AuthRole> queryRoleList(AuthRoleReqDTO authRoleReqDTO) throws Exception {

        AuthRoleExample authRoleExample = new AuthRoleExample();
        AuthRoleExample.Criteria criteria = authRoleExample.createCriteria();
        List<AuthRole> list = new ArrayList<AuthRole>();
        if (authRoleReqDTO!=null){
            initCriteria(criteria,authRoleReqDTO);
            list = authRoleMapper.selectByExample(authRoleExample);
        }

        return list;
    }

    @Override
    public List<AuthRole> queryAuthRoleListByRole2Staff(AuthRoleReqDTO authRoleReqDTO) throws Exception {
        List<AuthRole> list = new ArrayList<AuthRole>();
        if (!StringUtil.isBlank(authRoleReqDTO.getStaffId())){
            AuthRole2StaffExample authRole2StaffExample = new AuthRole2StaffExample();
            AuthRole2StaffExample.Criteria criteria = authRole2StaffExample.createCriteria();
            criteria.andStaffIdEqualTo(authRoleReqDTO.getStaffId());
            criteria.andStatusEqualTo("1");
            List<AuthRole2Staff> authRole2StaffList = authRole2StaffMapper.selectByExample(authRole2StaffExample);
            if (!CollectionUtil.isEmpty(authRole2StaffList)){
                List<Integer> roleIdList = new ArrayList<Integer>();
                for (AuthRole2Staff authRole2Staff : authRole2StaffList){
                    roleIdList.add(authRole2Staff.getRoleId());
                }

                AuthRoleExample authRoleExample = new AuthRoleExample();
                AuthRoleExample.Criteria authRoleCriteria = authRoleExample.createCriteria();
                authRoleCriteria.andRoleIdIn(roleIdList);
                authRoleCriteria.andStatusEqualTo("1");
                list = authRoleMapper.selectByExample(authRoleExample);
            }
        }

        return list;
    }

    private void initCriteria(AuthRoleExample.Criteria criteria,AuthRoleReqDTO authRoleReqDTO){
        if (authRoleReqDTO.getRoleId()!=null){
            criteria.andRoleIdEqualTo(authRoleReqDTO.getRoleId());
        }
        if (!StringUtil.isBlank(authRoleReqDTO.getRoleName())){
            criteria.andRoleNameLike("%"+authRoleReqDTO.getRoleName()+"%");
        }
        if (!StringUtil.isBlank(authRoleReqDTO.getStatus())){
            criteria.andStatusEqualTo(authRoleReqDTO.getStatus());
        }
    }
}
