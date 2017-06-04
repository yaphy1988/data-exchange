package com.ai.bdex.dataexchange.busi.user.controller;

import com.ai.bdex.dataexchange.busi.user.entity.AuthRoleVO;
import com.ai.bdex.dataexchange.busi.user.entity.AuthStaffVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.*;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthRole2StaffRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthRoleRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffPassRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StaffUtil;
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
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

    @DubboConsumer
    private IAuthStaffPassRSV iAuthStaffPassRSV;

    @DubboConsumer
    private IAuthRoleRSV iAuthRoleRSV;

    @DubboConsumer
    private IAuthRole2StaffRSV iAuthRole2StaffRSV;

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
        String isEdit = request.getParameter("isEdit");
        if (!StringUtil.isBlank(staffId)){
            try {
                AuthStaffRespDTO authStaffRespDTO = iAuthStaffRSV.selectByPrimaryKey(staffId);
                request.setAttribute("staffInfoInit",authStaffRespDTO);
            }catch (Exception e){
                log.error("根据staffId查询用户信息异常：",e);
            }
        }
        request.setAttribute("isEdit",isEdit);

        return "user_manage :: #staffInfoModalTable";
    }


    @RequestMapping(value = "/saveStaffInfo")
    @ResponseBody
    public AjaxJson saveStaffInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session, AuthStaffDTO authStaffDTO){
        AjaxJson ajaxJson = new AjaxJson();

        if (authStaffDTO == null){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("保存失败，系统错误，请重试或联系管理员！");
            return ajaxJson;
        }
        String isEdit = request.getParameter("isEdit");

        try {
            if (!"1".equals(isEdit)){//新增
                //查询用户名是否重复
                AuthStaffDTO input1 = new AuthStaffDTO();
                input1.setStaffId(authStaffDTO.getStaffId());
                AuthStaffDTO phoneResult1 = iAuthStaffRSV.findAuthStaffInfo(input1);
                if(phoneResult1!=null){
                    ajaxJson.setSuccess(false);
                    ajaxJson.setMsg("保存失败，用户ID已经存在!");
                    return ajaxJson;
                }
                //查询手机号是否重复
                AuthStaffDTO input = new AuthStaffDTO();
                input.setSerialNumber(authStaffDTO.getSerialNumber());
                AuthStaffDTO phoneResult = iAuthStaffRSV.findAuthStaffInfo(input);
                if(phoneResult!=null){
                    ajaxJson.setSuccess(false);
                    ajaxJson.setMsg("保存失败，手机号已经存在!");
                    return ajaxJson;
                }

                if (!StringUtil.isBlank(authStaffDTO.getBirthdayStr())){
                    authStaffDTO.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(authStaffDTO.getBirthdayStr()));
                }
                authStaffDTO.setCreateStaff(StaffUtil.getStaffId(session));
                authStaffDTO.setCreateTime(new Date());
                authStaffDTO.setCreateFrom("1");//管理员新增
                authStaffDTO.setStaffFlag("1");//默认有效
                authStaffDTO.setLockStatus("1");//默认正常
                authStaffDTO.setAuthenFlag("1");//默认认证通过
                authStaffDTO.setStartDate(new Date());
                authStaffDTO.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2099-12-31 23:59:59"));
                iAuthStaffRSV.insertInfoToAuthStaff(authStaffDTO);

                try {
                    AuthStaffPassDTO authStaffPassDTO = new AuthStaffPassDTO();
                    authStaffPassDTO.setStaffId(authStaffDTO.getStaffId());
                    authStaffPassDTO.setStaffPasswd("123456");
                    authStaffPassDTO.setIsFirst("1");
                    authStaffPassDTO.setCreateStaff(StaffUtil.getStaffId(session));
                    authStaffPassDTO.setCreateTime(new Date());
                    authStaffPassDTO.setInvalidTime(new SimpleDateFormat("yyyy-MM-dd").parse("2099-12-31"));
                    iAuthStaffPassRSV.savePassInfo(authStaffPassDTO);
                }catch (Exception e){
                    log.error("自动设置账户密码异常：",e);
                    ajaxJson.setMsg("保存失败，请重试或联系管理员");
                    ajaxJson.setSuccess(false);
                    return ajaxJson;
                }
                ajaxJson.setSuccess(true);
                ajaxJson.setMsg("新增用户成功！");

            }else{//编辑
                authStaffDTO.setUpdateStaff(StaffUtil.getStaffId(session));
                if (!StringUtil.isBlank(authStaffDTO.getBirthdayStr())){
                    authStaffDTO.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(authStaffDTO.getBirthdayStr()));
                }
                iAuthStaffRSV.updateAuthStaffInfo(authStaffDTO);
                ajaxJson.setMsg("编辑用户信息保存成功！");
            }

        }catch (Exception e){
            log.error("保存用户信息异常：",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("保存失败，请重试或联系管理员！");
        }

        return ajaxJson;
    }

    @RequestMapping(value = "/resetStaffPass")
    @ResponseBody
    public AjaxJson resetStaffPass(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        AjaxJson ajaxJson = new AjaxJson();
        String staffId = request.getParameter("staffId");
        if (StringUtil.isBlank(staffId)){
            log.error("重置密码异常，入参staffId为空！");
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("重置密码失败！");
            return ajaxJson;
        }

        try {
            AuthStaffPassDTO authStaffPassDTO = new AuthStaffPassDTO();
            authStaffPassDTO.setPasswdFlag("0");
            authStaffPassDTO.setStaffPasswd("123456");
            authStaffPassDTO.setStaffId(staffId);
            authStaffPassDTO.setUpdateStaff(StaffUtil.getStaffId(session));
            iAuthStaffPassRSV.updatePasswd(authStaffPassDTO);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("重置密码成功！");
        }catch (Exception e){
            log.error("重置密码异常：",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("重置密码失败！");
        }


        return ajaxJson;
    }

    @RequestMapping(value = "/giveAuthorityInit")
    public String giveAuthorityInit(HttpServletRequest request,HttpServletResponse response){
        String staffId = request.getParameter("staffId");

        List<AuthRoleVO> authRoleVOList = new ArrayList<AuthRoleVO>();
        List<AuthRoleVO> haveSelRoleList = new ArrayList<AuthRoleVO>();
        List<AuthRoleVO> notSelRoleList = new ArrayList<AuthRoleVO>();
        try{
            AuthRoleReqDTO authRoleReqDTO = new AuthRoleReqDTO();
            authRoleReqDTO.setStatus("1");
            List<AuthRoleRespDTO> authRoleRespDTOList = iAuthRoleRSV.queryAuthRoleList(authRoleReqDTO);
            if (!CollectionUtil.isEmpty(authRoleRespDTOList)){
                for (AuthRoleRespDTO authRoleRespDTO : authRoleRespDTOList){
                    AuthRoleVO authRoleVO = new AuthRoleVO();
                    ObjectCopyUtil.copyObjValue(authRoleRespDTO,authRoleVO,null,false);
                    authRoleVOList.add(authRoleVO);
                }
            }

            AuthRoleReqDTO role2StaffReq = new AuthRoleReqDTO();
            role2StaffReq.setStaffId(staffId);
            List<AuthRoleRespDTO> haveSelRoleRespList = iAuthRoleRSV.queryAuthRoleListByRole2Staff(role2StaffReq);
            if (!CollectionUtil.isEmpty(haveSelRoleRespList)){
                for (AuthRoleRespDTO authRoleRespDTO : haveSelRoleRespList){
                    AuthRoleVO authRoleVO = new AuthRoleVO();
                    ObjectCopyUtil.copyObjValue(authRoleRespDTO,authRoleVO,null,false);
                    haveSelRoleList.add(authRoleVO);
                }
            }
        }catch (Exception e){
            log.error("初始化授权弹出框异常：",e);
        }

        if (!CollectionUtil.isEmpty(authRoleVOList) && !CollectionUtil.isEmpty(haveSelRoleList)){
            for (AuthRoleVO authRoleVO : authRoleVOList){
                boolean ifSelFlag = false;
                for (AuthRoleVO haveSelRoleVO : haveSelRoleList){
                    if (authRoleVO.getRoleId().intValue() == haveSelRoleVO.getRoleId().intValue()){
                        ifSelFlag = true;
                        break;
                    }
                }
                if (!ifSelFlag){
                    notSelRoleList.add(authRoleVO);
                }
            }
        }else if (!CollectionUtil.isEmpty(authRoleVOList) && CollectionUtil.isEmpty(haveSelRoleList)){
            notSelRoleList = authRoleVOList;
        }

        request.setAttribute("authRoleVOList",notSelRoleList);
        request.setAttribute("haveSelRoleList",haveSelRoleList);
        return "user_manage :: #authStaff2RoleModalContent";
    }

    @RequestMapping(value = "/confirmStaff2Role")
    @ResponseBody
    public AjaxJson confirmStaff2Role(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        AjaxJson ajaxJson = new AjaxJson();

        String staffId = request.getParameter("staffId");
        String roleIdStr = request.getParameter("roleIdStr");

        List<String> roleIdList = new ArrayList<String>();
        if (!StringUtil.isBlank(roleIdStr)){
            roleIdList = Arrays.asList(roleIdStr.split(","));
        }

        try{
            AuthRole2StaffReqDTO authRole2StaffReqDTO = new AuthRole2StaffReqDTO();
            authRole2StaffReqDTO.setStaffId(staffId);
            authRole2StaffReqDTO.setStatus("0");
            iAuthRole2StaffRSV.updateRole2Staff(authRole2StaffReqDTO);

            for (String roleId : roleIdList){
                AuthRole2StaffRespDTO authRole2StaffRespDTO = iAuthRole2StaffRSV.queryAuthRole2StaffByKey(staffId,Integer.parseInt(roleId));
                if (authRole2StaffRespDTO!=null){
                    AuthRole2StaffReqDTO updateReqDTO = new AuthRole2StaffReqDTO();
                    updateReqDTO.setStaffId(staffId);
                    updateReqDTO.setRoleId(Integer.parseInt(roleId));
                    updateReqDTO.setStatus("1");
                    updateReqDTO.setUpdateId(StaffUtil.getStaffId(session));
                    updateReqDTO.setUpdateDate(new Date());
                    iAuthRole2StaffRSV.updateRole2Staff(updateReqDTO);
                }else {
                    AuthRole2StaffReqDTO insertReqDTO = new AuthRole2StaffReqDTO();
                    insertReqDTO.setStaffId(staffId);
                    insertReqDTO.setRoleId(Integer.parseInt(roleId));
                    insertReqDTO.setStatus("1");
                    insertReqDTO.setUpdateId(StaffUtil.getStaffId(session));
                    insertReqDTO.setUpdateDate(new Date());
                    iAuthRole2StaffRSV.insertRole2Staff(insertReqDTO);
                }
            }
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("保存成功！");
        }catch (Exception e){
            log.error("用户授权异常：",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("用户授权失败！");
        }

        return ajaxJson;
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
