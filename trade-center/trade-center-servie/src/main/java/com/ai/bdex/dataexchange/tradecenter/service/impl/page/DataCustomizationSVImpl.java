package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.DataCustomizationMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.DataCustomization;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IDataCustomizationSV;
 
@Service("iDataCustomizationSV")
public class DataCustomizationSVImpl implements IDataCustomizationSV {
	  @Resource
	  private DataCustomizationMapper dataCustomizationMapper;
	   /***
	    * 接口没有时，需要定制的信息
	    */
	  @Override
	    public DataCustomization queryDATAById(Integer dcza_id) throws Exception {
 	        if (dcza_id==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
 	       DataCustomization dataCustomizationSVImpl =  dataCustomizationMapper.selectByPrimaryKey(dcza_id);
	        return dataCustomizationSVImpl;
	    }
}
