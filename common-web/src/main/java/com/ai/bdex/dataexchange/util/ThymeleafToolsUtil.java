package com.ai.bdex.dataexchange.util;

import com.ai.paas.util.SystemConfUtil;

/**
 * Created by xiongqian on 2017/5/5.
 */
public class ThymeleafToolsUtil {
    /**
     * 获取商城全路径
     * 使用：<a th:href="${#tools.mailDomain()}"></a>
     * @return http://domain/mall-web
     */
    public String mailDomain(){
        return SystemConfUtil.getSystemModuleInfo("01","1").genFullUrl();
    }

    /**
     * 获取管理后台全路径
     * 使用：<a th:href="${#tools.managerDomain()}"></a>
     * @return http://domain/manager-web
     */
    public String managerDomain(){
        return SystemConfUtil.getSystemModuleInfo("02","1").genFullUrl();
    }
}
