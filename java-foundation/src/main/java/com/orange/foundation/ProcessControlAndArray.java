package com.orange.foundation;

public class ProcessControlAndArray {
	public static void main(String[] args){
		//switchDemo();
		arrayDemo();
	}
	
	public static void arrayDemo(){
//		初始化
		int[] intArray1 = new int[]{1,3,4,5,5,5}; //静态初始化
		int[] intArray2 = new int[4]; //动态初始化
		String s1 = "";
		for (int i : intArray1) {
			s1 += i+",";
		}
		System.out.println("intArray1 = "+s1);
		String s2 = "";
		for (int i : intArray2) {
			s2 += i+",";
		}
		System.out.println("intArray2 = "+s2);
		
//		使用foreach循环迭代数组元素时，并不能改变数组元素的值
		String[] stringArray=new String[]{"Hello","World","!"};
		for (String string : stringArray) {
			string="gogogo";
		}
		System.out.println(stringArray[0]);
	}
	public static void switchDemo(){
//		swich语句后面的控制表达式数据类型只能是byte、short、char、int四种整数类型，枚举类型和String类型(从java7开始支持)。不能是boolean类型。
		int day = 8;
		switch(day)
		{
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
