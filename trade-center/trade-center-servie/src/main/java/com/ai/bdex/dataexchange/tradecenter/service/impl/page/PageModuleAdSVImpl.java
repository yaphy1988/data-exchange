package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageModuleAdMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAd;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleAdSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
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
	public PageResponseDTO<PageModuleAdRespDTO> queryPageModuleAdPageInfo(PageModuleAdReqDTO moduleAdDTO)
			throws Exception {
		Integer pageNo = moduleAdDTO.getPageNo();
		Integer pageSize = moduleAdDTO.getPageSize();
				
		PageModuleAdExample example = new PageModuleAdExample();
		Criteria criteria = example.createCriteria();
		if(moduleAdDTO.getAdId() !=null && moduleAdDTO.getAdId() != 0){
			criteria.andAdIdEqualTo(moduleAdDTO.getAdId());
		}
		if(moduleAdDTO.getModuleId() != null && moduleAdDTO.getModuleId() !=0){
			criteria.andModuleIdEqualTo(moduleAdDTO.getModuleId());
		}
		if(!StringUtils.isBlank(moduleAdDTO.getStatus())){
			criteria.andStatusEqualTo(moduleAdDTO.getStatus());
		}
		if(CollectionUtils.isNotEmpty(moduleAdDTO.getStatusList())){
			criteria.andStatusIn(moduleAdDTO.getStatusList());
		}
		example.setOrderByClause("UPDATE_TIME DESC ,AD_ORDER DESC");
		PageHelper.startPage(pageNo, pageSize);
		List<PageModuleAd> pageModuleAdList = moduleAdMapper.selectByExample(example);
		// 使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(pageModuleAdList);
		PageResponseDTO<PageModuleAdRespDTO> pageResponseDTO = PageResponseFactory.genPageResponse(pageInfo,
				PageModuleAdRespDTO.class);
		return pageResponseDTO;
	}

	@Override
	public int updatePageModuleAdInfo(PageModuleAdReqDTO moduleAdDTO) throws Exception {
		PageModuleAd modularAd = new PageModuleAd();
		PageModuleAdExample example = new PageModuleAdExample();
		Criteria criteria = example.createCriteria();
		if(moduleAdDTO.getAdId()!=null){
			criteria.andAdIdEqualTo(moduleAdDTO.getAdId());
		}        
		moduleAdDTO.setUpdateTime(DateUtil.getNowAsDate());
		ObjectCopyUtil.copyObjValue(moduleAdDTO, modularAd, null, false);
		int code = moduleAdMapper.updateByExampleSelective(modularAd, example);
		return code;
	}
	@Override
	public int insertPageModuleAdInfo(PageModuleAdReqDTO moduleAdDTO) throws Exception {
		PageModuleAd modularAd = new PageModuleAd();
		modularAd.setModuleId(moduleAdDTO.getModuleId());
		List<PageModuleAd> adList=this.queryPageModuleAdList(modularAd);
		int adOrder=0;
		if(CollectionUtils.isNotEmpty(adList)){
			adOrder=adList.get(0).getAdOrder();
		}
		int adId=SeqUtil.getInt("SEQ_PAGE_MODULE_AD");
		moduleAdDTO.setAdId(adId);
		moduleAdDTO.setAdOrder(adOrder+1);
		moduleAdDTO.setCreateTime(DateUtil.getNowAsDate());
		ObjectCopyUtil.copyObjValue(moduleAdDTO, modularAd, null, false);
		int code = moduleAdMapper.insert(modularAd);
		return adId;
	}
}
