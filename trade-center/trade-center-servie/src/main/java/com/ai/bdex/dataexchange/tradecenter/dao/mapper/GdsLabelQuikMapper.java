package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelQuik;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelQuikExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GdsLabelQuikMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label_quik
     *
     * @mbg.generated Tue Apr 18 15:07:01 CST 2017
     */
    long countByExample(GdsLabelQuikExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label_quik
     *
     * @mbg.generated Tue Apr 18 15:07:01 CST 2017
     */
    int deleteByExample(GdsLabelQuikExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label_quik
     *
     * @mbg.generated Tue Apr 18 15:07:01 CST 2017
     */
    int deleteByPrimaryKey(Integer labId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label_quik
     *
     * @mbg.generated Tue Apr 18 15:07:01 CST 2017
     */
    int insert(GdsLabelQuik record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label_quik
     *
     * @mbg.generated Tue Apr 18 15:07:01 CST 2017
     */
    int insertSelective(GdsLabelQuik record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label_quik
     *
     * @mbg.generated Tue Apr 18 15:07:01 CST 2017
     */
    List<GdsLabelQuik> selectByExample(GdsLabelQuikExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label_quik
     *
     * @mbg.generated Tue Apr 18 15:07:01 CST 2017
     */
    GdsLabelQuik selectByPrimaryKey(Integer labId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label_quik
     *
     * @mbg.generated Tue Apr 18 15:07:01 CST 2017
     */
    int updateByExampleSelective(@Param("record") GdsLabelQuik record, @Param("example") GdsLabelQuikExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label_quik
     *
     * @mbg.generated Tue Apr 18 15:07:01 CST 2017
     */
    int updateByExample(@Param("record") GdsLabelQuik record, @Param("example") GdsLabelQuikExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label_quik
     *
     * @mbg.generated Tue Apr 18 15:07:01 CST 2017
     */
    int updateByPrimaryKeySelective(GdsLabelQuik record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label_quik
     *
     * @mbg.generated Tue Apr 18 15:07:01 CST 2017
     */
    int updateByPrimaryKey(GdsLabelQuik record);
}