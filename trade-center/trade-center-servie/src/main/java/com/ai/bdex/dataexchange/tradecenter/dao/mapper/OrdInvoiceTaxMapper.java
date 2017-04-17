package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInvoiceTax;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInvoiceTaxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdInvoiceTaxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_invoice_tax
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    long countByExample(OrdInvoiceTaxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_invoice_tax
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int deleteByExample(OrdInvoiceTaxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_invoice_tax
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int deleteByPrimaryKey(Long orderTaxId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_invoice_tax
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int insert(OrdInvoiceTax record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_invoice_tax
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int insertSelective(OrdInvoiceTax record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_invoice_tax
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    List<OrdInvoiceTax> selectByExample(OrdInvoiceTaxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_invoice_tax
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    OrdInvoiceTax selectByPrimaryKey(Long orderTaxId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_invoice_tax
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int updateByExampleSelective(@Param("record") OrdInvoiceTax record, @Param("example") OrdInvoiceTaxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_invoice_tax
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int updateByExample(@Param("record") OrdInvoiceTax record, @Param("example") OrdInvoiceTaxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_invoice_tax
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int updateByPrimaryKeySelective(OrdInvoiceTax record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_invoice_tax
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int updateByPrimaryKey(OrdInvoiceTax record);
}