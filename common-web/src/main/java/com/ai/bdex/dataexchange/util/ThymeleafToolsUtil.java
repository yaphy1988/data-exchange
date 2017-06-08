package com.ai.bdex.dataexchange.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ai.paas.config.ModuleInfo;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.util.SystemConfUtil;

/**
 * Created by xiongqian on 2017/5/5.
 */
public class ThymeleafToolsUtil {
    /**
     * 获取商城全路径
     * 使用：<a th:href="${#tools.mallDomain()}"></a>
     * @return http://domain/mall-web
     */
    public String mallDomain(){
        ModuleInfo moduleInfo = SystemConfUtil.getSystemModuleInfo("01","1");
        if(moduleInfo != null){
            return moduleInfo.genFullUrl();
        }
        return "";
    }

    /**
     * 获取管理后台全路径
     * 使用：<a th:href="${#tools.managerDomain()}"></a>
     * @return http://domain/manager-web
     */
    public String managerDomain(){
        ModuleInfo moduleInfo = SystemConfUtil.getSystemModuleInfo("02","1");
        if(moduleInfo != null){
            return moduleInfo.genFullUrl();
        }
        return "";
    }

    /**
     * 获取报表模块全路径
     * 使用：<a th:href="${#tools.reportDomain()}"></a>
     * @return http://domain/report-web
     */
    public String reportDomain() {
        ModuleInfo moduleInfo = SystemConfUtil.getSystemModuleInfo("02","2");
        if(moduleInfo != null){
            return moduleInfo.genFullUrl();
        }
        return "";
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
     * @param money
     * @return
     * @since JDK 1.6
     */
    public String formatMoneyClean(int money){
        return  BigDecimal.valueOf(Long.valueOf(money)).divide(new BigDecimal(100)).toString();
    }
    /**
     * 将金额转换为元显示(厘转为元)
     * 使用：<a th:href="${#tools.managerDomain()}"></a>
     * @return http://domain/manager-web
     */
    public String formatLiToMoney(int money){
     /*  	double f = 34.236323;
    	BigDecimal b = new BigDecimal(f);
    	double f1 = b.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();*/
    	
     	double dmoney = money;
     	double dmoneytmp = dmoney/1000;
     	BigDecimal b = new BigDecimal(dmoneytmp);
     	double dmoneyback =  b.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
        return  "￥"+dmoneyback;
    }
    /**
     *
     * formatLiToMoneyClean:(返回没有元符号,厘转为元). <br/>
     *
     * @param money
     * @return
     * @since JDK 1.6
     */
    public String formatLiToMoneyClean(int money){
        return  BigDecimal.valueOf(Long.valueOf(money)).divide(new BigDecimal(1000)).toString();
    }
    /**  
     * 元转换为分.  
     * @param yuan  元
     * @return 分  
     */  
    public  String fromMoneyToFenClean(String yuan) {  
        String fen = "";  
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?{1}");  
        Matcher matcher = pattern.matcher(yuan);  
        if (matcher.matches()) {  
            try {  
                NumberFormat format = NumberFormat.getInstance();  
                Number number = format.parse(yuan);  
                double temp = number.doubleValue() * 100.0;  
                // 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012  
                format.setGroupingUsed(false);  
                // 设置返回数的小数部分所允许的最大位数  
                format.setMaximumFractionDigits(0);  
                fen = format.format(temp);  
            } catch (ParseException e) {  
                e.printStackTrace();  
            }  
        }else{  
            System.out.println("参数格式不正确!");  
        }  
        return fen;  
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
    public String formatOrderStatus(String status){
        String showStatus = "订单提交";
        switch(status)
        {
            case "01":
                showStatus = "申请订购中";
                break;
            case "02":
                showStatus = "正在生效中";
                break;
            case "03":
                showStatus = "订单失效";
                break;
            case "04":
                showStatus = "订单已完成";
                break;
            case "99":
                showStatus = "订单已取消";
                break;
            default:
                showStatus = "未知的状态";
                break;
            //订单状态：01  申请订购中（未付款），
            // 02 正在生效中（订购合同签订-已支付），
            // 03  API接口下线，订单失效（管理员手工失效）、
            // 04 订单已完成（订单调用量量已经达到最大值、或余额已为0，或有效期已经达到，三者中一种情况达到均为订单完成）、
            // 99 已失效（取消订单）
        }
        return  showStatus;
    }

    public String formatOrderType(String orderType){
        String orderTypeShow = "未知类型";
        switch(orderType)
        {
            case "10":
                orderTypeShow = "固定套餐订单";
                break;
            case "20":
                orderTypeShow = "自定义套餐订单";
                break;
            case "30":
                orderTypeShow = "跨类套餐订单";
                break;
            default:
                orderTypeShow = "未知的类型";
                break;
        }
        //10普通订单 20手动创建订单
        return  orderTypeShow;
    }

    /***
     * 	private String packageStatus;//套餐状态 01 未生效(默认)，02 在用，03 有效期已达到，不可再用；
     // 04 套餐已完成（订单调用量量已经达到最大值、或余额已为0，二者中一种情况达到均为订单完成--界面实时计算）
     * @param packageStatus
     * @return
     */
    public String formatPackageStatus(String packageStatus){
        String packageStatusShow = "未知类型";
        switch(packageStatus)
        {
            case "01":
                packageStatusShow = "未生效";
                break;
            case "02":
                packageStatusShow = "在用";
                break;
            case "03":
                packageStatusShow = "有效期已达到";
                break;
            case "04":
                packageStatusShow = "已完成";
                break;
            default:
                packageStatusShow = "--";
                break;
        }
         return  packageStatusShow;
    }
	public static void main(String[] args){
		ThymeleafToolsUtil thymeleafToolsUtil = new ThymeleafToolsUtil();
		String returnmoney = thymeleafToolsUtil.formatMoney(5);
		
		String payment = thymeleafToolsUtil.fromMoneyToFenClean("100.02");
		System.out.println("￥转换后：" + payment);
	}
}
