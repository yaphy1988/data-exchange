package com.ai.bdex.dataexchange.busi.gds.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfoVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsJsonBean;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsManageInfoVO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.dubbo.rpc.service.GenericException;



@Controller
@RequestMapping("/gdsEdit")
public class GdsEditController {
    /**
     * 记录日志
     */
    private final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private IGdsInfoRSV iGdsInfoRSV;

    @RequestMapping("/pageInit")
    public String pageInit(Model model,GdsInfoVO gdsInfoVO){
    	//新增
    	boolean isEdit = false;
    	boolean isView = false;
    	
    	if(gdsInfoVO.getGdsId()!=null||gdsInfoVO.getGdsId()!=0){
    		isEdit = true;
    		
    	}
    	 if (gdsInfoVO.getIsView() != null && gdsInfoVO.getIsView().equals("true")) {// 查看详情
         	isView=true;
         }
        
        model.addAttribute("isView", isView);
    	model.addAttribute("isEdit", isEdit);
        return "/demo/demo";
    }
    /**
     * 获取商品分类子分类
     * @param req
     * @param rep
     * @param catId
     * @return
     */
    @RequestMapping(value = "/querySubCatNodes")
    @ResponseBody
    public GdsJsonBean querySubCatNodes(HttpServletRequest req,HttpServletResponse rep,int catId) {
        GdsJsonBean json=new GdsJsonBean();
        try{
        	GdsCatReqDTO  reqDTO = new GdsCatReqDTO();
        	reqDTO.setCatId(Integer.valueOf(catId));
        	List<GdsCatRespDTO> catListAll = iGdsInfoRSV.queryGdsCatListDTO(reqDTO);
        	json.setObject(catListAll);
        	json.setSuccess("true");
        }catch(Exception e){
            json.setSuccess("false");
            json.setMsg("获取子分类失败,原因："+e.getMessage());
            logger.error("获取子分类失败,原因："+e.getMessage(),e);
        }
        return  json;
    }
    /**
     * 获取商品标签快速选择数据
     * @param req
     * @param rep
     * @param catId
     * @return
     */
    @RequestMapping(value = "/queryGdsLabelQuikList")
    public GdsJsonBean queryGdsLabelQuikList(HttpServletRequest req,HttpServletResponse rep,long catId) {
        GdsJsonBean json=new GdsJsonBean();
        try{
        	GdsLabelQuikReqDTO reqDTO = new GdsLabelQuikReqDTO();
        	List<GdsLabelQuikRespDTO> labelList = iGdsInfoRSV.queryGdsLabelQuikListDTO(reqDTO);
        	json.setObject(labelList);
        	json.setSuccess("true");
        }catch(Exception e){
            json.setSuccess("false");
            json.setMsg("商品标签快速选择数据失败,原因："+e.getMessage());
            logger.error("商品标签快速选择数据失败,原因："+e.getMessage(),e);
        }
        return  json;
    }
    /**
     * 
     * 
     * @param req
     * @param rep
     * @param gdsSpuInfo
     * @return
     * @throws ParseException
     * @throws BusinessException
     * @throws GenericException
     */
    @RequestMapping(value = "/addGds")
    @ResponseBody
    public GdsJsonBean addGdsSpu(HttpServletRequest req, HttpServletResponse rep,
    		GdsManageInfoVO gdsManageInfoVO) throws ParseException, BusinessException, GenericException {
    	GdsJsonBean jsonBean = new GdsJsonBean();
    	GdsInfoVO gdsInfoReqDTO = new GdsInfoVO();
        try {
        	iGdsInfoRSV.addGds(gdsInfoReqDTO);

            jsonBean.setSuccess("true");
            jsonBean.setMsg("产品新增成功！");
        } catch (BusinessException e) {
            jsonBean.setSuccess("false");
            jsonBean.setMsg(e.getMessage());

        } catch (GenericException e) {
            jsonBean.setSuccess("false");
            jsonBean.setMsg("系统异常!");
        }
        catch (Exception e) {
            jsonBean.setSuccess("false");
            jsonBean.setMsg(e.getMessage());
        }

        return jsonBean;
    }

}
