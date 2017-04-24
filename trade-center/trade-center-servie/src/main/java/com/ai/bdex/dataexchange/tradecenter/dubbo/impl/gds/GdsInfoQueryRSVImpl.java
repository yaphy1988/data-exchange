package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.gds;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoQueryRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("gdsInfoQueryRSV")
public class GdsInfoQueryRSVImpl implements IGdsInfoQueryRSV{
    private static final Logger logger = LoggerFactory.getLogger(GdsInfoQueryRSVImpl.class.getName());
    @Resource
    private IGdsInfoSV iGdsInfoSV;
    /**
     * add gongxq
     * TODO 简单描述该方法的实现功能（获取商品列表信息 ）. 
     * @see com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoQueryRSV#queryGdsInfoListPage(com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO)
     */
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

