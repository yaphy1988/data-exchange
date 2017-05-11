package com.ai.bdex.dataexchange.busi.page.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
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

import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfoVO;
import com.ai.bdex.dataexchange.busi.page.entity.PageModuleAdVO;
import com.ai.bdex.dataexchange.busi.page.entity.PageModuleGoodsVO;
import com.ai.bdex.dataexchange.busi.page.entity.PageModuleVO;
//import com.ai.bdex.dataexchange.busi.search.entiry.SearchVO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageAdPalceReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageAdPalceRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsCatRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageDisplayRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageModuleRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.util.MongoFileUtil;
import com.ai.paas.utils.CollectionUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 商品模块配置
 * @author win8
 *
 */
@Controller
@RequestMapping(value = "/pageModuleGoods")
public class PageModuleGoodsController {

	private final static String STATUS_VALID = "1";// 有效
	private final static String STATUS_INVALID = "0";// 失效
	private final static Integer PAGE_SIZE=10;//
	private final static String  MODULE_TYPE_AD="02";
	private static final Logger log = LoggerFactory.getLogger(PageModuleGoodsController.class);

	@DubboConsumer(timeout = 30000)
	private IPageModuleRSV iPageModuleRSV;
	@DubboConsumer(timeout = 30000)
	private IGdsInfoRSV iGdsInfoRSV;
	@DubboConsumer
	private IGdsCatRSV iGdsCatRSV;
	/**
	 * 商品模块管理入口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pageInit")
	public ModelAndView pageInit(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("goods_module");
		String moduleId=request.getParameter("moduleId");
		modelAndView.addObject("moduleId", moduleId);
		return modelAndView;
	}
	/**
	 * 商品模块查询未选择商品
	 * @param model
	 * @param request
	 * @param pageModuleGoodsVO
	 * @return
	 */
	@RequestMapping(value = "/qryModuleGoodsUnSelList")
	public String qryModuleGoodsUnSelList(Model model, HttpServletRequest request,PageModuleGoodsVO pageModuleGoodsVO) {
        PageResponseDTO<GdsInfoVO> pageInfo = new PageResponseDTO<GdsInfoVO>();
        try {
    		GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
    		if(pageModuleGoodsVO.getGdsId()!=null){
    			gdsInfoReqDTO.setGdsId(pageModuleGoodsVO.getGdsId());
    		}
    		if(StringUtil.isNotBlank(pageModuleGoodsVO.getGdsName())){
    			gdsInfoReqDTO.setGdsName(pageModuleGoodsVO.getGdsName());
    		}
    		if(StringUtil.isNotBlank(pageModuleGoodsVO.getCatId())){
    			gdsInfoReqDTO.setCatId(Integer.parseInt(pageModuleGoodsVO.getCatId()));
    		}
    		gdsInfoReqDTO.setPageNo(pageModuleGoodsVO.getPageNo());
            gdsInfoReqDTO.setPageSize(10);
            //查询已选择商品
        	PageModuleGoodsReqDTO moduleGoodsReqDTO = new PageModuleGoodsReqDTO();
    		if(pageModuleGoodsVO.getModuleId()!=null){
    			moduleGoodsReqDTO.setModuleId(pageModuleGoodsVO.getModuleId());
    		}
    		if(pageModuleGoodsVO.getGdsId()!=null){
    			moduleGoodsReqDTO.setGdsId(pageModuleGoodsVO.getGdsId());
    		}
    		moduleGoodsReqDTO.setStatus(Constants.Page.STATUS_VALID);
    		List<PageModuleGoodsRespDTO> selGoodsList=iPageModuleRSV.queryPageModuleGoodsInfoList(moduleGoodsReqDTO);
    		List<Integer> gdsIdList = new ArrayList<Integer>();
    		if(!CollectionUtils.isEmpty(selGoodsList)){
    			for(PageModuleGoodsRespDTO moduleGoods:selGoodsList){
    				gdsIdList.add(moduleGoods.getGdsId());
    			}
    		}
    		gdsInfoReqDTO.setStatus("1");//已上架
    		gdsInfoReqDTO.setGdsIdsNotIn(gdsIdList);
            PageResponseDTO<GdsInfoRespDTO> gdsInfoRespPage = iGdsInfoRSV.queryGdsInfoPage(gdsInfoReqDTO);
            ObjectCopyUtil.copyObjValue(gdsInfoRespPage,pageInfo,null,false);
            List<GdsInfoVO> gdsInfoVOList = new ArrayList<GdsInfoVO>();
            if(!CollectionUtil.isEmpty(gdsInfoRespPage.getResult())){
                for (GdsInfoRespDTO gdsInfoRespDTO : gdsInfoRespPage.getResult()){
                    GdsInfoVO gdsInfoVO = new GdsInfoVO();
                    ObjectCopyUtil.copyObjValue(gdsInfoRespDTO,gdsInfoVO,null,false);
                    gdsInfoVO.setCatName(traslateCatName(gdsInfoVO.getCatId()));
                    gdsInfoVOList.add(gdsInfoVO);
                }
            }
            pageInfo.setResult(gdsInfoVOList);
            model.addAttribute("pageInfo", pageInfo);
        }catch (Exception e){
            log.error("查询商品列表异常");
        }

		return "goods_module :: #unSelGoodsList" ;
	}
	/**
	 * 商品模块查询已选择商品
	 * @param model
	 * @param request
	 * @param pageModuleGoodsVO
	 * @return
	 */
	@RequestMapping(value = "/qryModuleGoodsSelList")
	public String qryModuleGoodsSelList(Model model, HttpServletRequest request,PageModuleGoodsVO pageModuleGoodsVO) {
		try{
			PageModuleGoodsReqDTO moduleGoodsReqDTO = new PageModuleGoodsReqDTO();
			if(pageModuleGoodsVO.getModuleId()!=null){
				moduleGoodsReqDTO.setModuleId(pageModuleGoodsVO.getModuleId());
			}
			if(pageModuleGoodsVO.getGdsId()!=null){
				moduleGoodsReqDTO.setGdsId(pageModuleGoodsVO.getGdsId());
			}
    		moduleGoodsReqDTO.setStatus(Constants.Page.STATUS_VALID);
			List<PageModuleGoodsRespDTO> goodsList=iPageModuleRSV.queryPageModuleGoodsInfoList(moduleGoodsReqDTO);
			List<PageModuleGoodsRespDTO> goodsSelList = new ArrayList<PageModuleGoodsRespDTO>();
			if(!CollectionUtils.isEmpty(goodsList)){
				for(PageModuleGoodsRespDTO respDTO : goodsList){
					int gdsid =  respDTO.getGdsId();
					//通过商品id去搜索商品信息，获取商品
					GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
					GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
					gdsInfoReqDTO.setGdsId(gdsid);
					if(StringUtil.isNotBlank(pageModuleGoodsVO.getGdsName())){
						gdsInfoReqDTO.setGdsName(pageModuleGoodsVO.getGdsName());
					}
					if(StringUtil.isNotBlank(pageModuleGoodsVO.getCatId())){
						gdsInfoReqDTO.setCatId(Integer.parseInt(pageModuleGoodsVO.getCatId()));
					}
					if(StringUtil.isNotBlank(pageModuleGoodsVO.getGdsStatus())){
						gdsInfoReqDTO.setStatus(pageModuleGoodsVO.getGdsStatus());
					}
					gdsInfoRespDTO =  iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO); 
					respDTO.setGdsInfoRespDTO(gdsInfoRespDTO);
					GdsCatRespDTO gdsCatRespDTO = new GdsCatRespDTO();
					if(gdsInfoRespDTO!=null&&gdsInfoRespDTO.getGdsId()!=null){
						//获取商品分类名称
						respDTO.setCatName(traslateCatName(gdsInfoRespDTO.getCatId()));
						goodsSelList.add(respDTO);
					}
				}
			}
			List<PageModuleGoodsRespDTO> resultList = new ArrayList<PageModuleGoodsRespDTO>();
			int startPage = (pageModuleGoodsVO.getPageNo() - 1) * pageModuleGoodsVO.getPageSize() + 1;; // 开始页
	        int endPage =pageModuleGoodsVO.getPageNo() * pageModuleGoodsVO.getPageSize(); // 结束页
	        int count = goodsSelList.size(); // 总记录
	        if (!CollectionUtils.isEmpty(goodsSelList) && goodsSelList.size() > 0) {
	            for (int i = (startPage - 1); i < endPage; i++) {
	            	if (i >= goodsSelList.size()) {
	                    break;
	                }
	            	PageModuleGoodsRespDTO vo = goodsSelList.get(i);
	            	resultList.add(vo);
	                if (i == count) {
	                    break;
	                }
	            }
	        }
	        PageResponseDTO<PageModuleGoodsRespDTO> pageInfo = new PageResponseDTO<PageModuleGoodsRespDTO>();
	        pageInfo.setPageSize(pageModuleGoodsVO.getPageSize());
	        pageInfo.setResult(resultList);
	        pageInfo.setPageNo(pageModuleGoodsVO.getPageNo());
	        pageInfo.setCount(goodsSelList.size());
	        model.addAttribute("pageInfoSel", pageInfo);
		}catch(Exception e){
			log.error("查询楼层已选择商品出错：" + e.getMessage());
		}
		return "goods_module :: #selGoodsList" ;
	}

	/**
	 * 删除商品
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updatePageModuleGoods")
	@ResponseBody
	public Map<String,Object> updatePageModuleGoods(HttpServletRequest request,PageModuleGoodsVO moduleGoodsVO){
		Map<String,Object>  rMap = new HashMap<>();
		int pmgId = moduleGoodsVO.getPmgId();
		String status = moduleGoodsVO.getStatus();
		try {
			PageModuleGoodsReqDTO moduleGoodsReqDTO = new PageModuleGoodsReqDTO();
			if(!StringUtils.isBlank(status)){
				moduleGoodsReqDTO.setStatus(status);
			}
			if(moduleGoodsVO.getPmgId()!=null){
				moduleGoodsReqDTO.setPmgId(moduleGoodsVO.getPmgId());
			}
			iPageModuleRSV.updatePageModuleGoods(moduleGoodsReqDTO);
			rMap.put("success", true);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("删除楼层商品成功出错：" + e.getMessage());
		}
		return rMap;
	}
	/**
	 * 楼层模块选择商品
	 * @param request
	 * @param moduleGoodsVO
	 * @return
	 */
	@RequestMapping(value = "/savePageModuleGoods")
	@ResponseBody
	public Map<String, Object> savePageModuleGoods(HttpServletRequest request,PageModuleGoodsVO moduleGoodsVO){
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			PageModuleGoodsReqDTO moduleGoodsReqDTO = new PageModuleGoodsReqDTO();
			if(moduleGoodsVO.getPmgId()!=null){
				moduleGoodsReqDTO.setPmgId(moduleGoodsVO.getPmgId());
			}
			if(moduleGoodsVO.getModuleId()!=null){
				moduleGoodsReqDTO.setModuleId(moduleGoodsVO.getModuleId());
			}
			if(moduleGoodsVO.getGdsId()!=null){
				moduleGoodsReqDTO.setGdsId(moduleGoodsVO.getGdsId());
			}
			if(!StringUtils.isBlank(moduleGoodsVO.getStatus())){
				moduleGoodsReqDTO.setStatus(moduleGoodsVO.getStatus());
			}else{
				moduleGoodsReqDTO.setStatus(Constants.Page.STATUS_VALID);
			}
			if(StringUtils.isBlank(moduleGoodsVO.getRecommendName())){
				moduleGoodsReqDTO.setRecommendName(moduleGoodsVO.getRecommendName());
			}
			iPageModuleRSV.insertPageModuleGoods(moduleGoodsReqDTO);
			rMap.put("success",true);
		} catch (Exception e) {
			rMap.put("success",false);
			log.error("选择商品信息出错：" + e.getMessage());
		}
		return rMap;
	}
	private String traslateCatName(Integer catId){
        String catName = "";
        if (catId!=null && catId.intValue()>0){
            GdsCatRespDTO gdsCatRespDTO = new GdsCatRespDTO();
            try {
                gdsCatRespDTO = iGdsCatRSV.queryGdsCatByCatId(catId);
            } catch (Exception e) {
                log.error("根据catId查询分类信息异常：",e);
            }
            if (gdsCatRespDTO!=null){
                catName = gdsCatRespDTO.getCatName();
            }
        }
        return  catName;
    }
}