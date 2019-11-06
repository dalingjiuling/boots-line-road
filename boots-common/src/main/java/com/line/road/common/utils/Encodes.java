package com.line.road.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.codec.binary.Hex;

public class Encodes {

	public static String urlEncode(String part) {
		return urlEncode(part, "UTF-8");
	}

	public static String urlEncode(String part, String encoding) {
		try {
			return URLEncoder.encode(part, encoding);
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static String encodeHex(byte[] input) {
		return new String(Hex.encodeHex(input));
	}
}
