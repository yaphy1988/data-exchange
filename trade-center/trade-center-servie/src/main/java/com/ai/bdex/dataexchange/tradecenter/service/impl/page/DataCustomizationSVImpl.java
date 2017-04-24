package com.ai.bdex.dataexchange.tradecenter.service.impl.page;
 

import com.ai.paas.utils.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.DataCustomizationMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.DataCustomization;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.DataCustomizationRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IDataCustomizationSV;
 import com.ai.paas.sequence.SeqUtil;
@Service("iDataCustomizationSV")
public class DataCustomizationSVImpl implements IDataCustomizationSV {
	
	  private static final Logger log = Logger.getLogger(DataCustomizationSVImpl.class);
	
	  @Autowired
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
	  //保存定制信息
	  @Override
	   public int saveDataCustomization(DataCustomizationRespDTO dataCustomizationRespDTO) throws Exception{
		    DataCustomization record = new DataCustomization();	
			BeanUtils.copyProperties(record, dataCustomizationRespDTO);
		    int seq =  SeqUtil.getInt("SEQ_DATA_CUSTOMIZATION");
		    record.setDczaId(seq);
 			record.setCreateStaffId(dataCustomizationRespDTO.getCreateStaffId());
		 	 record.setCreateTime(DateUtil.getNowAsDate());
 			return dataCustomizationMapper.insertSelective(record);
 	  }
	  //将定制信息处理为已处理
	  @Override
	   public int updateDataCustomizationStatus(DataCustomizationRespDTO dataCustomizationRespDTO) throws Exception{
		    DataCustomization record = new DataCustomization();	
			BeanUtils.copyProperties(record, dataCustomizationRespDTO);
  			record.setCreateStaffId(dataCustomizationRespDTO.getCreateStaffId());
		    record.setCreateTime(DateUtil.getNowAsDate()); 
			return dataCustomizationMapper.updateByPrimaryKey(record);
 	  }

	  
}
