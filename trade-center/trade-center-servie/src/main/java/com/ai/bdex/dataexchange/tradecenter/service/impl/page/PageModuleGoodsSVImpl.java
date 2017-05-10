package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageModuleGoodsMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoods;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoodsExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoodsExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleGoodsSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("iPageModuleGoodsSV")
public class PageModuleGoodsSVImpl implements IPageModuleGoodsSV {
	@Autowired
    private PageModuleGoodsMapper pageModuleGoodsMapper;
	@Override
	public PageModuleGoods queryPageModuleGoodsById(Integer pmgId) throws Exception {
		return pageModuleGoodsMapper.selectByPrimaryKey(pmgId);
	}

	@Override
	public PageResponseDTO<PageModuleGoodsRespDTO>   queryPageModuleGoodsList(PageModuleGoodsReqDTO moduleGoodsReqDTO) throws Exception {
		  //分页信息赋值
        int page = moduleGoodsReqDTO.getPageNo();
        int rows = moduleGoodsReqDTO.getPageSize();
        
		PageModuleGoodsExample example = new PageModuleGoodsExample();
		PageModuleGoodsExample.Criteria criteria = example.createCriteria();
		if(moduleGoodsReqDTO.getPmgId() != null && moduleGoodsReqDTO.getPmgId() != 0){
			criteria.andPmgIdEqualTo(moduleGoodsReqDTO.getPmgId());
		}
		if(moduleGoodsReqDTO.getModuleId() != null && moduleGoodsReqDTO.getModuleId() != 0){
			criteria.andModuleIdEqualTo(moduleGoodsReqDTO.getModuleId());
		}
		if(!StringUtils.isBlank(moduleGoodsReqDTO.getStatus())){
			criteria.andStatusEqualTo(moduleGoodsReqDTO.getStatus());
		}
		example.setOrderByClause(" ORDER_NO desc ");
		PageHelper.startPage(page, rows);
		List<PageModuleGoods> listGds =  pageModuleGoodsMapper.selectByExample(example);
		//使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(listGds);
        PageResponseDTO<PageModuleGoodsRespDTO> respDTO = PageResponseFactory.genPageResponse(pageInfo,PageModuleGoodsRespDTO.class);
        return respDTO;
        
	}
	@Override
	public PageResponseDTO<PageModuleGoodsRespDTO> queryPageModuleGoodsPage(PageModuleGoodsReqDTO moduleGoodsReqDTO) throws Exception {
		  //分页信息赋值
        int page = moduleGoodsReqDTO.getPageNo();
        int rows = moduleGoodsReqDTO.getPageSize();
        
		PageModuleGoodsExample example = new PageModuleGoodsExample();
		PageModuleGoodsExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria,moduleGoodsReqDTO);
		example.setOrderByClause(" ORDER_NO desc ");
		PageHelper.startPage(page, rows);
		List<PageModuleGoods> listGds =  pageModuleGoodsMapper.selectByExample(example);
		//使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(listGds);
        PageResponseDTO<PageModuleGoodsRespDTO> respDTO = PageResponseFactory.genPageResponse(pageInfo,PageModuleGoodsRespDTO.class);
        return respDTO;
        
	}
	public List<PageModuleGoodsRespDTO> queryPageModuleGoodsInfoList(PageModuleGoodsReqDTO moduleGoodsReqDTO) throws Exception {
		PageModuleGoodsExample example = new PageModuleGoodsExample();
		PageModuleGoodsExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria,moduleGoodsReqDTO);
		example.setOrderByClause(" ORDER_NO desc ");
		List<PageModuleGoods> listGds =  pageModuleGoodsMapper.selectByExample(example);
		List<PageModuleGoodsRespDTO> respDTOList = new ArrayList<PageModuleGoodsRespDTO>();
		if(!CollectionUtil.isEmpty(listGds)){
			for(PageModuleGoods pageModuleGoods:listGds){
				PageModuleGoodsRespDTO pageModuleGoodsRespDTO = new PageModuleGoodsRespDTO();
				ObjectCopyUtil.copyObjValue(pageModuleGoods, pageModuleGoodsRespDTO, null, false);
				respDTOList.add(pageModuleGoodsRespDTO);
			}
		}
      return respDTOList;
      
	}
	public int insertPageModuleGoods(PageModuleGoodsReqDTO moduleGoodsReqDTO)throws Exception {
		int pmgId = SeqUtil.getInt("SEQ_PAGE_MODULE_GOODS");
		//查询当前最大的排序
		PageModuleGoodsExample example = new PageModuleGoodsExample();
		PageModuleGoodsExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constants.Page.STATUS_VALID);
		example.setOrderByClause(" ORDER_NO desc ");
		List<PageModuleGoods> goodsList =  pageModuleGoodsMapper.selectByExample(example);
		int orderNo=0;
		if(!CollectionUtil.isEmpty(goodsList)){
			orderNo=goodsList.get(0).getOrderNo();
		}
		moduleGoodsReqDTO.setOrderNo(orderNo+1);
		PageModuleGoods pageModuleGoods = new PageModuleGoods();
		ObjectCopyUtil.copyObjValue(moduleGoodsReqDTO, pageModuleGoods, null, false);
		pageModuleGoods.setUpdateTime(DateUtil.getNowAsDate());
		pageModuleGoodsMapper.insert(pageModuleGoods);
		return pmgId;
	}
	public int updatePageModuleGoods(PageModuleGoodsReqDTO moduleGoodsReqDTO)throws Exception {
		PageModuleGoodsExample example = new PageModuleGoodsExample();
		PageModuleGoodsExample.Criteria criteria = example.createCriteria();
		if(moduleGoodsReqDTO.getPmgId()!=null){
			criteria.andPmgIdEqualTo(moduleGoodsReqDTO.getPmgId());
		}
		PageModuleGoods pageModuleGoods = new PageModuleGoods();
		ObjectCopyUtil.copyObjValue(moduleGoodsReqDTO, pageModuleGoods, null, false);
		pageModuleGoods.setUpdateTime(DateUtil.getNowAsDate());
		int code=pageModuleGoodsMapper.updateByExampleSelective(pageModuleGoods, example);
		return code;
	}
	private void initCriteria(PageModuleGoodsExample.Criteria criteria, PageModuleGoodsReqDTO moduleGoodsReqDTO) {
		if(moduleGoodsReqDTO.getPmgId()!=null){
			criteria.andPmgIdEqualTo(moduleGoodsReqDTO.getPmgId());
		}
		if(moduleGoodsReqDTO.getModuleId()!=null){
			criteria.andModuleIdEqualTo(moduleGoodsReqDTO.getModuleId());
		}
		if(moduleGoodsReqDTO.getGdsId()!=null){
			criteria.andGdsIdEqualTo(moduleGoodsReqDTO.getGdsId());
		}
		if(StringUtil.isNotBlank(moduleGoodsReqDTO.getRecommendName())){
			criteria.andRecommendNameLike("%"+moduleGoodsReqDTO.getRecommendName()+"%");
		}
		if(!StringUtils.isBlank(moduleGoodsReqDTO.getStatus())){
			criteria.andStatusEqualTo(moduleGoodsReqDTO.getStatus());
		}
	}
}
