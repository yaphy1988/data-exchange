package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsProp;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsPropExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GdsPropMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_prop
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    long countByExample(GdsPropExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_prop
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    int deleteByExample(GdsPropExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_prop
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    int deleteByPrimaryKey(Integer proId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_prop
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    int insert(GdsProp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_prop
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    int insertSelective(GdsProp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_prop
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    List<GdsProp> selectByExample(GdsPropExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_prop
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    GdsProp selectByPrimaryKey(Integer proId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_prop
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    int updateByExampleSelective(@Param("record") GdsProp record, @Param("example") GdsPropExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_prop
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    int updateByExample(@Param("record") GdsProp record, @Param("example") GdsPropExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_prop
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    int updateByPrimaryKeySelective(GdsProp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_prop
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    int updateByPrimaryKey(GdsProp record);
}