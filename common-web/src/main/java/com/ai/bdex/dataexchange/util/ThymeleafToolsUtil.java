package com.ai.bdex.dataexchange.util;

import com.ai.paas.util.SystemConfUtil;

/**
 * Created by xiongqian on 2017/5/5.
 */
public class ThymeleafToolsUtil {
    /**
     * 使用：<a th:href="${#tool.mailDomain()}"></a>
     * @return http://domain/mall-web
     */
    public String mailDomain(){
        return SystemConfUtil.getSystemModuleInfo("01","1").genFullUrl();
    }

    /**
     * 使用：<a th:href="${#tool.managerDomain()}"></a>
     * @return http://domain/manager-web
     */
    public String managerDomain(){
        return SystemConfUtil.getSystemModuleInfo("02","1").genFullUrl();
    }
}
