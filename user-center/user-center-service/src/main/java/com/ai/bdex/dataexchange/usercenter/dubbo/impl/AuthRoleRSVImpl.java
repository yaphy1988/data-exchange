package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRoleReqDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRoleRespDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthRoleRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthRoleSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/6/2.
 */
@Service("iAuthRoleRSV")
public class AuthRoleRSVImpl implements IAuthRoleRSV {

    private final static Logger log = LoggerFactory.getLogger(AuthRoleRSVImpl.class);

    @Autowired
    private IAuthRoleSV iAuthRoleSV;

    @Override
    public List<AuthRoleRespDTO> queryAuthRoleList(AuthRoleReqDTO authRoleReqDTO) throws Exception {

        List<AuthRoleRespDTO> authRoleRespDTOList = new ArrayList<AuthRoleRespDTO>();
        try {
            List<AuthRole> list = iAuthRoleSV.queryRoleList(authRoleReqDTO);
            if (!CollectionUtil.isEmpty(list)){
                for (AuthRole authRole : list){
                    AuthRoleRespDTO authRoleRespDTO = new AuthRoleRespDTO();
                    ObjectCopyUtil.copyObjValue(authRole,authRoleRespDTO,null,false);
                    authRoleRespDTOList.add(authRoleRespDTO);
                }
            }
        }catch (Exception e){
            log.error("查询角色列表异常：",e);
        }

        return authRoleRespDTOList;
    }

    @Override
    public List<AuthRoleRespDTO> queryAuthRoleListByRole2Staff(AuthRoleReqDTO authRoleReqDTO) throws Exception {


        return null;
    }
}
