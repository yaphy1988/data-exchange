package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageAdPalceMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageAdPalce;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageAdPalceExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageAdPalceExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageAdPalceReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageAdPalceRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageAdPlaceSV;
import com.alibaba.dubbo.common.utils.StringUtils;
@Service("iPageAdPlaceSV")
public class PageAdPalceSVImpl implements IPageAdPlaceSV {
	@Resource
    private PageAdPalceMapper pageAdPalceMapper;
	
	@Override
	public PageAdPalceRespDTO queryPageAdPlace(PageAdPalceReqDTO pageAdPalce) throws Exception {
		Integer placeAd = pageAdPalce.getPlaceAd();
		PageAdPalce adPalce = pageAdPalceMapper.selectByPrimaryKey(placeAd);
		PageAdPalceRespDTO pageAdPalceRespDTO = new PageAdPalceRespDTO();
		BeanUtils.copyProperties(adPalce, pageAdPalceRespDTO);
		return pageAdPalceRespDTO;
	}

	@Override
	public List<PageAdPalceRespDTO> queryPageAdPalceList(PageAdPalceReqDTO pageAdPalce) throws Exception {
		PageAdPalceExample example = new PageAdPalceExample();
		Criteria criteria = example.createCriteria();
		if(pageAdPalce.getPlaceAd() != null){
			criteria.andPlaceAdEqualTo(pageAdPalce.getPlaceAd());
		}
		if(pageAdPalce.getModuleId() != null){
			criteria.andModuleIdEqualTo(pageAdPalce.getModuleId());
		}
		if(!StringUtils.isBlank(pageAdPalce.getStatus())){
			criteria.andStatusEqualTo(pageAdPalce.getStatus());
		}
		List<PageAdPalce> list = pageAdPalceMapper.selectByExample(example);
		List<PageAdPalceRespDTO> resultList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(list)){
			for(PageAdPalce adPalce : list){
				PageAdPalceRespDTO palceRespDTO = new PageAdPalceRespDTO();
				BeanUtils.copyProperties(adPalce, palceRespDTO);
				resultList.add(palceRespDTO);
			}
		}
		return resultList;
	}

}
