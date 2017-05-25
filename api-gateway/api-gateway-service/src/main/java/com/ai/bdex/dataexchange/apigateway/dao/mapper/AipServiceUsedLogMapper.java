package com.ai.bdex.dataexchange.apigateway.dao.mapper;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceUsedLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AipServiceUsedLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    long countByExample(AipServiceUsedLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int deleteByExample(AipServiceUsedLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int deleteByPrimaryKey(String usedId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int insert(AipServiceUsedLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int insertSelective(AipServiceUsedLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    List<AipServiceUsedLog> selectByExample(AipServiceUsedLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    AipServiceUsedLog selectByPrimaryKey(String usedId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int updateByExampleSelective(@Param("record") AipServiceUsedLog record, @Param("example") AipServiceUsedLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int updateByExample(@Param("record") AipServiceUsedLog record, @Param("example") AipServiceUsedLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int updateByPrimaryKeySelective(AipServiceUsedLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int updateByPrimaryKey(AipServiceUsedLog record);
}