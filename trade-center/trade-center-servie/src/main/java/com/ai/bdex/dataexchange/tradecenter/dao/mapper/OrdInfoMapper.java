package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    long countByExample(OrdInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int deleteByExample(OrdInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int deleteByPrimaryKey(OrdInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int insert(OrdInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int insertSelective(OrdInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    List<OrdInfo> selectByExample(OrdInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    OrdInfo selectByPrimaryKey(OrdInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int updateByExampleSelective(@Param("record") OrdInfo record, @Param("example") OrdInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int updateByExample(@Param("record") OrdInfo record, @Param("example") OrdInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int updateByPrimaryKeySelective(OrdInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_info
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int updateByPrimaryKey(OrdInfo record);
}