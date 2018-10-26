package com.sky.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

/**
 * @since 2017-08-18
 * @author tyro
 *
 */
public class Tools {

	/**
	 * 返回随机数
	 * 
	 * @param n 个数
	 * @return
	 */
	public static String random(int n) {
		if (n < 1 || n > 10) {
			throw new IllegalArgumentException("cannot random " + n + " bit number");
		}
		Random ran = new Random();
		if (n == 1) {
			return String.valueOf(ran.nextInt(10));
		}
		int bitField = 0;
		char[] chs = new char[n];
		for (int i = 0; i < n; i++) {
			while (true) {
				int k = ran.nextInt(10);
				if ((bitField & (1 << k)) == 0) {
					bitField |= 1 << k;
					chs[i] = (char) (k + '0');
					break;
				}
			}
		}
		return new String(chs);
	}

	/**
	 * 指定范围的随机数
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return
	 */
	public static int getRandomNum(int min, int max) {
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}

	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 检测是否为数字
	 * @param s
	 * @return
	 */
	public static boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {

//		System.out.println(utf8ToString("%EE%81%94%E9%B9%8F%E9%A3%9E%F0%9F%90%B3"));

		System.out.println(String_length("我"));
		System.out.println(String_length("1"));
		System.out.println(String_length("a"));
		System.out.println(String_length("!"));
		System.out.println(String_length(""));
		System.out.println(String_length("我 我"));
	}

	public static int String_length(String value) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}
	
	/**
	 * 字符串换成UTF-8
	 *
	 * @param str
	 * @return
	 */
	public static String stringToUtf8(String str) {
		String result = null;
		try {
			if(Tools.isEmpty(str)){
				return "";
			}
			result = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * utf-8换成字符串
	 *
	 * @param str
	 * @return
	 */
	public static String utf8ToString(String str) {
		String result = null;
		try {
			if(Tools.isEmpty(str)){
				return "";
			}
			result = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	public static String getSixRandom() {

		String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
		Random rand = new Random();
		StringBuffer flag = new StringBuffer();
		for (int j = 0; j < 6; j++)
		{
			flag.append(sources.charAt(rand.nextInt(9)) + "");
		}
		return flag.toString();

	}

	/**
	 * 根据数字和位数进行补位，位数不足补0
	 *
	 * @param num 原始数字
	 * @param ws  需要生成的位数
	 * @return
	 */
	public static String padLeft(Long num, int ws) {
		String formatstr = "";
		for (int i = 0; i < ws; i++) {
			formatstr += "0";
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat(formatstr);
		return df.format(num);
	}



}
