package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageModuleGoodsMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoods;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoodsExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoodsExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleGoodsSV;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
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
		Criteria criteria = example.createCriteria();
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

}
