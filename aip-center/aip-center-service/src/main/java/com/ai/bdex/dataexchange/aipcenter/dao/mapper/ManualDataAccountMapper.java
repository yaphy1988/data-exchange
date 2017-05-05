package com.ai.bdex.dataexchange.aipcenter.dao.mapper;

import com.ai.bdex.dataexchange.aipcenter.dao.model.DataAccount;
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
    int updateTotalMoney(@Param("dataAccount") DataAccount record,@Param("increaseMoney") Integer increaseMoney);
}
