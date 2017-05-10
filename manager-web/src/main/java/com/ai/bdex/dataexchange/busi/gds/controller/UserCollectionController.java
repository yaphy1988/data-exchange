package com.ai.bdex.dataexchange.busi.gds.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.gds.entity.UserCollectionVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserCollectionReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserCollectionRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IUserCollectionRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.util.ImageUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@RequestMapping(value="/usercollection")
@Controller
public class UserCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(UserCollectionController.class.getName());
    
    @DubboConsumer
    private IUserCollectionRSV iUserCollectionRSV;
    
    @DubboConsumer
    private IGdsInfoRSV iGdsInfoRSV;
    
    @RequestMapping
    public String init(Model model){
        //初始化必须返回一个对象,对象不能是null噢。否则页面解析报错
        PageResponseDTO<UserCollectionRespDTO> pageResponseDTO = new PageResponseDTO<UserCollectionRespDTO>();
        model.addAttribute("pageInfo", pageResponseDTO);
        return "myfavorite";
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
    @RequestMapping(value="/gridusercollection")
    public String gridUserCollection(Model model,UserCollectionVO userCollectionVO){
        try {
            UserCollectionReqDTO userCollectionReqDTO = new UserCollectionReqDTO();
            ObjectCopyUtil.copyObjValue(userCollectionVO, userCollectionReqDTO, null, false);
            userCollectionReqDTO.setPageSize(10);
            PageResponseDTO<UserCollectionRespDTO> pageInfo = iUserCollectionRSV.queryUserCollectionPage(userCollectionReqDTO);
            if(pageInfo != null&& pageInfo.getResult() != null && pageInfo.getResult().size() >=1){
                List<UserCollectionRespDTO> list = pageInfo.getResult();
                GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                for(UserCollectionRespDTO userCollectionRespDTO : list){
                    gdsInfoReqDTO.setGdsId(userCollectionRespDTO.getGdsId());
                    try {
                        GdsInfoRespDTO gdsInfo = iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO);
                        userCollectionRespDTO.setGdsName(gdsInfo.getGdsName());
                        if(StringUtil.isNotBlank(gdsInfo.getGdsPic())){
                            userCollectionRespDTO.setGdsPic(ImageUtil.getImageUrl(gdsInfo.getGdsPic() + "_200x200"));
                        }
                        userCollectionRespDTO.setCatFirstName(parseFirstCatName(userCollectionRespDTO.getCatFirst()));
                    } catch (Exception e) {
                        logger.error("获取商品信息失败", e);
                    }
                }
            }
            model.addAttribute("pageInfo", pageInfo);
        } catch (BusinessException e) {
            logger.error("获取收藏列表信息失败", e);
        }
        return "myfavorite :: #user_collection_list_template";
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
     * deleteUserCollect:(删除收藏记录信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param userCollectionVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/deleteusercollect")
    @ResponseBody
    public AjaxJson deleteUserCollect(Model model,UserCollectionVO userCollectionVO){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            UserCollectionReqDTO userCollectionReqDTO = new UserCollectionReqDTO();
            userCollectionReqDTO.setColId(userCollectionVO.getColId());
            iUserCollectionRSV.deleteUserCollection(userCollectionReqDTO);
            ajaxJson.setSuccess(true);
        } catch (BusinessException e) {
            logger.error("删除收藏记录信息失败", e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
}

