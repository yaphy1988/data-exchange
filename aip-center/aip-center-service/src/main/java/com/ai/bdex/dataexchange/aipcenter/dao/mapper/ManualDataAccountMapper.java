package com.ai.bdex.dataexchange.aipcenter.dao.mapper;

import com.ai.bdex.dataexchange.aipcenter.dao.model.DataAccount;
import com.ai.bdex.dataexchange.aipcenter.dao.model.DataAccountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 统计用户不同接口的剩余可用次数，剩余可用金额
     * @param example
     * @return
     */
    List<DataAccount> queryStatisticByExample(DataAccountExample example);
}
