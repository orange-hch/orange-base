package com.orange.foundation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * 集合List、set、map
 * @author Administrator
 *
 */
public class Collections {
	public static void main(String[] args){
//		collectionDemo();
//		setDemo();
		listDemo();
	}
	
	/**
	 * 集合相关的方法
	 */
	public static void collectionDemo(){
		int[] array = new int[]{4,2,23,8888};
//		List<int> list = new ArrayList<int>(); 编译错误，集合只能存储对象，数组可以存储基本数据类型
		List<Integer> list = new ArrayList<Integer>();
		
		Collection books = new HashSet();
		books.add("西游记");
		books.add("水浒传");
		books.add("红楼梦");
		books.add("三国演义");
		
		//Iterator遍历集合元素
		Iterator it = books.iterator();
		while(it.hasNext()){
			String book = (String)it.next();
			System.out.println(book);
			if(book.equals("水浒传")){
				it.remove(); //删除集合中上一次next()方法返回的集合元素。当使用Iterator迭代访问Collection集合元素时，Collection集合里的元素不能被改变，只用通过Iterator的remove()方法删除才可以。
				
//				books.remove(book); //使用Iterator迭代过程中，不可修改集合元素。此段代码将会引发java.util.ConcurrentModificationException异常。
			}
			book = "修改后的字符串"; //当使用Iterator对集合元素进行迭代时，Iterator并不是把集合元素本身传给了迭代变量，而是把集合元素的值传给了迭代变量，所以修改迭代变量的值对集合元素本身没有影响。
		}
		System.out.println(books); //[红楼梦, 三国演义, 西游记]
		
		//foreach循环遍历集合元素
		for(Object object:books){
			String book = (String)object;
			System.out.println(book);
			if(book.equals("三国演义")){
//				books.remove(book); //迭代过程中，不可修改集合元素。此段代码将会引发java.util.ConcurrentModificationException异常。
			}
			book = "修改后的字符串";
		}
		System.out.println(books); //[红楼梦, 三国演义, 西游记]
	}
	
	/**
	 * set集合
	 */
	public static void setDemo(){
		//****************hashSet***************************************************
		Set books = new HashSet();
		books.add("西游记");
		books.add("水浒传");
		books.add("红楼梦");
		books.add("三国演义");
		
		Boolean isAdd = books.add("西游记"); //false Set集合不允许包含相同的元素。
		
		Set books1 = new HashSet();
		String s1 = new String("西游记");
		String s2 = new String("水浒传");
		String s3 = new String("西游记");
		books1.add(s1);
		books1.add(s2);
		//当把对象放入HashSet中时，如果两个对象通过equals()方法比较返回true,这两个对象的hashCode值也应该相同。
		//如果两个对象通过equals()方法比较返回true,但是两个对象的hashCode()返回不同的hashCode值时，这将导致HashSet会把这两个对象保存在hash表的不同位置，从而使两个对象都可以添加成功，这就与Set集合的规则冲突了。
		Boolean isAdd1 = books1.add(s3); //false  HashSet集合判断两个元素相等的标准是两个对象通过equal()方法比较相等，并且两个对象的hashCode()方法返回值也相等。
		System.out.println(s1.equals(s3)); //true
		System.out.println(s1.hashCode() == s3.hashCode()); //true String的获取hashCode的值是根据字符串来计算的，所以返回true。
		System.out.println(s1 == s3); //false 两个变量指向同一个对象才会返回true。
		System.out.println(books1); //[水浒传, 西游记]
		
		//********************TreeSet*********************************************************
		//如果试图把一个对象添加到TreeSet时，则该对象的类必须实现Comparable接口，否则程序将会抛出异常。
		//如果两个对象通过compareTo(Object obj)方法比较相等，新对象将无法添加到TreeSet集合中。
	}
	
	/**
	 * List
	 */
	public static void listDemo(){
		//ArrayList是线程不安全的。Vector是线程安全的，所以Vector的性能比ArrayList的性能要低
		List books = new ArrayList();
		books.add(new String("西游记"));
		books.add(new String("水浒传"));
		books.add(new String("红楼梦"));
		books.add(new String("三国演义"));
		//list新增一种迭代方法
		for(int i=0;i<books.size();i++){
			System.out.println(books.get(i));
		}
		System.out.println(books.indexOf(new String("三国演义"))); //3 List判断两个对象相等只要通过equals()方法返回true即可。
		//List还额外提供了一个listIterator()方法。ListIterator增加了向前迭代，而且可以通过add()方法向list集合中添加元素。
		ListIterator lIterator = books.listIterator();
		System.out.println("********正向迭代开始********");
		while(lIterator.hasNext()){
			System.out.println(lIterator.next());
			lIterator.add("*********分隔符*********"); //添加元素
		}
		System.out.println("********向前迭代开始********");
		while(lIterator.hasPrevious()){
			System.out.println(lIterator.previous());
		}
		
		//固定长度的List。通过工具类Arrays的方法asList(Object ...a)返回
		List fixedList = Arrays.asList("西游记","水浒传","红楼梦","三国演义");
		System.out.println(fixedList.getClass()); //class java.util.Arrays$ArrayList 他是Arrays的一个内部类
		//增加、删除都会引发java.lang.UnsupportedOperationException 异常
//		fixedList.add("西厢记"); 
//		fixedList.remove("水浒传");
	}
}
