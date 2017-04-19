package com.ai.bdex.dataexchange.usercenter.dao.mapper;

import com.ai.bdex.dataexchange.usercenter.dao.model.AuthStaff;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthStaffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthStaffMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff
     *
     * @mbg.generated Wed Apr 19 15:02:03 CST 2017
     */
    long countByExample(AuthStaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff
     *
     * @mbg.generated Wed Apr 19 15:02:03 CST 2017
     */
    int deleteByExample(AuthStaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff
     *
     * @mbg.generated Wed Apr 19 15:02:03 CST 2017
     */
    int deleteByPrimaryKey(String staffId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff
     *
     * @mbg.generated Wed Apr 19 15:02:03 CST 2017
     */
    int insert(AuthStaff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff
     *
     * @mbg.generated Wed Apr 19 15:02:03 CST 2017
     */
    int insertSelective(AuthStaff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff
     *
     * @mbg.generated Wed Apr 19 15:02:03 CST 2017
     */
    List<AuthStaff> selectByExample(AuthStaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff
     *
     * @mbg.generated Wed Apr 19 15:02:03 CST 2017
     */
    AuthStaff selectByPrimaryKey(String staffId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff
     *
     * @mbg.generated Wed Apr 19 15:02:03 CST 2017
     */
    int updateByExampleSelective(@Param("record") AuthStaff record, @Param("example") AuthStaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff
     *
     * @mbg.generated Wed Apr 19 15:02:03 CST 2017
     */
    int updateByExample(@Param("record") AuthStaff record, @Param("example") AuthStaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff
     *
     * @mbg.generated Wed Apr 19 15:02:03 CST 2017
     */
    int updateByPrimaryKeySelective(AuthStaff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff
     *
     * @mbg.generated Wed Apr 19 15:02:03 CST 2017
     */
    int updateByPrimaryKey(AuthStaff record);
}