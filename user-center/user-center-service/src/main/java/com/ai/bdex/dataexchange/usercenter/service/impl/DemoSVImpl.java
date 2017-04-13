package com.ai.bdex.dataexchange.usercenter.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dao.mapper.TdemoMapper;
import com.ai.bdex.dataexchange.common.dao.model.Tdemo;
import com.ai.bdex.dataexchange.common.dao.model.TdemoExample;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IDemoSV;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
@Service("demoSV")
public class DemoSVImpl implements IDemoSV {
	
	@Autowired
	TdemoMapper tdemoMapper;
	
    @Override
    public String callDemo(DemoDTO demoDTO) {
    	
    	//按主键查询
    	Tdemo model = tdemoMapper.selectByPrimaryKey("idkey");
    	System.out.println(model);
    	//按条件查询
    	TdemoExample example = new TdemoExample();
    	TdemoExample.Criteria sql = example.createCriteria();
    	
    	sql.andUserIdEqualTo("userID");
    	
    	List<Tdemo> list = tdemoMapper.selectByExample(example);
    	System.out.println(list);
    	
    	//分页查询
    	//第一个参数 PageNo，第二个参数PageSize
    	PageHelper.startPage(1, 10);
    	List<Tdemo> pageList = tdemoMapper.selectByExample(example);
    	//转成page对象
 	    Page<Tdemo> page = (Page<Tdemo>)pageList;
 	    if(page != null){
 	    	System.out.println("总记录数：" + page.getTotal());
 	    	System.out.println("当前页数据：" + page.getResult());
 	    }
 	    
 	    //新增，删除，更新 请查阅 tdemoMapper 提供的方法
    	
        return null;
    }
}
