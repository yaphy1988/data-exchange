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

import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfoVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsJsonBean;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsManageInfoVO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelQuikReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelQuikRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.util.StringUtil;
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
    private IGdsInfoRSV gdsInfoRSV;

    @RequestMapping("/pageInit")
    public String pageInit(Model model,GdsInfoVO gdsInfoVO) throws Exception{
    	boolean isEdit = false;
    	boolean isView = false;
    	GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();

    	try{
    		//新增
        	GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        	if(gdsInfoVO.getGdsId()!=null||gdsInfoVO.getGdsId()!=0){
        		isEdit = true;
        		gdsInfoReqDTO.setGdsId(gdsInfoVO.getGdsId());
        		gdsInfoRespDTO=gdsInfoRSV.queryGdsInfoDetails(gdsInfoReqDTO);
        		
        	}
        	if (gdsInfoVO.getIsView() != null && gdsInfoVO.getIsView().equals("true")) {// 查看详情
        		isView=true;
            }
        	GdsCatReqDTO  reqDTO = new GdsCatReqDTO();
        	reqDTO.setCatId(Integer.valueOf(0));
        	List<GdsCatRespDTO> catListAll = gdsInfoRSV.queryGdsCatListDTO(reqDTO);
    	}catch(Exception e){
            logger.error("查询商品录入信息失败,原因："+e.getMessage(),e);
        }
    	
        model.addAttribute("isView", isView);
    	model.addAttribute("isEdit", isEdit);
    	model.addAttribute("gdsInfoRespDTO", gdsInfoRespDTO);
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
        	GdsCatReqDTO reqDTO = new GdsCatReqDTO();
        	reqDTO.setCatId(Integer.valueOf(catId));
        	List<GdsCatRespDTO> catListAll = gdsInfoRSV.queryGdsCatListDTO(reqDTO);
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
        	List<GdsLabelQuikRespDTO> labelList = gdsInfoRSV.queryGdsLabelQuikListDTO(reqDTO);
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
    public GdsJsonBean addGds(HttpServletRequest req, HttpServletResponse rep,
    		GdsManageInfoVO gdsManageInfoVO) throws ParseException, BusinessException, GenericException {
    	GdsJsonBean jsonBean = new GdsJsonBean();
    	GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        try {
        	gdsInfoRSV.addGds(gdsInfoReqDTO);

            jsonBean.setSuccess("true");
            jsonBean.setMsg("商品录入成功！");
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
    @RequestMapping(value = "/editGds")
    @ResponseBody
    public GdsJsonBean editGds(HttpServletRequest req, HttpServletResponse rep,
    		GdsManageInfoVO gdsManageInfoVO) throws ParseException, BusinessException, GenericException {
    	GdsJsonBean jsonBean = new GdsJsonBean();
    	GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
    	GdsLabelReqDTO gdsLabelReqDTO = new GdsLabelReqDTO();
        try {
        	if(gdsManageInfoVO.getGdsId()!=0){
            	gdsInfoReqDTO.setGdsId(gdsManageInfoVO.getGdsId());
        	}
        	if(StringUtil.isNotBlank(gdsManageInfoVO.getGdsName())){
            	gdsInfoReqDTO.setGdsName(gdsManageInfoVO.getGdsName());
        	}
        	if(StringUtil.isNotBlank(gdsManageInfoVO.getGdsSubtitle())){
            	gdsInfoReqDTO.setGdsSubtitle(gdsManageInfoVO.getGdsSubtitle());
        	}	
        	if(gdsManageInfoVO.getCatFirst()!=0){
            	gdsInfoReqDTO.setCatFirst(gdsManageInfoVO.getCatFirst());
        	}
        	if(StringUtil.isNotBlank(gdsManageInfoVO.getGdsPic())){
            	gdsInfoReqDTO.setGdsPic(gdsManageInfoVO.getGdsPic());
        	}
        	if(gdsManageInfoVO.getGdsLabelVO()!=null){
        		gdsLabelReqDTO.setLabColor(gdsManageInfoVO.getGdsLabelVO().getLabColor());
        		gdsLabelReqDTO.setLabName(gdsManageInfoVO.getGdsLabelVO().getLabName());
        		gdsLabelReqDTO.setLabId(gdsManageInfoVO.getGdsLabelVO().getLabId());
        		gdsLabelReqDTO.setGdsId(gdsManageInfoVO.getGdsLabelVO().getGdsId());

        	}
        	gdsInfoRSV.editGds(gdsInfoReqDTO);

            jsonBean.setSuccess("true");
            jsonBean.setMsg("编辑商品录入成功！");
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
