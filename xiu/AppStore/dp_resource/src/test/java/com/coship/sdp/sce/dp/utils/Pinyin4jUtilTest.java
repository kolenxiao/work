package com.coship.sdp.sce.dp.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class Pinyin4jUtilTest {
	private String cnStr = "愤怒的小鸟";
	private String engStr = "coship";

	@Test
	public void testGetPinYin() {
	 assertEquals("fennudexiaoniao", Pinyin4jUtil.getPinYin(cnStr));
	 assertEquals("coship", Pinyin4jUtil.getPinYin(engStr));

	}

	@Test
	public void testGetPinYinHeadChar() {
		assertEquals("fndxn", Pinyin4jUtil.getPinYinHeadChar(cnStr));
	}

	@Test
	public void testGetCnASCII() {
		assertEquals("b7dfc5adb5c4d0a1c4f1", Pinyin4jUtil.getCnASCII(cnStr));
	}

}
