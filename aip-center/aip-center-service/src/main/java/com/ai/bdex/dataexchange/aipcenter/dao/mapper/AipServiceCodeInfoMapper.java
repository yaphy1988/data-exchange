package com.ai.bdex.dataexchange.aipcenter.dao.mapper;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceCodeInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceCodeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AipServiceCodeInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_code_info
     *
     * @mbg.generated Thu Apr 20 15:38:06 CST 2017
     */
    long countByExample(AipServiceCodeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_code_info
     *
     * @mbg.generated Thu Apr 20 15:38:06 CST 2017
     */
    int deleteByExample(AipServiceCodeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_code_info
     *
     * @mbg.generated Thu Apr 20 15:38:06 CST 2017
     */
    int deleteByPrimaryKey(String codeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_code_info
     *
     * @mbg.generated Thu Apr 20 15:38:06 CST 2017
     */
    int insert(AipServiceCodeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_code_info
     *
     * @mbg.generated Thu Apr 20 15:38:06 CST 2017
     */
    int insertSelective(AipServiceCodeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_code_info
     *
     * @mbg.generated Thu Apr 20 15:38:06 CST 2017
     */
    List<AipServiceCodeInfo> selectByExample(AipServiceCodeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_code_info
     *
     * @mbg.generated Thu Apr 20 15:38:06 CST 2017
     */
    AipServiceCodeInfo selectByPrimaryKey(String codeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_code_info
     *
     * @mbg.generated Thu Apr 20 15:38:06 CST 2017
     */
    int updateByExampleSelective(@Param("record") AipServiceCodeInfo record, @Param("example") AipServiceCodeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_code_info
     *
     * @mbg.generated Thu Apr 20 15:38:06 CST 2017
     */
    int updateByExample(@Param("record") AipServiceCodeInfo record, @Param("example") AipServiceCodeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_code_info
     *
     * @mbg.generated Thu Apr 20 15:38:06 CST 2017
     */
    int updateByPrimaryKeySelective(AipServiceCodeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_code_info
     *
     * @mbg.generated Thu Apr 20 15:38:06 CST 2017
     */
    int updateByPrimaryKey(AipServiceCodeInfo record);
}