package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageModuleAdMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAd;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleAdSV;
import com.alibaba.dubbo.common.utils.StringUtils;

public class PageModuleAdSVImpl implements IPageModuleAdSV {
	@Autowired
	private PageModuleAdMapper moduleAdMapper;
	@Override
	public PageModuleAd queryPageModuleAdById(Integer adId) throws Exception {
		return moduleAdMapper.selectByPrimaryKey(adId);
	}

	@Override
	public List<PageModuleAd> queryPageModuleAdList(PageModuleAd pageModuleAd) throws Exception {
		PageModuleAdExample example = new PageModuleAdExample();
		Criteria criteria = example.createCriteria();
		if(pageModuleAd.getAdId() !=null && pageModuleAd.getAdId() != 0){
			criteria.andAdIdEqualTo(pageModuleAd.getAdId());
		}
		if(pageModuleAd.getModuleId() != null && pageModuleAd.getModuleId() !=0){
			criteria.andModuleIdEqualTo(pageModuleAd.getModuleId());
		}
		if(!StringUtils.isBlank(pageModuleAd.getStatus())){
			criteria.andStatusEqualTo(pageModuleAd.getStatus());
		}
		example.setOrderByClause("AD_ORDER DESC");
		return moduleAdMapper.selectByExample(example);
	}

}
