package com.ai.bdex.dataexchange.util;

import java.math.BigDecimal;

import com.ai.paas.util.ImageUtil;
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
    /**
     * 将金额转换为元显示
     * 使用：<a th:href="${#tools.managerDomain()}"></a>
     * @return http://domain/manager-web
     */
    public String formatMoney(int money){
     /*  	double f = 34.236323;
    	BigDecimal b = new BigDecimal(f);
    	double f1 = b.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();*/
    	
     	double dmoney = money;
     	double dmoneytmp = dmoney/100;
     	BigDecimal b = new BigDecimal(dmoneytmp);
     	double dmoneyback =  b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        return  "￥"+dmoneyback;
    }
    /**
     * 
     * formatMoneyClean:(返回没有元符号). <br/> 
     * 
     * @author gxq 
     * @param money
     * @return 
     * @since JDK 1.6
     */
    public String formatMoneyClean(int money){
        return  BigDecimal.valueOf(Long.valueOf(money)).divide(new BigDecimal(100)).toString();
    }
    /**
     * 
     * genImageUrl:(根据图片id和分辨率获取图片路径). <br/> 
     * 
     * @author gxq 
     * @param picId
     * @param size
     * @return 
     * @since JDK 1.6
     */
    public String genImageUrl(String picId,String size){
        String picUrl = "";
        if(StringUtil.isNotBlank(picId)){
            if(StringUtil.isNotBlank(size)){
                picUrl = ImageUtil.getImageUrl(picId + "_"+size);
            }else{
                picUrl = ImageUtil.getImageUrl(picId);
            }
        }
       
        return picUrl;
    }
	public static void main(String[] args){
		ThymeleafToolsUtil thymeleafToolsUtil = new ThymeleafToolsUtil();
		String returnmoney = thymeleafToolsUtil.formatMoney(5);
		System.out.println("￥转换后：" + returnmoney);
	}
}
