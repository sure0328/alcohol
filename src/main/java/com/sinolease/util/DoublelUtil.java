package com.sinolease.util;

import java.math.BigDecimal;

/**
 * Double类型数据操作类
 * @author penggang
 *
 */
public class DoublelUtil {

	/**
	 * 将null转换为0
	 * @param initValue
	 * @return
	 */
	public static Double nullChangeZero(Double initValue) {
		if(initValue==null) {
			initValue = Double.valueOf("0");
		}
		return initValue;
	}
	
	public static double truncPoint(double value) {
		return new BigDecimal(value).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
