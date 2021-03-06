package com.ai.bdex.dataexchange.aipcenter.dao.mapper;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipPServiceOutPara;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipPServiceOutParaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AipPServiceOutParaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_p_service_out_para
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    long countByExample(AipPServiceOutParaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_p_service_out_para
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int deleteByExample(AipPServiceOutParaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_p_service_out_para
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int deleteByPrimaryKey(String outputId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_p_service_out_para
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int insert(AipPServiceOutPara record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_p_service_out_para
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int insertSelective(AipPServiceOutPara record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_p_service_out_para
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    List<AipPServiceOutPara> selectByExample(AipPServiceOutParaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_p_service_out_para
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    AipPServiceOutPara selectByPrimaryKey(String outputId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_p_service_out_para
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByExampleSelective(@Param("record") AipPServiceOutPara record, @Param("example") AipPServiceOutParaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_p_service_out_para
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByExample(@Param("record") AipPServiceOutPara record, @Param("example") AipPServiceOutParaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_p_service_out_para
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByPrimaryKeySelective(AipPServiceOutPara record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_p_service_out_para
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByPrimaryKey(AipPServiceOutPara record);
}