package com.sinolease.util;

import java.math.BigDecimal;

/**
 * BigDecimal类型数据操作类
 * @author penggang
 *
 */
public class BigDecimalUtil {

	/**
	 * 将null转换为0
	 * @param initValue
	 * @return
	 */
	public static BigDecimal nullChangeZero(BigDecimal initValue) {
		if(initValue==null) {
			initValue = BigDecimal.ZERO;
		}
		return initValue;
	}
}
