package com.ai.bdex.dataexchange.interceptor;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceUsedLogDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceUsedLogRSV;
import com.ai.bdex.dataexchange.annotation.Security;
import com.ai.bdex.dataexchange.security.PermissionCheckHandler;
import com.ai.paas.util.Utils;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@Aspect
@Component
public class ControllerInterceptor {
	 	private final Log logger = LogFactory.getLog(getClass());
	 	@DubboConsumer
	 	private IAipServiceUsedLogRSV aipServiceUsedLogRSV;
	   /** 
	     * 定义拦截规则：拦截com.ai.bdex.dataexchange.busi.*.controller包下面的所有类中，有@Path注解的方法。 
	     */  
//	    @Pointcut("execution(* com.ai.bdex.dataexchange.busi.*.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping) && !execution(* com.ai.bdex.dataexchange.busi.oauth.controller..*(..))")  
	 	public void controllerMethodPointcut(){} 
	    
//	    @Before(value="@annotation(security)", argNames="security")
	    public String checkPermission(Security security)
	      throws Exception
	    {
	      String result=null;
	      if ((security.authorCheckType() != null) && (!security.authorCheckType().isInterface()) && (PermissionCheckHandler.class.isAssignableFrom(security.authorCheckType()))) {
	        PermissionCheckHandler handler = (PermissionCheckHandler)Utils.getCtx().getBean(security.authorCheckType());	        
	        result=handler.isPermission(security);	        
	      }
	      return result;
	    }
	    
	    /** 
	     * 拦截器具体实现 
	     * @param pjp 
	     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。） 
	     */  
//	    @Around("controllerMethodPointcut()") //指定拦截器规则；也可以直接把“execution(* com.ai.........)”写进这里  
	    @Around(value="@annotation(security)", argNames="security")
	    public Object Interceptor(ProceedingJoinPoint pjp,Security security)throws Exception{  
	        long beginTime = System.currentTimeMillis();  
	        MethodSignature signature = (MethodSignature) pjp.getSignature();  
	        Method method = signature.getMethod(); //获取被拦截的方法  
	        String methodName = method.getName(); //获取被拦截的方法名  
	          
	        Set<Object> allParams = new LinkedHashSet<>(); //保存所有请求参数，用于输出到日志中  
	          
	        logger.info("请求开始，方法："+ methodName);  
	          
	        Object result = null;  
	  
	        Object[] args = pjp.getArgs();  
	        for(Object arg : args){  
	            //logger.debug("arg: {}", arg);  
	            if (arg instanceof Map<?, ?>) {  
	                //提取方法中的MAP参数，用于记录进日志中  
	                @SuppressWarnings("unchecked")  
	                Map<String, Object> map = (Map<String, Object>) arg;  
	  
	                allParams.add(map);  
	            }else if(arg instanceof HttpServletRequest){  
	                HttpServletRequest request = (HttpServletRequest) arg;  	              	                
	                //获取query string 或 posted form data参数  
	                Map<String, String[]> paramMap = request.getParameterMap();  
	                if(paramMap!=null && paramMap.size()>0){  
	                    allParams.add(paramMap);  
	                }  
	            }else if(arg instanceof HttpServletResponse){  
	                //do nothing...  
	            }else{  
	                //allParams.add(arg);  
	            }  
	        }  
	        result=checkPermission(security);
	        try {  
	            if(result == null){  
	                // 一切正常的情况下，继续执行被拦截的方法  
	                result = pjp.proceed();  
	            }  
	        } catch (Throwable e) {  
	            logger.info("exception: ", e);  
	        }  
	          
	        if(result instanceof Object){  
	            long costMs = System.currentTimeMillis() - beginTime;  
//	            logger.info("{}请求结束，耗时：{}ms", methodName, costMs);  
	        }  

	        return result;  
	    }  
	    
//	    private String checkAccessToken(HttpServletRequest request){
//	    	String accessToken=null;
//	    	String result=null;
//	    	try{
//	    		accessToken=request.getParameter(APIConstants.AIP_PARAM_ACCESSTOKEN);	    	
//	    		if(null==CacheUtil.getItem(APIConstants.AipToken.AIP_CACHE_ACCESSTOKEN+accessToken)){
//	    			AipClientAccesstokenDTO dto=aipClientAccesstokenRSV.getAipClientAccesstokenByKey(accessToken);
//	    			if(null==dto){
//	    				result= "123";
//	    			}
//	    		}
//	    	}catch(Exception e){
//	    		logger.error("check token failted:"+accessToken, e);
//	    		result= "Exception";
//	    	}
//	    	return result;
//	    }

}
