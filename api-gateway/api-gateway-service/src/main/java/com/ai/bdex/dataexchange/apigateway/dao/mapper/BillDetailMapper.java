package com.ai.bdex.dataexchange.apigateway.dao.mapper;

import com.ai.bdex.dataexchange.apigateway.dao.model.BillDetail;
import com.ai.bdex.dataexchange.apigateway.dao.model.BillDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BillDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 11:44:36 CST 2017
     */
    long countByExample(BillDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 11:44:36 CST 2017
     */
    int deleteByExample(BillDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 11:44:36 CST 2017
     */
    int deleteByPrimaryKey(String billId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 11:44:36 CST 2017
     */
    int insert(BillDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 11:44:36 CST 2017
     */
    int insertSelective(BillDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 11:44:36 CST 2017
     */
    List<BillDetail> selectByExample(BillDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 11:44:36 CST 2017
     */
    BillDetail selectByPrimaryKey(String billId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 11:44:36 CST 2017
     */
    int updateByExampleSelective(@Param("record") BillDetail record, @Param("example") BillDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 11:44:36 CST 2017
     */
    int updateByExample(@Param("record") BillDetail record, @Param("example") BillDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 11:44:36 CST 2017
     */
    int updateByPrimaryKeySelective(BillDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 11:44:36 CST 2017
     */
    int updateByPrimaryKey(BillDetail record);
}