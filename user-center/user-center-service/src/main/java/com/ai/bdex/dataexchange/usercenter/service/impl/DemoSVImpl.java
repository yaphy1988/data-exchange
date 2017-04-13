package com.ai.bdex.dataexchange.usercenter.service.impl;


import java.util.List;

import com.ai.bdex.dataexchange.common.dao.mapper.DemoMapper;
import com.ai.bdex.dataexchange.common.dao.model.Demo;
import com.ai.bdex.dataexchange.common.dao.model.DemoExample;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;
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
	DemoMapper demoMapper;
	
    @Override
    public String callDemo(DemoDTO demoDTO) {
		List<Demo> list= queryTest(demoDTO);
        return list.get(0).getUserName();
    }

    private void insertTest(){
		Demo model = new Demo();
		model.setId((int)(Math.random()*100));
		model.setUserName("fangyf_"+model.getId());
		model.setAddr("gx");
		//model.set
		//插入
		demoMapper.insert(model);
	}

	private void updateByPrimaryKeyTest(){
		Demo model = new Demo();
		model.setId(1);
		model.setUserName("fangyf_update");
		//model.set
		//插入
		demoMapper.updateByPrimaryKey(model);
	}

	private void commonUpdateTest(){

		Demo model = new Demo();
		model.setUserName("fangyf_update_2");
		DemoExample example = new DemoExample();
		example.createCriteria().andUserNameEqualTo("fangyf_update");
		//model.set
		//插入
		demoMapper.updateByPrimaryKey(model);
	}



	private List<Demo> queryTest(DemoDTO demoDTO){
		DemoExample example = new DemoExample();
		DemoExample.Criteria criteria = example.createCriteria();
		criteria.andAddrEqualTo(demoDTO.getAddr());
		List<Demo> listDemo = demoMapper.selectByExample(example);
		System.out.println("============queryTest result============");
		for(Demo demo : listDemo){
			System.out.println(demo.getId()+":"+demo.getUserName()+":"+demo.getAddr());
		}

		return listDemo;
	}

	private void queryByPageTest(){


		DemoExample example = new DemoExample();
		DemoExample.Criteria criteria = example.createCriteria();
		criteria.andAddrEqualTo("gx");

		//第一个参数 PageNo，第二个参数PageSize，该设置只对紧随其后的第一次查询有效
		PageHelper.startPage(1, 2);
		List<Demo> listDemo = demoMapper.selectByExample(example);

        //转成page对象
		Page<Demo> page = (Page<Demo>)listDemo;
		System.out.println("============queryByPageTest result============");
		if(page != null){
			System.out.println("总记录数：" + page.getTotal());
			System.out.println("当前页数据：");
			for(Demo demo : page.getResult()){
				System.out.println(demo.getId()+":"+demo.getUserName()+":"+demo.getAddr());
			}
		}
		//System.out.println(listDemo);
	}
}
