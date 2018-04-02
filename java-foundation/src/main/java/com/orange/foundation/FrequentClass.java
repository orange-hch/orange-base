package com.orange.foundation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 * 常用基础类
 * @author Administrator
 *
 */
public class FrequentClass {
	public static void main(String[] args){
//		scannerKeyBoardTest();
//		scannerXXX();
//		scannerFile("E:\\logback.xml");
//		systemDemo();
//		stringDemo();
		randomDemo();
	}
	
	/**
	 * 读取键盘输入
	 */
	public static void scannerKeyBoardTest(){
		//System.in 代表标准输入，就是键盘输入
		Scanner scanner = new Scanner(System.in);
		//增加下面一行将只把回车作为分隔符（默认情况下，Scanner使用空白(包括空格、Tab空白、回车)）作为多个输入项之间的分隔符。
		//scanner.useDelimiter("\n");  //此行也表示把键盘的每行输入当成一个输入项。相当与hasNextLine()和nextLine()
		while (scanner.hasNext()){
			String input = scanner.next();
			if (input.contains("exit")){
				System.out.println("程序退出");
				break;
			}
			System.out.println("键盘输入的内容是1：" + input);
		}
		scanner.close();
		return;
	}
	
	/**
	 * scanner***
	 */
	public static void scannerXXX(){
		Scanner scanner = new Scanner(System.in);
		try {
			//hasNextXXX 要求键盘输入的必须类型是XXX,否则程序就会退出
			while (scanner.hasNextLong()){
				System.out.println("键盘输入的内容是：" + scanner.nextLong());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != scanner){
				System.out.println("关闭资源");
				scanner.close();
			}
		}
	}
	
	/**
	 * 读取文件内容
	 * @param filePath
	 * @throws FileNotFoundException
	 */
	public static void scannerFile(String filePath){
		Scanner scanner = null;
		try {
			//读取文件内容
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} finally {
			if (null != scanner) {
				scanner.close();
			}
		}
		System.out.println("文件内容如下：");
		while (scanner.hasNextLine()){
			System.out.println(scanner.nextLine());
		}
	}
	
	/**
	 * 系统相关
	 */
	public static void systemDemo(){
		//-------System类代表当前java程序的运行平台。-------
		//获取系统中所有的环境变量
		Map<String, String> env = System.getenv();
		for (String name : env.keySet()){
			System.out.println(name + "-->" + env.get(name));
		}
		System.out.println(System.getenv("JAVA_HOME"));
		
		//获取所有的系统属性
		Properties properties = System.getProperties();
		try {
			properties.store(new FileOutputStream("E:\\propsTest.txt"), "hello world");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.getProperty("os.name"));
		
		System.out.println("当前时间：" + System.currentTimeMillis());
		
		String s1 = new String("hello");
		String s2 = new String("hello");
		//String重写了的hashCode()方法
		System.out.println("hashcod:" + s1.hashCode() + "----" + s2.hashCode());
		
		//identityHashCode值是根据对象的地址计算得到的，所以任何两个对象的identityHashCode值总是不相等的。
		System.out.println("identityHashCode:" + System.identityHashCode(s1) + "----" + System.identityHashCode(s2));
		
		String s3 = "hello";
		String s4 = "hello";
		
		//s3 和 s4是相同的字符串对象。
		System.out.println("identityHashCode:" + System.identityHashCode(s3) + "----" + System.identityHashCode(s4));
		
		//-------Runtime类代表Java程序的运行时环境，每个Java程序都有一个与之对应的Runtime实例，应用程序通过该对象与其运行时环境相连-------
		Runtime runtime = Runtime.getRuntime();
		System.out.println("处理器数量：" + runtime.availableProcessors());
		System.out.println("可用最大内存：" + runtime.maxMemory());
		
		//直接单独启动一个进程来运行操作系统的命令
		try {
			runtime.exec("notepad.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * String StringBuilder StringBuffer
	 */
	public static void stringDemo(){
		//String类是不可变类
		//StringBuffer对象则代表一个字符序列可变的字符串
		//StringBuilder和StringBuffer基本相似。StringBuffer是线程安全的，而StringBuilder则没有实现线程安全功能，所以性能略高
		
		String s1 = "你好!";
		try {
			byte[] bytes = s1.getBytes();
//			String s2 = new String(bytes, "UTF-8");
			String s2 = new String(bytes, "ISO-8859-1");
			System.out.println("默认编码格式：" + Charset.defaultCharset());
			System.out.println(s2); //此处乱码，编码格式不同
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String s2 = "abcdef";
		String s3 = "abcdggsww";
		//比较字符串的大小
		System.out.println(s2.compareTo(s3)); //比较字母e和g值 'e'-'g' = -2
		
		//将字符串中的所有oldChar 替换成 newChar
		System.out.println(s3.replace('g', 'k')); //abcdkksww
	}
	
	/**
	 * 随机数组
	 */
	public static void randomDemo(){
		Random random = new Random();
		byte[] bytes = new byte[16];
		random.nextBytes(bytes);
		System.out.println(Arrays.toString(bytes));
		//生成0.0~1.0之间的伪随机double数
		System.out.println(random.nextDouble());
		
		System.out.println("-------------------");
		Random random1 = new Random(10);
		System.out.println(random1.nextLong());
		System.out.println(random1.nextDouble());
		System.out.println(random1.nextInt(100));
		System.out.println(random1.nextInt(100));
		
		System.out.println("-------------------");
		//Random使用一个48位的种子，如果这个类的两个实例是用同一个种子创建的，对他们以同样的顺序调用方法，则他们产生相同的数字序列(调用顺序不同则产生的结果不同)
		Random random2 = new Random(10);
		System.out.println(random2.nextLong()); //结果相同
		System.out.println(random2.nextInt(100)); //结果不同
		System.out.println(random2.nextDouble()); //结果不同
		System.out.println(random2.nextInt(100)); //结果相同
		
	}
}
