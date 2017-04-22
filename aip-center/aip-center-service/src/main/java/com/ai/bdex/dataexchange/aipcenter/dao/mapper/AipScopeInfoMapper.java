package com.ai.bdex.dataexchange.aipcenter.dao.mapper;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipScopeInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipScopeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AipScopeInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_scope_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    long countByExample(AipScopeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_scope_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int deleteByExample(AipScopeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_scope_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int deleteByPrimaryKey(String scopeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_scope_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int insert(AipScopeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_scope_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int insertSelective(AipScopeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_scope_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    List<AipScopeInfo> selectByExample(AipScopeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_scope_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    AipScopeInfo selectByPrimaryKey(String scopeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_scope_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByExampleSelective(@Param("record") AipScopeInfo record, @Param("example") AipScopeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_scope_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByExample(@Param("record") AipScopeInfo record, @Param("example") AipScopeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_scope_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByPrimaryKeySelective(AipScopeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_scope_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByPrimaryKey(AipScopeInfo record);
}