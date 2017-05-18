package com.ai.bdex.dataexchange.constants;

import com.ai.paas.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Constants {
    
    public static class Page {  
        // 商品品牌展示个数
        public static int BRAND_LENGTH = 10;
        public static String CUSTOM_DATA_STATUS_0 = "0";//失效-无用的
        public static String CUSTOM_DATA_STATUS_1 = "1";//未处理
        public static String CUSTOM_DATA_STATUS_2 = "2";//已处理
        
        public static String STATUS_VALID="1";//有效
        public static String STATUS_INVALID="0";//失效
        //楼层类型
        public static String MODULE_TYPE_01="01";
        public static String MODULE_TYPE_02="02";
        public static String MODULE_TYPE_03="03";
    }
    public static class Shop {
        // 商品品牌展示个数
        public static String GZDATA_SHOP_ID = "GZSHOPID";//廣州數據交易中心店鋪ID
    }
    public static class Order {   
        // 订单状态
        /*01  申请订购中（未付款），02 正在生效中（订购合同签订-已支付），
        03  API接口下线，订单失效（管理员手工失效）、
        04 订单已完成（订单调用量量已经达到最大值、或余额已为0，或有效期已经达到，三者中一种情况达到均为订单完成）、
        99 已失效（取消订单）*/
        public static String ORDER_STATUS_01 = "01";// 订单提交
        public static String ORDER_STATUS_02 = "02";// 订单已支付
        public static String ORDER_STATUS_03 = "03";// API接口下线，订单失效（管理员手工失效）
        public static String ORDER_STATUS_04 = "04";// 订单已完成
        public static String ORDER_STATUS_99 = "99";//取消订单

        // NODE节点 
        public static String LOG_CODE_01 = "01"; 
        // 订单日志-节点描述
        public static String LOG_CODE_DESC_01 = "订单提交"; 
     
	    public static String LOG_CODE_02 = "02"; 
        // 订单日志-节点描述
        public static String LOG_CODE_DESC_02 = "订单支付"; 
		
		public static String LOG_CODE_03 = "03"; 
        // 订单日志-节点描述
        public static String LOG_CODE_DESC_03 = "手动失效订单"; 
		
	    public static String LOG_CODE_CODE_99 = "99"; 
        // 订单日志-节点描述
        public static String LOG_CODE_DESC_99 = "取消订单"; 
		
        // 订单来源
        public final static String ORDER_SOURCE_0 = "0";//web端   订单来源， 
        public final static String ORDER_SOURCE_1 = "1";//后台创建
		// 订单类型 
		public final static String ORDER_TYPE_10 = "10";//10普通订单
		public final static String ORDER_TYPE_20 = "20";//自定义套餐下单
        public final static String ORDER_TYPE_30 = "30";//跨类套餐下单


        public final static String ORDER_PAY_FLAG_0 = "0";//未支付
		public final static String ORDER_PAY_FLAG_1 = "1";//已支付
		
		//发票类型:1 需开普通发票 2需开 增值税发票
		public final static String ORDER_invoiceModType_1 = "1";// 需开普通发票
		public final static String ORDER_invoiceModType_2 = "2";//需开 增值税发票
		
		//开具发票状态 ：0 未申请； 1 待开发票（申请中）， 2 已开发票，9 拒开发票'
		public final static String ORDER_INVOICE_STATUS_0 = "0";// 0 未申请
		public final static String ORDER_INVOICE_STATUS_1 = "1";// 1 待开发票（申请中）
		public final static String ORDER_INVOICE_STATUS_2 = "2";// 2 已开发票
		public final static String ORDER_INVOICE_STATUS_9 = "9";//9 拒开发票
		
		public final static int ORDER_AIP_ACTIVE_DAY =  36500 ;//天  -- 无期限给你100年
        //无期限给的时间限制为 100年
        public final static String   ORDER_API_NODATE   =  "2067-10-01";// 服务计数方式：2-金额

        public final static String ORDER_PAY_WAY_ZHIFUBAO =  "9010" ;//支付通道 -- 9010 支付宝

        //调用计费服务时的计费方式入参：1-次数，2-金额
        public final static String ORDER_API_RECHARGETYPE_1   =  "1" ;// 服务计数方式：1-次数，2-金额
        public final static String ORDER_API_RECHARGETYPE_2   =  "2" ;// 服务计数方式：2-金额

        //调用计费服务时的 有效期类型 入参：1-有有效期，2-永久有效 PeriodType
        public final static String ORDER_API_PERIODTYPE_1   =  "1" ;//
        public final static String ORDER_API_PERIODTYPE_2   =  "2" ;//

        //跨类套餐的商品给一个ID，给一个名称
        public final static long ORDER_GDS_30_GDSID   =   100  ;//
        public final static String ORDER_GDS_30_GDSNAME   =  "跨类套餐商品" ;//

        public static Date getActiveEndTimeForEver(){
            Date orderTime = DateUtil.getNowAsDate();
            Calendar calendar   =   new GregorianCalendar();
            calendar.setTime(orderTime);
            calendar.add(calendar.DATE,ORDER_AIP_ACTIVE_DAY);//把日期往后增加一年.整数往后推,负数往前移动
            Date activeEndTime =  calendar.getTime();   //这个时间就是日期往后推一天的结果
            return activeEndTime;
        }
    }

    public static class Bill {
        //数据账户类型：1-基于次数，2-基于余额
        public static final String DATA_ACCT_TYPE_NUM = "1";//1 基于次数
        public static final String DATA_ACCT_TYPE_MONEY = "2";//2 基于余额

        //数据账户类型：0-失效，1-有效，2-冻结
        public static final String DATA_ACCT_STATUS_INVALID = "0";//0 数据账户失效
        public static final String DATA_ACCT_STATUS_OK = "1";//1 数据账户有效
        public static final String DATA_ACCT_STATUS_FROZEN = "2";// 2 数据账户冻结

        //数据账户有效期类型：1-有有效期，2-永久有效
        public static final String DATA_ACCT_PERIOD_VALID = "1";// 1 有生效开始日期，和生效结束日期
        public static final String DATA_ACCT_PERIOD_PERMANENT = "2"; //永久有效

        //充值类型，与数据账户类型对应：1-基于次数，2-基于余额
        public static final String RECHARGE_TYPE_NUM = "1";//1 基于次数
        public static final String RECHARGE_TYPE_MONEY = "2";//2 基于余额

        //套餐类型，10-固定套餐，20-自定义套餐，30-跨类套餐
        public static final String PACKAGE_TYPE_FIX = "10";//固定套餐
        public static final String PACKAGE_TYPE_CUSTOM = "20";//自定义套餐
        public static final String PACKAGE_TYPE_MIX = "30";//跨类套餐

        //充值状态：0-待充值，1-充值中，2-充值成功，3-充值失败
        public static final String RECHARGE_STATUS_WAITING = "0";//0 待充值
        public static final String RECHARGE_STATUS_GOING = "1";//1 充值中
        public static final String RECHARGE_STATUS_SUCCESS = "2";//2 充值成功
        public static final String RECHARGE_STATUS_FAILED = "3";//3 充值失败

        //计费结果：OK-计费扣减成功，2-计费扣减失败
        public static final String CHARGE_RESULE_OK = "OK";// OK 计费扣减成功
        public static final String CHARGE_RESULE_NA = "NA";// NA 计费扣减失败
    }

    public static class Cache{
        //免登陆url缓存map
        public static String UN_LOGIN_URL_MAP = "AUTH.UNLOGIN.URL.MAP";
        //免登陆url缓存key前缀  格式：UL_{系统编码}_{URL}
        public static String UN_LOGIN_URL_PRE = "UL_"; //UL:un login 简写
    }


}
