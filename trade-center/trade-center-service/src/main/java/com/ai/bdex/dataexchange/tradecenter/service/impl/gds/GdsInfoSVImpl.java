package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2PropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsResultVO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfo2PropSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsSkuSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsInfoSV")
public class GdsInfoSVImpl implements IGdsInfoSV{

    @Autowired
    private GdsInfoMapper gdsInfoMapper;
    @Resource
    private IGdsSkuSV iGdsSkuSV;
    @Resource
    private IGdsLabelSV iGdsLabelSV;
    @Resource
    private IGdsInfo2PropSV iGdsInfo2PropSV;

    @Override
    public GdsInfo queryGdsInfoById(Integer gdsId) throws Exception {

        if (gdsId==null || gdsId.intValue()<=0){
            throw new Exception("查询商品信息入参为空");
        }
        GdsInfo gdsInfo = gdsInfoMapper.selectByPrimaryKey(gdsId);
        return gdsInfo;
    }

    @Override
    public List<GdsInfo> queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
        if (gdsInfoReqDTO ==null){
            throw new Exception("查询商品信息列表入参为空");
        }
        GdsInfoExample example = new GdsInfoExample();
        GdsInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsInfoReqDTO);
        List<GdsInfo> gdsInfos = gdsInfoMapper.selectByExample(example);
        return gdsInfos;
    }

    @Override
    public int insertGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
        if (gdsInfoReqDTO ==null){
            throw new Exception("插入商品信息入参为空");
        }
        int gdsId=SeqUtil.getInt("SEQ_GDS_INFO");
        GdsInfo gdsInfo = new GdsInfo();
        gdsInfoReqDTO.setGdsId(gdsId);
        gdsInfoReqDTO.setCreateTime(DateUtil.getNowAsDate());
        gdsInfoReqDTO.setUpdateTime(DateUtil.getNowAsDate());
        ObjectCopyUtil.copyObjValue(gdsInfoReqDTO,gdsInfo,null,false);
        int code = gdsInfoMapper.insert(gdsInfo);
        return gdsId;
    }

    @Override
    public int updateGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
        if (gdsInfoReqDTO ==null){
            throw new Exception("更新商品信息入参为空");
        }
        GdsInfo gdsInfo = new GdsInfo();
        ObjectCopyUtil.copyObjValue(gdsInfoReqDTO,gdsInfo,null,false);
        int code = gdsInfoMapper.updateByPrimaryKeySelective(gdsInfo);
        return gdsInfoReqDTO.getGdsId();
    }

    @Override
    public int deleteGdsInfoById(Integer gdsId) throws Exception {
        if (gdsId==null || gdsId.intValue()<=0){
            throw new Exception("删除商品信息入参为空");
        }
        int code = gdsInfoMapper.deleteByPrimaryKey(gdsId);
        return code;
    }

    private void initCriteria(GdsInfoExample.Criteria criteria, GdsInfoReqDTO gdsInfoReqDTO){
        if(gdsInfoReqDTO.getGdsId()!=null && gdsInfoReqDTO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsInfoReqDTO.getGdsId());
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getGdsName())){
            criteria.andGdsNameLike("%"+ gdsInfoReqDTO.getGdsName()+"%");
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getGdsSubtitle())){
            criteria.andGdsSubtitleLike("%"+ gdsInfoReqDTO.getGdsSubtitle()+"%");
        }
        if (gdsInfoReqDTO.getCatFirst()!=null && gdsInfoReqDTO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(gdsInfoReqDTO.getCatFirst());
        }
        if (gdsInfoReqDTO.getApiId()!=null && gdsInfoReqDTO.getApiId().intValue()>0){
            criteria.andApiIdEqualTo(gdsInfoReqDTO.getApiId());
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getIfRecommend())){
            criteria.andIfRecommendEqualTo(gdsInfoReqDTO.getIfRecommend());
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getFunIntroduction())){
            criteria.andFunIntroductionEqualTo(gdsInfoReqDTO.getFunIntroduction());
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getCommpanyName())){
            criteria.andCommpanyNameLike("%"+ gdsInfoReqDTO.getCommpanyName()+"%");
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsInfoReqDTO.getStatus());
        }
        if (!CollectionUtil.isEmpty(gdsInfoReqDTO.getGdsIds())){
            criteria.andGdsIdIn(gdsInfoReqDTO.getGdsIds());
        }
        if (!CollectionUtil.isEmpty(gdsInfoReqDTO.getGdsIdsNotIn())){
            criteria.andGdsIdNotIn(gdsInfoReqDTO.getGdsIdsNotIn());
        }
        if (gdsInfoReqDTO.getCatId()!=null){
            criteria.andCatIdEqualTo(gdsInfoReqDTO.getCatId());
        }
        
    }
    /**
     * 新增商品
     * @param reqDTO
     * @return
     * @throws Exception
     */
	@Override
	public GdsResultVO addGds(GdsInfoReqDTO reqDTO) throws Exception {
		// 商品主表
        GdsInfo gdsInfo = new GdsInfo();
        ObjectCopyUtil.copyObjValue(reqDTO,gdsInfo,null,false);
//        BeanUtils.copyProperties(reqDTO,gdsInfo);
        int gdsId = gdsInfoMapper.insert(gdsInfo);
        //保存商品标签
        List<GdsLabelReqDTO> labelReqDTOList = reqDTO.getGdsLabelReqDTOList();
        if(!CollectionUtil.isEmpty(labelReqDTOList)){
        	for(GdsLabelReqDTO labelReqDTO : labelReqDTOList){
            	labelReqDTO.setGdsId(gdsId);
        		iGdsLabelSV.insertGdsLabel(labelReqDTO);
        	}
        }
        //保存单品信息
        List<GdsSkuReqDTO> skuList = reqDTO.getGdsSkuReqDTOList();
        if(!CollectionUtil.isEmpty(skuList)){
        	for(GdsSkuReqDTO skuReqDTO : skuList){
        		skuReqDTO.setGdsId(gdsId);
        		iGdsSkuSV.insertGdsSku(skuReqDTO);
        	}
        }
        GdsInfo2PropReqDTO propReqDTO = reqDTO.getGdsInfo2PropReqDTO();
        if(propReqDTO!=null&&propReqDTO.getProId()!=0){
        	propReqDTO.setGdsId(gdsId);
        	iGdsInfo2PropSV.insertGdsInfo2Prop(propReqDTO);
        }

		// 返回处理结果
        GdsResultVO resultVO = new GdsResultVO();
		resultVO.setGdsId(gdsId);
		resultVO.setMessage("新增商品成功");
		resultVO.setResult(true);
		return resultVO;
	}
	 /**
     * 新增商品
     * @param reqDTO
     * @return
     * @throws Exception
     */
	@Override
	public GdsResultVO editGds(GdsInfoReqDTO reqDTO) throws Exception {
		// 商品主表
        GdsInfo gdsInfo = new GdsInfo();
        ObjectCopyUtil.copyObjValue(reqDTO,gdsInfo,null,false);
//        BeanUtils.copyProperties(reqDTO,gdsInfo);
        gdsInfoMapper.updateByPrimaryKeySelective(gdsInfo);
        //保存商品标签
        List<GdsLabelReqDTO> labelReqDTOList = reqDTO.getGdsLabelReqDTOList();
        if(!CollectionUtil.isEmpty(labelReqDTOList)){
        	GdsLabelReqDTO gdsLabelReqDTO = new GdsLabelReqDTO();
        	gdsLabelReqDTO.setGdsId(reqDTO.getGdsId());
        	iGdsLabelSV.deleteGdslabelByGdsId(gdsLabelReqDTO);
        	for(GdsLabelReqDTO labelReqDTO : labelReqDTOList){
        		iGdsLabelSV.insertGdsLabel(labelReqDTO);
        	}
        }
        //保存单品信息
        List<GdsSkuReqDTO> skuList = reqDTO.getGdsSkuReqDTOList();
        if(!CollectionUtil.isEmpty(skuList)){
        	GdsSkuReqDTO gdsSkuReqDTO = new GdsSkuReqDTO();
        	gdsSkuReqDTO.setGdsId(reqDTO.getGdsId());
        	//删除单品信息
        	iGdsSkuSV.deleteGdsSkuByGdsId(gdsSkuReqDTO);
        	for(GdsSkuReqDTO skuReqDTO : skuList){
        		skuReqDTO.setGdsId(reqDTO.getGdsId());
        		iGdsSkuSV.insertGdsSku(skuReqDTO);
        	}
        }
        GdsInfo2PropReqDTO propReqDTO = reqDTO.getGdsInfo2PropReqDTO();
        if(propReqDTO!=null&&propReqDTO.getProId()!=0){
        	iGdsInfo2PropSV.updateGdsInfo2Prop(propReqDTO);
        }

		// 返回处理结果
        GdsResultVO resultVO = new GdsResultVO();
		resultVO.setGdsId(reqDTO.getGdsId());
		resultVO.setMessage("编辑商品成功");
		resultVO.setResult(true);
		return resultVO;
	}
	public GdsInfoRespDTO queryGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
		GdsInfoRespDTO respDTO = new GdsInfoRespDTO();
		GdsInfoExample example = new GdsInfoExample();
		GdsInfoExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria, gdsInfoReqDTO);
		List<GdsInfo> gdsInfoList = gdsInfoMapper.selectByExample(example);
		if (!CollectionUtil.isEmpty(gdsInfoList)) {
//			BeanUtils.copyProperties(gdsInfoList.get(0), respDTO);
            ObjectCopyUtil.copyObjValue(gdsInfoList.get(0),respDTO,null,false);
		}
		return respDTO;
	}

    @Override
    public long count(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
        GdsInfoExample example = new GdsInfoExample();
        GdsInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsInfoReqDTO);
        return gdsInfoMapper.countByExample(example);
    }

    @Override
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoPage(GdsInfoReqDTO gdsInfoReqDTO) {
        PageResponseDTO<GdsInfoRespDTO> page = null;

        int pageNo = gdsInfoReqDTO.getPageNo();
        int pageSize = gdsInfoReqDTO.getPageSize();

        GdsInfoExample gdsInfoExample = new GdsInfoExample();
        GdsInfoExample.Criteria criteria = gdsInfoExample.createCriteria();
        if (!StringUtil.isBlank(gdsInfoReqDTO.getGridQuerySortOrder()) && !StringUtil.isBlank(gdsInfoReqDTO.getGridQuerySortName())){
            gdsInfoExample.setOrderByClause(gdsInfoReqDTO.getGridQuerySortName() + " " + gdsInfoReqDTO.getGridQuerySortOrder());
        }
        initCriteria(criteria,gdsInfoReqDTO);
        PageHelper.startPage(pageNo,pageSize);
        List<GdsInfo> lists = gdsInfoMapper.selectByExample(gdsInfoExample);
        PageInfo pageInfo = new PageInfo(lists);
        page = PageResponseFactory.genPageResponse(pageInfo,GdsInfoRespDTO.class);

        return page;
    }

}
