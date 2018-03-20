package com.orange.foundation;

import java.math.BigDecimal;

/**
 * java基本数据类型
 * 
 * @author Administrator
 *
 */
public class BasicDataTypes {
	public static void main(String[] args) {
		// 二进制，八进制，十进制，十六进制
		int binValue = 0B110;
		int octValue = 013;
		int hexValue = 0X1F;
		System.out.println("二进制：" + binValue + " 八进制： " + octValue + " 十六进制： " + hexValue);
		// 二进制：6 八进制： 11 十六进制： 31

		// 浮点型数据精度丢失
		float f1 = 1.23F;
		float f2 = 0.9F;
		System.out.println("f1-f2 = " + (f1 - f2));
		// f1-f2 = 0.33000004

		double d1 = 1.23;
		double d2 = 0.9;
		System.out.println("d1-d2 = " + (d1 - d2));
		// d1-d2 = 0.32999999999999996

		BigDecimal b1 = new BigDecimal(1.23);
		BigDecimal b2 = new BigDecimal(0.9);
		System.out.println("b1-b2 = " + b1.subtract(b2));
		// b1-b2 = 0.32999999999999996003197111349436454474925994873046875

		BigDecimal b3 = new BigDecimal("1.23");
		BigDecimal b4 = new BigDecimal("0.9");
		System.out.println("b3-b4 = " + b3.subtract(b4));
		// b3-b4 = 0.33
	}
}
