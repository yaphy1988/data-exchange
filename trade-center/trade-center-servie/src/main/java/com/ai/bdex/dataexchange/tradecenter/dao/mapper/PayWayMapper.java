package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PayWay;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayWayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayWayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_way
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    long countByExample(PayWayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_way
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int deleteByExample(PayWayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_way
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_way
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int insert(PayWay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_way
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int insertSelective(PayWay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_way
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    List<PayWay> selectByExample(PayWayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_way
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    PayWay selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_way
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExampleSelective(@Param("record") PayWay record, @Param("example") PayWayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_way
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExample(@Param("record") PayWay record, @Param("example") PayWayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_way
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKeySelective(PayWay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_way
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKey(PayWay record);
}