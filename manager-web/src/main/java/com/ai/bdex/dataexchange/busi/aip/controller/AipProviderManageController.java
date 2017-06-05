package com.ai.bdex.dataexchange.busi.aip.controller;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderInfoRespDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipProviderServiceMgrRSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yx on 2017/5/23.
 */
@Controller
@RequestMapping(value = "/aipProviderManage")
public class AipProviderManageController {

    private final static Logger log = LoggerFactory.getLogger(AipProviderManageController.class);

    @DubboConsumer
    private IAipProviderServiceMgrRSV iAipProviderServiceMgrRSV;
    private static final Integer PAGE_SIZE=10;


    /**
     * 初始化供应商首页
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pageInit")
    public String pageInit(HttpServletRequest request, HttpServletResponse response) throws Exception{
        return "supplier_manage";
    }

    /**
     * 查询供应商首页商品列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryAipProviderInfoList")
    public String queryAipProviderInfoList(HttpServletRequest request,HttpServletResponse response){
    	  String providerName = request.getParameter("providerName");
          String status = request.getParameter("status");
          String pageNo = request.getParameter("pageNo");
          AipProviderInfoReqDTO aipProviderInfoReqDTO = new AipProviderInfoReqDTO();
          if(!StringUtil.isBlank(providerName)){
          	aipProviderInfoReqDTO.setProviderName(providerName);
          }
          if(!StringUtil.isBlank(status)){
          	aipProviderInfoReqDTO.setStatus(status);
          }
          if(!StringUtil.isBlank(pageNo)){
          	aipProviderInfoReqDTO.setPageNo(Integer.parseInt(pageNo));
          }
          aipProviderInfoReqDTO.setPageSize(PAGE_SIZE);
          PageResponseDTO<AipProviderInfoRespDTO>  pageInfo = new PageResponseDTO<AipProviderInfoRespDTO>();
          try {
          	pageInfo = iAipProviderServiceMgrRSV.pageAipProviderInfo(aipProviderInfoReqDTO);
          	if(CollectionUtils.isNotEmpty(pageInfo.getResult())){
          		for(AipProviderInfoRespDTO respDTO : pageInfo.getResult()){
          			if(StringUtils.isNotEmpty(respDTO.getProviderLogo())){
          				respDTO.setProviderLogo(ImageUtil.getImageUrl(respDTO.getProviderLogo() + "_100x100"));
          			}
          		}
          	}
          } catch (Exception e) {
              log.error("查询aip供应商信息异常：",e);
          }

          request.setAttribute("pageInfo",pageInfo);
        return "supplier_manage :: #aipProviderInfoList";
    }
}
