package com.ai.bdex.dataexchange.constants;

public class Constants { 
    
    public static class Page {  
        // 商品品牌展示个数
        public static int BRAND_LENGTH = 10;  
    } 
    public static class Order {  
        // 订单状态--- 已发货
        public static String ORDER_STATUS_SEND = "04"; 
        // 已收货
        public static String ORDER_STATUS_RECEPT = "06";  
        // 订单状态
        public static String ORDER_STATUS_2 = "02";// 订单已支付
        public static String ORDER_STATUS_6 = "6";// 订单已收货
        public static String ORDER_STATUS_09 = "09";// 退货申请
        public static String ORDER_STATUS_10 = "10";// 同意退货 

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
		
		/*订单主表状态：
		01  申请订购中（未付款），
		02 正在生效中（订购合同签订-已支付），
		03  API接口下线，订单失效（管理员手工失效）、
		04 订单已完成（订单调用量量已经达到最大值、或余额已为0，或有效期已经达到，三者中一种情况达到均为订单完成）、
		99 已失效（取消订单）*/
		
        // 订单来源
        public final static String ORDER_SOURCE_0 = "0";//web端   订单来源，{0：WEB；1：手机；2：微网端，3：安卓，4：ios，5：M站，6：微网端，9:未知}
        public final static String ORDER_SOURCE_1 = "1";//后台创建
		// 订单类型
		
		public final static String ORDER_TYPE_10 = "10";//10普通订单
		public final static String ORDER_TYPE_20 = "20";//20后台创建
    }  
}
