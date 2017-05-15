package com.ai.bdex.dataexchange.busi.api.controller;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.*;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipProviderServiceMgrRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceManagerRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IServiceMessageRSV;
import com.ai.bdex.dataexchange.busi.api.entity.AipProviderServiceInfoVO;
import com.ai.bdex.dataexchange.busi.api.entity.AipServiceDetailsInfoVO;
import com.ai.bdex.dataexchange.busi.api.entity.AipServiceInParaVO;
import com.ai.bdex.dataexchange.busi.api.entity.AipServiceOutParaVO;
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
        request.setAttribute("isView",isView);

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

        if (aipServiceInfoReqDTO!=null){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("保存失败，入参为空！");
        }
//        String inParaStr = request.getParameter("inParaStr");
//        String outParaStr = request.getParameter("outParaStr");
//        List<AipServiceInParaDTO> inParaDTOList = new ArrayList<AipServiceInParaDTO>();
//        List<AipServiceOutParaDTO> outParaDTOList = new ArrayList<AipServiceOutParaDTO>();
//        if (!StringUtil.isBlank(inParaStr)){
//            inParaDTOList = JSONArray.parseArray(inParaStr,AipServiceInParaDTO.class);
//        }
//        if (!StringUtil.isBlank(outParaStr)){
//            outParaDTOList = JSONArray.parseArray(outParaStr,AipServiceOutParaDTO.class);
//        }

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
            //返回的不为空则继续保存出参入参信息
//            if (!StringUtil.isBlank(serviceId)){
//                //入参信息
//                try{
//                    if (!CollectionUtil.isEmpty(inParaDTOList)){
//                        for (AipServiceInParaDTO aipServiceInParaDTO : inParaDTOList){
//                            aipServiceInParaDTO.setServiceId(serviceId);
//                            aipServiceInParaDTO.setVersion("1.0");
//                            aipServiceInParaDTO.setStatus("1");
//                            aipServiceInParaDTO.setCreateStaff(StaffUtil.getStaffId(session));
//                            aipServiceInfoReqDTO.setCreateTime(new Date());
//                            iAipServiceManagerRSV.insertInPara(aipServiceInParaDTO);
//                        }
//                    }
//                }catch (Exception e){
//                    log.error("插入aip服务的入参信息列表异常：",e);
//                    ajaxJson.setSuccess(false);
//                    ajaxJson.setMsg("保存aip服务信息异常！");
//                    return ajaxJson;
//                }
//
//                //出参信息
//                try{
//                    if (!CollectionUtil.isEmpty(outParaDTOList)){
//                        for (AipServiceOutParaDTO aipServiceOutParaDTO : outParaDTOList){
//                            aipServiceOutParaDTO.setServiceId(serviceId);
//                            aipServiceOutParaDTO.setVersion("1.0");
//                            aipServiceOutParaDTO.setStatus("1");
//                            aipServiceOutParaDTO.setCreateStaff(StaffUtil.getStaffId(session));
//                            aipServiceOutParaDTO.setCreateTime(new Date());
//                            iAipServiceManagerRSV.insertOutPara(aipServiceOutParaDTO);
//                        }
//                    }
//                }catch (Exception e){
//                    log.error("插入aip服务的出参信息列表异常：",e);
//                    ajaxJson.setSuccess(false);
//                    ajaxJson.setMsg("保存aip服务信息异常！");
//                    return ajaxJson;
//                }
//
//            }else{
//                ajaxJson.setSuccess(false);
//                ajaxJson.setMsg("保存aip服务基本信息异常，请联系管理员！");
//                return ajaxJson;
//            }

        }else if (!StringUtil.isBlank(aipServiceInfoReqDTO.getServiceId()) && !StringUtil.isBlank(aipServiceInfoReqDTO.getVersion())){//编辑

        }

        ajaxJson.setSuccess(true);
        ajaxJson.setMsg("保存成功！");
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
                updateOutParaDTO.setType("01");
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

        return ajaxJson;
    }
}
