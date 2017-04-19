package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Gds;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelQuikReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelQuikRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsResultVO;

/**
 * Created by yx on 2017/4/18.
 */
public interface IGdsInfoRSV {

    public GdsInfoRespDTO queryGdsInfoDetails (GdsInfoReqDTO gdsInfoReqDTO) throws Exception;
    
    /**
     * 查询商品分类
     * @param gdsCatReqDTO
     * @return
     * @throws Exception
     */
    public List<GdsCatRespDTO> queryGdsCatListDTO(GdsCatReqDTO gdsCatReqDTO) throws Exception;
    /**
     * 查询商品标签
     * @param gdsLabelReqDTO
     * @return
     * @throws Exception
     */
    public List<GdsLabelRespDTO> queryGdsLabelListDTO(GdsLabelReqDTO gdsLabelReqDTO) throws Exception;
    /**
     * 查询商品标签快速选择数据
     * @param gdsLabelQuikReqDTO
     * @return
     * @throws Exception
     */
    public List<GdsLabelQuikRespDTO> queryGdsLabelQuikListDTO(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception ;
    
    /**
     * 新增商品信息
     * @param reqDTO
     * @return
     * @throws Exception
     */
	public GdsResultVO addGds(GdsInfoReqDTO reqDTO) throws Exception ;
	/**
	 *  编辑商品信息
	 * @param reqDTO
	 * @return
	 * @throws Exception
	 */
	public GdsResultVO editGds(GdsInfoReqDTO reqDTO) throws Exception;
	/**
	 * 查询商品信息
	 * @param gdsInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public GdsInfoRespDTO queryGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception;
}
