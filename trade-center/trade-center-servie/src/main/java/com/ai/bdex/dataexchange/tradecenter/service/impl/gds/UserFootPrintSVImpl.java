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
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.UserFootPrintExtendsMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.UserFootPrintMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.UserFootPrint;
import com.ai.bdex.dataexchange.tradecenter.dao.model.UserFootPrintExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.UserFootPrintExtends;
import com.ai.bdex.dataexchange.tradecenter.dao.model.UserFootPrintExtendsExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserFootPrintReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.UserFootPrintRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IUserFootPrintSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("iUserFootPrintSV")
public class UserFootPrintSVImpl implements IUserFootPrintSV{
    private static final Logger logger = LoggerFactory.getLogger(UserFootPrintSVImpl.class.getName());
    
    @Resource
    private UserFootPrintMapper userFootPrintMapper;
    @Resource
    private UserFootPrintExtendsMapper userFootPrintExtendsMapper;
    
    @Override
    public List<UserFootPrintRespDTO> queryUserFootPrintList(
            UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        UserFootPrintExample example = new UserFootPrintExample();
        UserFootPrintExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userFootPrintReqDTO);
        List<UserFootPrint> userFootPrints = userFootPrintMapper.selectByExample(example);
        List<UserFootPrintRespDTO> resultList = null;
        if(!CollectionUtil.isEmpty(userFootPrints)){
            resultList = new ArrayList<UserFootPrintRespDTO>();
            UserFootPrintRespDTO UserFootPrintRespDTO = null;
            for(UserFootPrint userFootPrint : userFootPrints){
                UserFootPrintRespDTO = new UserFootPrintRespDTO();
                ObjectCopyUtil.copyObjValue(userFootPrint, UserFootPrintRespDTO, null, false);
                resultList.add(UserFootPrintRespDTO);
            }
        }
        return resultList;
    }

    @Override
    public int insertUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO)
            throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        int fpId =SeqUtil.getInt("SEQ_USER_FOOTPRINT");
        UserFootPrint userFootPrint = new UserFootPrint();
        ObjectCopyUtil.copyObjValue(userFootPrintReqDTO, userFootPrint, null, false);
        userFootPrint.setFpId(fpId);
        Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
        userFootPrint.setCreateTime(time);
        int code = userFootPrintMapper.insert(userFootPrint);
        return code;
    }

    @Override
    public int updateUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO)
            throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        UserFootPrintExample example = new UserFootPrintExample();
        UserFootPrintExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userFootPrintReqDTO);
        UserFootPrint userFootPrint = new  UserFootPrint();
        ObjectCopyUtil.copyObjValue(userFootPrintReqDTO, userFootPrint, null, false);
        Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
        userFootPrint.setUpdateTime(time);
        int code = userFootPrintMapper.updateByExampleSelective(userFootPrint, example);
        return code;
    }

    @Override
    public int deleteUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO)
            throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        UserFootPrintExample example = new UserFootPrintExample();
        UserFootPrintExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userFootPrintReqDTO);
        int code = userFootPrintMapper.deleteByExample(example);
        return code;
    }

    @Override
    public UserFootPrintRespDTO queryUserFootPrint(UserFootPrintReqDTO userFootPrintReqDTO)
            throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        UserFootPrintExample example = new UserFootPrintExample();
        UserFootPrintExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userFootPrintReqDTO);
        List<UserFootPrint> userFootPrints = userFootPrintMapper.selectByExample(example);
        UserFootPrintRespDTO userFootPrintRespDTO = null;
        if(!CollectionUtil.isEmpty(userFootPrints)){
            userFootPrintRespDTO = new UserFootPrintRespDTO();
            ObjectCopyUtil.copyObjValue(userFootPrints.get(0), userFootPrintRespDTO, null, false);
        }
        return userFootPrintRespDTO;
    }

    @Override
    public long count(UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        UserFootPrintExample example = new UserFootPrintExample();
        UserFootPrintExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userFootPrintReqDTO);
        long count = userFootPrintMapper.countByExample(example);
        return count;
    }

    @Override
    public PageResponseDTO<UserFootPrintRespDTO> queryUserFootPrintPage(
            UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        int page = userFootPrintReqDTO.getPageNo();
        int rows = userFootPrintReqDTO.getPageSize();
        //执行查询第一个mybatis查询方法，会被进行分页
        UserFootPrintExample example = new UserFootPrintExample();
        UserFootPrintExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userFootPrintReqDTO);
        example.setOrderByClause("create_time desc");
        //开启分页查询，使用mybatis-PageHelper分页插件，第三个条件是排序子句
        PageHelper.startPage(page, rows);
        List<UserFootPrint> userFootPrints = userFootPrintMapper.selectByExample(example);
        //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(userFootPrints);
        logger.info("查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + userFootPrints.size());
        //按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
        PageResponseDTO<UserFootPrintRespDTO> resultDTO = PageResponseFactory.genPageResponse(pageInfo,UserFootPrintRespDTO.class);
        return resultDTO;
    }
    
    private void initCriteria(UserFootPrintExample.Criteria criteria, UserFootPrintReqDTO userFootPrintReqDTO){
        if(userFootPrintReqDTO.getFpId()!=null && userFootPrintReqDTO.getFpId().intValue()>0){
            criteria.andFpIdEqualTo(userFootPrintReqDTO.getFpId());
        }
        if(userFootPrintReqDTO.getGdsId()!=null && userFootPrintReqDTO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(userFootPrintReqDTO.getGdsId());
        }
        if (userFootPrintReqDTO.getCatFirst()!=null && userFootPrintReqDTO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(userFootPrintReqDTO.getCatFirst());
        }
        if (StringUtil.isNotBlank(userFootPrintReqDTO.getStaffId())){
            criteria.andStaffIdEqualTo(userFootPrintReqDTO.getStaffId());
        }
        if (!StringUtil.isBlank(userFootPrintReqDTO.getStatus())){
            criteria.andStatusEqualTo(userFootPrintReqDTO.getStatus());
        }
        if(userFootPrintReqDTO.getSeeNum()!=null && userFootPrintReqDTO.getSeeNum().intValue()>0){
            criteria.andSeeNumEqualTo(userFootPrintReqDTO.getSeeNum());
        }
    }

    @Override
    public PageResponseDTO<UserFootPrintRespDTO> queryUserFootPrintPageExtends(
            UserFootPrintReqDTO userFootPrintReqDTO) throws BusinessException {
        if(userFootPrintReqDTO == null){
            throw new BusinessException("入参userFootPrintReqDTO不能为null");
        }
        int page = userFootPrintReqDTO.getPageNo();
        int rows = userFootPrintReqDTO.getPageSize();
        //执行查询第一个mybatis查询方法，会被进行分页
        UserFootPrintExtendsExample example = new UserFootPrintExtendsExample();
        UserFootPrintExtendsExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,userFootPrintReqDTO);
        example.setOrderByClause("create_time desc");
        //开启分页查询，使用mybatis-PageHelper分页插件，第三个条件是排序子句
        PageHelper.startPage(page, rows);
        List<UserFootPrintExtends> userFootPrints = userFootPrintExtendsMapper.selectByExampleExtends(example);
        //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(userFootPrints);
        logger.info("查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + userFootPrints.size());
        //按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
        PageResponseDTO<UserFootPrintRespDTO> resultDTO = PageResponseFactory.genPageResponse(pageInfo,UserFootPrintRespDTO.class);
        return resultDTO;
    }
    
    private void initCriteria(UserFootPrintExtendsExample.Criteria criteria, UserFootPrintReqDTO userFootPrintReqDTO){
        if(userFootPrintReqDTO.getFpId()!=null && userFootPrintReqDTO.getFpId().intValue()>0){
            criteria.andFpIdEqualTo(userFootPrintReqDTO.getFpId());
        }
        if(userFootPrintReqDTO.getGdsId()!=null && userFootPrintReqDTO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(userFootPrintReqDTO.getGdsId());
        }
        if (userFootPrintReqDTO.getCatFirst()!=null && userFootPrintReqDTO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(userFootPrintReqDTO.getCatFirst());
        }
        if (StringUtil.isNotBlank(userFootPrintReqDTO.getStaffId())){
            criteria.andStaffIdEqualTo(userFootPrintReqDTO.getStaffId());
        }
        if (!StringUtil.isBlank(userFootPrintReqDTO.getStatus())){
            criteria.andStatusEqualTo(userFootPrintReqDTO.getStatus());
        }
        if(userFootPrintReqDTO.getSeeNum()!=null && userFootPrintReqDTO.getSeeNum().intValue()>0){
            criteria.andSeeNumEqualTo(userFootPrintReqDTO.getSeeNum());
        }
        if(StringUtil.isNotBlank(userFootPrintReqDTO.getGdsName())){
            criteria.andGdsNameLike("%"+userFootPrintReqDTO.getGdsName()+"%");
        }
    }
}

