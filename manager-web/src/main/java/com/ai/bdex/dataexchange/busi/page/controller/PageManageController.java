package com.ai.bdex.dataexchange.busi.page.controller;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.ai.bdex.dataexchange.busi.page.entity.PageModuleAdVO;
import com.ai.bdex.dataexchange.busi.page.entity.PageModuleVO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageDisplayRSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.util.MongoFileUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Description:广交所首页 <br>
 * Date: 2017年4月19日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author liangwy
 */
@Controller
@RequestMapping(value = "/pageManage")
public class PageManageController {

	private final static String STATUS_VALID = "1";// 有效
	private final static String STATUS_INVALID = "0";// 失效
	private final static Integer PAGE_SIZE=10;//
	private final static String  MODULE_TYPE_AD="02";
	private static final Logger log = LoggerFactory.getLogger(PageManageController.class);

	@DubboConsumer(timeout = 30000)
	IPageDisplayRSV iPageDisplayRSV;
	@DubboConsumer(timeout = 30000)
	IGdsInfoRSV iGdsInfoRSV;
	
	@RequestMapping(value = "/newsInfo")
	public ModelAndView pageInit(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("info_details");
		return modelAndView;
	}

	/**
	 * 查询新闻资讯信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryNewsPageInfo")
	@ResponseBody
	public Map<String, Object> queryNewsPageInfo(Model model, HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		String infoTitle = request.getParameter("infoTitle");
		String status = request.getParameter("status");
		String infotype = request.getParameter("infotype");
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			PageNewsInfoReqDTO sortInfoDTO = new PageNewsInfoReqDTO();
			if(!StringUtils.isBlank(pageNo)){
				sortInfoDTO.setPageNo(Integer.valueOf(pageNo));
			}
			if(!StringUtils.isBlank(pageSize)){
				sortInfoDTO.setPageSize(Integer.valueOf(pageSize));
			}
			if(!StringUtils.isBlank(infoTitle)){
				sortInfoDTO.setInfoTitle(infoTitle);
			}
			if(!StringUtils.isBlank(status)){
				sortInfoDTO.setStatus(status);
			}
			if(!StringUtils.isBlank(infotype)){
				sortInfoDTO.setInfoType(infotype);
			}
			if(!StringUtils.isBlank(pageSize)){
				sortInfoDTO.setPageSize(Integer.valueOf(pageSize));
			}else{
				sortInfoDTO.setPageSize(10);
			}
			
			PageResponseDTO<PageNewsInfoRespDTO> newsInfoPageInfo = iPageDisplayRSV.queryPageNewsInfoList(sortInfoDTO);
			if(!CollectionUtils.isEmpty(newsInfoPageInfo.getResult())){
				for(PageNewsInfoRespDTO newsInfoRespDTO: newsInfoPageInfo.getResult()){
					if("1".equals(newsInfoRespDTO.getInfoType())){
						newsInfoRespDTO.setInfoType("行业资讯");
					}else if("2".equals(newsInfoRespDTO.getInfoType())){
						newsInfoRespDTO.setInfoType("平台公告");
					}else if("3".equals(newsInfoRespDTO.getInfoType())){
						newsInfoRespDTO.setInfoType("平台活动");
					}else{
						newsInfoRespDTO.setInfoType("常见问题");
					}
				}
			}
			rMap.put("pageInfo", newsInfoPageInfo);
			rMap.put("infoTitle", infoTitle);
			rMap.put("status", status);
			rMap.put("infotype", infotype);
			rMap.put("success",true);
		} catch (Exception e) {
			rMap.put("success",false);
			log.error("查询新闻资讯信息出错：" + e.getMessage());
		}
		return rMap;
	}
	@RequestMapping(value = "/addNewsPageInfo")
	public String addNewsPageInfo(Model model, HttpServletRequest request) {
		String infoId = request.getParameter("infoId");
		PageNewsInfoRespDTO newsInfo = new PageNewsInfoRespDTO();
		String moduleId = "";
		String infoUrl = "";
		String infoTitle = "";
		String infoType = "";
		try {
			if(!StringUtils.isBlank(infoId)){
				newsInfo = iPageDisplayRSV.queryPageNewsInfoById(Integer.valueOf(infoId));
				if(newsInfo != null){
					if(newsInfo.getInfoUrl()!= null){
						infoUrl = ImageUtil.getStaticDocUrl(newsInfo.getInfoUrl(), "html");
						newsInfo.setInfoUrl(infoUrl);
					}
					moduleId = String.valueOf(newsInfo.getModuleId());
					infoTitle = newsInfo.getInfoTitle();
					infoType = newsInfo.getInfoType();
				}
			}
		} catch (Exception e) {
			log.error("新闻资讯信息新增出错：" + e.getMessage());
		}
		request.setAttribute("infoId", infoId);
		request.setAttribute("moduleId", moduleId);
		request.setAttribute("infoUrl", infoUrl);
		request.setAttribute("infoTitle", infoTitle);
		request.setAttribute("infoType", infoType);
		return "page/newsInfo-add";
	} 
	@RequestMapping(value="/updateNewsPageInfo")
	@ResponseBody
	public Map<String,Object> updateNewsPageInfo(HttpServletRequest request){
		Map<String,Object>  rMap = new HashMap<>();
		String infoId = request.getParameter("infoId");
		String status = request.getParameter("status");
		try {
			PageNewsInfoReqDTO newsInfoReqDTO = new PageNewsInfoReqDTO();
			if(!StringUtils.isBlank(status)){
				newsInfoReqDTO.setStatus(status);
			}
			if(!StringUtils.isBlank(infoId)){
				newsInfoReqDTO.setInfoId(Integer.valueOf(infoId));
			}
			long infoByKey = iPageDisplayRSV.updatePageNewsInfoByKey(newsInfoReqDTO);
			rMap.put("infoByKey", infoByKey);
		} catch (Exception e) {
			log.error("新闻资讯信息删除出错：" + e.getMessage());
		}
		return rMap;
	}
	@RequestMapping(value = "/moduleManage")
	public ModelAndView moduleManage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("module_manager");
		return modelAndView;
	}
	@RequestMapping(value = "/editModule")
	public ModelAndView editModule(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("edit_module");
		String moduleId=request.getParameter("moduleId");
		try {
			if(StringUtils.isNotEmpty(moduleId)){
				PageModuleRespDTO moduleRespDTO = iPageDisplayRSV.queryPageModuleById(Integer.parseInt(moduleId));
				if(STATUS_VALID.equals(moduleRespDTO.getStatus())){
					moduleRespDTO.setValidStatus(true);
				}else{
					moduleRespDTO.setInValidStatus(true);
				}
				modelAndView.addObject("moduleRespDTO", moduleRespDTO);
			}
		}catch (Exception e) {
				log.error("查询楼层信息出错：" + e.getMessage());
		}
		modelAndView.addObject("moduleId", moduleId);
		return modelAndView;
	}
	/**
	 * 保存模块信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/savePageModule")
	@ResponseBody
	public Map<String, Object> savePageModule(Model model, HttpServletRequest request,PageModuleVO pageModuleVO) {
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
			if(pageModuleVO.getModuleId()!=null){
				pageModuleReqDTO.setModuleId(pageModuleVO.getModuleId());
			}
			if(pageModuleVO.getModulePid()!=null){
				pageModuleReqDTO.setModulePid(pageModuleVO.getModulePid());
			}
			if(!StringUtils.isBlank(pageModuleVO.getModuleName())){
				pageModuleReqDTO.setModuleName(pageModuleVO.getModuleName());
			}
			if(!StringUtils.isBlank(pageModuleVO.getStatus())){
				pageModuleReqDTO.setStatus(pageModuleVO.getStatus());
			}
			if(pageModuleVO.getOrderNo()!=null){
				pageModuleReqDTO.setOrderNo(pageModuleVO.getOrderNo());
			}
			if(pageModuleVO.getModuleCount()!=null){
				pageModuleReqDTO.setModuleCount(pageModuleVO.getModuleCount());
			}
			pageModuleReqDTO.setRemark(pageModuleVO.getRemark());
			iPageDisplayRSV.updatePageModule(pageModuleReqDTO);
			rMap.put("success",true);
		} catch (Exception e) {
			rMap.put("success",false);
			log.error("保存模块出错：" + e.getMessage());
		}
		return rMap;
	}
	@RequestMapping(value = "/queryModuleList")
	@ResponseBody
	public Map<String, Object> queryModuleList(Model model, HttpServletRequest request) {
		String moduleName = request.getParameter("moduleName");
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
			if(StringUtil.isNotBlank(moduleName)){
				pageModuleReqDTO.setModuleName(moduleName);
			}
			List<PageModuleRespDTO> moduleList = iPageDisplayRSV.queryPageModuleList(pageModuleReqDTO);
			if(!CollectionUtils.isEmpty(moduleList)){
				for(PageModuleRespDTO moduleRespDTO : moduleList){
					if(moduleRespDTO.getModulePid() != -1){
						pageModuleReqDTO.setModuleId(moduleRespDTO.getModulePid());
						PageModuleRespDTO respDTO = iPageDisplayRSV.queryPageModuleList(pageModuleReqDTO).get(0);
						if(respDTO != null){
							moduleRespDTO.setRemark(respDTO.getModuleName());
						}
					}
				}
			}
			rMap.put("moduleList", moduleList);
			rMap.put("moduleName", moduleName);
			rMap.put("success",true);
		} catch (Exception e) {
			rMap.put("success",false);
			log.error("查询楼层信息出错：" + e.getMessage());
		}
		return rMap;
	}
	/**
	 * 查询广告
	 * 
	 * @param model
	 * @param searchVO
	 * @return
	 */
	@RequestMapping(value = "queryModulePageAdList")
	public String queryModulePageAdList(Model model, PageModuleAdVO moduleAdVO) {
		try {
			PageResponseDTO<PageModuleAdRespDTO> pageInfo = new PageResponseDTO<PageModuleAdRespDTO>();
			PageModuleAdReqDTO adReqDTO = new PageModuleAdReqDTO();
			adReqDTO.setPageNo(moduleAdVO.getPageNo());
			adReqDTO.setPageSize(PAGE_SIZE);
			if(moduleAdVO.getModuleId()!=null){
				adReqDTO.setModuleId(moduleAdVO.getModuleId());
			}
			List<String> statusList=new ArrayList<String>();
			//查询有效、失效
			statusList.add(STATUS_VALID);
			statusList.add(STATUS_INVALID);
			adReqDTO.setStatusList(statusList);
			pageInfo = iPageDisplayRSV.queryPageModuleAdPageInfo(adReqDTO);
			if(!CollectionUtils.isEmpty(pageInfo.getResult())){
				for(PageModuleAdRespDTO adRespDTO :pageInfo.getResult()){
					adRespDTO.setVfsIdUrl(ImageUtil.getImageUrl(adRespDTO.getVfsId() + "_100x100"));
					//根据modularID查询t_page_modular信息
					PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
					pageModuleReqDTO.setModuleId(adRespDTO.getModuleId());
					List<PageModuleRespDTO> respDTOList = iPageDisplayRSV.queryPageModuleInfoList(pageModuleReqDTO);
					if(!CollectionUtils.isEmpty(respDTOList)){
						adRespDTO.setPageModuleRespDTO(respDTOList.get(0));

					}
				}
			}
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			log.error("查询广告列表失败！原因是：" + e.getMessage());
		}
		return "page/modulePageAdList";
	}
	/**
	 * 更新广告信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updatePageModuleAdInfo")
	@ResponseBody
	public Map<String,Object> updatePageModuleAdInfo(HttpServletRequest request,PageModuleAdVO moduleAdVO){
		Map<String,Object>  rMap = new HashMap<>();
		int adId = moduleAdVO.getAdId();
		String status = moduleAdVO.getStatus();
		try {
			PageModuleAdReqDTO adReqDTO = new PageModuleAdReqDTO();
			if(!StringUtils.isBlank(status)){
				adReqDTO.setStatus(status);
			}
			if(moduleAdVO.getAdId()!=null){
				adReqDTO.setAdId(moduleAdVO.getAdId());
			}
			if(moduleAdVO.getModuleId()!=null){
				adReqDTO.setModuleId(moduleAdVO.getModuleId());
			}
			iPageDisplayRSV.updatePageModuleAdByKey(adReqDTO);
			rMap.put("success", true);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("更新广告信息出错：" + e.getMessage());
		}
		return rMap;
	}
	@RequestMapping(value = "/saveNewsInfo")
	@ResponseBody
	public Map<String, Object> saveNewsInfo(HttpServletRequest request){
		String infoId = request.getParameter("infoId");
		String moduleId = request.getParameter("moduleId");
		String infoTitle = request.getParameter("infoTitle");
		String infoType = request.getParameter("infoType");
		String ckeditContent = request.getParameter("ckeditContent");
		ckeditContent = HtmlUtils.htmlUnescape(ckeditContent);
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			PageNewsInfoReqDTO newsInfoReqDTO = new PageNewsInfoReqDTO();
			 //保存静态文件到静态文件服务器
			if(!StringUtils.isBlank(ckeditContent)){
				String infoUrl = MongoFileUtil.saveFile(ckeditContent.getBytes("utf-8"),"gdsContent", ".html");
				newsInfoReqDTO.setInfoUrl(infoUrl);
			}
			if(!StringUtils.isBlank(infoId)){
				newsInfoReqDTO.setInfoId(Integer.valueOf(infoId));
			}
			if(!StringUtils.isBlank(moduleId)){
				newsInfoReqDTO.setModuleId(Integer.valueOf(moduleId));
			}else{
				if("1".equals(infoType)){
					newsInfoReqDTO.setModuleId(105);
				}else if("2".equals(infoType)){
					newsInfoReqDTO.setModuleId(106);
				}else if("3".equals(infoType)){
					newsInfoReqDTO.setModuleId(107);
				}else{
					newsInfoReqDTO.setModuleId(108);
				}
			}
			newsInfoReqDTO.setStatus(STATUS_VALID);
			newsInfoReqDTO.setInfoTitle(infoTitle);
			newsInfoReqDTO.setInfoType(infoType);
			if(StringUtils.isBlank(infoId)){
				iPageDisplayRSV.insertPageNewsInfo(newsInfoReqDTO);
			}else{
				long infoByKey = iPageDisplayRSV.updatePageNewsInfoByKey(newsInfoReqDTO);
				System.out.println(infoByKey);
			}
			rMap.put("success",true);
		} catch (Exception e) {
			rMap.put("success",false);
			log.error("查询楼层信息出错：" + e.getMessage());
		}
		return rMap;
	}
	/**
	 * 编辑广告
	 * 
	 * @param model
	 * @param searchVO
	 * @return
	 */
	@RequestMapping(value = "editModulePageAd")
	public String editModulePageAd(Model model, PageModuleAdVO moduleAdVO) {
		try {
			PageModuleAdRespDTO adRespDTO = new PageModuleAdRespDTO();
			if(moduleAdVO.getAdId()!=null){
				adRespDTO =iPageDisplayRSV.queryPageModuleAdById(moduleAdVO.getAdId());
				adRespDTO.setVfsIdUrl(ImageUtil.getImageUrl(adRespDTO.getVfsId() + "_100x100"));
			}
			//根据moduleTye查询广告信息信息 
			PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
			pageModuleReqDTO.setModuleType(MODULE_TYPE_AD);
			//查询全部的广告版位
			List<PageModuleRespDTO> moduleAdList = iPageDisplayRSV.queryPageModuleInfoList(pageModuleReqDTO);
			model.addAttribute("moduleId", moduleAdVO.getModuleId());
			model.addAttribute("moduleAdList", moduleAdList);
			model.addAttribute("adRespDTO", adRespDTO);
		} catch (Exception e) {
			log.error("查询广告列表失败！原因是：" + e.getMessage());
		}
		return "edit_banner";
	}
	/**
	 * 保存广告信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/savePageModuleAdInfo")
	@ResponseBody
	public Map<String,Object> savePageModuleAdInfo(HttpServletRequest request,PageModuleAdVO moduleAdVO){
		Map<String,Object>  rMap = new HashMap<>();
		String status = moduleAdVO.getStatus();
		try {
			PageModuleAdReqDTO adReqDTO = new PageModuleAdReqDTO();
			if(!StringUtils.isBlank(status)){
				adReqDTO.setStatus(status);
			}
			if(moduleAdVO.getAdId()!=null){
				adReqDTO.setAdId(moduleAdVO.getAdId());
			}
			if(moduleAdVO.getModuleId()!=null){
				adReqDTO.setModuleId(moduleAdVO.getModuleId());
			}
			if(StringUtils.isNotEmpty(moduleAdVO.getAdTitle())){
				adReqDTO.setAdTitle(moduleAdVO.getAdTitle());
			}
			if(StringUtils.isNotEmpty(moduleAdVO.getLinkPage())){
				adReqDTO.setLinkPage(moduleAdVO.getLinkPage());
			}
			if(StringUtils.isNotEmpty(moduleAdVO.getBmpName())){
				adReqDTO.setBmpName(moduleAdVO.getBmpName());
			}
			if(StringUtils.isNotEmpty(moduleAdVO.getVfsId())){
				adReqDTO.setVfsId(moduleAdVO.getVfsId());
			}
			if(moduleAdVO.getAdOrder()!=null){
				adReqDTO.setAdOrder(moduleAdVO.getAdOrder());
			}
			adReqDTO.setStatus(STATUS_VALID);
			if(moduleAdVO.getAdId()!=null){//编辑
				iPageDisplayRSV.updatePageModuleAdByKey(adReqDTO);
			}else{
				iPageDisplayRSV.insertPageModuleAdInfo(adReqDTO);
			}
			rMap.put("success", true);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("保存广告信息出错：" + e.getMessage());
		}
		return rMap;
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
            String imagePath=ImageUtil.getImageUrl(imageId);
            resultMap.put("flag", true);
            resultMap.put("imageId", imageId);
            resultMap.put("imageName", fileName);
            resultMap.put("id", id);
            resultMap.put("imagePath", imagePath);
            out.print(JSONObject.toJSONString(resultMap));
            log.debug("imageId = " + imageId);
        } catch (Exception e) {
        	log.error("【图片保存失败】异常信息：" + e);
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