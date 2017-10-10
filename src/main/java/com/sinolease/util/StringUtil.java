package com.sinolease.util;

public class StringUtil {

	public static Boolean judgeNull(String str) {
		if(str==null || "".equals(str)) {
			return true;
		}
		return false;
	}
}
