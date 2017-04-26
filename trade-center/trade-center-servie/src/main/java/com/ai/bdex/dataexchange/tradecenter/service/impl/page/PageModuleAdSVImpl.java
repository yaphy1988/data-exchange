package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageModuleAdMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAd;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleAdSV;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("iPageModuleAdSV")
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

	@Override
	public PageResponseDTO<PageModuleAdDTO> queryPageModulePageInfo(PageModuleAdRespDTO moduleAdRespDTO)
			throws Exception {
		Integer pageNo = moduleAdRespDTO.getPageNo();
		Integer pageSize = moduleAdRespDTO.getPageSize();
		PageHelper.startPage(pageNo, pageSize, "AD_ORDER desc");		
		PageModuleAdExample example = new PageModuleAdExample();
		Criteria criteria = example.createCriteria();
		if(moduleAdRespDTO.getAdId() !=null && moduleAdRespDTO.getAdId() != 0){
			criteria.andAdIdEqualTo(moduleAdRespDTO.getAdId());
		}
		if(moduleAdRespDTO.getModuleId() != null && moduleAdRespDTO.getModuleId() !=0){
			criteria.andModuleIdEqualTo(moduleAdRespDTO.getModuleId());
		}
		if(!StringUtils.isBlank(moduleAdRespDTO.getStatus())){
			criteria.andStatusEqualTo(moduleAdRespDTO.getStatus());
		}
		List<PageModuleAd> pageModuleAdList = moduleAdMapper.selectByExample(example);
		// 使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(pageModuleAdList);
		PageResponseDTO<PageModuleAdDTO> pageResponseDTO = PageResponseFactory.genPageResponse(pageInfo,
				PageModuleAdDTO.class);
		return pageResponseDTO;
	}

}
