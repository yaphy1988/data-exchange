package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageModuleGoodsMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoods;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoodsExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoodsExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleGoodsSV;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
@Service("iPageModuleGoodsSV")
public class PageModuleGoodsSVImpl implements IPageModuleGoodsSV {
	@Autowired
    private PageModuleGoodsMapper pageModuleGoodsMapper;
	@Override
	public PageModuleGoods queryPageModuleGoodsById(Integer pmgId) throws Exception {
		return pageModuleGoodsMapper.selectByPrimaryKey(pmgId);
	}

	@Override
	public PageResponseDTO<PageModuleGoodsRespDTO>   queryPageModuleGoodsList(PageModuleGoods pageModuleGoods) throws Exception {
		  //分页信息赋值
        int page = 1;
        int rows = 10;
        PageHelper.startPage(page, rows, "ORDER_NO desc");
		PageModuleGoodsExample example = new PageModuleGoodsExample();
		Criteria criteria = example.createCriteria();
		if(pageModuleGoods.getPmgId() != null && pageModuleGoods.getPmgId() != 0){
			criteria.andPmgIdEqualTo(pageModuleGoods.getPmgId());
		}
		if(pageModuleGoods.getModuleId() != null && pageModuleGoods.getModuleId() != 0){
			criteria.andModuleIdEqualTo(pageModuleGoods.getModuleId());
		}
		if(!StringUtils.isBlank(pageModuleGoods.getStatus())){
			criteria.andStatusEqualTo(pageModuleGoods.getStatus());
		}
		//example.setOrderByClause("ORDER_NO DESC");
		
		List<PageModuleGoods> listGds =  pageModuleGoodsMapper.selectByExample(example);
		   //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(listGds);
        PageResponseDTO<PageModuleGoodsRespDTO> respDTO = PageResponseFactory.genPageResponse(pageInfo,PageModuleGoodsRespDTO.class);
        return respDTO;
        
	}

}
