package com.ai.bdex.dataexchange.usercenter.dao.mapper;

import com.ai.bdex.dataexchange.usercenter.dao.model.SmsSeccodeLog;

public interface SmsSeccodeLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(SmsSeccodeLog record);

    int insertSelective(SmsSeccodeLog record);

    SmsSeccodeLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(SmsSeccodeLog record);

    int updateByPrimaryKey(SmsSeccodeLog record);
}