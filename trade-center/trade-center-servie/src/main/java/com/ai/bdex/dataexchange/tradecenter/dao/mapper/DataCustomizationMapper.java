package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.DataCustomization;
import com.ai.bdex.dataexchange.tradecenter.dao.model.DataCustomizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataCustomizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_customization
     *
     * @mbg.generated Thu Apr 20 12:14:20 CST 2017
     */
    long countByExample(DataCustomizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_customization
     *
     * @mbg.generated Thu Apr 20 12:14:20 CST 2017
     */
    int deleteByExample(DataCustomizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_customization
     *
     * @mbg.generated Thu Apr 20 12:14:20 CST 2017
     */
    int deleteByPrimaryKey(Integer dczaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_customization
     *
     * @mbg.generated Thu Apr 20 12:14:20 CST 2017
     */
    int insert(DataCustomization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_customization
     *
     * @mbg.generated Thu Apr 20 12:14:20 CST 2017
     */
    int insertSelective(DataCustomization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_customization
     *
     * @mbg.generated Thu Apr 20 12:14:20 CST 2017
     */
    List<DataCustomization> selectByExample(DataCustomizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_customization
     *
     * @mbg.generated Thu Apr 20 12:14:20 CST 2017
     */
    DataCustomization selectByPrimaryKey(Integer dczaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_customization
     *
     * @mbg.generated Thu Apr 20 12:14:20 CST 2017
     */
    int updateByExampleSelective(@Param("record") DataCustomization record, @Param("example") DataCustomizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_customization
     *
     * @mbg.generated Thu Apr 20 12:14:20 CST 2017
     */
    int updateByExample(@Param("record") DataCustomization record, @Param("example") DataCustomizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_customization
     *
     * @mbg.generated Thu Apr 20 12:14:20 CST 2017
     */
    int updateByPrimaryKeySelective(DataCustomization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_customization
     *
     * @mbg.generated Thu Apr 20 12:14:20 CST 2017
     */
    int updateByPrimaryKey(DataCustomization record);
}