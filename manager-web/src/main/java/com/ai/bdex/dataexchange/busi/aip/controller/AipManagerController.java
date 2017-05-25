package com.ai.bdex.dataexchange.busi.aip.controller;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceManagerRSV;
import com.ai.bdex.dataexchange.busi.aip.entity.AipServiceDetailsInfoVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yx on 2017/5/21.
 */
@Controller
@RequestMapping(value = "/aipManager")
public class AipManagerController {

    private final static Logger log = LoggerFactory.getLogger(AipManagerController.class);

    @DubboConsumer
    private IAipServiceInfoRSV iAipServiceInfoRSV;
    @DubboConsumer
    private IAipServiceManagerRSV iAipServiceManagerRSV;


    /**
     * aip管理初始化
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/pageInit")
    public ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response){
        String viewName = "aip_document";


        ModelAndView mv = new ModelAndView(viewName);
        return mv;
    }

    @RequestMapping(value = "/queryAipList")
    public String queryAipList(HttpServletRequest request, HttpServletResponse response, AipServiceInfoReqDTO aipServiceInfoReqDTO){
        if (aipServiceInfoReqDTO==null){
            aipServiceInfoReqDTO = new AipServiceInfoReqDTO();
        }

        PageResponseDTO<AipServiceDetailsInfoVO> pageInfo = new PageResponseDTO<AipServiceDetailsInfoVO>();
        try{
            aipServiceInfoReqDTO.setGridQuerySortOrder("desc");
            aipServiceInfoReqDTO.setGridQuerySortName("create_time");
            aipServiceInfoReqDTO.setPageSize(10);
            PageResponseDTO<AipServiceInfoDTO> pageResponseDTO = iAipServiceInfoRSV.selectServiceWithPage(aipServiceInfoReqDTO);
            ObjectCopyUtil.copyObjValue(pageResponseDTO,pageInfo,null,false);
            List<AipServiceDetailsInfoVO> aipServiceDetailsInfoVOList = new ArrayList<AipServiceDetailsInfoVO>();
            if (!CollectionUtil.isEmpty(pageResponseDTO.getResult())){
                for (AipServiceInfoDTO aipServiceInfoDTO : pageResponseDTO.getResult()){
                    AipServiceDetailsInfoVO aipServiceDetailsInfoVO = new AipServiceDetailsInfoVO();
                    ObjectCopyUtil.copyObjValue(aipServiceInfoDTO,aipServiceDetailsInfoVO,null,false);
                    aipServiceDetailsInfoVO.setStatusName(traslateStatusName(aipServiceDetailsInfoVO.getStatus()));
                    aipServiceDetailsInfoVOList.add(aipServiceDetailsInfoVO);
                }
            }
            pageInfo.setResult(aipServiceDetailsInfoVOList);
        }catch (Exception e){
            log.error("分页查询AIP服务列表异常：",e);
        }

        request.setAttribute("pageInfo",pageInfo);

        return "aip_document :: #aipManagerList";
    }

    /**
     * 生效失效
     * @param request
     * @param response
     * @param aipServiceInfoReqDTO
     * @return
     */
    @RequestMapping(value = "/doAipStatus")
    @ResponseBody
    public AjaxJson doAipStatus(HttpServletRequest request , HttpServletResponse response, HttpSession session ,AipServiceInfoReqDTO aipServiceInfoReqDTO){
        AjaxJson ajaxJson = new AjaxJson();

        if (aipServiceInfoReqDTO == null || StringUtil.isBlank(aipServiceInfoReqDTO.getServiceId()) || StringUtil.isBlank(aipServiceInfoReqDTO.getVersion())){
            ajaxJson.setMsg("系统错误，请联系管理员！");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        try {
            aipServiceInfoReqDTO.setUpdateTime(new Date());
            aipServiceInfoReqDTO.setUpdateStaff(StaffUtil.getStaffId(session));
            iAipServiceManagerRSV.updateAipServiceByServiceId(aipServiceInfoReqDTO);
            ajaxJson.setSuccess(true);
        }catch (Exception e){
            if ("1".equals(aipServiceInfoReqDTO.getStatus())){
                log.error("生效aip服务异常：",e);
                ajaxJson.setMsg("生效aip服务异常，请重新操作或联系管理员！");
            }else if ("2".equals(aipServiceInfoReqDTO.getStatus())){
                log.error("失效aip服务异常：",e);
                ajaxJson.setMsg("失效aip服务异常，请重新操作或联系管理员！");
            }
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        return ajaxJson;
    }

    private String traslateStatusName(String status){
        String statusName = "";
        if (!StringUtil.isBlank(status)){
            if ("0".equals(status)){
                statusName = "待生效";
            }else if ("1".equals(status)){
                statusName = "生效";
            }else if ("2".equals(status)){
                statusName = "失效";
            }
        }
        return statusName;
    }
}