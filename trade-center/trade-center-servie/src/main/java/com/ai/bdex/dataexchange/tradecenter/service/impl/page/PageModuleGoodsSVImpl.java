package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageModuleGoodsMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoods;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoodsExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoodsExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleGoodsSV;
import com.alibaba.dubbo.common.utils.StringUtils;

public class PageModuleGoodsSVImpl implements IPageModuleGoodsSV {
	@Autowired
    private PageModuleGoodsMapper pageModuleGoodsMapper;
	@Override
	public PageModuleGoods queryPageModuleGoodsById(Integer pmgId) throws Exception {
		return pageModuleGoodsMapper.selectByPrimaryKey(pmgId);
	}

	@Override
	public List<PageModuleGoods> queryPageModuleGoodsList(PageModuleGoods pageModuleGoods) throws Exception {
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
		example.setOrderByClause("ORDER_NO DESC");
		return pageModuleGoodsMapper.selectByExample(example);
	}

}
