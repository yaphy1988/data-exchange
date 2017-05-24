package com.ai.bdex.dataexchange.usercenter.dao.mapper;

import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole;

import java.util.List;

public interface AuthRoleExtMapper {

    List<AuthRole> selectByStaffId(String staffId);
}