
/** 
 * Project Name:aipaas 
 * File Name:LocaleUtil.java 
 * Package Name:com.ai.paas.client.utils 
 * Date:2015-7-22下午3:48:33 
 * Copyright (c) 2015, asia All Rights Reserved. 
 * 
 */ 
package com.ai.bdex.dataexchange.util;

import java.util.Locale;

/**
 * Title: ECP <br>
 * Project Name:aipaas <br>
 * Description: i18N国际化的标准类<br>
 * Date:2015-7-22下午3:48:33  <br>
 * Copyright (c) 2015 asia All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class LocaleUtil {
    
    private static ThreadLocal<Locale> threadLocal = new ThreadLocal<Locale>();
    
    public static Locale getLocale(){
        Locale locale = threadLocal.get();
        if(locale == null){
            //return Locale.getDefault();
            //默认为中文
            return Locale.SIMPLIFIED_CHINESE;
        } else {
            return locale;
        }
    }
    
    public static void setLocale(Locale locale){
        threadLocal.set(locale);
    }
    
    public static void setLocale(String country){
        threadLocal.set(new Locale(country));
    }
    
    ///fetch language
    public static String getLocalString(){
        Locale locale = getLocale();
        if(locale == null){
            return "";
        } else {
            return locale.getLanguage();
        }
        
    }
}

