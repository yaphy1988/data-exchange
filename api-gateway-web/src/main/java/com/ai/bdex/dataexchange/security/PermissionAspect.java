package com.ai.bdex.dataexchange.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Component;

import com.ai.bdex.dataexchange.annotation.Security;

//@Aspect
public class PermissionAspect
  implements InitializingBean, ApplicationContextAware
{
  private ApplicationContext context;
  private List<PermissionCheckHandler> checkHandlers;
  
  @Before(value="@annotation(ecurity)", argNames="security")
  public void checkPermission(Security security)
    throws Exception
  {
    if ((security.authorCheckType() != null) && (!security.authorCheckType().isInterface()) && (PermissionCheckHandler.class.isAssignableFrom(security.authorCheckType()))) {
      PermissionCheckHandler handler = (PermissionCheckHandler)this.context.getBean(security.authorCheckType());
      handler.isPermission(security);
      return;
    }
//    if (this.checkHandlers != null) {
//      for (PermissionCheckHandler handler : this.checkHandlers) {
//        if (!handler.isPermission(security)) {
//          AuthorizationResult result = new AuthorizationResult();
//          result.setResult(AuthorizationResult.ResultEnum.NOT_PERMIT);
//          result.setMessage("访问未授权");
//          throw new AuthorException(result);
//        }
//      }
//    }
  }
  
  public void afterPropertiesSet()
    throws Exception
  {
    if ((this.checkHandlers == null) || (this.checkHandlers.size() == 0))
    {
      Map<String, PermissionCheckHandler> handlers = this.context.getBeansOfType(PermissionCheckHandler.class, true, true);
      if ((handlers != null) && (!handlers.isEmpty())) {
        this.checkHandlers = new ArrayList();
        for (PermissionCheckHandler handle : handlers.values()) {
          this.checkHandlers.add(handle);
        }
      }
    }
    if ((this.checkHandlers != null) && (this.checkHandlers.size() > 0)) {
      OrderComparator.sort(this.checkHandlers);
    }
  }
  
  public void setApplicationContext(ApplicationContext applicationContext)
    throws BeansException
  {
    this.context = applicationContext;
  }
  
  public List<PermissionCheckHandler> getCheckHandlers() {
    return this.checkHandlers;
  }
  
  public void setCheckHandlers(List<PermissionCheckHandler> checkHandlers) {
    this.checkHandlers = checkHandlers;
  }
}

