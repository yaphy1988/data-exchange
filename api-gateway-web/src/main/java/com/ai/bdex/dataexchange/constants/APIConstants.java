package com.ai.bdex.dataexchange.constants;

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
	    
	    public static final long AIP_EXPIRE_ACCESSTOKEN=24*3600*1000;//毫秒
	    public static final long AIP_EXPIRE_REFRESHTOKEN=30*24*3600*1000;//毫秒
	    
	    public static final String AIP_EXPIRE_FORMAT_ACCESSTOKEN="yyyy-MM-dd HH:mm:ss";//秒
	    public static final String AIP_EXPIRE_FORMAT_REFRESHTOKEN="yyyy-MM-dd HH:mm:ss:SSS";//毫秒
	    
    }

}
