package com.ai.bdex.dataexchange.apigateway.dao.mapper;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipClientRefreshtoken;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipClientRefreshtokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AipClientRefreshtokenMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_refreshtoken
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    long countByExample(AipClientRefreshtokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_refreshtoken
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int deleteByExample(AipClientRefreshtokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_refreshtoken
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int deleteByPrimaryKey(String refreshToken);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_refreshtoken
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int insert(AipClientRefreshtoken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_refreshtoken
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int insertSelective(AipClientRefreshtoken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_refreshtoken
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    List<AipClientRefreshtoken> selectByExample(AipClientRefreshtokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_refreshtoken
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    AipClientRefreshtoken selectByPrimaryKey(String refreshToken);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_refreshtoken
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int updateByExampleSelective(@Param("record") AipClientRefreshtoken record, @Param("example") AipClientRefreshtokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_refreshtoken
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int updateByExample(@Param("record") AipClientRefreshtoken record, @Param("example") AipClientRefreshtokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_refreshtoken
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int updateByPrimaryKeySelective(AipClientRefreshtoken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_client_refreshtoken
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    int updateByPrimaryKey(AipClientRefreshtoken record);
}