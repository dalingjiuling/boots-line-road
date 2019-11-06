package com.line.road.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	public StringUtils() {
	}

	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}
		return null;
	}

	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}

	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean inStringIgnoreCase(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equalsIgnoreCase(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == '_') {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < s.length() - 1) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && (Character.isUpperCase(c))) {
				if ((!upperCase) || (!nextUpperCase)) {
					sb.append('_');
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)) {
			target = source;
		}
	}

	public static String jsGetVal(String objectString) {
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (int i = 0; i < vals.length; i++) {
			val.append("." + vals[i]);
			result.append("!" + val.substring(1) + "?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	public static String getRandomStr(int count) {
		char[] codeSeq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		Random random = new Random();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < count; i++) {
			String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);
			s.append(r);
		}
		return s.toString();
	}

	public static String getRandomNum(int count) {
		char[] codeSeq = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Random random = new Random();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < count; i++) {
			String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);
			s.append(r);
		}
		return s.toString();
	}

	@Deprecated
	public boolean isExist(String[] ss, String str) {
		for (String s : ss) {
			if (s.equals(str))
				return true;
		}
		return false;
	}
}
