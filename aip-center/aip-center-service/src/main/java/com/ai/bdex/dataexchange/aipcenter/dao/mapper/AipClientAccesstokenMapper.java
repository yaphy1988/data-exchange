package com.ai.bdex.dataexchange.aipcenter.dao.mapper;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientAccesstoken;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientAccesstokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AipClientAccesstokenMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_accesstoken
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    long countByExample(AipClientAccesstokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_accesstoken
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int deleteByExample(AipClientAccesstokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_accesstoken
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int deleteByPrimaryKey(String accessToken);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_accesstoken
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int insert(AipClientAccesstoken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_accesstoken
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int insertSelective(AipClientAccesstoken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_accesstoken
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    List<AipClientAccesstoken> selectByExample(AipClientAccesstokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_accesstoken
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    AipClientAccesstoken selectByPrimaryKey(String accessToken);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_accesstoken
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByExampleSelective(@Param("record") AipClientAccesstoken record, @Param("example") AipClientAccesstokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_accesstoken
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByExample(@Param("record") AipClientAccesstoken record, @Param("example") AipClientAccesstokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_accesstoken
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByPrimaryKeySelective(AipClientAccesstoken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_accesstoken
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    int updateByPrimaryKey(AipClientAccesstoken record);
}