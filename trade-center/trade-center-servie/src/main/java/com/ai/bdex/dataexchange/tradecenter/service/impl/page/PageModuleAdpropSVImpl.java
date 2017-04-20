package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageModuleAdpropMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdprop;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdpropExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdpropExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleAdpropSV;
import com.alibaba.dubbo.common.utils.StringUtils;
@Service("iPageModuleAdpropSV")
public class PageModuleAdpropSVImpl implements IPageModuleAdpropSV {
	@Autowired
	private PageModuleAdpropMapper pageModuleAdpropMapper;
	@Override
	public PageModuleAdprop queryPageModuleAdpropById(Integer id) throws Exception {
		return pageModuleAdpropMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PageModuleAdprop> queryPageModuleAdpropList(PageModuleAdprop pageModuleAdprop) throws Exception {
		PageModuleAdpropExample example = new PageModuleAdpropExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isBlank(pageModuleAdprop.getModuleId())){
			criteria.andModuleIdEqualTo(pageModuleAdprop.getModuleId());
		}
		criteria.andStatusEqualTo("1");
		return pageModuleAdpropMapper.selectByExample(example);
	}
	

}
