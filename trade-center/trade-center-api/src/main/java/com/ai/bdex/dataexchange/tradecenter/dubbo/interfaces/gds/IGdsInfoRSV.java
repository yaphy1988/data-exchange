package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2CatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelQuikReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelQuikRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsPropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsPropRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsResultVO;

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
     * 查询商品列表
     * @param gdsInfoRespDTO
     * @return
     * @throws Exception
     */
    public List<GdsInfoRespDTO> queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO) throws Exception;
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
	
	public List<GdsPropRespDTO> queryGdsPropList(GdsPropReqDTO gdsPropReqDTO) throws Exception;
	
	public int insertGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception;
	public int insertGdsLabelQuik(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception;
    public GdsLabelQuikRespDTO queryGdsLabelQuikByName(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception;
    public int insertGdsInfo2Cat(GdsInfo2CatReqDTO gdsInfo2CatReqDTO) throws Exception;
}
