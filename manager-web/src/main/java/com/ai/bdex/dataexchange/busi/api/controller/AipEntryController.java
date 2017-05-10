package com.ai.bdex.dataexchange.busi.api.controller;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInParaDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceOutParaDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IServiceMessageRSV;
import com.ai.bdex.dataexchange.busi.api.entity.AipServiceDetailsInfoVO;
import com.ai.bdex.dataexchange.busi.api.entity.AipServiceInParaVO;
import com.ai.bdex.dataexchange.busi.api.entity.AipServiceOutParaVO;
import com.ai.bdex.dataexchange.busi.gds.entity.AipServiceInfoVO;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

        String viewName = "aip_document_deploy";
        ModelAndView mv = new ModelAndView(viewName);

        AipServiceDetailsInfoVO aipServiceDetailsInfoVO = new AipServiceDetailsInfoVO();
        List<AipServiceInParaVO> aipServiceInParaVOList = new ArrayList<AipServiceInParaVO>();
        List<AipServiceOutParaVO> aipServiceOutParaVOList = new ArrayList<AipServiceOutParaVO>();

        //判断是否是录入界面
        if (StringUtil.isBlank(serviceId) && StringUtil.isBlank(version)){
            mv.addObject("aipServiceBaseInfo",new AipServiceDetailsInfoVO());
            mv.addObject("aipServiceInParaList",new ArrayList<AipServiceInParaVO>());
            mv.addObject("aipServiceOutParaList",new ArrayList<AipServiceOutParaVO>());
            return mv;
        }

        //aip基本信息
        try {
            AipServiceInfoDTO aipServiceInfoDTO= iAipServiceInfoRSV.selectServiceByPk(serviceId,version);
            if (aipServiceInfoDTO!=null){
                ObjectCopyUtil.copyObjValue(aipServiceInfoDTO,aipServiceDetailsInfoVO,null,false);
            }else{
                aipServiceDetailsInfoVO = new AipServiceDetailsInfoVO();
            }
        } catch (Exception e) {
            log.error("查询aip信息异常：",e);
        }

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


        mv.addObject("aipServiceBaseInfo",aipServiceDetailsInfoVO);
        mv.addObject("aipServiceInParaList",aipServiceInParaVOList);
        mv.addObject("aipServiceOutParaList",aipServiceOutParaVOList);
        return mv;
    }
}
