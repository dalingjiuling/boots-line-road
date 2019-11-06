package com.line.road.common.utils;

public class Exceptions {

	public static RuntimeException unchecked(Exception e) {
		if ((e instanceof RuntimeException)) {
			return (RuntimeException) e;
		}
		return new RuntimeException(e);
	}
}
