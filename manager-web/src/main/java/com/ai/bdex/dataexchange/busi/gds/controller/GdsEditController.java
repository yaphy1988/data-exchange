package com.ai.bdex.dataexchange.busi.gds.controller;

import com.ai.bdex.dataexchange.busi.demo.controller.ArrayList;
import com.ai.bdex.dataexchange.busi.demo.controller.GdsJsonBean;
import com.ai.bdex.dataexchange.busi.demo.controller.GdsSpuCategoryVO;
import com.ai.bdex.dataexchange.busi.demo.controller.HttpServletRequest;
import com.ai.bdex.dataexchange.busi.demo.controller.List;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IDemoRSV;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/gdsEdit")
public class GdsEditController {

    @Autowired
    private IGdsInfoRSVImpl iGdsInfoRSV;

//    @RequestMapping("/pageInit")
//    public String pageInit(Model model){
//        DemoDTO demoDTO = new DemoDTO();
//        demoDTO.setAddr("gx");
//        String userName = demoRSV.callDemoApi(demoDTO);
//        model.addAttribute("username",userName);
//        return "/demo/demo";
//    }
    /**
     * 获取商品分类子分类
     * @param req
     * @param rep
     * @param catId
     * @return
     */
    @RequestMapping(value = "/querySubCatNodes")
    @ResponseBody
    public GdsJsonBean querySubCatNodes(HttpServletRequest req,HttpServletResponse rep,long catId) {
        GdsJsonBean json=new GdsJsonBean();
        try{
        	List<GdsSpuCategoryVO> catListAll = iGdsSpuCategoryManageSIDSV.queryCategoryInfo(catId);
        	List<GdsSpuCategoryVO> catList = new ArrayList<>();
        	if(catListAll!=null && catListAll.size()>0){
        		for(GdsSpuCategoryVO c : catListAll){
        			//去除 “移动业务卡包” 和 “联通手机卡”
        			if(!Constants.Goods.GDS_STATUS_VALID.equals(c.getIfCard())){
        				catList.add(c);
        			}
        		}
        	}
        	json.setObject(catList);
        	json.setSuccess("true");
        }catch(Exception e){
            json.setSuccess("false");
            json.setMsg("获取子分类失败,原因："+e.getMessage());
            logger.error("获取子分类失败,原因："+e.getMessage(),e);
        }
        return  json;
    }
}
