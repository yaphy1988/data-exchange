package com.ai.bdex.dataexchange.util;

import java.util.Random;

/**
 * @author yafei
 * @since 2016/11/22
 */
public class StringUtil {
    public StringUtil() {
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || obj.toString().length() == 0;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static String toString(Object obj) {
        return obj == null?"":obj.toString();
    }

    public static String restrictLength(String strSrc, int iMaxLength) {
        if(strSrc == null) {
            return null;
        } else if(iMaxLength <= 0) {
            return strSrc;
        } else {
            String strResult = strSrc;
            Object b = null;
            int iLength = strSrc.length();
            if(iLength > iMaxLength) {
                strResult = strSrc.substring(0, iMaxLength);
                iLength = iMaxLength;
            }

            while(true) {
                byte[] var5 = strResult.getBytes();
                if(var5.length <= iMaxLength) {
                    return strResult;
                }

                --iLength;
                strResult = strResult.substring(0, iLength);
            }
        }
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int num = random.nextInt(str.length());
            buf.append(str.charAt(num));
        }

        return buf.toString();
    }

    public static String lPad(String target, String fix, int length) {
        if(target != null && fix != null && target.length() < length) {
            StringBuffer newStr = new StringBuffer();

            for(int i = 0; i < length - target.length(); ++i) {
                newStr.append(fix);
            }

            return newStr.append(target).toString();
        } else {
            return target;
        }
    }

    public static String rPad(String target, String fix, int length) {
        if(target != null && fix != null && target.length() < length) {
            StringBuffer newStr = new StringBuffer();
            newStr.append(target);

            for(int i = 0; i < length - target.length(); ++i) {
                newStr.append(fix);
            }

            return newStr.toString();
        } else {
            return target;
        }
    }

    public static String join(String[] strs, String spi) {
        StringBuffer buf = new StringBuffer();
        int step = 0;
        String[] arr$ = strs;
        int len$ = strs.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String str = arr$[i$];
            buf.append(str);
            if(step++ < strs.length - 1) {
                buf.append(spi);
            }
        }

        return buf.toString();
    }

}
