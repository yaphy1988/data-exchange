package com.ai.bdex.dataexchange.apigateway.dao.mapper;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaCfg;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AipParaCfgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_para_cfg
     *
     * @mbg.generated Wed May 24 09:28:23 CST 2017
     */
    long countByExample(AipParaCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_para_cfg
     *
     * @mbg.generated Wed May 24 09:28:23 CST 2017
     */
    int deleteByExample(AipParaCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_para_cfg
     *
     * @mbg.generated Wed May 24 09:28:23 CST 2017
     */
    int deleteByPrimaryKey(String paraCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_para_cfg
     *
     * @mbg.generated Wed May 24 09:28:23 CST 2017
     */
    int insert(AipParaCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_para_cfg
     *
     * @mbg.generated Wed May 24 09:28:23 CST 2017
     */
    int insertSelective(AipParaCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_para_cfg
     *
     * @mbg.generated Wed May 24 09:28:23 CST 2017
     */
    List<AipParaCfg> selectByExample(AipParaCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_para_cfg
     *
     * @mbg.generated Wed May 24 09:28:23 CST 2017
     */
    AipParaCfg selectByPrimaryKey(String paraCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_para_cfg
     *
     * @mbg.generated Wed May 24 09:28:23 CST 2017
     */
    int updateByExampleSelective(@Param("record") AipParaCfg record, @Param("example") AipParaCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_para_cfg
     *
     * @mbg.generated Wed May 24 09:28:23 CST 2017
     */
    int updateByExample(@Param("record") AipParaCfg record, @Param("example") AipParaCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_para_cfg
     *
     * @mbg.generated Wed May 24 09:28:23 CST 2017
     */
    int updateByPrimaryKeySelective(AipParaCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_para_cfg
     *
     * @mbg.generated Wed May 24 09:28:23 CST 2017
     */
    int updateByPrimaryKey(AipParaCfg record);
}