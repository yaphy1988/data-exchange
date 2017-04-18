package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    long countByExample(OrdInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int deleteByExample(OrdInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int deleteByPrimaryKey(String subOrder);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int insert(OrdInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int insertSelective(OrdInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    List<OrdInfo> selectByExample(OrdInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    OrdInfo selectByPrimaryKey(String subOrder);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int updateByExampleSelective(@Param("record") OrdInfo record, @Param("example") OrdInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int updateByExample(@Param("record") OrdInfo record, @Param("example") OrdInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int updateByPrimaryKeySelective(OrdInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    int updateByPrimaryKey(OrdInfo record);
}