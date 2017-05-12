package com.ai.bdex.dataexchange.tradecenter.dao.mapper;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageAdPalce;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageAdPalceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageAdPalceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_ad_palce
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    long countByExample(PageAdPalceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_ad_palce
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int deleteByExample(PageAdPalceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_ad_palce
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int deleteByPrimaryKey(Integer placeAd);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_ad_palce
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int insert(PageAdPalce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_ad_palce
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int insertSelective(PageAdPalce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_ad_palce
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    List<PageAdPalce> selectByExample(PageAdPalceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_ad_palce
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    PageAdPalce selectByPrimaryKey(Integer placeAd);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_ad_palce
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int updateByExampleSelective(@Param("record") PageAdPalce record, @Param("example") PageAdPalceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_ad_palce
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int updateByExample(@Param("record") PageAdPalce record, @Param("example") PageAdPalceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_ad_palce
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int updateByPrimaryKeySelective(PageAdPalce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_ad_palce
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    int updateByPrimaryKey(PageAdPalce record);
}