package com.ai.bdex.dataexchange.aipcenter.dao.mapper;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfoExample;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AipServiceInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_info
     *
     * @mbg.generated Wed Apr 26 16:22:34 CST 2017
     */
    long countByExample(AipServiceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_info
     *
     * @mbg.generated Wed Apr 26 16:22:34 CST 2017
     */
    int deleteByExample(AipServiceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_info
     *
     * @mbg.generated Wed Apr 26 16:22:34 CST 2017
     */
    int deleteByPrimaryKey(AipServiceInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_info
     *
     * @mbg.generated Wed Apr 26 16:22:34 CST 2017
     */
    int insert(AipServiceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_info
     *
     * @mbg.generated Wed Apr 26 16:22:34 CST 2017
     */
    int insertSelective(AipServiceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_info
     *
     * @mbg.generated Wed Apr 26 16:22:34 CST 2017
     */
    List<AipServiceInfo> selectByExample(AipServiceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_info
     *
     * @mbg.generated Wed Apr 26 16:22:34 CST 2017
     */
    AipServiceInfo selectByPrimaryKey(AipServiceInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_info
     *
     * @mbg.generated Wed Apr 26 16:22:34 CST 2017
     */
    int updateByExampleSelective(@Param("record") AipServiceInfo record, @Param("example") AipServiceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_info
     *
     * @mbg.generated Wed Apr 26 16:22:34 CST 2017
     */
    int updateByExample(@Param("record") AipServiceInfo record, @Param("example") AipServiceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_info
     *
     * @mbg.generated Wed Apr 26 16:22:34 CST 2017
     */
    int updateByPrimaryKeySelective(AipServiceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_info
     *
     * @mbg.generated Wed Apr 26 16:22:34 CST 2017
     */
    int updateByPrimaryKey(AipServiceInfo record);
}