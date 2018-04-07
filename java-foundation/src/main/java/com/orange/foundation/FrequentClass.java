package com.orange.foundation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 常用基础类
 * @author Administrator
 *
 */
public class FrequentClass {
	public static Logger log = LoggerFactory.getLogger(FrequentClass.class);
	
	public static void main(String[] args){
//		scannerKeyBoardTest();
//		scannerXXX();
//		scannerFile("E:\\logback.xml");
//		systemDemo();
//		stringDemo();
//		randomDemo();
//		bigDecimalAndDate();
//		regexDemo();
		i18NDemo();
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
	 * 随机数
	 */
	public static void randomDemo(){
		//Math类的random()方法返回一个伪随机数double，该值大于等于0.0且小于1.0
		System.out.println("Math.random(): " + Math.random());
		
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
	
	/**
	 * BigDecimal 和 日期
	 */
	public static void bigDecimalAndDate(){
		//*********BigDecimal********************
		System.out.println("0.05 + 0.01 = " + (0.05 + 0.01)); // 0.05 + 0.01 = 0.060000000000000005  java的double类型会发生精度丢失
		
		BigDecimal bigDecimal1 = new BigDecimal(0.08); 
		System.out.println(bigDecimal1); //0.08000000000000000166533453693773481063544750213623046875  使用BigDecimal(double val)构造器时有一定的不可预知性。
		
		BigDecimal bigDecimal2 = new BigDecimal("0.08");
		System.out.println(bigDecimal2); //0.08  通常建议使用基于String的构造器
		BigDecimal bigDecimal3 = BigDecimal.valueOf(0.06);
		System.out.println(bigDecimal3); //0.06   如果一定要通过double来创建BigDecimal，则应该通过BigDecimal.valueOf(double val)静态方法来创建。
		
		System.out.println("0.08 + 0.06 = " + bigDecimal2.add(bigDecimal3));
		System.out.println("0.08 - 0.06 = " + bigDecimal2.subtract(bigDecimal3));
		System.out.println("0.08 * 0.06 = " + bigDecimal2.multiply(bigDecimal3));
		System.out.println("0.08 / 0.06 = " + bigDecimal2.divide(bigDecimal3, 2)); //divide 除方法需要设置舍入方式，否则如果没法除尽，则会报错
		
		//******************Date Calendar************************
		//Date提供6个构造器，其中4个已经Deprecated。 剩下两个如下
		Date date = new Date(); //该构造器底层调用System.currentTimeMillis()获得long整数作为日期参数调用下面这个构造器。
		System.out.println(date);
		Date date2 = new Date(100000); //参数表示创建的Date对象与GMT1970年1月1日 00:00:00之间的时间差，以毫秒作为计时单位
		System.out.println(date2);
		
		//Calendar是一个抽象类。
		//Calendar 与 Date都是表示日期的工具类，他们可以自由转换。
		Calendar calendar = Calendar.getInstance(); //创建一个默认的Calendar对象
		Date date3 = calendar.getTime();
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date3);
		System.out.println("年： " + calendar2.get(Calendar.YEAR) + ",月： " + (calendar2.get(Calendar.MONTH) + 1) + ",日： " + calendar2.get(Calendar.DATE)); //月份起始从0开始
	}
	
	/**
	 * 正则表达式
	 */
	public static void regexDemo(){
		//Pattern 对象是正则表达是编译后在内存中的表示形式。 因此正则表达式字符串必须先被编译为Pattern对象，然后利用该Pattern对象创建相应的Matcher对象。
		Pattern pattern = Pattern.compile("a*b"); //* 表示匹配0-N个a
		Matcher matcher = pattern.matcher("aaaaaaaaab");
		System.out.println(matcher.matches());
//		matcher.reset("b");  //ture
//		System.out.println(matcher.matches());
		
		//上面的3行，定义的Pattern对象可以多次重复使用，下面的方法直接使用Pattern类的静态matches()方法，此方法自动将指定字符串编译成匿名的Pattern对象，并执行匹配。
		boolean isMatch = Pattern.matches("a*b", "aaaaaaaaab");
		System.out.println(isMatch);
		
		String string = "我想求购一本书，尽快联系13430459801"
				+ "交友，电话号码18711319999"
				+ "出售二手电脑，联系方式13538819999";
		Matcher m = Pattern.compile("((13\\d)|(18\\d))\\d{8}").matcher(string);
		while (m.find()) { //find()方法依次查找字符串中与Pattern匹配的子串，一旦找到对应的子串，下次调用find()方法时将接着向下查找。
			System.out.println(m.group()); //group()返回上一次与Pattern匹配的子串。
		}
	}
	
	/**
	 * 国际化
	 * ResourceBundle Locale MessageFormat
	 */
	public static void i18NDemo(){
		//************获取java所支持的国家和语言**********************
		Locale[] locales = Locale.getAvailableLocales();
		for (int i = 0; i < locales.length; i++){
			System.out.println(locales[i].getDisplayCountry()
					+ "=" + locales[i].getCountry() + "  "
					+ locales[i].getDisplayLanguage()
					+ "=" + locales[i].getLanguage());
		}
		
		//***********国际化***************
		//取得系统默认的国家/语言环境
		Locale locale = Locale.getDefault(Locale.Category.FORMAT);
		//根据指定的国家/语言环境加载资源文件
		ResourceBundle bundle = ResourceBundle.getBundle("mess", locale);
		System.out.println(bundle.getString("hello")); // 你好！ 
		String msg = bundle.getString("msg");
		log.info("你好！{}！今天是{}。", "orange", new Date());
		System.out.println(MessageFormat.format(msg, "orange", new Date()));
		
		// 设置指定的国家/语言环境
		Locale locale1 = new Locale("en", "US");
		// 根据指定的国家/语言环境加载资源文件
		ResourceBundle bundle1 = ResourceBundle.getBundle("mess", locale1);
		System.out.println(bundle1.getString("hello")); // 取得mess_en_Us资源文件的配置
		String msg1 = bundle1.getString("msg");
		System.out.println(MessageFormat.format(msg1, "orange", new Date()));
		
		//使用NumberFormat格式化数字
		double db = 12345000.678;
		Locale[] locales2 = {Locale.CHINA, Locale.JAPAN, Locale.GERMAN, Locale.US};
		NumberFormat[] numberFormats = new NumberFormat[12];
		for (int i = 0; i < locales2.length; i++){
			numberFormats[i * 3] = NumberFormat.getNumberInstance(locales2[i]);
			numberFormats[i * 3 + 1] = NumberFormat.getPercentInstance(locales2[i]);
			numberFormats[i * 3 + 2] = NumberFormat.getCurrencyInstance(locales2[i]);
		}
		for (int i = 0; i < locales2.length; i++){
			String tip = i == 0 ? "----中国的格式----" : i == 1 ? "----日本的格式----" : i == 2 ? "----德国的格式----" : "----美国的格式----";
			System.out.println(tip);
			System.out.println("通用数值格式："  + numberFormats[i * 3].format(db));
			System.out.println("百分比数值格式："  + numberFormats[i * 3 + 1].format(db));
			System.out.println("货币数值格式："  + numberFormats[i * 3 + 2].format(db));
		}
		
		//使用SimpleDateFormat格式化日期
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("Gyyyy年中的第D天");
		System.out.println(simpleDateFormat.format(date));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("y###MMM##d");
		System.out.println(dateFormat.format(date));
		
		try {
			Date date2 = dateFormat.parse("2018###四月##7");
			System.out.println(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
