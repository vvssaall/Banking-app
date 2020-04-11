package com.banking.utils;

import java.util.Random;

public class Utils {

	public static String genRandomAlphaNum() {

		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		Random random = new Random();
		StringBuilder sb = new StringBuilder((10000 + random.nextInt(90000)) + "-");
		for (int i = 0; i < 5; i++)
			sb.append(chars[random.nextInt(chars.length)]);

		return sb.toString();
	}

}
