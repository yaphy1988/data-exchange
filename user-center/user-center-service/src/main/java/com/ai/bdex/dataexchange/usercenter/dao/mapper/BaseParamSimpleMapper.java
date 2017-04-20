package com.ai.bdex.dataexchange.usercenter.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.bdex.dataexchange.usercenter.dao.model.BaseParamSimple;
import com.ai.bdex.dataexchange.usercenter.dao.model.BaseParamSimpleExample;
import com.ai.bdex.dataexchange.usercenter.dao.model.BaseParamSimpleKey;

public interface BaseParamSimpleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_param_simple
     *
     * @mbg.generated Tue Apr 18 16:26:14 CST 2017
     */
    long countByExample(BaseParamSimpleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_param_simple
     *
     * @mbg.generated Tue Apr 18 16:26:14 CST 2017
     */
    int deleteByExample(BaseParamSimpleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_param_simple
     *
     * @mbg.generated Tue Apr 18 16:26:14 CST 2017
     */
    int deleteByPrimaryKey(BaseParamSimpleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_param_simple
     *
     * @mbg.generated Tue Apr 18 16:26:14 CST 2017
     */
    int insert(BaseParamSimple record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_param_simple
     *
     * @mbg.generated Tue Apr 18 16:26:14 CST 2017
     */
    int insertSelective(BaseParamSimple record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_param_simple
     *
     * @mbg.generated Tue Apr 18 16:26:14 CST 2017
     */
    List<BaseParamSimple> selectByExample(BaseParamSimpleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_param_simple
     *
     * @mbg.generated Tue Apr 18 16:26:14 CST 2017
     */
    BaseParamSimple selectByPrimaryKey(BaseParamSimpleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_param_simple
     *
     * @mbg.generated Tue Apr 18 16:26:14 CST 2017
     */
    int updateByExampleSelective(@Param("record") BaseParamSimple record, @Param("example") BaseParamSimpleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_param_simple
     *
     * @mbg.generated Tue Apr 18 16:26:14 CST 2017
     */
    int updateByExample(@Param("record") BaseParamSimple record, @Param("example") BaseParamSimpleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_param_simple
     *
     * @mbg.generated Tue Apr 18 16:26:14 CST 2017
     */
    int updateByPrimaryKeySelective(BaseParamSimple record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_param_simple
     *
     * @mbg.generated Tue Apr 18 16:26:14 CST 2017
     */
    int updateByPrimaryKey(BaseParamSimple record);
}