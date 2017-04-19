package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import com.ai.bdex.dataexchange.tradecenter.TradeCenterServiceStart;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo; 
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.SortInfoRespDTO; 
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Page.IPageInfoRSV; 
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortInfoSV;
import com.ai.paas.util.Utils;

 @SuppressWarnings("unused")
public class PageInfoRSVImpl implements IPageInfoRSV {
    private static final Logger log = LoggerFactory.getLogger(PageInfoRSVImpl.class);
    /*
     * 获取分类数据
     * @see com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Page.IPageInfoRSV#querySortInfos(com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.SortInfoRespDTO)
     */
    @Resource
    private ISortInfoSV   iSortInfoSV;
    @Override
    public List<SortInfoRespDTO> querySortInfos(SortInfoRespDTO sortInfoRespDTO) throws Exception {
        List<SortInfoRespDTO> sortInfoRespLis = new ArrayList<SortInfoRespDTO>();
        try{
        	 if (sortInfoRespDTO ==null ){
                 throw new Exception("查询商品分类信息异常，入参为空");
             }
         	 SortInfo exam = new SortInfo();
         	 BeanUtils.copyProperties( sortInfoRespDTO,exam);
          	 List<SortInfo> listSortInfo = iSortInfoSV.querySortInfoList(exam);
          	    if(!CollectionUtils.isEmpty(listSortInfo)){
                    for (SortInfo sortInfo : listSortInfo){
                    	SortInfoRespDTO sortInfoResp = new SortInfoRespDTO();
                        BeanUtils.copyProperties(sortInfo, sortInfoResp);
                        sortInfoRespLis.add(sortInfoResp);
                    }
                 }   
        }catch(Exception e){
        	log.error("获取商品分类信息异常:", e);
            throw new Exception(e);
        }
        return sortInfoRespLis;
    }
    public static void main(String[] args) throws Exception  { 
    	PageInfoRSVImpl pageInfoRSVImpl = new PageInfoRSVImpl();
    	SortInfoRespDTO sortInfoRespDTO = new SortInfoRespDTO();
    	sortInfoRespDTO.setStatus("1");
    	pageInfoRSVImpl.querySortInfos(sortInfoRespDTO);
    }
}
