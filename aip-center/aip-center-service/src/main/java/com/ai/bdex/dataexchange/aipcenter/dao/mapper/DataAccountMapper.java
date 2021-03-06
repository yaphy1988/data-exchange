package com.ai.bdex.dataexchange.aipcenter.dao.mapper;

import com.ai.bdex.dataexchange.aipcenter.dao.model.DataAccount;
import com.ai.bdex.dataexchange.aipcenter.dao.model.DataAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account
     *
     * @mbg.generated Fri May 05 16:17:02 CST 2017
     */
    long countByExample(DataAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account
     *
     * @mbg.generated Fri May 05 16:17:02 CST 2017
     */
    int deleteByExample(DataAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account
     *
     * @mbg.generated Fri May 05 16:17:02 CST 2017
     */
    int deleteByPrimaryKey(Long dataAcctId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account
     *
     * @mbg.generated Fri May 05 16:17:02 CST 2017
     */
    int insert(DataAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account
     *
     * @mbg.generated Fri May 05 16:17:02 CST 2017
     */
    int insertSelective(DataAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account
     *
     * @mbg.generated Fri May 05 16:17:02 CST 2017
     */
    List<DataAccount> selectByExample(DataAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account
     *
     * @mbg.generated Fri May 05 16:17:02 CST 2017
     */
    DataAccount selectByPrimaryKey(Long dataAcctId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account
     *
     * @mbg.generated Fri May 05 16:17:02 CST 2017
     */
    int updateByExampleSelective(@Param("record") DataAccount record, @Param("example") DataAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account
     *
     * @mbg.generated Fri May 05 16:17:02 CST 2017
     */
    int updateByExample(@Param("record") DataAccount record, @Param("example") DataAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account
     *
     * @mbg.generated Fri May 05 16:17:02 CST 2017
     */
    int updateByPrimaryKeySelective(DataAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_account
     *
     * @mbg.generated Fri May 05 16:17:02 CST 2017
     */
    int updateByPrimaryKey(DataAccount record);
}