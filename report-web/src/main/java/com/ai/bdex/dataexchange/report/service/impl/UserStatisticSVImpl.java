package com.ai.bdex.dataexchange.report.service.impl;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.report.dao.mapper.AuthStaffSignMapper;
import com.ai.bdex.dataexchange.report.dao.model.AuthStaffSign;
import com.ai.bdex.dataexchange.report.dao.model.AuthStaffSignExample;
import com.ai.bdex.dataexchange.report.entity.UserStatisticQueryInfo;
import com.ai.bdex.dataexchange.report.service.interfaces.IUserStatisticSV;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yaphy on 2017/5/24.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service("userStatisticSV")
public class UserStatisticSVImpl implements IUserStatisticSV {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthStaffSignMapper authStaffSignMapper;

    @Override
    public PageInfo<AuthStaffSign> queryAuthStaffSignPageInfo(UserStatisticQueryInfo queryInfo) throws BusinessException {
        //分页信息赋值
        int page = queryInfo.getPageNo();
        int rows = queryInfo.getPageSize();
        //查询条件赋值
        AuthStaffSignExample example = new AuthStaffSignExample();
        AuthStaffSignExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(queryInfo.getGridQuerySortName() +" "+queryInfo.getGridQuerySortOrder());
        criteria.andCreateTimeBetween(queryInfo.getStartTime(),queryInfo.getEndTime());
        //开启分页查询，使用mybatis-PageHelper分页插件，第一个参数 PageNo，第二个参数PageSize，该设置只对紧随其后的第一次查询有效
        PageHelper.startPage(page, rows);
        //执行查询第一个mybatis查询方法，会被进行分页
        List<AuthStaffSign> lists = authStaffSignMapper.selectByExample(example);
        //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(lists);
        logger.info("IDemoInfoSV查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + lists.size());
        return pageInfo;
    }
}
