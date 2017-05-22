package com.ai.bdex.dataexchange.busi.api.controller;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.*;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipProviderServiceMgrRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceManagerRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IServiceMessageRSV;
import com.ai.bdex.dataexchange.busi.api.entity.*;
import com.ai.bdex.dataexchange.busi.gds.entity.AipServiceInfoVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfoVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.util.MongoFileUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yx on 2017/5/6.
 */
@Controller
@RequestMapping(value = "/aipEntry")
public class AipEntryController {

    private final static Logger log = LoggerFactory.getLogger(AipEntryController.class);

    @DubboConsumer(timeout = 30000)
    private IAipServiceInfoRSV iAipServiceInfoRSV;
    @DubboConsumer(timeout = 30000)
    private IServiceMessageRSV iServiceMessageRSV;
    @DubboConsumer(timeout = 30000)
    private IAipServiceManagerRSV iAipServiceManagerRSV;
    @DubboConsumer(timeout = 30000)
    private IAipProviderServiceMgrRSV iAipProviderServiceMgrRSV;

    /**
     * 初始化基本信息界面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/baseInfoInit")
    public ModelAndView baseInfoInit(HttpServletRequest request, HttpServletResponse response){

        String serviceId = request.getParameter("serviceId");
        String version = request.getParameter("version");
        String isView = request.getParameter("isView");//是否是查看页面，1是，0否
        if (StringUtil.isBlank(isView)){
            isView = "0";
        }
        request.setAttribute("isView",isView);
        String isEdit = request.getParameter("isEdit");//是否是编辑页面，1是，0否
        if(StringUtil.isBlank(isEdit)){
            isEdit = "0";
        }
        request.setAttribute("isEdit",isEdit);

        String viewName = "aip_document_deploy";
        ModelAndView mv = new ModelAndView(viewName);

        AipServiceDetailsInfoVO aipServiceDetailsInfoVO = new AipServiceDetailsInfoVO();

        //判断是否是录入界面
        if (StringUtil.isBlank(serviceId) && StringUtil.isBlank(version)){
            mv.addObject("aipServiceBaseInfo",new AipServiceDetailsInfoVO());
            mv.addObject("aipServiceInParaList",new ArrayList<AipServiceInParaVO>());
            mv.addObject("aipServiceOutParaList",new ArrayList<AipServiceOutParaVO>());
            return mv;
        }

        //aip基本信息
        try {
            AipServiceInfoDTO aipServiceInfoDTO = new AipServiceInfoDTO();
//            if (StringUtil.isBlank(version)){
//                aipServiceInfoDTO = iAipServiceInfoRSV.selectServiceByServiceIdWithInitversion(serviceId);
//            }else{
//                aipServiceInfoDTO= iAipServiceInfoRSV.selectServiceByPk(serviceId,version);
//            }
            AipServiceInfoReqDTO aipServiceInfoReqDTO = new AipServiceInfoReqDTO();
            aipServiceInfoReqDTO.setServiceId(serviceId);
            if (!StringUtil.isBlank(version)){
                aipServiceInfoReqDTO.setVersion(version);
            }
            aipServiceInfoReqDTO.setStatus("");
            aipServiceInfoDTO = iAipServiceInfoRSV.queryAipServiceInfo(aipServiceInfoReqDTO);
            if (aipServiceInfoDTO!=null){
                ObjectCopyUtil.copyObjValue(aipServiceInfoDTO,aipServiceDetailsInfoVO,null,false);
                AipProviderInfoRespDTO aipProviderInfoRespDTO = iAipProviderServiceMgrRSV.queryAipProviderInfoByProviderId(aipServiceDetailsInfoVO.getProviderId());
                if (aipProviderInfoRespDTO !=null){
                    aipServiceDetailsInfoVO.setProviderName(aipProviderInfoRespDTO.getProviderName());
                }
                AipProviderServiceInfoRespDTO aipProviderServiceInfoRespDTO = iAipProviderServiceMgrRSV.queryPServiceByKey(aipServiceDetailsInfoVO.getpServiceId(),aipServiceDetailsInfoVO.getpVersion());
                if (aipProviderServiceInfoRespDTO != null) {
                    aipServiceDetailsInfoVO.setpServiceName(aipProviderServiceInfoRespDTO.getServiceName());
                }
                if (!StringUtil.isBlank(aipServiceDetailsInfoVO.getReturnExample())){
                    aipServiceDetailsInfoVO.setReturnExample(ImageUtil.getStaticDocUrl(aipServiceDetailsInfoVO.getReturnExample(),"html"));
                }
            }else{
                aipServiceDetailsInfoVO = new AipServiceDetailsInfoVO();
            }
        } catch (Exception e) {
            log.error("查询aip信息异常：",e);
        }


        mv.addObject("aipServiceBaseInfo",aipServiceDetailsInfoVO);
        return mv;
    }


    @RequestMapping(value = "/submitInfo")
    @ResponseBody
    public AjaxJson submitInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session, AipServiceInfoReqDTO aipServiceInfoReqDTO){
        AjaxJson ajaxJson = new AjaxJson();

        if (aipServiceInfoReqDTO==null){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("保存失败，入参为空！");
        }

        if (StringUtil.isBlank(aipServiceInfoReqDTO.getServiceId()) && StringUtil.isBlank(aipServiceInfoReqDTO.getVersion())){//新增
            aipServiceInfoReqDTO.setVersion("1.0");
            aipServiceInfoReqDTO.setCreateStaff(StaffUtil.getStaffId(session));
            aipServiceInfoReqDTO.setCreateTime(new Date());
            aipServiceInfoReqDTO.setStatus("0");
            aipServiceInfoReqDTO.setType("01");

            String serviceId = "";
            try{
                if (!StringUtil.isBlank(aipServiceInfoReqDTO.getReturnExample())){
                    String htmlData= HtmlUtils.htmlUnescape(aipServiceInfoReqDTO.getReturnExample());
                    //保存静态文件到静态文件服务器
                    String staticUrl = MongoFileUtil.saveFile(htmlData.getBytes("utf-8"),"gdsContent", ".html");
                    aipServiceInfoReqDTO.setReturnExample(staticUrl);
                }
                serviceId = iAipServiceManagerRSV.insertAipService(aipServiceInfoReqDTO);
                ajaxJson.setSuccess(true);
                ajaxJson.setMsg("serviceId="+serviceId+"&version=1.0");
            }catch (Exception e){
                log.error("保存aip服务基本信息异常：",e);
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("保存aip服务基本信息异常!");
                return ajaxJson;
            }
            if (StringUtil.isBlank(serviceId)){
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("保存aip服务基本信息异常，请联系管理员！");
                return ajaxJson;
            }

        }else if (!StringUtil.isBlank(aipServiceInfoReqDTO.getServiceId())){//编辑
            if (!StringUtil.isBlank(aipServiceInfoReqDTO.getVersion())){
                aipServiceInfoReqDTO.setVersion("");
            }
            aipServiceInfoReqDTO.setUpdateStaff(StaffUtil.getStaffId(session));
            aipServiceInfoReqDTO.setUpdateTime(new Date());
            try{
                if (!StringUtil.isBlank(aipServiceInfoReqDTO.getReturnExample())){
                    String htmlData= HtmlUtils.htmlUnescape(aipServiceInfoReqDTO.getReturnExample());
                    //保存静态文件到静态文件服务器
                    String staticUrl = MongoFileUtil.saveFile(htmlData.getBytes("utf-8"),"gdsContent", ".html");
                    aipServiceInfoReqDTO.setReturnExample(staticUrl);
                }
                iAipServiceManagerRSV.updateAipServiceByServiceId(aipServiceInfoReqDTO);
                ajaxJson.setSuccess(true);
            }catch (Exception e){
                log.error("保存aip服务基本信息异常：",e);
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("保存aip服务基本信息异常!");
                return ajaxJson;
            }
        }


        return ajaxJson;
    }

    @RequestMapping(value = "/queryProviderServicePage")
    public String queryProviderServicePage(HttpServletRequest request,HttpServletResponse response,AipProviderServiceInfoReqDTO aipProviderServiceInfoReqDTO){

        PageResponseDTO<AipProviderServiceInfoVO> pageInfo = new PageResponseDTO<AipProviderServiceInfoVO>();
        try {
            aipProviderServiceInfoReqDTO.setStatus("1");
            aipProviderServiceInfoReqDTO.setPageSize(10);
            PageResponseDTO<AipProviderServiceInfoRespDTO> pageResponseDTO = iAipProviderServiceMgrRSV.pagePServiceInfo(aipProviderServiceInfoReqDTO);
            if (pageResponseDTO!=null){
                ObjectCopyUtil.copyObjValue(pageResponseDTO,pageInfo,null,false);
                if (!CollectionUtil.isEmpty(pageResponseDTO.getResult())){
                    List<AipProviderServiceInfoVO> aipProviderServiceInfoVOList = new ArrayList<AipProviderServiceInfoVO>();
                    for (AipProviderServiceInfoRespDTO aipProviderServiceInfoRespDTO : pageResponseDTO.getResult()){
                        AipProviderServiceInfoVO aipProviderServiceInfoVO = new AipProviderServiceInfoVO();
                        ObjectCopyUtil.copyObjValue(aipProviderServiceInfoRespDTO,aipProviderServiceInfoVO,null,false);
                        AipProviderInfoRespDTO aipProviderInfoRespDTO = iAipProviderServiceMgrRSV.queryAipProviderInfoByProviderId(aipProviderServiceInfoVO.getpServiceId());
                        aipProviderServiceInfoVO.setProviderName(aipProviderInfoRespDTO.getProviderName());
                        aipProviderServiceInfoVOList.add(aipProviderServiceInfoVO);
                    }
                    pageInfo.setResult(aipProviderServiceInfoVOList);
                }
            }
        }catch (Exception e){
            log.error("查询供应商服务分页列表异常：",e);
        }

        request.setAttribute("pageInfo",pageInfo);
        return "aip_document_deploy :: #pServiceModal_aipTable";
    }

    /**
     * 初始化出参入参界面
     * @param request
     * @param response
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/paraInfoInit")
    public String paraInfoInit(HttpServletRequest request,HttpServletResponse response) throws BusinessException {
        String serviceId = request.getParameter("serviceId");
        String version = request.getParameter("version");
        String isView = request.getParameter("isView");//是否是查看页面，1是，0否
        if(StringUtil.isBlank(isView)){
            isView = "0";
        }
        request.setAttribute("isView",isView);
        String isEdit = request.getParameter("isEdit");//是否是编辑页面，1是，0否
        if(StringUtil.isBlank(isEdit)){
            isEdit = "0";
        }
        request.setAttribute("isEdit",isEdit);

        List<AipServiceInParaVO> aipServiceInParaVOList = new ArrayList<AipServiceInParaVO>();
        List<AipServiceOutParaVO> aipServiceOutParaVOList = new ArrayList<AipServiceOutParaVO>();

        if (StringUtil.isBlank(serviceId) && StringUtil.isBlank(version)){
            throw new BusinessException("初始化aip出参入参输入界面异常，serviceId和version为空！");
        }
        if (!StringUtil.isBlank(serviceId) && !StringUtil.isBlank(version)){
            //入参信息
            try{
                List<AipServiceInParaDTO> aipServiceInParaDTOList = iServiceMessageRSV.queryAipServiceInParaList(serviceId, version);
                if (!CollectionUtil.isEmpty(aipServiceInParaDTOList)){
                    for (AipServiceInParaDTO aipServiceInParaDTO : aipServiceInParaDTOList){
                        AipServiceInParaVO aipServiceInParaVO = new AipServiceInParaVO();
                        ObjectCopyUtil.copyObjValue(aipServiceInParaDTO,aipServiceInParaVO,null,false);
                        aipServiceInParaVOList.add(aipServiceInParaVO);
                    }
                }
            }catch (Exception e){
                log.error("查询aip入参信息异常：",e);
            }

            //出参信息
            try{
                List<AipServiceOutParaDTO> aipServiceOutParaDTOList = iServiceMessageRSV.queryAipServiceOutParaList(serviceId, version);
                if (!CollectionUtil.isEmpty(aipServiceOutParaDTOList)){
                    for (AipServiceOutParaDTO aipServiceOutParaDTO : aipServiceOutParaDTOList){
                        AipServiceOutParaVO aipServiceOutParaVO = new AipServiceOutParaVO();
                        ObjectCopyUtil.copyObjValue(aipServiceOutParaDTO,aipServiceOutParaVO,null,false);
                        aipServiceOutParaVOList.add(aipServiceOutParaVO);
                    }
                }
            }catch (Exception e){
                log.error("查询aip出参信息异常：",e);
            }
        }


        request.setAttribute("serviceId",serviceId);
        request.setAttribute("version",version);
        request.setAttribute("aipServiceInParaList",aipServiceInParaVOList);
        request.setAttribute("aipServiceOutParaList",aipServiceOutParaVOList);
        return "aip_document_deploy_para";
    }

    /**
     * 提交保存参数信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/submitParaInfo")
    @ResponseBody
    public AjaxJson submitParaInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        AjaxJson ajaxJson = new AjaxJson();
        String serviceId = request.getParameter("serviceId");
        String version = request.getParameter("version");
        String inParaStr = request.getParameter("inParaStr");
        String outParaStr = request.getParameter("outParaStr");
        List<AipServiceInParaDTO> inParaDTOList = new ArrayList<AipServiceInParaDTO>();
        List<AipServiceOutParaDTO> outParaDTOList = new ArrayList<AipServiceOutParaDTO>();
        if (!StringUtil.isBlank(inParaStr)){
            inParaDTOList = JSONArray.parseArray(inParaStr,AipServiceInParaDTO.class);
        }
        if (!StringUtil.isBlank(outParaStr)){
            outParaDTOList = JSONArray.parseArray(outParaStr,AipServiceOutParaDTO.class);
        }

        //返回的不为空则继续保存出参入参信息
        if (!StringUtil.isBlank(serviceId) && !StringUtil.isBlank(version)){

            //入参信息
            List<AipServiceInParaDTO> aipServiceInParaDTOList = new ArrayList<AipServiceInParaDTO>();
            try {
                aipServiceInParaDTOList = iAipServiceManagerRSV.queryServiceInParaList(serviceId,version);
            } catch (Exception e) {
                log.error("查询已配置入参信息异常：",e);
            }
            if (!CollectionUtil.isEmpty(aipServiceInParaDTOList)){
                AipServiceInParaDTO updateInParaDTO = new AipServiceInParaDTO();
                updateInParaDTO.setServiceId(serviceId);
                updateInParaDTO.setVersion(version);
                updateInParaDTO.setStatus("0");
                try {
                    iAipServiceManagerRSV.updateInParaByServiceIdAndVersion(updateInParaDTO);
                } catch (Exception e) {
                    log.error("失效已配置入参异常：",e);
                }
            }
            try{
                if (!CollectionUtil.isEmpty(inParaDTOList)){
                    for (AipServiceInParaDTO aipServiceInParaDTO : inParaDTOList){
                        aipServiceInParaDTO.setServiceId(serviceId);
                        aipServiceInParaDTO.setVersion("1.0");
                        aipServiceInParaDTO.setStatus("1");
                        aipServiceInParaDTO.setCreateStaff(StaffUtil.getStaffId(session));
                        aipServiceInParaDTO.setCreateTime(new Date());
                        iAipServiceManagerRSV.insertInPara(aipServiceInParaDTO);
                    }
                }
            }catch (Exception e){
                log.error("插入aip服务的入参信息列表异常：",e);
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("保存aip服务信息异常！");
                return ajaxJson;
            }

            //出参信息
            List<AipServiceOutParaDTO> aipServiceOutParaDTOList = new ArrayList<AipServiceOutParaDTO>();
            try {
                aipServiceOutParaDTOList = iAipServiceManagerRSV.queryServiceOutParaList(serviceId,version);
            } catch (Exception e) {
                log.error("查询已配置出参信息异常：",e);
            }
            if (!CollectionUtil.isEmpty(aipServiceOutParaDTOList)){
                AipServiceOutParaDTO updateOutParaDTO = new AipServiceOutParaDTO();
                updateOutParaDTO.setServiceId(serviceId);
                updateOutParaDTO.setVersion(version);
                updateOutParaDTO.setStatus("0");
                try {
                    iAipServiceManagerRSV.updateOutParaByServiceIdAndVersion(updateOutParaDTO);
                } catch (Exception e) {
                    log.error("失效已配置出参异常：",e);
                }
            }
            try{
                if (!CollectionUtil.isEmpty(outParaDTOList)){
                    for (AipServiceOutParaDTO aipServiceOutParaDTO : outParaDTOList){
                        aipServiceOutParaDTO.setServiceId(serviceId);
                        aipServiceOutParaDTO.setVersion("1.0");
                        aipServiceOutParaDTO.setStatus("1");
                        aipServiceOutParaDTO.setType("01");
                        aipServiceOutParaDTO.setCreateStaff(StaffUtil.getStaffId(session));
                        aipServiceOutParaDTO.setCreateTime(new Date());
                        iAipServiceManagerRSV.insertOutPara(aipServiceOutParaDTO);
                    }
                }
            }catch (Exception e){
                log.error("插入aip服务的出参信息列表异常：",e);
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("保存aip服务信息异常！");
                return ajaxJson;
            }
        }else{
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("保存aip服务基本信息异常，请联系管理员！");
            return ajaxJson;
        }
        ajaxJson.setSuccess(true);
        ajaxJson.setMsg("serviceId="+serviceId+"&version=1.0");

        return ajaxJson;
    }

    /**
     * 错误代码信息录入初始化页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/errorCodeInfoInit")
    public String errorCodeInfoInit(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String serviceId = request.getParameter("serviceId");
        String version = request.getParameter("version");
        request.setAttribute("serviceId",serviceId);
        request.setAttribute("version",version);
        String isView = request.getParameter("isView");//是否是查看页面，1是，0否
        if (StringUtil.isBlank(isView)){
            isView = "0";
        }
        request.setAttribute("isView",isView);
        String isEdit = request.getParameter("isEdit");//是否是编辑页面，1是，0否
        if(StringUtil.isBlank(isEdit)){
            isEdit = "0";
        }
        request.setAttribute("isEdit",isEdit);

        if (StringUtil.isBlank(serviceId)){
            throw new BusinessException("初始化页面异常，serviceId为空");
        }
        if (StringUtil.isBlank(version)){
            version = "1.0";
        }

        List<AipServiceErrorInfoVO> systemErrorInfoVOList = new ArrayList<AipServiceErrorInfoVO>();
        List<AipServiceErrorInfoVO> serviceErrorInfoVOList = new ArrayList<AipServiceErrorInfoVO>();
        List<AipServiceErrorInfoDTO> aipServiceErrorInfoDTOList = new ArrayList<AipServiceErrorInfoDTO>();
        try {
            aipServiceErrorInfoDTOList = iAipServiceManagerRSV.queryServiceErrorInfoList(serviceId,version);
        }catch (Exception e){
            log.error("查询aip服务已配置的错误代码信息异常：",e);
        }
        if (!CollectionUtil.isEmpty(aipServiceErrorInfoDTOList)){
            for (AipServiceErrorInfoDTO aipServiceErrorInfoDTO : aipServiceErrorInfoDTOList){
                AipServiceErrorInfoVO aipServiceErrorInfoVO = new AipServiceErrorInfoVO();
                ObjectCopyUtil.copyObjValue(aipServiceErrorInfoDTO,aipServiceErrorInfoVO,null,false);
                if ("00".equals(aipServiceErrorInfoVO.getType())){//服务级错误
                    serviceErrorInfoVOList.add(aipServiceErrorInfoVO);
                }else if ("01".equals(aipServiceErrorInfoVO.getType())){//系统级错误
                    systemErrorInfoVOList.add(aipServiceErrorInfoVO);
                }
            }
        }

        request.setAttribute("serviceErrorList",serviceErrorInfoVOList);
        request.setAttribute("systemErrorList",systemErrorInfoVOList);
        return "aip_document_deploy2";
    }

    /**
     * 提交错误代码信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/submitErrCodeInfo")
    @ResponseBody
    public AjaxJson submitErrCodeInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        AjaxJson ajaxJson = new AjaxJson();

        String serviceId = request.getParameter("serviceId");
        String version = request.getParameter("version");
        if (StringUtil.isBlank(serviceId) || StringUtil.isBlank(version)){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("保存失败，服务ID或版本号为空！");
            return ajaxJson;
        }
        String sysErrCodeStr = request.getParameter("sysErrCodeStr");
        String serErrCodeStr = request.getParameter("serErrCodeStr");
        List<AipServiceErrorInfoReqDTO> sysErrCodeReqList = new ArrayList<AipServiceErrorInfoReqDTO>();
        List<AipServiceErrorInfoReqDTO> serErrCodeReqList = new ArrayList<AipServiceErrorInfoReqDTO>();
        if (!StringUtil.isBlank(sysErrCodeStr)){
            sysErrCodeReqList = JSONArray.parseArray(sysErrCodeStr,AipServiceErrorInfoReqDTO.class);
        }
        if (!StringUtil.isBlank(serErrCodeStr)){
            serErrCodeReqList = JSONArray.parseArray(serErrCodeStr,AipServiceErrorInfoReqDTO.class);
        }
        if (!StringUtil.isBlank(serviceId) && !StringUtil.isBlank(version)){
            List<AipServiceErrorInfoDTO> aipServiceErrorInfoDTOList = new ArrayList<AipServiceErrorInfoDTO>();
            try{
                aipServiceErrorInfoDTOList = iAipServiceManagerRSV.queryServiceErrorInfoList(serviceId,version);
            }catch (Exception e){
                log.error("根据serviceId和version查询已配置的错误代码信息异常：",e);
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("根据serviceId和version查询已配置的错误代码信息异常!");
                return ajaxJson;
            }

            try{
                if (!CollectionUtil.isEmpty(aipServiceErrorInfoDTOList)){
                        AipServiceErrorInfoReqDTO invalidSerErrorReq = new AipServiceErrorInfoReqDTO();
                        invalidSerErrorReq.setStatus("0");
                        invalidSerErrorReq.setUpdateTime(new Date());
                        invalidSerErrorReq.setUpdateStaff(StaffUtil.getStaffId(session));
                        invalidSerErrorReq.setServiceId(serviceId);
                        invalidSerErrorReq.setVersion(version);
                        iAipServiceManagerRSV.updateErrorInfoByServiceIdAndVersion(invalidSerErrorReq);
                }
            }catch (Exception e){
                log.error("失效原有错误代码信息异常：",e);
                ajaxJson.setMsg("失效原有错误代码信息异常！");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }

            //服务级
            try{
                for (AipServiceErrorInfoReqDTO serviceErrorInfoReqDTO : serErrCodeReqList){
                    serviceErrorInfoReqDTO.setServiceId(serviceId);
                    serviceErrorInfoReqDTO.setVersion(version);
                    serviceErrorInfoReqDTO.setStatus("1");
                    serviceErrorInfoReqDTO.setCreateStaff(StaffUtil.getStaffId(session));
                    serviceErrorInfoReqDTO.setCreateTime(new Date());
                    serviceErrorInfoReqDTO.setType("00");
                }
                String errorStr = iAipServiceManagerRSV.insertErrorInfoBatch(serErrCodeReqList);
                if (StringUtil.isBlank(errorStr)){
                    ajaxJson.setSuccess(false);
                    ajaxJson.setMsg("保存aip服务级错误代码信息异常！");
                    return ajaxJson;
                }else{
                    ajaxJson.setSuccess(true);
                }
            }catch (Exception e){
                log.error("保存aip服务服务级错误代码信息异常：",e);
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("保存aip服务级错误代码信息异常！");
                return ajaxJson;
            }

            //系统级
            try{
                for (AipServiceErrorInfoReqDTO systemErrorInfoReqDTO : sysErrCodeReqList){
                    systemErrorInfoReqDTO.setServiceId(serviceId);
                    systemErrorInfoReqDTO.setVersion(version);
                    systemErrorInfoReqDTO.setStatus("1");
                    systemErrorInfoReqDTO.setCreateStaff(StaffUtil.getStaffId(session));
                    systemErrorInfoReqDTO.setCreateTime(new Date());
                    systemErrorInfoReqDTO.setType("01");
                }
                String errorStr = iAipServiceManagerRSV.insertErrorInfoBatch(sysErrCodeReqList);
                if (StringUtil.isBlank(errorStr)){
                    ajaxJson.setSuccess(false);
                    ajaxJson.setMsg("保存aip系统级错误代码信息异常！");
                    return ajaxJson;
                }else{
                    ajaxJson.setSuccess(true);
                    ajaxJson.setMsg("serviceId="+serviceId+"&version=1.0");
                }
            }catch (Exception e){
                log.error("保存aip服务系统级错误代码信息异常：",e);
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("保存aip系统级错误代码信息异常！");
                return ajaxJson;
            }
        }
        return ajaxJson;
    }


    /**
     * 初始化示例录入界面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exampleInfoInit")
    public String exampleInfoInit(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String serviceId = request.getParameter("serviceId");
        String version = request.getParameter("version");
        String isView = request.getParameter("isView");//是否是查看页面，1是，0否
        if (StringUtil.isBlank(isView)){
            isView = "0";
        }
        request.setAttribute("isView",isView);
        String isEdit = request.getParameter("isEdit");//是否是编辑页面，1是，0否
        if(StringUtil.isBlank(isEdit)){
            isEdit = "0";
        }
        request.setAttribute("isEdit",isEdit);
        if (StringUtil.isBlank(serviceId) || StringUtil.isBlank(version)){
            throw new BusinessException("初始化示例代码录入界面异常，服务ID或版本号为空！");
        }

        List<AipServiceCodeInfoVO> aipServiceCodeInfoVOList = new ArrayList<AipServiceCodeInfoVO>();
        try {
            List<AipServiceCodeInfoDTO> aipServiceCodeInfoDTOList = iAipServiceManagerRSV.queryAipServiceCodeList(serviceId,version);
            if (!CollectionUtil.isEmpty(aipServiceCodeInfoDTOList)){
                for (AipServiceCodeInfoDTO aipServiceCodeInfoDTO : aipServiceCodeInfoDTOList){
                    AipServiceCodeInfoVO aipServiceCodeInfoVO = new AipServiceCodeInfoVO();
                    ObjectCopyUtil.copyObjValue(aipServiceCodeInfoDTO,aipServiceCodeInfoVO,null,false);
                    if (!StringUtil.isBlank(aipServiceCodeInfoVO.getDocId())){
                        aipServiceCodeInfoVO.setDocId(ImageUtil.getStaticDocUrl(aipServiceCodeInfoVO.getDocId(),"html"));
                    }
                    aipServiceCodeInfoVOList.add(aipServiceCodeInfoVO);
                }
            }
        }catch (Exception e){
            log.error("查询已配置的aip服务示例代码信息异常：",e);
        }

        request.setAttribute("aipServiceCodeList",aipServiceCodeInfoVOList);
        request.setAttribute("serviceId",serviceId);
        request.setAttribute("version",version);
        return "aip_document_deploy3";
    }

    /**
     * 保存示例代码信息
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "/sumbitExampleInfo")
    @ResponseBody
    public AjaxJson sumbitExampleInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        AjaxJson ajaxJson = new AjaxJson();
        String serviceId = request.getParameter("serviceId");
        String version = request.getParameter("version");
        if (StringUtil.isBlank(serviceId) || StringUtil.isBlank(version)){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("保存失败！");
            return ajaxJson;
        }

        String exampleStr = request.getParameter("exampleStr");
        List<AipServiceCodeInfoReqDTO> aipServiceCodeInfoReqDTOList = new ArrayList<AipServiceCodeInfoReqDTO>();
        if (!StringUtil.isBlank(exampleStr)){
            aipServiceCodeInfoReqDTOList = JSONArray.parseArray(exampleStr,AipServiceCodeInfoReqDTO.class);
        }

        List<AipServiceCodeInfoDTO> aipServiceCodeInfoDTOList = new ArrayList<AipServiceCodeInfoDTO>();
        try {
            aipServiceCodeInfoDTOList = iAipServiceManagerRSV.queryAipServiceCodeList(serviceId,version);
        }catch (Exception e){
            log.error("查询已配置的aip服务示例代码列表异常：",e);
            ajaxJson.setMsg("保存失败，请联系管理员!");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        if (!CollectionUtil.isEmpty(aipServiceCodeInfoDTOList)){
            try {
                AipServiceCodeInfoReqDTO updateReq = new AipServiceCodeInfoReqDTO();
                updateReq.setServiceId(serviceId);
                updateReq.setVersion(version);
                updateReq.setStatus("0");
                updateReq.setCreateStaff(StaffUtil.getStaffId(session));
                updateReq.setUpdateTime(new Date());
                iAipServiceManagerRSV.updateServiceCodeByServiceIdAndVersion(updateReq);
            }catch (Exception e){
                log.error("失效已配置的aip示例代码异常：",e);
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("保存失败，请重试或联系管理员！");
                return ajaxJson;
            }
        }

        try{
            if (!CollectionUtil.isEmpty(aipServiceCodeInfoReqDTOList)){
                for (AipServiceCodeInfoReqDTO aipServiceCodeInfoReqDTO : aipServiceCodeInfoReqDTOList){
                    if (!StringUtil.isBlank(aipServiceCodeInfoReqDTO.getDocId())){
                        String htmlData= HtmlUtils.htmlUnescape(aipServiceCodeInfoReqDTO.getDocId());
                        //保存静态文件到静态文件服务器
                        String staticUrl = MongoFileUtil.saveFile(htmlData.getBytes("utf-8"),"gdsContent", ".html");
                        aipServiceCodeInfoReqDTO.setDocId(staticUrl);
                    }
                    aipServiceCodeInfoReqDTO.setStatus("1");
                    aipServiceCodeInfoReqDTO.setServiceId(serviceId);
                    aipServiceCodeInfoReqDTO.setVersion(version);
                    aipServiceCodeInfoReqDTO.setCreateStaff(StaffUtil.getStaffId(session));
                    aipServiceCodeInfoReqDTO.setCreateTime(new Date());
                }
                String codeReturn = iAipServiceManagerRSV.insertServiceCodeBatch(aipServiceCodeInfoReqDTOList);

                if (StringUtil.isBlank(codeReturn)){
                    ajaxJson.setSuccess(false);
                    ajaxJson.setMsg("保存失败，请重试或联系管理员！");
                    return ajaxJson;
                }
            }
        }catch (Exception e){
            log.error("插入aip示例代码信息异常：",e);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("保存失败，请重试或联系管理员！");
        }


        return ajaxJson;
    }
}
