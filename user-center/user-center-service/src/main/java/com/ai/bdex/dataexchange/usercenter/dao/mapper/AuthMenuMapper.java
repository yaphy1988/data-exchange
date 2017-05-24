package com.ai.bdex.dataexchange.usercenter.dao.mapper;

import com.ai.bdex.dataexchange.usercenter.dao.model.AuthMenu;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_menu
     *
     * @mbg.generated Mon May 22 10:25:40 CST 2017
     */
    long countByExample(AuthMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_menu
     *
     * @mbg.generated Mon May 22 10:25:40 CST 2017
     */
    int deleteByExample(AuthMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_menu
     *
     * @mbg.generated Mon May 22 10:25:40 CST 2017
     */
    int deleteByPrimaryKey(Integer menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_menu
     *
     * @mbg.generated Mon May 22 10:25:40 CST 2017
     */
    int insert(AuthMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_menu
     *
     * @mbg.generated Mon May 22 10:25:40 CST 2017
     */
    int insertSelective(AuthMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_menu
     *
     * @mbg.generated Mon May 22 10:25:40 CST 2017
     */
    List<AuthMenu> selectByExample(AuthMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_menu
     *
     * @mbg.generated Mon May 22 10:25:40 CST 2017
     */
    AuthMenu selectByPrimaryKey(Integer menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_menu
     *
     * @mbg.generated Mon May 22 10:25:40 CST 2017
     */
    int updateByExampleSelective(@Param("record") AuthMenu record, @Param("example") AuthMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_menu
     *
     * @mbg.generated Mon May 22 10:25:40 CST 2017
     */
    int updateByExample(@Param("record") AuthMenu record, @Param("example") AuthMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_menu
     *
     * @mbg.generated Mon May 22 10:25:40 CST 2017
     */
    int updateByPrimaryKeySelective(AuthMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_menu
     *
     * @mbg.generated Mon May 22 10:25:40 CST 2017
     */
    int updateByPrimaryKey(AuthMenu record);
}