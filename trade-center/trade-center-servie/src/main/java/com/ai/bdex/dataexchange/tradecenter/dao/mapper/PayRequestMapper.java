package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PayRequest;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayRequestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayRequestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_request
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    long countByExample(PayRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_request
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int deleteByExample(PayRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_request
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_request
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int insert(PayRequest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_request
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int insertSelective(PayRequest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_request
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    List<PayRequest> selectByExample(PayRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_request
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    PayRequest selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_request
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExampleSelective(@Param("record") PayRequest record, @Param("example") PayRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_request
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExample(@Param("record") PayRequest record, @Param("example") PayRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_request
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKeySelective(PayRequest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_request
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKey(PayRequest record);
}