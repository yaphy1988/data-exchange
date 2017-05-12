package com.ai.bdex.dataexchange.utils;

import java.security.MessageDigest;

public class MD5Util {

    /**
     * MD5加密函数
     *
     * @param sourceString
     * @return
     */
    public static String md5(String sourceString) {
        String resultString = sourceString;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
    
    private static String byte2hexString(byte[] bytes) {
        StringBuilder bf = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            if ((aByte & 0xff) < 0x10) {
                bf.append("0");
            }
            bf.append(Long.toString(aByte & 0xff, 16));
        }
        return bf.toString();
    }
    
}
