package com.ai.bdex.dataexchange.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ai.bdex.dataexchange.security.PermissionCheckHandler;

@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Security
{
  boolean mustLogin() default false;
  
  Class<? extends PermissionCheckHandler> authorCheckType() default PermissionCheckHandler.class;
  
  String[] userName() default {};
  
  String[] role() default {};
  
  String comment() default "";
}