package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageModuleMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModule;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleSV;
import com.alibaba.dubbo.common.utils.StringUtils;
@Service("iPageModuleSV")
public class PageModuleSVImpl implements IPageModuleSV {
    @Autowired
    private PageModuleMapper pageModuleMapper;
	@Override
	public PageModule queryPageModuleById(Integer moduleId) throws Exception {
		return pageModuleMapper.selectByPrimaryKey(moduleId);
	}

	@Override
	public List<PageModule> queryPageModuleList(PageModule pageModule) throws Exception {
		PageModuleExample example = new PageModuleExample();
		Criteria criteria = example.createCriteria();
		if(pageModule.getModuleId() != null && pageModule.getModuleId() != 0){
			criteria.andModuleIdEqualTo(pageModule.getModuleId());
		}
		if(StringUtils.isBlank(pageModule.getModuleType())){
			criteria.andModuleTypeEqualTo(pageModule.getModuleType());
		}
		if(StringUtils.isBlank(pageModule.getStatus())){
			criteria.andStatusEqualTo(pageModule.getStatus());
		}
		if(pageModule.getModulePid() != null && pageModule.getModulePid() != 0){
			criteria.andModulePidEqualTo(pageModule.getModulePid());
		}
		example.setOrderByClause("UPDATE_TIME ASC");
		return pageModuleMapper.selectByExample(example);
	}

}
