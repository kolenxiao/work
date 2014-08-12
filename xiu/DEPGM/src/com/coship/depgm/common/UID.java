package com.coship.depgm.common;

import java.util.Random;

public class UID {
	private static Random random = new Random();
	
	private static final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ0123456789";
			
	public static String create(int length){
		StringBuffer uid = new StringBuffer();
		for(int i=0;i<length;i++){
			int pos = random.nextInt(characters.length());
			uid.append(characters.charAt(pos));
		}
		return uid.toString();
	}
	
	public static String create(){
		return create(12);
	}
}