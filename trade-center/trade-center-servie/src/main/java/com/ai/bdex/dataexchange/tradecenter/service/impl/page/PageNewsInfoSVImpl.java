package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageNewsInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageNewsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageNewsInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageNewsInfoSV;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("iPageNewsInfoSV")
public class PageNewsInfoSVImpl implements IPageNewsInfoSV {
	@Resource
	private PageNewsInfoMapper pageNewsInfoMapper;

	/***
	 * 接口没有时，需要定制的信息
	 */
	@Override
	public PageNewsInfo queryPageNewsInfoById(Integer PageInfoid) throws Exception {

		if (PageInfoid == null) {
			throw new Exception("根据ID查询 数据定制 信息入参为空");
		}
		return pageNewsInfoMapper.selectByPrimaryKey(PageInfoid);
	}

	@Override
	public PageResponseDTO<PageNewsInfoRespDTO> queryPageNewsInfoList(PageNewsInfoReqDTO exam) throws Exception {
		// 分页信息赋值
		int page = exam.getPageNo();
		int rows = exam.getPageSize();
		
		PageNewsInfoExample example = new PageNewsInfoExample();
		example.setOrderByClause("INFO_ORDER desc");
		PageNewsInfoExample.Criteria criteria = example.createCriteria();
		if (exam.getModuleId() != null) {
			criteria.andModuleIdEqualTo(exam.getModuleId());
		}
		if (!StringUtils.isBlank(exam.getInfoType())) {
			criteria.andInfoTypeEqualTo(exam.getInfoType());
		}
		if (!StringUtils.isBlank(exam.getStatus())) {
			criteria.andStatusEqualTo(exam.getStatus());
		} 
		example.setOrderByClause( "INFO_ORDER asc,update_time desc");
		PageHelper.startPage(page, rows);
		List<PageNewsInfo> pageList = pageNewsInfoMapper.selectByExample(example);
		// 使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(pageList);
		PageResponseDTO<PageNewsInfoRespDTO> pageResponseDTO = PageResponseFactory.genPageResponse(pageInfo,
				PageNewsInfoRespDTO.class);
		return pageResponseDTO;
	}
}
