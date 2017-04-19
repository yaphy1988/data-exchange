package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModule;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageModuleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    long countByExample(PageModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    int deleteByExample(PageModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    int deleteByPrimaryKey(Integer moduleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    int insert(PageModule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    int insertSelective(PageModule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    List<PageModule> selectByExample(PageModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    PageModule selectByPrimaryKey(Integer moduleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    int updateByExampleSelective(@Param("record") PageModule record, @Param("example") PageModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    int updateByExample(@Param("record") PageModule record, @Param("example") PageModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module
     *
     * @mbg.generated Tue Apr 18 20:28:29 CST 2017
     */
    int updateByPrimaryKeySelective(PageModule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module
     *
     * @mbg.generated Tue Apr 18 20:28:29 CST 2017
     */
    int updateByPrimaryKey(PageModule record);
}