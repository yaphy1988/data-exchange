package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PayShopCfgLog;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayShopCfgLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopCfgLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_shop_cfg_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    long countByExample(PayShopCfgLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_shop_cfg_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int deleteByExample(PayShopCfgLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_shop_cfg_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_shop_cfg_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int insert(PayShopCfgLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_shop_cfg_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int insertSelective(PayShopCfgLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_shop_cfg_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    List<PayShopCfgLog> selectByExample(PayShopCfgLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_shop_cfg_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    PayShopCfgLog selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_shop_cfg_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExampleSelective(@Param("record") PayShopCfgLog record, @Param("example") PayShopCfgLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_shop_cfg_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByExample(@Param("record") PayShopCfgLog record, @Param("example") PayShopCfgLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_shop_cfg_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKeySelective(PayShopCfgLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_shop_cfg_log
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    int updateByPrimaryKey(PayShopCfgLog record);
}