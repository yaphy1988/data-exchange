package com.ai.bdex.dataexchange.busi.user.controller;

import com.ai.bdex.dataexchange.busi.user.entity.AuthStaffVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffRespDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.CollectionUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yx on 2017/5/31.
 */
@Controller
@RequestMapping(value = "/authStaff")
public class AuthStaffManagerController {

    private static final Logger log = LoggerFactory.getLogger(AuthStaffManagerController.class);

    @DubboConsumer
    private IAuthStaffRSV iAuthStaffRSV;

    @RequestMapping(value = "/pageInit")
    public String pageInit(HttpServletRequest request, HttpServletResponse response){

        return "user_manage";
    }

    @RequestMapping(value = "/queryAuthStaffPage")
    public String queryAuthStaffPage(HttpServletRequest request, HttpServletResponse response, AuthStaffDTO authStaffDTO){

        if (authStaffDTO == null){
            authStaffDTO = new AuthStaffDTO();
        }

        PageResponseDTO<AuthStaffVO> pageInfo = new PageResponseDTO<AuthStaffVO>();
        try{
            authStaffDTO.setPageSize(10);
            authStaffDTO.setGridQuerySortName("create_time");
            authStaffDTO.setGridQuerySortOrder("desc");
            PageResponseDTO<AuthStaffRespDTO> pageResponseDTO = iAuthStaffRSV.queryAuthStaffPage(authStaffDTO);
            if (pageResponseDTO!=null){
                ObjectCopyUtil.copyObjValue(pageResponseDTO,pageInfo,null,false);
                List<AuthStaffVO> authStaffVOList = new ArrayList<AuthStaffVO>();
                if (!CollectionUtil.isEmpty(pageResponseDTO.getResult())){
                    for (AuthStaffRespDTO authStaffRespDTO : pageResponseDTO.getResult()){
                        AuthStaffVO authStaffVO = new AuthStaffVO();
                        ObjectCopyUtil.copyObjValue(authStaffRespDTO,authStaffVO,null,false);
                        if ("0".equals(authStaffVO.getStaffType())){
                            authStaffVO.setStaffTypeName("买家");
                        }else if ("1".equals(authStaffVO.getStaffType())){
                            authStaffVO.setStaffTypeName("管理员");
                        }
                        authStaffVO.setStaffFlagName(translateAuthStaffFlag(authStaffVO.getStaffFlag()));
                        authStaffVOList.add(authStaffVO);
                    }
                }
                pageInfo.setResult(authStaffVOList);
            }
        }catch (Exception e){
            log.error("分页查询用户信息异常：",e);
        }

        request.setAttribute("pageInfo",pageInfo);
        return "user_manage :: #staffList";
    }

    /**
     * 生失效，加解锁用户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/doStaffFlag")
    @ResponseBody
    public AjaxJson doStaffFlag(HttpServletRequest request,HttpServletResponse response){
        AjaxJson ajaxJson = new AjaxJson();
        String targetStaffFlag = request.getParameter("targetStaffFlag");
        String staffFlag = request.getParameter("targetStaffFlag");
        String staffId = request.getParameter("staffId");

        if (StringUtil.isBlank(staffId)){
            log.error("生失效、加锁用户失败，入参staffId为空！");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        try {
            AuthStaffDTO authStaffDTO = new AuthStaffDTO();
            authStaffDTO.setStaffId(staffId);
            authStaffDTO.setStaffFlag(targetStaffFlag);
            if ("2".equals(targetStaffFlag)){
                authStaffDTO.setLockStatus("0");
                authStaffDTO.setLockTime(new Date());
            }
            if ("1".equals(targetStaffFlag) && "2".equals(staffFlag)){
                authStaffDTO.setLockStatus("1");
            }
            iAuthStaffRSV.updateAuthStaffInfo(authStaffDTO);
            ajaxJson.setSuccess(true);
        }catch (Exception e){
            log.error("更新staffFlag异常：",e);
        }

        return ajaxJson;
    }


    @RequestMapping(value = "/initStaffInfo")
    public String initStaffInfo(HttpServletRequest request,HttpServletResponse response){

        String staffId = request.getParameter("staffId");
        if (!StringUtil.isBlank(staffId)){
            try {
                AuthStaffRespDTO authStaffRespDTO = iAuthStaffRSV.selectByPrimaryKey(staffId);
                request.setAttribute("authStaffInit",authStaffRespDTO);
            }catch (Exception e){
                log.error("根据staffId查询用户信息异常：",e);
            }

        }

        return "user_manage :: #staffInfoModalTable";
    }


    private String translateAuthStaffFlag(String staffFlag){
        String flagName = "";
        if ("0".equals(staffFlag)){
            flagName = "失效";
        }else if ("1".equals(staffFlag)){
            flagName = "正常";
        }else if ("2".equals(staffFlag)){
            flagName = "加锁";
        }
        return flagName;
    }
}
