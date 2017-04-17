package com.ai.bdex.dataexchange.usercenter.service.impl;


import java.sql.Timestamp;
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.DemoMapper;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.custom.BaseSysCfgMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.Demo;
import com.ai.bdex.dataexchange.usercenter.dao.model.DemoExample;
import com.ai.bdex.dataexchange.sequence.SeqUtil;
import com.ai.bdex.dataexchange.usercenter.dao.model.custom.BaseSysCfg;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ReqDemoDTO;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DemoMapper demoMapper;

	@Autowired
	BaseSysCfgMapper baseSysCfgMapper;
	
    @Override
    public String callDemo(DemoDTO demoDTO) {
		List<Demo> list= queryTest(demoDTO);
        return list.get(0).getUserName();
    }

	@Override
	public PageResponseDTO<DemoDTO> queryDemoPage(ReqDemoDTO demoDTO) throws BusinessException {
		//分页信息赋值
		int page = demoDTO.getPageNo();
		int rows = demoDTO.getPageSize();
		//查询条件赋值
		DemoExample example = new DemoExample();
		DemoExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause(demoDTO.getGridQuerySortName() +" "+demoDTO.getGridQuerySortOrder());
		criteria.andUserNameEqualTo(demoDTO.getUserName());
		//开启分页查询，使用mybatis-PageHelper分页插件，第一个参数 PageNo，第二个参数PageSize，该设置只对紧随其后的第一次查询有效
		PageHelper.startPage(page, rows);
		//执行查询第一个mybatis查询方法，会被进行分页
		List<Demo> lists = demoMapper.selectByExample(example);
		//使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(lists);
		logger.info("IDemoInfoSV查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + lists.size());
		//按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
		PageResponseDTO<DemoDTO> respDTO = PageResponseFactory.genPageResponse(pageInfo,DemoDTO.class);
		return respDTO;
	}

	private void insertTest2(){
		BaseSysCfg baseSysCfg = new BaseSysCfg();
		baseSysCfg.setParaCode("test");
		baseSysCfg.setCreateTime(new Timestamp(System.currentTimeMillis()));
		baseSysCfg.setParaDesc("heihei");
		baseSysCfg.setParaValue("kill");
		baseSysCfg.setCreateStaff(1000L);
		baseSysCfgMapper.insert(baseSysCfg);

	}

    private void insertTest(){
		Demo model = new Demo();
		model.setId(SeqUtil.getInt("SEQ_DEMO"));
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
