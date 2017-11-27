package com.orange.foundation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 集合List、set、map
 * @author Administrator
 *
 */
public class Collections {
	public static void main(String[] args){
//		collectionDemo();
		setDemo();
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
	
	public static void setDemo(){
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
		Boolean isAdd1 = books1.add(s3); //false  HashSet集合判断两个元素相等的标准是两个对象通过equal()方法比较相等，并且两个对象的hashCode()方法返回值也相等。
		System.out.println(s1.equals(s3)); //true
		System.out.println(s1.hashCode() == s3.hashCode()); //true String的获取hashCode的值是根据字符串来计算的，所以返回true。
		System.out.println(s1 == s3); //false 两个变量指向同一个对象才会返回true。
		System.out.println(books1); //[水浒传, 西游记]
	}
	
}
