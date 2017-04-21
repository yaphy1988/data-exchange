package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageModuleAdPropMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdProp;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdPropExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdPropExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleAdPropSV;
import com.alibaba.dubbo.common.utils.StringUtils;
@Service("iPageModuleAdPropSV")
public class PageModuleAdPropSVImpl implements IPageModuleAdPropSV {
	@Autowired
	private PageModuleAdPropMapper pageModuleAdpropMapper;
	@Override
	public PageModuleAdProp queryPageModuleAdPropById(Integer id) throws Exception {
		return pageModuleAdpropMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PageModuleAdProp> queryPageModuleAdPropList(PageModuleAdProp pageModuleAdprop) throws Exception {
		PageModuleAdPropExample example = new PageModuleAdPropExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isBlank(pageModuleAdprop.getModuleId())){
			criteria.andModuleIdEqualTo(pageModuleAdprop.getModuleId());
		}
		criteria.andStatusEqualTo("1");
		return pageModuleAdpropMapper.selectByExample(example);
	}
	

}
