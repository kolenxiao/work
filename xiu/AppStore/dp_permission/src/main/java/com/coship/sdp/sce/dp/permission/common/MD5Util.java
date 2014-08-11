package com.coship.sdp.sce.dp.permission.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

public class MD5Util {
	/**
	 * 对字符串进行MD5加密
	 * @param plainText
	 * @return
	 */
	public static String Md5(String plainText) {
		try {
			if (plainText == null || plainText.length() == 0)
			{
				return null;
			}
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			// System.out.println("result: " + buf.toString());//32位的加密

																				

			return buf.toString().substring(8, 24);

		} catch (NoSuchAlgorithmException e) {

			return null;
		}
	}

}
