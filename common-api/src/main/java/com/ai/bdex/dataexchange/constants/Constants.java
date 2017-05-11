package com.ai.bdex.dataexchange.constants;

public class Constants { 
    
    public static class Page {  
        // 商品品牌展示个数
        public static int BRAND_LENGTH = 10;
        public static String CUSTOM_DATA_STATUS_0 = "0";//失效-无用的
        public static String CUSTOM_DATA_STATUS_1 = "1";//未处理
        public static String CUSTOM_DATA_STATUS_2 = "2";//已处理

        public static String STATUS_VALID="1";//有效
        public static String STATUS_INVALID="0";//失效
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
		public final static String ORDER_TYPE_20 = "20";//20后台创建
		
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
		
		public final static int ORDER_AIP_ACTIVE_MON =  12 ;//12个月
    }  
}
