package com.ai.bdex.dataexchange.busi.gds.controller;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ai.bdex.dataexchange.busi.gds.entity.GdsCatVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfoVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsJsonBean;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsLabelQuikVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsManageInfoVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsPropVO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2CatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2CatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2PropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelQuikReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelQuikRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsPropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsPropRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsCatRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfo2PropRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsLabelRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsSkuRSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.util.ImageUtil;
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
        		gdsInfoRespDTO=gdsInfoRSV.queryGdsInfoDetails(gdsInfoReqDTO);
        		
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
    @RequestMapping(value = "/saveGdsLabelQuik")
    @ResponseBody
    public GdsJsonBean GdsLabelQuik(HttpServletRequest req, HttpServletResponse rep,
    		GdsLabelQuikVO gdsLabelQuikVO) throws BusinessException, GenericException {
    	GdsJsonBean jsonBean = new GdsJsonBean();
    	GdsLabelQuikReqDTO labelReqDTO = new GdsLabelQuikReqDTO();
        try {
        	labelReqDTO.setLabName(gdsLabelQuikVO.getLabName());
        	labelReqDTO.setLabColor(gdsLabelQuikVO.getLabColor());
        	labelReqDTO.setCatFirst(gdsLabelQuikVO.getCatFirst());
        	int labId = gdsInfoRSV.insertGdsLabelQuik(labelReqDTO);
        	//商品标签信息
            jsonBean.setSuccess("true");
            jsonBean.setMsg("商品标签快速选择数据录入成功！");
            jsonBean.setObject(labId);
        } catch (Exception e) {
            jsonBean.setSuccess("false");
            jsonBean.setMsg(e.getMessage());
            logger.error("商品标签快速选择数据录入失败,原因："+e.getMessage(),e);
        }

        return jsonBean;
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
    		GdsManageInfoVO gdsManageInfoVO) throws BusinessException, GenericException {
    	GdsJsonBean jsonBean = new GdsJsonBean();
    	GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        try {
        	//商品基本信息
    		JSONObject gdsInfoVO=JSONObject.parseObject(req.getParameter("gdsInfoVO"));
			this.setGdsInfo(gdsInfoReqDTO, gdsInfoVO);
        	int gdsId=gdsInfoRSV.insertGdsInfo(gdsInfoReqDTO);
        	GdsInfo2CatReqDTO gdsInfo2CatReqDTO = new GdsInfo2CatReqDTO();
       		JSONObject gdsInfo2CatVO=JSONObject.parseObject(req.getParameter("gdsInfo2CatVO"));
    		this.setGdsInfo2Cat(gdsInfo2CatReqDTO, gdsInfo2CatVO);
        	
        	//商品标签信息
        	JSONArray gdsLabelVOList=JSONArray.parseArray(req.getParameter("gdsLabelVOList"));
        	List<GdsLabelReqDTO> gdsLabelReqDTOList =  new ArrayList<>();
        	this.setGdsLabel(gdsLabelReqDTOList, gdsLabelVOList);
        	//保存商品标签
        	if(CollectionUtils.isNotEmpty(gdsLabelReqDTOList)){
        		for(GdsLabelReqDTO labelReq : gdsLabelReqDTOList){
        			labelReq.setGdsId(gdsId);
        			iGdsLabelRSV.insertGdsLabel(labelReq);
        		}
        	}
        	//获取商品单品信息
        	JSONArray gdsSkuVOList=JSONArray.parseArray(req.getParameter("gdsSkuList"));
        	List<GdsSkuReqDTO> gdsSkuReqDTOList =  new ArrayList<>();
        	this.setGdsSkuInfo(gdsSkuReqDTOList, gdsSkuVOList);
        	//保存单品信息
        	if(CollectionUtils.isNotEmpty(gdsSkuReqDTOList)){
        		for(GdsSkuReqDTO skuReq : gdsSkuReqDTOList){
        			skuReq.setGdsId(gdsId);
        			iGdsSkuRSV.insertGdsSku(skuReq);
        		}
        	}
        	//获取商品属性配置信息
        	JSONArray gdsInfo2PropVOList=JSONArray.parseArray(req.getParameter("gdsInfo2PropList"));
        	List<GdsInfo2PropReqDTO> gdsInfo2PropReqDTOList =  new ArrayList<>();
        	this.setGdsInfo2PropInfo(gdsInfo2PropReqDTOList, gdsInfo2PropVOList);
        	//保存商品属性配置信息
        	if(CollectionUtils.isNotEmpty(gdsInfo2PropReqDTOList)){
        		for(GdsInfo2PropReqDTO propReq : gdsInfo2PropReqDTOList){
        			propReq.setGdsId(gdsId);
        			iGdsInfo2PropRSV.insertGdsInfo2Prop(propReq);
        		}
        	}
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
    		GdsManageInfoVO gdsManageInfoVO) throws  BusinessException, GenericException {
    	GdsJsonBean jsonBean = new GdsJsonBean();
    	GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
    	GdsLabelReqDTO gdsLabelReqDTO = new GdsLabelReqDTO();
        try {
        	if(gdsManageInfoVO.getGdsInfoVO().getGdsId()!=0){
            	gdsInfoReqDTO.setGdsId(gdsManageInfoVO.getGdsInfoVO().getGdsId());
        	}
        	if(StringUtil.isNotBlank(gdsManageInfoVO.getGdsInfoVO().getGdsName())){
            	gdsInfoReqDTO.setGdsName(gdsManageInfoVO.getGdsInfoVO().getGdsName());
        	}
        	if(StringUtil.isNotBlank(gdsManageInfoVO.getGdsInfoVO().getGdsSubtitle())){
            	gdsInfoReqDTO.setGdsSubtitle(gdsManageInfoVO.getGdsInfoVO().getGdsSubtitle());
        	}	
        	if(gdsManageInfoVO.getGdsInfoVO().getCatFirst()!=0){
            	gdsInfoReqDTO.setCatFirst(gdsManageInfoVO.getGdsInfoVO().getCatFirst());
        	}
        	if(StringUtil.isNotBlank(gdsManageInfoVO.getGdsInfoVO().getGdsPic())){
            	gdsInfoReqDTO.setGdsPic(gdsManageInfoVO.getGdsInfoVO().getGdsPic());
        	}
//        	if(gdsManageInfoVO.getGdsLabelVOList()!=null){
//        		gdsLabelReqDTO.setLabColor(gdsManageInfoVO.getGdsLabelVO().getLabColor());
//        		gdsLabelReqDTO.setLabName(gdsManageInfoVO.getGdsLabelVO().getLabName());
//        		gdsLabelReqDTO.setLabId(gdsManageInfoVO.getGdsLabelVO().getLabId());
//        		gdsLabelReqDTO.setGdsId(gdsManageInfoVO.getGdsLabelVO().getGdsId());
//
//        	}
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
	private void setGdsInfo(GdsInfoReqDTO vo,JSONObject baseInfoJson){
  		try {
			if(StringUtil.isNotBlank(baseInfoJson.getString("gdsId"))){
			    vo.setGdsId(Integer.parseInt(baseInfoJson.getString("gdsId")));
			}
			vo.setGdsName(baseInfoJson.getString("gdsName"));
			vo.setCatFirst(Integer.parseInt(baseInfoJson.getString("catFirst")));
			vo.setApiId(Integer.parseInt(baseInfoJson.getString("apiId")));
			vo.setGdsPic(baseInfoJson.getString("gdsPic"));
			vo.setIfRecommend(baseInfoJson.getString("ifRecommend"));
			vo.setFunIntroduction(baseInfoJson.getString("funIntroduction"));
			vo.setCommpanyName(baseInfoJson.getString("commpanyName"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	private void setGdsInfo2Cat(GdsInfo2CatReqDTO vo,JSONObject baseInfoJson){
  		try {
  			if(StringUtil.isNotBlank(baseInfoJson.getString("gcId"))){
			    vo.setGcId(Integer.parseInt(baseInfoJson.getString("gcId")));
			}
  			if(StringUtil.isNotBlank(baseInfoJson.getString("catId"))){
			    vo.setCatId(Integer.parseInt(baseInfoJson.getString("catId")));
			}
  			if(StringUtil.isNotBlank(baseInfoJson.getString("catFirst"))){
			    vo.setCatFirst(Integer.parseInt(baseInfoJson.getString("catFirst")));
			}
			if(StringUtil.isNotBlank(baseInfoJson.getString("gdsId"))){
			    vo.setGdsId(Integer.parseInt(baseInfoJson.getString("gdsId")));
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
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	private void setGdsSkuInfo(List<GdsSkuReqDTO> reqList, JSONArray jsonArray) {

		try {
			// 遍历元素
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject skuVOJson = (JSONObject) jsonArray.get(i);
				GdsSkuReqDTO skuReqDTO = new GdsSkuReqDTO();
				if (StringUtil.isNotBlank(skuVOJson.getString("skuId"))) {
					skuReqDTO.setSkuId(Integer.parseInt(skuVOJson.getString("skuId")));
				}
				if (StringUtil.isNotBlank(skuVOJson.getString("gdsId"))) {
					skuReqDTO.setGdsId(Integer.parseInt(skuVOJson.getString("gdsId")));
				}
				skuReqDTO.setSkuName(skuVOJson.getString("skuName"));
				if (StringUtil.isNotBlank(skuVOJson.getString("packPrice"))) {
					skuReqDTO.setPackPrice(Integer.parseInt(skuVOJson.getString("packPrice")));
				}
				//次数
				if (StringUtil.isNotBlank(skuVOJson.getString("packTimes"))) {
					skuReqDTO.setPackTimes(Integer.parseInt(skuVOJson.getString("packTimes")));
				}
				//有效期
				if (StringUtil.isNotBlank(skuVOJson.getString("packDay"))) {
					skuReqDTO.setPackDay(Integer.parseInt(skuVOJson.getString("packDay")));
				}
				if (StringUtil.isNotBlank(skuVOJson.getString("showOrder"))) {
					skuReqDTO.setShowOrder(Integer.parseInt(skuVOJson.getString("showOrder")));
				}
				reqList.add(skuReqDTO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	private void setGdsInfo2PropInfo(List<GdsInfo2PropReqDTO> reqList, JSONArray jsonArray) {

		try {
			// 遍历元素
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject propVOJson = (JSONObject) jsonArray.get(i);
				GdsInfo2PropReqDTO propReqDTO = new GdsInfo2PropReqDTO();
				if (StringUtil.isNotBlank(propVOJson.getString("gpId"))) {
					propReqDTO.setGpId(Integer.parseInt(propVOJson.getString("gpId")));
				}
				if (StringUtil.isNotBlank(propVOJson.getString("gdsId"))) {
					propReqDTO.setGdsId(Integer.parseInt(propVOJson.getString("gdsId")));
				}
				if (StringUtil.isNotBlank(propVOJson.getString("proId"))) {
					propReqDTO.setProId(Integer.parseInt(propVOJson.getString("proId")));
				}
				if (StringUtil.isNotBlank(propVOJson.getString("proType"))) {
					propReqDTO.setProType(propVOJson.getString("proType"));
				}
				propReqDTO.setProName(propVOJson.getString("proName"));
				propReqDTO.setProValue(propVOJson.getString("proValue"));
				//展示顺序
				if (StringUtil.isNotBlank(propVOJson.getString("showOrder"))) {
					propReqDTO.setShowOrder(Integer.parseInt(propVOJson.getString("showOrder")));
				}
				reqList.add(propReqDTO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
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
     * 上传图片
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
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
            byte[] datas = inputStream2Bytes(file.getInputStream());
            String imageId = ImageUtil.upLoadImage(datas, fileName);
            resultMap.put("flag", true);
            resultMap.put("imageId", imageId);
            resultMap.put("imageName", fileName);
            resultMap.put("id", id);
            resultMap.put("imagePath", ImageUtil.getImageUrl(imageId + "_150x150"));
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
    
   
}
 
