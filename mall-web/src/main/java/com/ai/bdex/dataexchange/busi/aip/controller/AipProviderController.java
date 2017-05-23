package com.ai.bdex.dataexchange.busi.aip.controller;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderInfoRespDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipProviderServiceMgrRSV;
import com.ai.bdex.dataexchange.busi.aip.entity.AipProviderInfoVO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.solrutil.ResultRespVO;
import com.ai.bdex.dataexchange.solrutil.SearchParam;
import com.ai.bdex.dataexchange.solrutil.SolrSearchUtil;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yx on 2017/5/23.
 */
@Controller
@RequestMapping(value = "/aipProvider")
public class AipProviderController {

    private final static Logger log = LoggerFactory.getLogger(AipProviderController.class);

    private IAipProviderServiceMgrRSV iAipProviderServiceMgrRSV;

    /**
     * 初始化供应商首页
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pageInit")
    public ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String viewName = "goods_list_gys";

        String providerId = request.getParameter("providerId");
        if (StringUtil.isBlank(providerId)){
            throw new BusinessException("没有供应商信息");
        }

        AipProviderInfoVO aipProviderInfoVO = new AipProviderInfoVO();
        try {
            AipProviderInfoRespDTO aipProviderInfoRespDTO = iAipProviderServiceMgrRSV.queryAipProviderInfoByProviderId(providerId);
            if (aipProviderInfoRespDTO!=null){
                ObjectCopyUtil.copyObjValue(aipProviderInfoRespDTO,aipProviderInfoVO,null,false);
            }
        } catch (Exception e) {
            log.error("查询aip供应商信息异常：",e);
        }

        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("aipProviderInfo",aipProviderInfoVO);
        return mv;
    }

    /**
     * 查询供应商首页商品列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryGdsList")
    public String queryGdsList(HttpServletRequest request,HttpServletResponse response){
        String providerId = request.getParameter("providerId");
        String pageNo = request.getParameter("pageNo");

        SearchParam searchParam = new SearchParam();

        PageResponseDTO<ResultRespVO> pageInfo = new PageResponseDTO<ResultRespVO>();
        try {
            Map<String,String> searchMap = new HashMap<String,String>();
            searchMap.put("providerId",providerId);
            searchParam.setSearchField(searchMap);

            searchParam.setPageSize(20);
            if (StringUtil.isBlank(pageNo)){
                searchParam.setPageNo(1);
            }else {
                searchParam.setPageNo(Integer.parseInt(pageNo));
            }
            pageInfo = SolrSearchUtil.Search(searchParam);
        }catch (Exception e){
            log.error("供应商首页查询商品列表异常");
        }


        if (pageInfo==null){
            pageInfo = new PageResponseDTO<ResultRespVO>();
        }
        request.setAttribute("pageInfo",pageInfo);
        return "goods_list_gys :: #aipProvider_goodsList";
    }
}
