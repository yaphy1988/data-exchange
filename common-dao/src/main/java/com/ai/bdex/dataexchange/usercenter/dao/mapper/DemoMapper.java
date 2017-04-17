package com.ai.bdex.dataexchange.usercenter.dao.mapper;

import com.ai.bdex.dataexchange.usercenter.dao.model.Demo;
import com.ai.bdex.dataexchange.usercenter.dao.model.DemoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DemoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_demo
     *
     * @mbg.generated Mon Apr 17 12:40:15 CST 2017
     */
    long countByExample(DemoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_demo
     *
     * @mbg.generated Mon Apr 17 12:40:15 CST 2017
     */
    int deleteByExample(DemoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_demo
     *
     * @mbg.generated Mon Apr 17 12:40:15 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_demo
     *
     * @mbg.generated Mon Apr 17 12:40:15 CST 2017
     */
    int insert(Demo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_demo
     *
     * @mbg.generated Mon Apr 17 12:40:15 CST 2017
     */
    int insertSelective(Demo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_demo
     *
     * @mbg.generated Mon Apr 17 12:40:15 CST 2017
     */
    List<Demo> selectByExample(DemoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_demo
     *
     * @mbg.generated Mon Apr 17 12:40:15 CST 2017
     */
    Demo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_demo
     *
     * @mbg.generated Mon Apr 17 12:40:15 CST 2017
     */
    int updateByExampleSelective(@Param("record") Demo record, @Param("example") DemoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_demo
     *
     * @mbg.generated Mon Apr 17 12:40:15 CST 2017
     */
    int updateByExample(@Param("record") Demo record, @Param("example") DemoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_demo
     *
     * @mbg.generated Mon Apr 17 12:40:15 CST 2017
     */
    int updateByPrimaryKeySelective(Demo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_demo
     *
     * @mbg.generated Mon Apr 17 12:40:15 CST 2017
     */
    int updateByPrimaryKey(Demo record);
}