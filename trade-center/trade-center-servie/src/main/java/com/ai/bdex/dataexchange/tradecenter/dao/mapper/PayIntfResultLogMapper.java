package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PayIntfResultLog;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayIntfResultLogExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayIntfResultLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayIntfResultLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    long countByExample(PayIntfResultLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int deleteByExample(PayIntfResultLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int insert(PayIntfResultLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int insertSelective(PayIntfResultLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    List<PayIntfResultLogWithBLOBs> selectByExampleWithBLOBs(PayIntfResultLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    List<PayIntfResultLog> selectByExample(PayIntfResultLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    PayIntfResultLogWithBLOBs selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExampleSelective(@Param("record") PayIntfResultLogWithBLOBs record, @Param("example") PayIntfResultLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") PayIntfResultLogWithBLOBs record, @Param("example") PayIntfResultLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExample(@Param("record") PayIntfResultLog record, @Param("example") PayIntfResultLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKeySelective(PayIntfResultLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKeyWithBLOBs(PayIntfResultLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_result_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKey(PayIntfResultLog record);
}