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

import com.ai.bdex.dataexchange.busi.page.entity.PageModuleAdVO;
import com.ai.bdex.dataexchange.busi.page.entity.PageModuleGoodsVO;
import com.ai.bdex.dataexchange.busi.page.entity.PageModuleVO;
//import com.ai.bdex.dataexchange.busi.search.entiry.SearchVO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageAdPalceReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageAdPalceRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageDisplayRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageModuleRSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.util.MongoFileUtil;
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
//		SearchVO searchVO = new SearchVO();
//		try {
//		            if(StringUtil.isNotBlank(searchVO.getKeyWord())){
//		                searchVO.setKeyWord(URLDecoder.decode(searchVO.getKeyWord()));
//		            }
//		            SearchParam searchParam = new SearchParam();
//		            searchParam.setCollectionName(SolrCoreEnum.GDS.getCode());
//		            searchParam.setSolrClient(solrClient);
//		            searchParam.setKeyWord(searchVO.getKeyWord());
//		            //查询字段 and
//		            List<SearchField> searchFieldList = new ArrayList<SearchField>();
//		            if(StringUtil.isNotBlank(searchVO.getKeyWord())){
//		                SearchField searchField = new SearchField();
//		                searchField.setName("name");
//		                searchField.setValue(searchVO.getKeyWord());
//		                searchFieldList.add(searchField);
//		            }
//		            if(searchVO.getCatFirst() >=1){
//		                SearchField searchField = new SearchField();
//		                searchField.setName("catFirst");
//		                searchField.setValue(searchVO.getCatFirst());
//		                searchFieldList.add(searchField);
//		            }
//		            //查询字段 and
//		            if(StringUtil.isNotBlank(searchVO.getSelectedCondition())){
//		                String[] strs = searchVO.getSelectedCondition().split(",");
//		                SearchField searchField = null;
//		                for(String str : strs){
//		                    searchField = new SearchField();
//		                    searchField.setName("catId");
//		                    searchField.setValue(str);
//		                    searchFieldList.add(searchField);
//		                }
//		            }
//		            searchParam.setSearchField(searchFieldList);
//		            //排序字段
//		            List<SortField> sortFieldList = new ArrayList<SortField>();
//		            if(StringUtil.isNotBlank(searchVO.getSortField()) && StringUtil.isNotBlank(searchVO.getSortValue())){
//		                ESort sortValue = ESort.DESC;
//		                if(ESort.ASC.getSort().equalsIgnoreCase(searchVO.getSortValue())){
//		                    sortValue = ESort.ASC;
//		                }else{
//		                    sortValue = ESort.DESC;
//		                }
//		                SortField sortField = new SortField(searchVO.getSortField(),sortValue);
//		                sortFieldList.add(sortField);
//		            }
//		            searchParam.setSortField(sortFieldList);
//		            searchParam.setPageNo(searchVO.getPageNo());
//		            searchParam.setPageSize(20);
//		            searchParam.setIfHightlight(true);
//		            PageResponseDTO<ResultRespVO> pageInfo = SolrSearchUtil.Search(searchParam);
//		            model.addAttribute("pageInfo", pageInfo);
//		            model.addAttribute("searchVO", searchVO);
//		        } catch (Exception e) {
//		            logger.error("查询商品列表失败！原因是："+e.getMessage());
//		        }
//		    }
//		} catch (Exception e) {
//			rMap.put("success",false);
//			log.error("查询楼层信息出错：" + e.getMessage());
//		}
		return "goods_module :: unSelGoodsList" ;
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

		return "goods_module :: selGoodsList" ;
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

}