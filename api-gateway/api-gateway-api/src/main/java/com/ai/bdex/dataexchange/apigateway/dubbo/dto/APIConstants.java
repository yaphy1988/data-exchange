package com.ai.bdex.dataexchange.apigateway.dubbo.dto;

public class APIConstants {
	public static final String AIP_PARAM_METHOD="method";
	public static final String AIP_PARAM_TIMESTAMP="timestamp";
	public static final String AIP_PARAM_FORMAT="format";
	public static final String AIP_PARAM_APP_KEY="app_key";
	public static final String AIP_PARAM_VERSION="v";
	public static final String AIP_PARAM_SIGN="sign";
	public static final String AIP_PARAM_SIGN_METHOD="sign_method";
	public static final String AIP_PARAM_SESSION="session";
	public static final String AIP_PARAM_TRANS_SEQ="trans_seq";
	

	public static final String AIP_PARAM_GRANT_TYPE="grant_type";
	public static final String AIP_PARAM_CLIENT_ID="client_id";
	public static final String AIP_PARAM_CLIENT_SECRET="client_secret";
	public static final String AIP_PARAM_USERNAME="username";
	public static final String AIP_PARAM_PASSWORD="password";
	public static final String AIP_PARAM_SCOPE="scope";
    public static final String AIP_PARAM_REDIRET_URI="redirect_uri";
    public static final String AIP_PARAM_STATE="state";
    public static final String AIP_PARAM_RESPONSE_TYPE="response_type";
    public static final String AIP_PARAM_WOEGO_KEY="woegoKey";
    public static final String AIP_PARAM_ACCESSTOKEN="access_token";
    public static final String AIP_PARAM_REFRESHTOKEN="refresh_token";
    
    public static class AipToken{
	    public static final String AIP_CACHE_ACCESSTOKEN="AIP_CACHE_ACCESSTOKEN_";
	    public static final String AIP_CACHE_REFRESHTOKEN="AIP_CACHE_REFRESHTOKEN_";
	    
	    public static final long AIP_EXPIRE_ACCESSTOKEN=86400000L;//毫秒(24hour)
	    public static final long AIP_EXPIRE_REFRESHTOKEN=2592000000L;//毫秒(30day)
	    
	    public static final String AIP_EXPIRE_FORMAT_ACCESSTOKEN="yyyy-MM-dd HH:mm:ss";//秒
	    public static final String AIP_EXPIRE_FORMAT_REFRESHTOKEN="yyyy-MM-dd HH:mm:ss:SSS";//毫秒
	    
    }
    public static class SystemErrorCode{
    	public static final String CODE_00000 =   "00000";//成功 
    	public static final String ERRORCODE_00001 =	"00001";//	未知原因   
    	public static final String ERRORCODE_10001 =   "10001";//	错误的key
    	public static final String ERRORCODE_10002 =	"10002";//	该key无请求权限
    	public static final String ERRORCODE_10003 =	"10003";//	key过期
    	public static final String ERRORCODE_10004 =	"10004";//	错误的clientId或已无效
    	public static final String ERRORCODE_10005 =	"10005";//	应用未审核超时，请提交认证
    	public static final String ERRORCODE_10007 =	"10007";//	未知的请求源
    	public static final String ERRORCODE_10008 =	"10008";//	被禁止的IP
    	public static final String ERRORCODE_10009 =	"10009";//	被禁止的key
    	public static final String ERRORCODE_10010 =	"10010";//	无效请求类型
    	public static final String ERRORCODE_10011 =	"10011";//	当前IP请求超过限制
    	public static final String ERRORCODE_10012 =	"10012";//	请求超过次数限制
    	public static final String ERRORCODE_10013 =	"10013";//	测试KEY超过请求限制
    	public static final String ERRORCODE_10014 =	"10014";//	系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)
    	public static final String ERRORCODE_10015 =	"10015";//	必填参数为空
    	public static final String ERRORCODE_10016 =	"10016";//	签名校验失败
    	public static final String ERRORCODE_10020 =	"10020";//	接口维护
    	public static final String ERRORCODE_10021 =	"10021";//	接口停用	    
    }
	public static class AipService{
		public static final String SERVICE_INIT_VERSION="1.0";//服务初始版本号
		public static final String SERVICE_VALID_STATUS="1";//服务有效标识
		public static final String SERVICE_INVALID_STATUS="0";//服务无效标识
	}
	
	public static class AipSeqName{
		public static final String SEQ_AIP_SERVICE_USED_LOG="T_AIP_SERVICE_USED_LOG";
		public static final String SEQ_AIP_P_SERVICE_USED_LOG="T_AIP_P_SERVICE_USED_LOG";
	}
}
