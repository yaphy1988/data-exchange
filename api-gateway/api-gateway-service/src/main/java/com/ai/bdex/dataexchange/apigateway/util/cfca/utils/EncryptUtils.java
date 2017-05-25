package com.ai.bdex.dataexchange.apigateway.util.cfca.utils;

import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @Description: 加解密工具类
 */
public class EncryptUtils {
    public final static String UTF_8 = "UTF-8";

    /**
     * AES 加密
     * 
     * @param source
     *            加密内容 必填 ( 必须为UTF_8)
     * @param key
     *            密钥 必填
     * @return 成功或失败或异常信息
     */
    public static String encryptWithAES(String source, String key) throws Exception {
        if (key == null) {
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {
            throw new InvalidKeyException("The key's length must be 16");
        }
        byte[] raw = key.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
        IvParameterSpec ivParameterSpec = new IvParameterSpec("Xadiapdfaxi0s91D".getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encryptedData = cipher.doFinal(source.getBytes(UTF_8));
        return Base64.encodeBase64String(encryptedData);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    /**
     * AES 解密
     * 
     * @param source
     *            解密密文 必填(HEX字符串)
     * @param key
     *            密钥 必填
     * @return 解密明文 ( 为UTF_8编码集)
     */
    public static String decryptWithAES(String source, String key) throws Exception {
        // 判断Key是否正确
        if (key == null) {
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {
            throw new InvalidKeyException("The key's length must be 16");
        }
        byte[] keyData = key.getBytes("ASCII");
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("Xadiapdfaxi0s91D".getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        byte[] encrypted1 = new Base64().decode(source);// 先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, UTF_8);
    }
}
