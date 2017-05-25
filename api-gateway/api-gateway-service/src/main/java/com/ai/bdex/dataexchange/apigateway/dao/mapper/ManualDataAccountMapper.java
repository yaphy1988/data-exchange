package com.ai.bdex.dataexchange.apigateway.dao.mapper;

import com.ai.bdex.dataexchange.apigateway.dao.model.DataAccount;
import org.apache.ibatis.annotations.Param;

/**
 * Created by fangyunfeng on 2017/5/5.
 */
public interface ManualDataAccountMapper {

    /**
     * 在原有账户上面变更账户总额和余额
     * total_money = total_money + #{totalMoney,jdbcType=INTEGER},
     * left_money = left_money + #{totalMoney,jdbcType=INTEGER},
     * @param record
     * @return
     */
    int updateTotalMoney(@Param("dataAccount") DataAccount record, @Param("increaseMoney") Integer increaseMoney);

    /**
     * 对数据账户扣减余额
     * @param record
     * @param consumeMoney
     * @return
     */
    int updateByConsumeMoney(@Param("dataAccount") DataAccount record, @Param("consumeMoney") Integer consumeMoney);

    /**
     * 对数据账户扣减剩余次数
     * @param record
     * @param consumeNum
     * @return
     */
    int updateByConsumeNum(@Param("dataAccount") DataAccount record, @Param("consumeNum") Integer consumeNum);
}
