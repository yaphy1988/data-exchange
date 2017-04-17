package com.ai.bdex.dataexchange.usercenter.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 创建人： zy
 * 创建时间： 2014年10月22日 下午2:36:19
 * 类描述：将字符串进行加密的工具类
 */
public class MD5Util {

	/**
	 * 将源字符串通过MD5进行加密为字节数组
	 * @param source
	 * @return
	 */
	public static byte[] encodeToBytes(String source) {
		byte[] result  = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();//重置
			md.update(source.getBytes("UTF-8"));//添加需要加密的源
			result = md.digest();//加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 将源字符串通过MD5加密成32位16进制数
	 * @param source
	 * @return
	 */
	public static String encodeToHex(String source) {
		byte[] data = encodeToBytes(source);//先加密为字节数组
		StringBuffer hexSb = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(0xff & data[i]);
			if (hex.length() == 1) {
				hexSb.append("0");
			}
			hexSb.append(hex);
		}
		return hexSb.toString();
	}
	
	/**
	 * 验证字符串是否匹配
	 * @param unknown	待验证的字符串
	 * @param okHex		使用MD5加密后的16进制字符串
	 * @return
	 */
	public static boolean validate(String unknown , String okHex) {
		return okHex.equals(encodeToHex(unknown));
	}
}
