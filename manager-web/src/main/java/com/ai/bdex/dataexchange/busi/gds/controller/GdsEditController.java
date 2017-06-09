package com.ai.bdex.dataexchange.busi.gds.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.HtmlUtils;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.busi.gds.entity.AipServiceInfoVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsCatVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfo2PropVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfoVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsJsonBean;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsLabelQuikVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsLabelVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsPropVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsSkuVO;
import com.ai.bdex.dataexchange.busi.pic.entity.PicManageVO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2CatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2PropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2PropRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelQuikReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelQuikRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsPropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsPropRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicLibReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicLibRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsCatRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfo2PropRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsLabelRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsSkuRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.pic.IPicInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.pic.IPicLibRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.util.MongoFileUtil;
import com.ai.paas.utils.CollectionUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/gdsEdit")
public class GdsEditController {
    /**
     * 记录日志
     */
    private final Log logger = LogFactory.getLog(getClass());
    private final static Integer AIP_CAT_ID = 1;//aip的catId
    private final static Integer CUSTOM_CAT_ID = 2;//定制需求catId
    private final static Integer SOLUTION_CAT_ID = 3;//解决方案catId
    private final static String GDS_VALID = "1";//商品状态-有效
    private final static String GDS_INVALID = "0";//商品状态-失效
    private final static String GDS_DEL= "9";//商品状态-删除
    private final static String PRO_TYPE = "2";//富文本
    private final static Integer PAGE_SIZE = 10;//分页查询大小



    @DubboConsumer
    private IGdsInfoRSV gdsInfoRSV;
    @DubboConsumer
    private IGdsCatRSV iGdsCatRSV;
    @DubboConsumer
    private IGdsLabelRSV iGdsLabelRSV;
    @DubboConsumer
    private IGdsInfo2PropRSV iGdsInfo2PropRSV;
    @DubboConsumer
    private IGdsSkuRSV iGdsSkuRSV;
    @DubboConsumer
    private IAipServiceInfoRSV iAipServiceInfoRSV;
    @DubboConsumer
    private IPicLibRSV iPicLibRSV;
    @DubboConsumer
    private IPicInfoRSV iPicInfoRSV;
    
    @RequestMapping("/pageInit")
    public String pageInit(Model model,GdsInfoVO gdsInfoVO) throws Exception{
    	boolean isEdit = false;
    	boolean isView = false;
    	GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();

    	try{
    		//新增
        	GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        	if(gdsInfoVO.getGdsId()!=null){
        		isEdit = true;
        		gdsInfoReqDTO.setGdsId(gdsInfoVO.getGdsId());
                gdsInfoRespDTO = gdsInfoRSV.queryGdsInfo(gdsInfoReqDTO);
                if (gdsInfoRespDTO!=null){
                    ObjectCopyUtil.copyObjValue(gdsInfoRespDTO,gdsInfoVO,null,false);
                    if(gdsInfoRespDTO.getGdsPic()!=null){
                    	gdsInfoVO.setGdsPicUrl(ImageUtil.getImageUrl(gdsInfoRespDTO.getGdsPic() + "_100x100"));
                    }
                    //设置分类名称
                    if (gdsInfoRespDTO.getCatId()!=null && gdsInfoRespDTO.getCatId().intValue()>0){
                        GdsCatRespDTO gdsCatRespDTO = iGdsCatRSV.queryGdsCatByCatId(gdsInfoRespDTO.getCatId());
                        if (gdsCatRespDTO!=null){
                            gdsInfoVO.setCatName(gdsCatRespDTO.getCatName());
                        }
                    }
                    if (gdsInfoRespDTO.getCatFirst()!=null && gdsInfoRespDTO.getCatId().intValue()>0){
                        GdsCatRespDTO gdsCatRespDTO = iGdsCatRSV.queryGdsCatByCatId(gdsInfoRespDTO.getCatFirst());
                        if (gdsCatRespDTO!=null){
                            gdsInfoVO.setCatFirstName(gdsCatRespDTO.getCatName());
                        }
                    }
                    //设置商品标签列表
                    List<GdsLabelVO> gdsLabelVOList = new ArrayList<GdsLabelVO>();
                    GdsLabelReqDTO gdsLabelReqDTO = new GdsLabelReqDTO();
                    gdsLabelReqDTO.setStatus(GDS_VALID);
                    gdsLabelReqDTO.setGdsId(gdsInfoRespDTO.getGdsId());
                    List<GdsLabelRespDTO> gdsLabelRespDTOList = iGdsLabelRSV.queryGdsLabelList(gdsLabelReqDTO);
                    if (!CollectionUtil.isEmpty(gdsLabelRespDTOList)){
                        for (GdsLabelRespDTO gdsLabelRespDTO : gdsLabelRespDTOList){
                            GdsLabelVO gdsLabelVO = new GdsLabelVO();
                            ObjectCopyUtil.copyObjValue(gdsLabelRespDTO,gdsLabelVO,null,false);
                            gdsLabelVOList.add(gdsLabelVO);
                        }
                        gdsInfoVO.setGdsLabelVOList(gdsLabelVOList);
                    }
                    if (AIP_CAT_ID.equals(gdsInfoRespDTO.getCatFirst())){
                        //api的分类要设置单品列表信息
                        List<GdsSkuVO> gdsSkuVOList = new ArrayList<GdsSkuVO>();
                        GdsSkuReqDTO gdsSkuReqDTO = new GdsSkuReqDTO();
                        gdsSkuReqDTO.setGdsId(gdsInfoRespDTO.getGdsId());
                        gdsSkuReqDTO.setStatus(gdsInfoRespDTO.getStatus());
                        List<GdsSkuRespDTO> gdsSkuRespDTOList = iGdsSkuRSV.queryGdsSkuList(gdsSkuReqDTO);
                        if (!CollectionUtil.isEmpty(gdsSkuRespDTOList)){
                            for (GdsSkuRespDTO gdsSkuRespDTO : gdsSkuRespDTOList){
                                GdsSkuVO gdsSkuVO = new GdsSkuVO();
                                ObjectCopyUtil.copyObjValue(gdsSkuRespDTO,gdsSkuVO,null,false);
                                if (gdsSkuVO.getPackPrice()!=null && gdsSkuVO.getPackPrice().intValue()>0){
                                	gdsSkuVO.setPackPrice(gdsSkuVO.getPackPrice()/100);
                                }
                                gdsSkuVOList.add(gdsSkuVO);
                            }
                            gdsInfoVO.setGdsSkuVOList(gdsSkuVOList);
                        }
                    }
                    //根据APIID查找API接口信息
                    if(gdsInfoRespDTO.getApiId()!=null){
                    	List<AipServiceInfoDTO> apiServiceList = iAipServiceInfoRSV.selectServiceByServiceId(String.valueOf(gdsInfoRespDTO.getApiId()));
            			if(CollectionUtils.isNotEmpty(apiServiceList)){
                        	gdsInfoVO.setApiIdName(apiServiceList.get(0).getServiceName());
            			}
                    }
                }
        	}
        	if (gdsInfoVO.getIsView() != null && gdsInfoVO.getIsView().equals("true")) {// 查看详情
        		isView=true;
            }
        	GdsCatReqDTO  reqDTO = new GdsCatReqDTO();
        	reqDTO.setCatPid(Integer.valueOf(0));
        	List<GdsCatRespDTO> catList = gdsInfoRSV.queryGdsCatListDTO(reqDTO);
        	model.addAttribute("catList", catList);
    	}catch(Exception e){
            logger.error("查询商品录入信息失败,原因："+e.getMessage(),e);
        }
    	try {
    	    PicLibReqDTO picLibReqDTO = new PicLibReqDTO();
    	    picLibReqDTO.setStatus("1");
    	    List<PicLibRespDTO> picLibList = iPicLibRSV.queryPicLibList(picLibReqDTO);
    	    if(picLibList == null){
    	        picLibList = new ArrayList<PicLibRespDTO>();
    	    }
    	    model.addAttribute("picLibList", picLibList);
        } catch (Exception e) {
            // TODO: handle exception
        }
    	model.addAttribute("gdsInfoVO", gdsInfoVO);
        model.addAttribute("isView", isView);
    	model.addAttribute("isEdit", isEdit);
    	model.addAttribute("gdsInfoRespDTO", gdsInfoRespDTO);
        return "goods_import";
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
    public GdsJsonBean querySubCatNodes(HttpServletRequest req,HttpServletResponse rep,GdsCatVO gdsCatVO) {
        GdsJsonBean json=new GdsJsonBean();
        try{
        	GdsCatReqDTO  reqDTO = new GdsCatReqDTO();
        	reqDTO.setCatPid(gdsCatVO.getCatId());
        	List<GdsCatRespDTO> catListAll = iGdsCatRSV.queryGdsCatList(reqDTO);
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
    @ResponseBody
    public GdsJsonBean queryGdsLabelQuikList(HttpServletRequest req,HttpServletResponse rep,GdsLabelQuikVO gdsLabelQuikVO) {
        GdsJsonBean json=new GdsJsonBean();
        try{
        	GdsLabelQuikReqDTO reqDTO = new GdsLabelQuikReqDTO();
        	if(StringUtil.isNotBlank(gdsLabelQuikVO.getLabName())){
            	reqDTO.setLabName(gdsLabelQuikVO.getLabName());
        	}
        	if(gdsLabelQuikVO.getCatFirst()!=null){
        		reqDTO.setCatFirst(gdsLabelQuikVO.getCatFirst());
        	}
        	reqDTO.setStatus(GDS_VALID);
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
     * 新增商品
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
    public GdsJsonBean addGds(HttpServletRequest req, HttpServletResponse rep,HttpSession session) throws BusinessException, GenericException {
    	GdsJsonBean jsonBean = new GdsJsonBean();
    	GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        try {
        	//商品基本信息
        	String staffId=StaffUtil.getStaffVO(session).getStaffId();
    		JSONObject gdsInfoVO=JSONObject.parseObject(req.getParameter("gdsInfoVO"));
			this.setGdsInfo(gdsInfoReqDTO, gdsInfoVO);
			gdsInfoReqDTO.setStatus(GDS_INVALID);
			gdsInfoReqDTO.setCreateUser(staffId);
			gdsInfoReqDTO.setUpdateUser(staffId);
			int  gdsId=gdsInfoRSV.insertGdsInfo(gdsInfoReqDTO);
        	//商品分类属性关联信息
        	GdsInfo2CatReqDTO gdsInfo2CatReqDTO = new GdsInfo2CatReqDTO();
       		JSONObject gdsInfo2CatVO=JSONObject.parseObject(req.getParameter("gdsInfo2CatVO"));
    		this.setGdsInfo2Cat(gdsInfo2CatReqDTO, gdsInfo2CatVO);
    		gdsInfo2CatReqDTO.setStatus(GDS_VALID);
    		gdsInfo2CatReqDTO.setCreateUser(staffId);
    		gdsInfo2CatReqDTO.setUpdateUser(staffId);
        	gdsInfoRSV.insertGdsInfo2Cat(gdsInfo2CatReqDTO);
        	//商品标签信息
        	JSONArray gdsLabelVOList=JSONArray.parseArray(req.getParameter("gdsLabelVOList"));
			List<GdsLabelReqDTO> gdsLabelReqDTOList = new ArrayList<>();
			this.setGdsLabel(gdsLabelReqDTOList, gdsLabelVOList);
			// 保存商品标签
			if (CollectionUtils.isNotEmpty(gdsLabelReqDTOList)) {
				for (GdsLabelReqDTO labelReq : gdsLabelReqDTOList) {
					labelReq.setGdsId(gdsId);
					labelReq.setStatus(GDS_VALID);
					labelReq.setCreateUser(staffId);
					labelReq.setUpdateUser(staffId);
					iGdsLabelRSV.insertGdsLabel(labelReq);
				}
			}
        	//获取商品单品信息
        	JSONArray gdsSkuVOList=JSONArray.parseArray(req.getParameter("gdsSkuVOList"));
        	List<GdsSkuReqDTO> gdsSkuReqDTOList =  new ArrayList<>();
			this.setGdsSkuInfo(gdsSkuReqDTOList, gdsSkuVOList);
			// 保存单品信息
			if (CollectionUtils.isNotEmpty(gdsSkuReqDTOList)) {
				for (GdsSkuReqDTO skuReq : gdsSkuReqDTOList) {
					skuReq.setGdsId(gdsId);
					skuReq.setStatus(GDS_INVALID);
					skuReq.setCreateUser(staffId);
					skuReq.setUpdateUser(staffId);
					iGdsSkuRSV.insertGdsSku(skuReq);
				}
			}
        	//获取商品属性配置信息
        	JSONArray gdsInfo2PropVOList=JSONArray.parseArray(req.getParameter("gdsInfo2PropVOList"));
        	List<GdsInfo2PropReqDTO> gdsInfo2PropReqDTOList =  new ArrayList<>();
			this.setGdsInfo2PropInfo(gdsInfo2PropReqDTOList, gdsInfo2PropVOList);
			// 保存商品属性配置信息
			if (CollectionUtils.isNotEmpty(gdsInfo2PropReqDTOList)) {
				for (GdsInfo2PropReqDTO propReq : gdsInfo2PropReqDTOList) {
					propReq.setGdsId(gdsId);
					propReq.setStatus(GDS_VALID);
					propReq.setCreateUser(staffId);
					propReq.setUpdateUser(staffId);
					iGdsInfo2PropRSV.insertGdsInfo2Prop(propReq);
				}
			}
            jsonBean.setSuccess("true");
            jsonBean.setMsg("商品录入成功！");
        } catch (BusinessException e) {
            jsonBean.setSuccess("false");
            jsonBean.setMsg(e.getMessage());
            logger.error("商品保存失败,原因："+e.getMessage(),e);
        } catch (GenericException e) {
            jsonBean.setSuccess("false");
            jsonBean.setMsg("系统异常!");
        }
        catch (Exception e) {
            jsonBean.setSuccess("false");
            jsonBean.setMsg(e.getMessage());
            logger.error("商品保存失败,原因："+e.getMessage(),e);

        }

        return jsonBean;
    }
    /**
     * 编辑商品
     * @param req
     * @param rep
     * @param session
     * @return
     * @throws BusinessException
     * @throws GenericException
     */
    @RequestMapping(value = "/editGds")
    @ResponseBody
    public GdsJsonBean editGds(HttpServletRequest req, HttpServletResponse rep,HttpSession session) throws  BusinessException, GenericException {
    	GdsJsonBean jsonBean = new GdsJsonBean();
    	GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        try {
        	//商品基本信息
        	String staffId=StaffUtil.getStaffVO(session).getStaffId();
    		JSONObject gdsInfoVO=JSONObject.parseObject(req.getParameter("gdsInfoVO"));
			this.setGdsInfo(gdsInfoReqDTO, gdsInfoVO);
			gdsInfoReqDTO.setCreateUser(staffId);
			int gdsId=gdsInfoReqDTO.getGdsId();
			gdsInfoRSV.updateGdsInfo(gdsInfoReqDTO);
			//获取商品信息
    		gdsInfoReqDTO.setGdsId(gdsId);
    		GdsInfoRespDTO gdsInfoRespDTO = gdsInfoRSV.queryGdsInfo(gdsInfoReqDTO);
        	//商品标签信息
        	JSONArray gdsLabelVOList=JSONArray.parseArray(req.getParameter("gdsLabelVOList"));
        	//删除的单品信息
        	String delLabelIds =req.getParameter("delLabelIds");
        	if(StringUtil.isNotBlank(delLabelIds)){
        		for(String labId:delLabelIds.split(",")){
                	//先失效再保存
                	GdsLabelReqDTO oldLabelReqDTO = new GdsLabelReqDTO();
                	oldLabelReqDTO.setLabId(Integer.parseInt(labId));
                	oldLabelReqDTO.setStatus(GDS_INVALID);
                	oldLabelReqDTO.setUpdateUser(staffId);
                	iGdsLabelRSV.updataGdslabelByGdsId(oldLabelReqDTO);
        		}
        	}
        	List<GdsLabelReqDTO> gdsLabelReqDTOList =  new ArrayList<>();
			this.setGdsLabel(gdsLabelReqDTOList, gdsLabelVOList);
			// 保存商品标签
			if (CollectionUtils.isNotEmpty(gdsLabelReqDTOList)) {
				for (GdsLabelReqDTO labelReq : gdsLabelReqDTOList) {
					labelReq.setGdsId(gdsId);
					labelReq.setStatus(GDS_VALID);
					labelReq.setCreateUser(staffId);
					iGdsLabelRSV.insertGdsLabel(labelReq);
				}
            	}
        	
        	//获取商品单品信息
        	JSONArray gdsSkuVOList=JSONArray.parseArray(req.getParameter("gdsSkuVOList"));
        	List<GdsSkuReqDTO> gdsSkuReqDTOList =  new ArrayList<>();
        	//删除的套餐信息
        	String delSkuIds =req.getParameter("delSkuIds");
        	if(StringUtil.isNotBlank(delSkuIds)){
        		for(String skuId:delSkuIds.split(",")){
                	GdsSkuReqDTO oldSkuReqDTO = new GdsSkuReqDTO();
                	oldSkuReqDTO.setSkuId(Integer.parseInt(skuId));
                	oldSkuReqDTO.setStatus(GDS_DEL);
                	oldSkuReqDTO.setUpdateUser(staffId);
                	iGdsSkuRSV.updataGdsSkuByGdsId(oldSkuReqDTO);
        		}
        	}
			this.setGdsSkuInfo(gdsSkuReqDTOList, gdsSkuVOList);
			if (CollectionUtils.isNotEmpty(gdsSkuReqDTOList)) {
				for (GdsSkuReqDTO skuReq : gdsSkuReqDTOList) {
					if (skuReq.getSkuId() == null) {
						skuReq.setGdsId(gdsId);
						skuReq.setStatus(gdsInfoRespDTO.getStatus());
						skuReq.setCreateUser(staffId);
						iGdsSkuRSV.insertGdsSku(skuReq);
					} else {
						skuReq.setUpdateUser(staffId);
						iGdsSkuRSV.updataGdsSkuByGdsId(skuReq);
					}

				}
			}
        	//获取商品属性配置信息
        	JSONArray gdsInfo2PropVOList=JSONArray.parseArray(req.getParameter("gdsInfo2PropVOList"));
        	List<GdsInfo2PropReqDTO> gdsInfo2PropReqDTOList =  new ArrayList<>();
			this.setGdsInfo2PropInfo(gdsInfo2PropReqDTOList, gdsInfo2PropVOList);
			// 保存商品属性配置信息
			if (CollectionUtils.isNotEmpty(gdsInfo2PropReqDTOList)) {
				for (GdsInfo2PropReqDTO propReq : gdsInfo2PropReqDTOList) {
					propReq.setGdsId(gdsId);
					propReq.setStatus(GDS_VALID);
					propReq.setUpdateUser(staffId);
					iGdsInfo2PropRSV.updateGdsInfo2Prop(propReq);
				}
			}

            jsonBean.setSuccess("true");
            jsonBean.setMsg("编辑商品成功！");
        } catch (GenericException e) {
            jsonBean.setSuccess("false");
            jsonBean.setMsg("系统异常!");
            logger.error("商品保存失败,原因："+e.getMessage(),e);
        }
        catch (Exception e) {
            jsonBean.setSuccess("false");
            jsonBean.setMsg(e.getMessage());
            logger.error("商品保存失败,原因："+e.getMessage(),e);

        }

        return jsonBean;
    }
	private void setGdsInfo(GdsInfoReqDTO vo,JSONObject baseInfoJson){
  		try {
			if (baseInfoJson != null) {

				if (StringUtil.isNotBlank(baseInfoJson.getString("gdsId"))) {
					vo.setGdsId(Integer.parseInt(baseInfoJson.getString("gdsId")));
				}
				vo.setGdsName(baseInfoJson.getString("gdsName"));
				vo.setGdsSubtitle(baseInfoJson.getString("gdsSubTitle"));
				vo.setCatFirst(Integer.parseInt(baseInfoJson.getString("catFirst")));
				vo.setCatId(Integer.parseInt(baseInfoJson.getString("catId")));
				if (StringUtil.isNotBlank(baseInfoJson.getString("apiId"))) {
					vo.setApiId(Integer.parseInt(baseInfoJson.getString("apiId")));
				}
				if (StringUtil.isNotBlank(baseInfoJson.getString("providerId"))) {
					vo.setProviderId(baseInfoJson.getString("providerId"));
				}
				vo.setGdsPic(baseInfoJson.getString("gdsPic"));
				vo.setIfRecommend(baseInfoJson.getString("ifRecommend"));
				vo.setFunIntroduction(baseInfoJson.getString("funIntroduction"));
				vo.setCommpanyName(baseInfoJson.getString("commpanyName"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			 throw e;
		}
	}
	private void setGdsInfo2Cat(GdsInfo2CatReqDTO vo,JSONObject baseInfoJson){
  		try {
			if (baseInfoJson != null) {
				if (StringUtil.isNotBlank(baseInfoJson.getString("gcId"))) {
					vo.setGcId(Integer.parseInt(baseInfoJson.getString("gcId")));
				}
				if (StringUtil.isNotBlank(baseInfoJson.getString("catId"))) {
					vo.setCatId(Integer.parseInt(baseInfoJson.getString("catId")));
				}
				if (StringUtil.isNotBlank(baseInfoJson.getString("catFirst"))) {
					vo.setCatFirst(Integer.parseInt(baseInfoJson.getString("catFirst")));
				}
				if (StringUtil.isNotBlank(baseInfoJson.getString("gdsId"))) {
					vo.setGdsId(Integer.parseInt(baseInfoJson.getString("gdsId")));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	private void setGdsLabel(List<GdsLabelReqDTO> reqList, JSONArray jsonArray) {

		try {
			// 遍历元素
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject labelVOJson = (JSONObject) jsonArray.get(i);
				GdsLabelReqDTO labelReqDTO = new GdsLabelReqDTO();
				if(labelVOJson!=null){
					if (StringUtil.isNotBlank(labelVOJson.getString("labId"))) {
						labelReqDTO.setLabId(Integer.parseInt(labelVOJson.getString("labId")));
					}
					if (StringUtil.isNotBlank(labelVOJson.getString("gdsId"))) {
						labelReqDTO.setGdsId(Integer.parseInt(labelVOJson.getString("gdsId")));
					}
					labelReqDTO.setLabName(labelVOJson.getString("labName"));
					labelReqDTO.setLabColor(labelVOJson.getString("labColor"));
					if (StringUtil.isNotBlank(labelVOJson.getString("showOrder"))) {
						labelReqDTO.setShowOrder(Integer.parseInt(labelVOJson.getString("showOrder")));
					}
					if (StringUtil.isNotBlank(labelVOJson.getString("ifEdit"))) {
						labelReqDTO.setIfEdit(labelVOJson.getString("ifEdit"));
					}
					reqList.add(labelReqDTO);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	private void setGdsSkuInfo(List<GdsSkuReqDTO> reqList, JSONArray jsonArray) {

		try {
			// 遍历元素
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject skuVOJson = (JSONObject) jsonArray.get(i);
				if (skuVOJson != null) {
					GdsSkuReqDTO skuReqDTO = new GdsSkuReqDTO();
					if (StringUtil.isNotBlank(skuVOJson.getString("skuId"))) {
						skuReqDTO.setSkuId(Integer.parseInt(skuVOJson.getString("skuId")));
					}
					if (StringUtil.isNotBlank(skuVOJson.getString("gdsId"))) {
						skuReqDTO.setGdsId(Integer.parseInt(skuVOJson.getString("gdsId")));
					}
					skuReqDTO.setSkuName(skuVOJson.getString("skuName"));
					if (StringUtil.isNotBlank(skuVOJson.getString("packPrice"))) {
						skuReqDTO.setPackPrice(Integer.parseInt(skuVOJson.getString("packPrice")) * 100);
					}
					// 次数
					if (StringUtil.isNotBlank(skuVOJson.getString("packTimes"))) {
						skuReqDTO.setPackTimes(Integer.parseInt(skuVOJson.getString("packTimes")));
					}
					// 有效期
					if (StringUtil.isNotBlank(skuVOJson.getString("packDay"))) {
						skuReqDTO.setPackDay(Integer.parseInt(skuVOJson.getString("packDay")));
					}
					if (StringUtil.isNotBlank(skuVOJson.getString("showOrder"))) {
						skuReqDTO.setShowOrder(Integer.parseInt(skuVOJson.getString("showOrder")));
					}
					reqList.add(skuReqDTO);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	private void setGdsInfo2PropInfo(List<GdsInfo2PropReqDTO> reqList, JSONArray jsonArray) throws Exception {

		try {
			// 遍历元素
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject propVOJson = (JSONObject) jsonArray.get(i);
				if (propVOJson != null) {
					GdsInfo2PropReqDTO propReqDTO = new GdsInfo2PropReqDTO();
					String popType = propVOJson.getString("proType");
					if (StringUtil.isNotBlank(propVOJson.getString("gpId"))) {
						propReqDTO.setGpId(Integer.parseInt(propVOJson.getString("gpId")));
					}
					if (StringUtil.isNotBlank(propVOJson.getString("gdsId"))) {
						propReqDTO.setGdsId(Integer.parseInt(propVOJson.getString("gdsId")));
					}
					if (StringUtil.isNotBlank(propVOJson.getString("proId"))) {
						propReqDTO.setProId(Integer.parseInt(propVOJson.getString("proId")));
					}
					if (StringUtil.isNotBlank(popType)) {
						propReqDTO.setProType(popType);
					}
					propReqDTO.setProName(propVOJson.getString("proName"));
					// 富文本
					if ("2".equals(popType)) {
						String propValuehtml = propVOJson.getString("proValue");
						if (StringUtil.isNotBlank(propValuehtml)) {
							// String
							// dd=HtmlUtils.htmlUnescape(java.net.URLDecoder.decode(propValuehtml,"UTF-8"));
							String htmlData = HtmlUtils.htmlUnescape(propValuehtml);
							// 保存静态文件到静态文件服务器
							String infoUrl = MongoFileUtil.saveFile(htmlData.getBytes("utf-8"), "gdsContent", ".html");
							propReqDTO.setProValue(infoUrl);
						}
					} else {
						propReqDTO.setProValue(propVOJson.getString("proValue"));
					}
					// 展示顺序
					if (StringUtil.isNotBlank(propVOJson.getString("showOrder"))) {
						propReqDTO.setShowOrder(Integer.parseInt(propVOJson.getString("showOrder")));
					}
					reqList.add(propReqDTO);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * 根据catId获取商品分类属性信息
	 * @param req
	 * @param rep
	 * @param catId
	 * @return
	 */
	@RequestMapping(value = "/queryGdsPropList")
    @ResponseBody
	public GdsJsonBean queryGdsPropList(HttpServletRequest req, HttpServletResponse rep, GdsPropVO gdsPropVO) {
		GdsJsonBean json = new GdsJsonBean();
		try {
			GdsPropReqDTO reqDTO = new GdsPropReqDTO();
			reqDTO.setCatId(gdsPropVO.getCatId());
			reqDTO.setStatus("1");//有效
			List<GdsPropRespDTO> propList = gdsInfoRSV.queryGdsPropList(reqDTO);
			json.setObject(propList);
			json.setSuccess("true");
		} catch (Exception e) {
			json.setSuccess("false");
			json.setMsg("获取商品分类属性信息失败,原因：" + e.getMessage());
			logger.error("获取商品分类属性信息失败,原因：" + e.getMessage(), e);
		}
		return json;
	}
	/**
	 * 获取商品分类属性信息
	 * @param req
	 * @param rep
	 * @param gdsInfo2PropVO
	 * @return
	 */
	@RequestMapping(value = "/queryGdsInfo2PropList")
    @ResponseBody
	public GdsJsonBean queryGdsInfo2PropList(HttpServletRequest req, HttpServletResponse rep, GdsInfo2PropVO gdsInfo2PropVO) {
		GdsJsonBean json = new GdsJsonBean();
		try {
			List<GdsInfo2PropVO> gdsInfo2PropVOList = new ArrayList<GdsInfo2PropVO>();
			GdsInfo2PropReqDTO gdsInfo2PropReqDTO = new GdsInfo2PropReqDTO();
			gdsInfo2PropReqDTO.setGdsId(gdsInfo2PropVO.getGdsId());
			gdsInfo2PropReqDTO.setStatus("1");
			List<GdsInfo2PropRespDTO> gdsInfo2PropRespDTOList = iGdsInfo2PropRSV.queryGdsInfo2PropList(gdsInfo2PropReqDTO);
			if(CollectionUtils.isNotEmpty(gdsInfo2PropRespDTOList)){
				for(GdsInfo2PropRespDTO resp:gdsInfo2PropRespDTOList){
					if(PRO_TYPE.equals(resp.getProType())&&resp.getProValue()!=null){
						resp.setProValue(getHtmlUrl(resp.getProValue()));
					}
				}
			}
			json.setObject(gdsInfo2PropRespDTOList);
			json.setSuccess("true");
		} catch (Exception e) {
			json.setSuccess("false");
			json.setMsg("获取商品分类属性信息失败,原因：" + e.getMessage());
			logger.error("获取商品分类属性信息失败,原因：" + e.getMessage(), e);
		}
		return json;
	}
	 /**
     * 上传图片
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String height=request.getParameter("height");
        String width=request.getParameter("width");
        String isSizeLimit=request.getParameter("isSizeLimit");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获取图片
        Iterator<MultipartFile> files = multipartRequest.getFileMap().values().iterator();
        MultipartFile file = null;
        if (files.hasNext()) {
            file = files.next();
        }
        Iterator<String> ids = multipartRequest.getFileMap().keySet().iterator();
        String id = null;
        if (ids.hasNext()) {
            id = ids.next();
        }
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (file == null) {
                resultMap.put("flag", false);
                resultMap.put("error", "请选择上传文件！");
                out.print(JSONObject.toJSONString(resultMap));
                return;
            }
            String fileName = file.getOriginalFilename();
            String extensionName = "." + getExtensionName(fileName);

            /** 支持文件拓展名：.jpg,.png,.jpeg,.gif,.bmp */
            boolean flag = Pattern.compile("\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$")
                    .matcher(extensionName.toLowerCase()).find();
            if (!flag) {
                resultMap.put("flag", false);
                resultMap.put("error", "请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)!");
                out.print(JSONObject.toJSONString(resultMap));
                return;
            }
            BufferedImage sourceImg = ImageIO.read(file.getInputStream());
            int imgWidth = sourceImg.getWidth();
            int imgHeight = sourceImg.getHeight();
            if(!StringUtil.isBlank(isSizeLimit)){//是否限制图片大小在100k
            	if("true".equals(isSizeLimit)){
            		if(file.getSize()>102400){
            			resultMap.put("flag", false);
            			resultMap.put("error", "请上传小于"+isSizeLimit+"k的图片。");
            			out.print(JSONObject.toJSONString(resultMap));
            			return;
            		}
            	}
            }
            if(StringUtil.isNotBlank(width)&&StringUtil.isNotBlank(height)){
            	if(Integer.parseInt(width)!=imgWidth||Integer.parseInt(height)!=imgHeight){
            		resultMap.put("flag", false);
            		resultMap.put("error", "请上传"+width+"*"+height+"的图片。");
            		out.print(JSONObject.toJSONString(resultMap));
            		return;
            	}
            }
            byte[] datas = inputStream2Bytes(file.getInputStream());
            String imageId = ImageUtil.upLoadImage(datas, fileName);
            String imagePath=ImageUtil.getImageUrl(imageId+ "_100x100");
            resultMap.put("flag", true);
            resultMap.put("imageId", imageId);
            resultMap.put("imageName", fileName);
            resultMap.put("id", id);
            resultMap.put("imagePath", imagePath);
            out.print(JSONObject.toJSONString(resultMap));
            logger.debug("imageId = " + imageId);
        } catch (Exception e) {
            logger.error("【图片保存失败】异常信息：" + e);
        } finally {
            out.close();
        }
    }
	/**
     * 将InputStream转换成byte数组
     * 
     * @param in
     * @return
     * @throws IOException
     */
    private byte[] inputStream2Bytes(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1)
            outStream.write(data, 0, count);
        data = null;
        return outStream.toByteArray();
    }

    /**
     * 获取文件拓展名
     * 
     * @param fileName
     * @return
     */
    private String getExtensionName(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(dot + 1);
            }
        }
        return fileName;
    }

    private String getHtmlUrl(String vfsId) {
        return ImageUtil.getStaticDocUrl(vfsId, "html");
    }

	/**
	 * 获取API接口
	 * 
	 * @param model
	 * @param searchVO
	 * @return
	 */
	@RequestMapping(value = "gridAPIInfoList")
	public String gridAPIInfoList(Model model, AipServiceInfoVO apiServiceVO) {
		try {
			PageResponseDTO<AipServiceInfoDTO> pageInfo = new PageResponseDTO<AipServiceInfoDTO>();
			AipServiceInfoReqDTO apiReqDTO = new AipServiceInfoReqDTO();
			apiReqDTO.setPageNo(apiServiceVO.getPageNo());
			apiReqDTO.setPageSize(PAGE_SIZE);
			pageInfo = iAipServiceInfoRSV.selectServiceWithPage(apiReqDTO);
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			logger.error("查询API接口失败！原因是：" + e.getMessage());
		}
		return "goods_import :: #gdsAPIList";
	}
	
	/**
	 * 
	 * gridPicList:(根据图片库libId获取图片列表). <br/> 
	 * 
	 * @author gxq 
	 * @param model
	 * @param picManageVO
	 * @return 
	 * @since JDK 1.6
	 */
    @RequestMapping(value = "gridPicList")
    public String gridPicList(Model model, PicManageVO picManageVO) {
        try {
            PageResponseDTO<PicInfoRespDTO> pageInfo = new PageResponseDTO<PicInfoRespDTO>();
            PicInfoReqDTO picInfoReqDTO = new PicInfoReqDTO();
            picInfoReqDTO.setPageNo(picManageVO.getPageNo());
            picInfoReqDTO.setPageSize(6);
            ObjectCopyUtil.copyObjValue(picManageVO, picInfoReqDTO, null, false);
            pageInfo = iPicInfoRSV.queryPicInfoPage(picInfoReqDTO);
            pageInfo.setPageSize(6);
            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error("查询图片列表失败！原因是：" + e.getMessage());
        }
        return "gds/div/gdsEditPicList";
    }
}
