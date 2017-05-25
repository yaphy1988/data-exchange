package com.ai.bdex.dataexchange.apigateway.util.cfca.utils;

import java.security.MessageDigest;

/**
 * @Description: hash工具类
 */
public class HashUtils {

    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * SHA256hash
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static String hashWithSHA256(String data) throws Exception {
        byte[] hashData = "".getBytes();
        MessageDigest digest = null;
        digest = MessageDigest.getInstance("SHA-256");
        digest.update(data.getBytes(DEFAULT_CHARSET));
        hashData = digest.digest();
        return bytes2hex(hashData);
    }

    /**
     * byte数组转16进制字符串
     * 
     * @param bytes
     * @return
     */
    public static String bytes2hex(byte[] bytes) {
        String result = "";
        String b = "";
        for (int i = 0; i < bytes.length; i++) {
            b = Integer.toHexString(bytes[i] & 0xFF);
            if (b.length() == 1) {
                b = "0" + b;
            }
            result += b;
        }
        return result.toUpperCase();
    }
}