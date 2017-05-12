package com.ai.bdex.dataexchange.busi.gds.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.gds.entity.UserFootPrintVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserFootPrintReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserFootPrintRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IUserFootPrintRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.util.ImageUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@RequestMapping(value="/userfootprint")
@Controller
public class UserFootPrintController {
 private static final Logger logger = LoggerFactory.getLogger(UserFootPrintController.class.getName());
    
    @DubboConsumer
    private IUserFootPrintRSV iUserFootPrintRSV;
    
    @DubboConsumer
    private IGdsInfoRSV iGdsInfoRSV;
    
    @RequestMapping
    public String init(Model model){
        //初始化必须返回一个对象,对象不能是null噢。否则页面解析报错
        PageResponseDTO<UserFootPrintRespDTO> pageResponseDTO = new PageResponseDTO<UserFootPrintRespDTO>();
        model.addAttribute("pageInfo", pageResponseDTO);
        return "browsing_ history";
    }
    
    /**
     * 
     * gridUserCollection:(获取收藏列表). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param userCollectionVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/griduserfootprint")
    public String gridUserFootPrint(Model model,UserFootPrintVO userFootPrintVO){
        try {
            UserFootPrintReqDTO userFootPrintReqDTO = new UserFootPrintReqDTO();
            ObjectCopyUtil.copyObjValue(userFootPrintVO, userFootPrintReqDTO, null, false);
            userFootPrintReqDTO.setPageSize(10);
            PageResponseDTO<UserFootPrintRespDTO> pageInfo = iUserFootPrintRSV.queryUserFootPrintPage(userFootPrintReqDTO);
            if(pageInfo != null&& pageInfo.getResult() != null && pageInfo.getResult().size() >=1){
                List<UserFootPrintRespDTO> list = pageInfo.getResult();
                GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                for(UserFootPrintRespDTO userFootPrintRespDTO : list){
                    gdsInfoReqDTO.setGdsId(userFootPrintRespDTO.getGdsId());
                    try {
                        GdsInfoRespDTO gdsInfo = iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO);
                        userFootPrintRespDTO.setGdsName(gdsInfo.getGdsName());
                        if(StringUtil.isNotBlank(gdsInfo.getGdsPic())){
                            userFootPrintRespDTO.setGdsPic(ImageUtil.getImageUrl(gdsInfo.getGdsPic() + "_200x200"));
                        }
                        userFootPrintRespDTO.setCatFirstName(parseFirstCatName(userFootPrintRespDTO.getCatFirst()));
                    } catch (Exception e) {
                        logger.error("获取商品信息失败", e);
                    }
                }
            }
            model.addAttribute("pageInfo", pageInfo);
        } catch (BusinessException e) {
            logger.error("获取商品浏览历史列表信息失败", e);
        }
        return "browsing_ history :: #user_footprint_list_template";
    }
    
    private String parseFirstCatName(int catFirstId){
        switch(catFirstId){
            case 1:
                return "API";
            case 2:
                return "定制数据";
            case 3:
                return "解决方案";
            default:
                return "";
        }
    }
    /**
     * 
     * deleteUserCollect:(删除商品浏览历史记录信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param userCollectionVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/deleteuserfootprint")
    @ResponseBody
    public AjaxJson deleteUserFootPrint(Model model,UserFootPrintVO userFootPrintVO){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            UserFootPrintReqDTO userFootPrintReqDTO = new UserFootPrintReqDTO();
            userFootPrintReqDTO.setFpId(userFootPrintVO.getFpId());
            iUserFootPrintRSV.deleteUserFootPrint(userFootPrintReqDTO);
            ajaxJson.setSuccess(true);
        } catch (BusinessException e) {
            logger.error("删除商品浏览历史记录信息失败", e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
}

