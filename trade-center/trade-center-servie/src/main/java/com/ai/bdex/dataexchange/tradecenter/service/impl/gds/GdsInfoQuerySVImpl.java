package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoQuerySV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: <br>
 * Date:2017年4月25日上午11:07:26  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("gdsInfoQuerySV")
public class GdsInfoQuerySVImpl implements IGdsInfoQuerySV{
    private static final Logger logger = LoggerFactory.getLogger(GdsInfoQuerySVImpl.class.getName());
    @Resource
    private IGdsInfoSV iGdsInfoSV;
    
    @Override
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO)
            throws Exception {
        //分页信息赋值
        int page = gdsInfoReqDTO.getPageNo();
        int rows = gdsInfoReqDTO.getPageSize();
        //开启分页查询，使用mybatis-PageHelper分页插件，第三个条件是order by排序子句
        PageHelper.startPage(page, rows, "update_time desc");
        //执行查询第一个mybatis查询方法，会被进行分页
        List<GdsInfo> lists = iGdsInfoSV.queryGdsInfoList(gdsInfoReqDTO);
        //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(lists);
        logger.info("IGdsInfoSV查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + lists.size());
        //按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
        PageResponseDTO<GdsInfoRespDTO> resultDTO = PageResponseFactory.genPageResponse(pageInfo,GdsInfoRespDTO.class);
        return resultDTO;
    }


}

