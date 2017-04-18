package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PayIntfReqLog;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayIntfReqLogExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayIntfReqLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayIntfReqLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    long countByExample(PayIntfReqLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int deleteByExample(PayIntfReqLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int insert(PayIntfReqLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int insertSelective(PayIntfReqLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    List<PayIntfReqLogWithBLOBs> selectByExampleWithBLOBs(PayIntfReqLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    List<PayIntfReqLog> selectByExample(PayIntfReqLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    PayIntfReqLogWithBLOBs selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExampleSelective(@Param("record") PayIntfReqLogWithBLOBs record, @Param("example") PayIntfReqLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") PayIntfReqLogWithBLOBs record, @Param("example") PayIntfReqLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExample(@Param("record") PayIntfReqLog record, @Param("example") PayIntfReqLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKeySelective(PayIntfReqLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKeyWithBLOBs(PayIntfReqLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_intf_req_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKey(PayIntfReqLog record);
}