package com.orange.foundation;

import java.util.Arrays;

public class ProcessControlAndArray {
	public static void main(String[] args) {
		// switchDemo();
		arrayDemo();
	}

	/**
	 * 数组demo
	 */
	public static void arrayDemo() {
		// 初始化,数组长度指定后就不可变了
		int[] intArray1 = new int[] { 1, 3, 4, 5, 5, 5 }; // 静态初始化
		int[] intArray2 = new int[4]; // 动态初始化
		System.out.println("intArray1 = " + Arrays.toString(intArray1));
		System.out.println("intArray2 = " + Arrays.toString(intArray2));

		// 使用foreach循环迭代数组元素时，并不能改变数组元素的值
		String[] stringArray = new String[] { "Hello", "World", "!" };
		for (String string : stringArray) {
			string = "gogogo";
		}
		System.out.println(stringArray[0]);

		int[] intArray3 = new int[5];
		Arrays.fill(intArray3, 5); // 给所有元素赋值
		System.out.println("intArray3 = " + Arrays.toString(intArray3));

		int[] intArray4 = new int[] { 3, 2, 8, 1, 29, 12, 9 };
		Arrays.sort(intArray4); // 排序
		System.out.println("intArray4 = " + Arrays.toString(intArray4));

		System.out.println("8在数组的第" + Arrays.binarySearch(intArray4, 8) + "位"); // 查找，数组必须是排好序的。返回的次序下标从0开始

		int[] intArray5 = Arrays.copyOf(intArray4, 4); // 复制数组
		System.out.println("intArray5 = " + Arrays.toString(intArray5));

	}

	/**
	 * switch流程demo
	 */
	public static void switchDemo() {
		// swich语句后面的控制表达式数据类型只能是byte、short、char、int四种整数类型，枚举类型和String类型(从java7开始支持)。不能是boolean类型。
		int day = 8;
		switch (day) {
		case 1:
			System.out.println("星期一");
			break;
		case 2:
			System.out.println("星期二");
			break;
		case 3:
			System.out.println("星期三");
			break;
		case 4:
			System.out.println("星期四");
			break;
		case 5:
			System.out.println("星期五");
			break;
		case 6:
			System.out.println("星期六");
			break;
		case 7:
			System.out.println("星期日");
			break;
		default:
			System.out.println("放假");
			break;
		}

	}
}
