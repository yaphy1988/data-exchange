package com.ai.bdex.dataexchange.usercenter.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.bdex.dataexchange.usercenter.dao.model.SmsSeccodeLog;
import com.ai.bdex.dataexchange.usercenter.dao.model.SmsSeccodeLogExample;

public interface SmsSeccodeLogMapper {
    int countByExample(SmsSeccodeLogExample example);

    int deleteByExample(SmsSeccodeLogExample example);

    int deleteByPrimaryKey(Long logId);

    int insert(SmsSeccodeLog record);

    int insertSelective(SmsSeccodeLog record);

    List<SmsSeccodeLog> selectByExample(SmsSeccodeLogExample example);

    SmsSeccodeLog selectByPrimaryKey(Long logId);

    int updateByExampleSelective(@Param("record") SmsSeccodeLog record, @Param("example") SmsSeccodeLogExample example);

    int updateByExample(@Param("record") SmsSeccodeLog record, @Param("example") SmsSeccodeLogExample example);

    int updateByPrimaryKeySelective(SmsSeccodeLog record);

    int updateByPrimaryKey(SmsSeccodeLog record);
}