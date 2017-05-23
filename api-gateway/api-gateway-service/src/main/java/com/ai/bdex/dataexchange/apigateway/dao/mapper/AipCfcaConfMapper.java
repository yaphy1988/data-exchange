package com.ai.bdex.dataexchange.apigateway.dao.mapper;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipCfcaConf;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipCfcaConfExample;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipCfcaConfKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AipCfcaConfMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_cfca_conf
     *
     * @mbg.generated Mon May 22 19:53:00 CST 2017
     */
    long countByExample(AipCfcaConfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_cfca_conf
     *
     * @mbg.generated Mon May 22 19:53:00 CST 2017
     */
    int deleteByExample(AipCfcaConfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_cfca_conf
     *
     * @mbg.generated Mon May 22 19:53:00 CST 2017
     */
    int deleteByPrimaryKey(AipCfcaConfKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_cfca_conf
     *
     * @mbg.generated Mon May 22 19:53:00 CST 2017
     */
    int insert(AipCfcaConf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_cfca_conf
     *
     * @mbg.generated Mon May 22 19:53:00 CST 2017
     */
    int insertSelective(AipCfcaConf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_cfca_conf
     *
     * @mbg.generated Mon May 22 19:53:00 CST 2017
     */
    List<AipCfcaConf> selectByExample(AipCfcaConfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_cfca_conf
     *
     * @mbg.generated Mon May 22 19:53:00 CST 2017
     */
    AipCfcaConf selectByPrimaryKey(AipCfcaConfKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_cfca_conf
     *
     * @mbg.generated Mon May 22 19:53:00 CST 2017
     */
    int updateByExampleSelective(@Param("record") AipCfcaConf record, @Param("example") AipCfcaConfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_cfca_conf
     *
     * @mbg.generated Mon May 22 19:53:00 CST 2017
     */
    int updateByExample(@Param("record") AipCfcaConf record, @Param("example") AipCfcaConfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_cfca_conf
     *
     * @mbg.generated Mon May 22 19:53:00 CST 2017
     */
    int updateByPrimaryKeySelective(AipCfcaConf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_cfca_conf
     *
     * @mbg.generated Mon May 22 19:53:00 CST 2017
     */
    int updateByPrimaryKey(AipCfcaConf record);
}