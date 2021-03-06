package com.ai.bdex.dataexchange.aipcenter.dao.mapper;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceErrorInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceErrorInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AipServiceErrorInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_error_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    long countByExample(AipServiceErrorInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_error_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int deleteByExample(AipServiceErrorInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_error_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int deleteByPrimaryKey(String errorId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_error_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int insert(AipServiceErrorInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_error_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int insertSelective(AipServiceErrorInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_error_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    List<AipServiceErrorInfo> selectByExample(AipServiceErrorInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_error_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    AipServiceErrorInfo selectByPrimaryKey(String errorId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_error_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByExampleSelective(@Param("record") AipServiceErrorInfo record, @Param("example") AipServiceErrorInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_error_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByExample(@Param("record") AipServiceErrorInfo record, @Param("example") AipServiceErrorInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_error_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByPrimaryKeySelective(AipServiceErrorInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_error_info
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByPrimaryKey(AipServiceErrorInfo record);
}