package com.ai.bdex.dataexchange.apigateway.dao.mapper;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsRevLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsRevLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AipSmsRevLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_sms_rev_log
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    long countByExample(AipSmsRevLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_sms_rev_log
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    int deleteByExample(AipSmsRevLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_sms_rev_log
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    int deleteByPrimaryKey(String logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_sms_rev_log
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    int insert(AipSmsRevLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_sms_rev_log
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    int insertSelective(AipSmsRevLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_sms_rev_log
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    List<AipSmsRevLog> selectByExample(AipSmsRevLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_sms_rev_log
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    AipSmsRevLog selectByPrimaryKey(String logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_sms_rev_log
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    int updateByExampleSelective(@Param("record") AipSmsRevLog record, @Param("example") AipSmsRevLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_sms_rev_log
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    int updateByExample(@Param("record") AipSmsRevLog record, @Param("example") AipSmsRevLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_sms_rev_log
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    int updateByPrimaryKeySelective(AipSmsRevLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_sms_rev_log
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    int updateByPrimaryKey(AipSmsRevLog record);
}