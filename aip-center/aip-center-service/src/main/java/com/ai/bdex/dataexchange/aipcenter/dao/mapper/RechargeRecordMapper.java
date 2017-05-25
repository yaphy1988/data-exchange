package com.ai.bdex.dataexchange.aipcenter.dao.mapper;

import com.ai.bdex.dataexchange.aipcenter.dao.model.RechargeRecord;
import com.ai.bdex.dataexchange.aipcenter.dao.model.RechargeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargeRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_recharge_record
     *
     * @mbg.generated Mon May 15 22:20:45 CST 2017
     */
    long countByExample(RechargeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_recharge_record
     *
     * @mbg.generated Mon May 15 22:20:45 CST 2017
     */
    int deleteByExample(RechargeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_recharge_record
     *
     * @mbg.generated Mon May 15 22:20:45 CST 2017
     */
    int deleteByPrimaryKey(String rechargeReqId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_recharge_record
     *
     * @mbg.generated Mon May 15 22:20:45 CST 2017
     */
    int insert(RechargeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_recharge_record
     *
     * @mbg.generated Mon May 15 22:20:45 CST 2017
     */
    int insertSelective(RechargeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_recharge_record
     *
     * @mbg.generated Mon May 15 22:20:45 CST 2017
     */
    List<RechargeRecord> selectByExample(RechargeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_recharge_record
     *
     * @mbg.generated Mon May 15 22:20:45 CST 2017
     */
    RechargeRecord selectByPrimaryKey(String rechargeReqId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_recharge_record
     *
     * @mbg.generated Mon May 15 22:20:45 CST 2017
     */
    int updateByExampleSelective(@Param("record") RechargeRecord record, @Param("example") RechargeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_recharge_record
     *
     * @mbg.generated Mon May 15 22:20:45 CST 2017
     */
    int updateByExample(@Param("record") RechargeRecord record, @Param("example") RechargeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_recharge_record
     *
     * @mbg.generated Mon May 15 22:20:45 CST 2017
     */
    int updateByPrimaryKeySelective(RechargeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_recharge_record
     *
     * @mbg.generated Mon May 15 22:20:45 CST 2017
     */
    int updateByPrimaryKey(RechargeRecord record);
}