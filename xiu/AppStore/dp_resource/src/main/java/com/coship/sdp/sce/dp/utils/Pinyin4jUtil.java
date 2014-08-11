package com.coship.sdp.sce.dp.utils;

import java.util.Locale;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Pinyin4jUtil {
	/**
	 * 将汉字转换为全拼.
	 *
	 * @param src
	 * @return String
	 */
	public static String getPinYin(String src) {
		StringBuffer t4 = new StringBuffer();
		try {
			char[] t1 = src.toCharArray();
			int length = t1.length;

			HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
			t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			t3.setVCharType(HanyuPinyinVCharType.WITH_V);

			for (int i = 0; i < length; i++) {

				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {

					t4.append(PinyinHelper.toHanyuPinyinStringArray(t1[i], t3)[0]);
				} else {

					t4.append(Character.toString(t1[i]));
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return t4.toString().toLowerCase(Locale.US);
	}

	/**
	 * 提取每个汉字的首字母
	 *
	 * @param str
	 * @return String
	 */
	public static String getPinYinHeadChar(String str) {
		StringBuffer convert = new StringBuffer();
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);

			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert.append(pinyinArray[0].charAt(0));
			} else {
				convert.append(word);
			}
		}
		return convert.toString().toLowerCase(Locale.US);
	}

	/**
	 * 将字符串转换成ASCII码
	 *
	 * @param cnStr
	 * @return String
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();

		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {

			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}

	/*public static void main(String[] args) {
		String cnStr = "愤怒的小鸟";
		System.out.println(getPinYin(cnStr));
		System.out.println(getPinYin("Coship QQ2012"));
		System.out.println(getPinYinHeadChar(cnStr));
		System.out.println(getPinYinHeadChar("Coship QQ2012"));
		System.out.println(getCnASCII(cnStr));
	}*/

}