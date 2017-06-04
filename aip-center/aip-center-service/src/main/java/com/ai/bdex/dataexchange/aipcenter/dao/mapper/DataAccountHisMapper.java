package com.ai.bdex.dataexchange.aipcenter.dao.mapper;

import com.ai.bdex.dataexchange.aipcenter.dao.model.DataAccountHis;
import com.ai.bdex.dataexchange.aipcenter.dao.model.DataAccountHisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataAccountHisMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account_his
     *
     * @mbg.generated Sun Jun 04 22:05:08 CST 2017
     */
    long countByExample(DataAccountHisExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account_his
     *
     * @mbg.generated Sun Jun 04 22:05:08 CST 2017
     */
    int deleteByExample(DataAccountHisExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account_his
     *
     * @mbg.generated Sun Jun 04 22:05:08 CST 2017
     */
    int deleteByPrimaryKey(Long hisId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account_his
     *
     * @mbg.generated Sun Jun 04 22:05:08 CST 2017
     */
    int insert(DataAccountHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account_his
     *
     * @mbg.generated Sun Jun 04 22:05:08 CST 2017
     */
    int insertSelective(DataAccountHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account_his
     *
     * @mbg.generated Sun Jun 04 22:05:08 CST 2017
     */
    List<DataAccountHis> selectByExample(DataAccountHisExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account_his
     *
     * @mbg.generated Sun Jun 04 22:05:08 CST 2017
     */
    DataAccountHis selectByPrimaryKey(Long hisId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account_his
     *
     * @mbg.generated Sun Jun 04 22:05:08 CST 2017
     */
    int updateByExampleSelective(@Param("record") DataAccountHis record, @Param("example") DataAccountHisExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account_his
     *
     * @mbg.generated Sun Jun 04 22:05:08 CST 2017
     */
    int updateByExample(@Param("record") DataAccountHis record, @Param("example") DataAccountHisExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account_his
     *
     * @mbg.generated Sun Jun 04 22:05:08 CST 2017
     */
    int updateByPrimaryKeySelective(DataAccountHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account_his
     *
     * @mbg.generated Sun Jun 04 22:05:08 CST 2017
     */
    int updateByPrimaryKey(DataAccountHis record);
}