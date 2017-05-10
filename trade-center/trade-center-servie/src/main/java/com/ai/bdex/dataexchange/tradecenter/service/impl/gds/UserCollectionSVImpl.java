package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.UserCollectionMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.UserCollection;
import com.ai.bdex.dataexchange.tradecenter.dao.model.UserCollectionExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserCollectionReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserCollectionRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IUserCollectionSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("iUserCollectionSV")
public class UserCollectionSVImpl implements IUserCollectionSV{
    
    private static Logger logger = LoggerFactory.getLogger(UserCollectionSVImpl.class.getName());
    @Resource
    private UserCollectionMapper userCollectionMapper;
    
    @Override
    public List<UserCollectionRespDTO> queryUserCollectionList(
            UserCollectionReqDTO userCollectionReqDTO) throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        UserCollectionExample example = new UserCollectionExample();
        UserCollectionExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userCollectionReqDTO);
        List<UserCollection> userCollections = userCollectionMapper.selectByExample(example);
        List<UserCollectionRespDTO> resultList = null;
        if(!CollectionUtil.isEmpty(userCollections)){
            resultList = new ArrayList<UserCollectionRespDTO>();
            UserCollectionRespDTO userCollectionRespDTO = null;
            for(UserCollection userCollection : userCollections){
                userCollectionRespDTO = new UserCollectionRespDTO();
                ObjectCopyUtil.copyObjValue(userCollection, userCollectionRespDTO, null, false);
                resultList.add(userCollectionRespDTO);
            }
        }
        return resultList;
    }

    @Override
    public int insertUserCollection(UserCollectionReqDTO userCollectionReqDTO)
            throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        int colId =SeqUtil.getInt("SEQ_USER_COLLECTION");
        UserCollection userCollection = new UserCollection();
        ObjectCopyUtil.copyObjValue(userCollectionReqDTO, userCollection, null, false);
        userCollection.setColId(colId);
        Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
        userCollection.setCreateTime(time);
        int code = userCollectionMapper.insert(userCollection);
        return code;
    }

    @Override
    public int updateUserCollection(UserCollectionReqDTO userCollectionReqDTO)
            throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        UserCollectionExample example = new UserCollectionExample();
        UserCollectionExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userCollectionReqDTO);
        UserCollection userCollection = new UserCollection();
        ObjectCopyUtil.copyObjValue(userCollectionReqDTO, userCollection, null, false);
        Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
        userCollection.setUpdateTime(time);
        int code = userCollectionMapper.updateByExampleSelective(userCollection, example);
        return code;
    }

    @Override
    public int deleteUserCollection(UserCollectionReqDTO userCollectionReqDTO)
            throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        UserCollectionExample example = new UserCollectionExample();
        UserCollectionExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userCollectionReqDTO);
        int code = userCollectionMapper.deleteByExample(example);
        return code;
    }

    @Override
    public UserCollectionRespDTO queryUserCollection(UserCollectionReqDTO userCollectionReqDTO)
            throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        UserCollectionExample example = new UserCollectionExample();
        UserCollectionExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userCollectionReqDTO);
        List<UserCollection> userCollections = userCollectionMapper.selectByExample(example);
        UserCollectionRespDTO userCollectionRespDTO = null;
        if(!CollectionUtil.isEmpty(userCollections)){
            userCollectionRespDTO = new UserCollectionRespDTO();
            ObjectCopyUtil.copyObjValue(userCollections.get(0), userCollectionRespDTO, null, false);
        }
        return userCollectionRespDTO;
    }

    @Override
    public long count(UserCollectionReqDTO userCollectionReqDTO) throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        UserCollectionExample example = new UserCollectionExample();
        UserCollectionExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userCollectionReqDTO);
        long count = userCollectionMapper.countByExample(example);
        return count;
    }

    @Override
    public PageResponseDTO<UserCollectionRespDTO> queryUserCollectionPage(
            UserCollectionReqDTO userCollectionReqDTO) throws BusinessException {
        if(userCollectionReqDTO == null){
            throw new BusinessException("入参userCollectionReqDTO不能为null");
        }
        int page = userCollectionReqDTO.getPageNo();
        int rows = userCollectionReqDTO.getPageSize();
        //执行查询第一个mybatis查询方法，会被进行分页
        UserCollectionExample example = new UserCollectionExample();
        UserCollectionExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userCollectionReqDTO);
        example.setOrderByClause("create_time desc");
        //开启分页查询，使用mybatis-PageHelper分页插件，第三个条件是排序子句
        PageHelper.startPage(page, rows);
        List<UserCollection> userCollections = userCollectionMapper.selectByExample(example);
        //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(userCollections);
        logger.info("IGdsInfoSV查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + userCollections.size());
        //按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
        PageResponseDTO<UserCollectionRespDTO> resultDTO = PageResponseFactory.genPageResponse(pageInfo,UserCollectionRespDTO.class);
        return resultDTO;
    }
    
    private void initCriteria(UserCollectionExample.Criteria criteria, UserCollectionReqDTO userCollectionReqDTO){
        if(userCollectionReqDTO.getColId()!=null && userCollectionReqDTO.getColId().intValue()>0){
            criteria.andColIdEqualTo(userCollectionReqDTO.getColId());
        }
        if(userCollectionReqDTO.getGdsId()!=null && userCollectionReqDTO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(userCollectionReqDTO.getGdsId());
        }
        if(userCollectionReqDTO.getSkuId()!=null && userCollectionReqDTO.getSkuId().intValue()>0){
            criteria.andSkuIdEqualTo(userCollectionReqDTO.getSkuId());
        }
        if (userCollectionReqDTO.getCatFirst()!=null && userCollectionReqDTO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(userCollectionReqDTO.getCatFirst());
        }
        if (StringUtil.isNotBlank(userCollectionReqDTO.getStaffId())){
            criteria.andStaffIdEqualTo(userCollectionReqDTO.getStaffId());
        }
        if (!StringUtil.isBlank(userCollectionReqDTO.getStatus())){
            criteria.andStatusEqualTo(userCollectionReqDTO.getStatus());
        }
        if(!CollectionUtil.isEmpty(userCollectionReqDTO.getGdsIds())){
            criteria.andGdsIdIn(userCollectionReqDTO.getGdsIds());
        }
        
    }
    
}



