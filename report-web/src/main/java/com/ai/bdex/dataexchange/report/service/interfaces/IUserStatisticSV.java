package com.ai.bdex.dataexchange.report.service.interfaces;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.report.dao.model.AuthStaffSign;
import com.ai.bdex.dataexchange.report.entity.UserStatisticQueryInfo;
import com.github.pagehelper.PageInfo;

/**
 * Created by yaphy on 2017/5/24.
 */
public interface IUserStatisticSV {

    /**
     * 从数据库分页查询数据demo
     * @param queryInfo
     * @return
     * @throws BusinessException
     */
    public PageInfo<AuthStaffSign> queryAuthStaffSignPageInfo(UserStatisticQueryInfo queryInfo) throws BusinessException;
}
