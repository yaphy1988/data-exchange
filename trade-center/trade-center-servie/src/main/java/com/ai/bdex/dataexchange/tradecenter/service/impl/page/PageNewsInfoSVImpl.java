package com.ai.bdex.dataexchange.tradecenter.service.impl.page;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageNewsInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageNewsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageNewsInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageNewsInfoSV;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
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
	   public PageNewsInfo queryPageNewsInfoById(Integer PageInfoid) throws Exception{

	        if (PageInfoid==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
	        PageNewsInfo pageInfo =  pageNewsInfoMapper.selectByPrimaryKey(PageInfoid);
	        return pageInfo;
	    }
	  @Override
	   public  PageResponseDTO<PageNewsInfoRespDTO>  queryPageNewsInfoList(PageNewsInfo exam) throws Exception{
		  //分页信息赋值
	      int page = 1;
	      int rows = 10;
	      PageHelper.startPage(page, rows, "ORDER_NO desc");
		  PageNewsInfoExample example = new PageNewsInfoExample();
		  PageNewsInfoExample.Criteria criteria = example.createCriteria(); 
		   if(exam.getInfoType()!= null){
				criteria.andInfoTypeEqualTo(exam.getInfoType());
			} 
			if(exam.getStatus() != null){
				criteria.andStatusEqualTo(exam.getStatus());
			} 
			else{
				//空值，就只查有效的数据
				criteria.andStatusEqualTo("1");
			} 
			//    List<PageInfo> selectByExample(PageInfoExample example);
			  List<PageNewsInfo> pageList = pageNewsInfoMapper.selectByExample(example);
		   //使用PageInfo对结果进行包装
	        PageInfo pageInfo = new PageInfo(pageList);
	        PageResponseDTO<PageNewsInfoRespDTO> respDTO = PageResponseFactory.genPageResponse(pageInfo,PageNewsInfoRespDTO.class);
	        return respDTO;
		  }  
}
