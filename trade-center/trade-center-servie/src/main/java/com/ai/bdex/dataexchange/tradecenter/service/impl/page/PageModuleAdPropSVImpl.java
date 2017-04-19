package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageModuleAdpropMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdprop;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdpropExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdpropExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleAdpropSV;
import com.alibaba.dubbo.common.utils.StringUtils;

public class PageModuleAdPropSVImpl implements IPageModuleAdpropSV {
	@Autowired
	private PageModuleAdpropMapper moduleAdpropMapper;
	@Override
	public PageModuleAdprop queryPageModuleAdpropById(Integer id) throws Exception {
		return moduleAdpropMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PageModuleAdprop> queryPageModuleAdpropList(PageModuleAdprop pageModuleAdprop) throws Exception {
		PageModuleAdpropExample example = new PageModuleAdpropExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isBlank(pageModuleAdprop.getModuleId())){
			criteria.andModuleIdEqualTo(pageModuleAdprop.getModuleId());
		}
		if(!StringUtils.isBlank(pageModuleAdprop.getStatus())){
			criteria.andStatusEqualTo(pageModuleAdprop.getStatus());
		}
		if(!StringUtils.isBlank(pageModuleAdprop.getModuleId())){
			criteria.andModuleIdEqualTo(pageModuleAdprop.getModuleId());
		}
		return moduleAdpropMapper.selectByExample(example);
	}

}
