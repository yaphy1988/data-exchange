package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageModuleRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleGoodsSV;

@Service("iPageModuleRSV")
public class PageModuleRSVImpl implements IPageModuleRSV {
    private static final Logger log = LoggerFactory.getLogger(PageModuleRSVImpl.class);
    @Resource
    private IPageModuleGoodsSV	iPageModuleGoodsSV;

	public PageResponseDTO<PageModuleGoodsRespDTO> queryPageModuleGoodsPage(PageModuleGoodsReqDTO moduleGoodsReqDTO) throws Exception {
		PageResponseDTO<PageModuleGoodsRespDTO> pageInfo = new PageResponseDTO<PageModuleGoodsRespDTO>();
        try{
        	 if (moduleGoodsReqDTO ==null ){
                 throw new Exception("查询商品楼层信息异常，入参为空");
             }
        	 pageInfo = iPageModuleGoodsSV.queryPageModuleGoodsPage(moduleGoodsReqDTO);
           
        }catch(Exception e){
        	log.error("获取商品楼层信息异常:", e);
            throw new Exception(e);
        }
        return pageInfo;
	}
	public List<PageModuleGoodsRespDTO> queryPageModuleGoodsInfoList(PageModuleGoodsReqDTO moduleGoodsReqDTO) throws Exception {
		List<PageModuleGoodsRespDTO> respDTO = new ArrayList<PageModuleGoodsRespDTO>();
		try{
        	 if (moduleGoodsReqDTO ==null ){
                 throw new Exception("查询商品楼层信息异常，入参为空");
             }
        	 respDTO = iPageModuleGoodsSV.queryPageModuleGoodsInfoList(moduleGoodsReqDTO);
        }catch(Exception e){
        	log.error("获取商品楼层信息异常:", e);
            throw new Exception(e);
        }
        return respDTO;
	}
	public int insertPageModuleGoods(PageModuleGoodsReqDTO moduleGoodsReqDTO)throws Exception {
		int pmgId=0;
		try{
			if (moduleGoodsReqDTO ==null ){
                throw new Exception("插入商品楼层信息异常，入参为空");
            }
			if(moduleGoodsReqDTO.getModuleId()==null){
                throw new Exception("插入商品楼层信息异常，moduleId入参为空");
			}
			if(moduleGoodsReqDTO.getGdsId()==null){
                throw new Exception("插入商品楼层信息异常，gdsId入参为空");
			}
			pmgId = iPageModuleGoodsSV.insertPageModuleGoods(moduleGoodsReqDTO);
       }catch(Exception e){
       		log.error("选择商品楼层信息异常:", e);
           throw new Exception(e);
       }
		return pmgId;
	}
	public int updatePageModuleGoods(PageModuleGoodsReqDTO moduleGoodsReqDTO)throws Exception {
		int code=0;
		try{
			if (moduleGoodsReqDTO ==null ){
                throw new Exception("更新商品楼层信息异常，入参为空");
            }
			if(moduleGoodsReqDTO.getPmgId()==null){
                throw new Exception("更新商品楼层信息异常，pmgId入参为空");
			}
			code = iPageModuleGoodsSV.updatePageModuleGoods(moduleGoodsReqDTO);
		}catch(Exception e){
	       	log.error("更新商品楼层信息异常:", e);
	          throw new Exception(e);
	    }
		return code;
	}
}
