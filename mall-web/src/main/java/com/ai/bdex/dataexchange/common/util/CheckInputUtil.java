package com.ai.bdex.dataexchange.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenjy on 2017/5/9.
 */
public class CheckInputUtil {



    /**
     * 校验手机号码
     * @param mobileNo
     * @return
     */

    public static boolean isMobile(String mobileNo) {
        if (!StringUtils.isBlank(mobileNo)) {
            String reg = "^1[34578]\\d{9}$";//13.14.15.17.18这些号段
            return Pattern.compile(reg).matcher(mobileNo).find();
        }

        return false;
    }

    /**
     * 校验固话
     * @param phoneNo
     * @return
     */
    public static boolean isLandline(String phoneNo){
        if(!StringUtils.isBlank(phoneNo)){
            String reg = "^(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})$";
            return Pattern.compile(reg).matcher(phoneNo).find();
        }
        return false;

    }

    /**
     * 校验电话号码（手机、固话）
     * @param phoneNo
     * @return
     */
    public static boolean isPhone(String phoneNo){
        if (!StringUtils.isBlank(phoneNo)) {
            if(isMobile(phoneNo)||isLandline(phoneNo))return true;
        }
        return false;
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

}
