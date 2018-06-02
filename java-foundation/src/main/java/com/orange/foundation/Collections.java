package com.orange.foundation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 集合List、set、map
 * 
 * @author Administrator
 *
 */
public class Collections {
	public static void main(String[] args) {
		// collectionDemo();
		// setDemo();
		// listDemo();
		//queueDemo();
		//mapDemo();
		collectionsDemo();
	}

	/**
	 * 集合相关的方法
	 */
	public static void collectionDemo() {
		int[] array = new int[] { 4, 2, 23, 8888 };
		// List<int> list = new ArrayList<int>(); 编译错误，集合只能存储对象，数组可以存储基本数据类型
		List<Integer> list = new ArrayList<Integer>();

		Collection<String> books = new HashSet<String>();
		books.add("西游记");
		books.add("水浒传");
		books.add("红楼梦");
		books.add("三国演义");

		// Iterator遍历集合元素
		Iterator<String> it = books.iterator();
		while (it.hasNext()) {
			String book = (String) it.next();
			System.out.println(book);
			if (book.equals("水浒传")) {
				it.remove(); // 删除集合中上一次next()方法返回的集合元素。当使用Iterator迭代访问Collection集合元素时，Collection集合里的元素不能被改变，只用通过Iterator的remove()方法删除才可以。

				// books.remove(book);
				// //使用Iterator迭代过程中，不可修改集合元素。此段代码将会引发java.util.ConcurrentModificationException异常。
			}
			book = "修改后的字符串"; // 当使用Iterator对集合元素进行迭代时，Iterator并不是把集合元素本身传给了迭代变量，而是把集合元素的值传给了迭代变量，所以修改迭代变量的值对集合元素本身没有影响。
		}
		System.out.println(books); // [红楼梦, 三国演义, 西游记]

		// foreach循环遍历集合元素
		for (Object object : books) {
			String book = (String) object;
			System.out.println(book);
			if (book.equals("三国演义")) {
				// books.remove(book);
				// //迭代过程中，不可修改集合元素。此段代码将会引发java.util.ConcurrentModificationException异常。
			}
			book = "修改后的字符串";
		}
		System.out.println(books); // [红楼梦, 三国演义, 西游记]
	}

	/**
	 * set集合
	 */
	public static void setDemo() {
		// ****************hashSet***************************************************
		Set<String> books = new HashSet<String>();
		books.add("西游记");
		books.add("水浒传");
		books.add("红楼梦");
		books.add("三国演义");

		Boolean isAdd = books.add("西游记"); // false Set集合不允许包含相同的元素。

		Set<String> books1 = new HashSet<String>();
		String s1 = new String("西游记");
		String s2 = new String("水浒传");
		String s3 = new String("西游记");
		books1.add(s1);
		books1.add(s2);
		// 当把对象放入HashSet中时，如果两个对象通过equals()方法比较返回true,这两个对象的hashCode值也应该相同。
		// 如果两个对象通过equals()方法比较返回true,但是两个对象的hashCode()返回不同的hashCode值时，这将导致HashSet会把这两个对象保存在hash表的不同位置，从而使两个对象都可以添加成功，这就与Set集合的规则冲突了。
		Boolean isAdd1 = books1.add(s3); // false
		// HashSet集合判断两个元素相等的标准是两个对象通过equal()方法比较相等，并且两个对象的hashCode()方法返回值也相等。
		System.out.println(s1.equals(s3)); // true
		System.out.println(s1.hashCode() == s3.hashCode()); // true
		// String的获取hashCode的值是根据字符串来计算的，所以返回true。
		System.out.println(s1 == s3); // false 两个变量指向同一个对象才会返回true。
		System.out.println(books1); // [水浒传, 西游记]

		// ********************TreeSet*********************************************************
		// 如果试图把一个对象添加到TreeSet时，则该对象的类必须实现Comparable接口，否则程序将会抛出异常。
		// 如果两个对象通过compareTo(Object obj)方法比较相等，新对象将无法添加到TreeSet集合中。
	}

	/**
	 * List
	 */
	public static void listDemo() {
		// ArrayList是线程不安全的。Vector是线程安全的，所以Vector的性能比ArrayList的性能要低
		List<String> books = new ArrayList<String>();
		books.add(new String("西游记"));
		books.add(new String("水浒传"));
		books.add(new String("红楼梦"));
		books.add(new String("三国演义"));
		// list新增一种迭代方法
		for (int i = 0; i < books.size(); i++) {
			System.out.println(books.get(i));
		}
		System.out.println(books.indexOf(new String("三国演义"))); // 3
		// List判断两个对象相等只要通过equals()方法返回true即可。
		// List还额外提供了一个listIterator()方法。ListIterator增加了向前迭代，而且可以通过add()方法向list集合中添加元素。
		ListIterator<String> lIterator = books.listIterator();
		System.out.println("********正向迭代开始********");
		while (lIterator.hasNext()) {
			System.out.println(lIterator.next());
			lIterator.add("*********分隔符*********"); // 添加元素
		}
		System.out.println("********向前迭代开始********");
		while (lIterator.hasPrevious()) {
			System.out.println(lIterator.previous());
		}

		// 固定长度的List。通过工具类Arrays的方法asList(Object ...a)返回
		List<String> fixedList = Arrays.asList("西游记", "水浒传", "红楼梦", "三国演义");
		System.out.println(fixedList.getClass()); // class
													// java.util.Arrays$ArrayList
													// 他是Arrays的一个内部类
		// 增加、删除都会引发java.lang.UnsupportedOperationException 异常
		// fixedList.add("西厢记");
		// fixedList.remove("水浒传");
	}

	/**
	 * 队列
	 */
	public static void queueDemo() {
		// Deque 双端队列 
		// 需要使用“栈”这种数据结构时，推荐使用ArrayDeque,方法使用push(相当addFirst),pop(相当removeFirst)
		ArrayDeque<String> stack = new ArrayDeque<>();
		// 依次将4个元素push入栈
		stack.push("西游记");
		stack.push("水浒传");
		stack.push("红楼梦");
		stack.push("三国演义");
		System.out.println(stack); // [三国演义, 红楼梦, 水浒传, 西游记]

		// peek(相当getFirst)访问队列头部元素，但并不将其pop出队列“栈”。
		System.out.println(stack.peek()); // 三国演义
		System.out.println(stack); // [三国演义, 红楼梦, 水浒传, 西游记]

		// poll出第一个元素。
		System.out.println(stack.pop()); // 三国演义
		System.out.println(stack); // [红楼梦, 水浒传, 西游记]

		// ArrayDeque当队列使用
		ArrayDeque<String> queue = new ArrayDeque<>();
		// 依次将4个元素加入队列,offer相当与addLast
		queue.offer("西游记");
		queue.offer("水浒传");
		queue.offer("红楼梦");
		queue.offer("三国演义");
		System.out.println(queue); //[西游记, 水浒传, 红楼梦, 三国演义]
		
		//poll出第一个元素。相当于removeFirst
		System.out.println(queue.poll()); //西游记
		System.out.println(queue); //[水浒传, 红楼梦, 三国演义]
	}
	
	/**
	 * Map(有时也被称为字典或者关联数组)
	 */
	public static void mapDemo(){
		//同一个Map对象的任何2个key通过equals方法比较总是返回false
		//java源码中是先实现了Map，然后通过包装一个所以value都为null的Map就实现了Set集合
		Map<String,Integer> map = new HashMap<>();
		map.put("西游记", 81);
		map.put("水浒传", 108);
		map.put("红楼梦", 23);
		map.put("三国演义", 3);
		
		System.out.println(map.put("红楼梦", 21)); //23    放入重复的key,新的value会覆盖原有的value.并返回原来的value
		
		System.out.println(map.containsKey("西游记")); //true 判断是否包含指定的key
		
		System.out.println(map.containsValue(108)); //true 判断是否包含指定的value
		
		Set<Entry<String, Integer>> entrys = map.entrySet(); //返回Map中包含的key-value对所组成的set集合，每个集合都是Map.Entry对象
		for(Entry<String, Integer> entry : entrys){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		System.out.println("---------------------------------------------");
		
		for(Object key : map.keySet()){
			System.out.println(key+"-->"+map.get(key));
		}
		
		//HashMap与Hashtable 都是Map接口的经典实现类
		//Hashtable线程安全，HashMap线程不安全。所以HashMap性能更好，但是如果多个线程访问同一个Map对象时，使用Hashtable更好。
		//Hashtable不允许使用null作为key和value，否则引发NullPointerException。
		//HashMap最多只有一个key-value对的key为null，但可以有无数多个key-value对的value为null
		
		//类似HashSet,HashMap、Hashtable判断两个key相等的标准也是：两个key通过equals()方法比较返回true,两个key的hashCode值也相等。 或者两个key通过==返回true。
		HashMap<A, String> hashMap = new HashMap<>();
		hashMap.put(new A(10), "10");
		System.out.println(hashMap.put(new A(10), "20")); //null
		System.out.println(hashMap.get(new A(10))); //null
		
		A a = new A(30);
		System.out.println(a.equals(a)); //false
		hashMap.put(a, "30");
		System.out.println(hashMap.put(a,"40")); //30  此处虽然a.equals(a)返回false.但是(a == a) 返回true
		System.out.println(hashMap.get(a)); //40
		
		HashMap<B, String> hashMapB = new HashMap<>();
		hashMapB.put(new B(10), "10");
		System.out.println(hashMapB.put(new B(10), "20")); //10 equals返回true,hashCode相同
		System.out.println(hashMapB.get(new B(10))); //20
		
		HashMap<C, String> hashMapC = new HashMap<>();
		hashMapC.put(new C(10), "10");
		System.out.println(hashMapC.put(new C(20), "20")); //10 equals返回true,hashCode相同
		System.out.println(hashMapC.get(new C(10))); //20
		
	}
	
	/**
	 * 操作集合的工具类 Collections
	 */
	public static void collectionsDemo(){
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(2);
		nums.add(-3);
		nums.add(5);
		nums.add(0);
		System.out.println(nums); // [2, -3, 5, 0]
		java.util.Collections.reverse(nums);
		System.out.println(nums); // [0, 5, -3, 2] 反转
		java.util.Collections.sort(nums);
		System.out.println(nums); // [-3, 0, 2, 5] 自然排序
		java.util.Collections.shuffle(nums); //随机打乱次序
		System.out.println(nums);
		
		nums.add(2);
		nums.add(2);
		
		System.out.println(java.util.Collections.max(nums)); // 输出最大元素   5
		System.out.println(java.util.Collections.min(nums)); // 输出最小元素   -3
		System.out.println(java.util.Collections.frequency(nums, 2)); //判断2在集合中出现次数  3
		
		java.util.Collections.sort(nums);
		System.out.println(nums);
		System.out.println(java.util.Collections.binarySearch(nums, 2)); //只有排序后的List集合才可以用二分法查询  2
		
		ArrayList<A> as = new ArrayList<>();
		as.add(new A(3));
		as.add(new A(6));
		as.add(new A(0));
		as.add(new A(-3));
		java.util.Collections.sort(as, new Comparator<A>() {

			@Override
			public int compare(A o1, A o2) {
				//根据aCount大小顺序排序
				return o1.aCount - o2.aCount;	//[A [aCount=-3], A [aCount=0], A [aCount=3], A [aCount=6]]		
				//根据aCount大小倒序排序
				//return o2.aCount - o1.aCount;  // [A [aCount=6], A [aCount=3], A [aCount=0], A [aCount=-3]]
			}
		});
		System.out.println(as);
	}
}
class A{
	int aCount;
	public A(int aCount){
		this.aCount = aCount;
	}
	@Override
	public boolean equals(Object obj){
		return false; //设置equals总返回false
	}
	@Override
	public int hashCode(){
		return this.aCount;
	}
	@Override
	public String toString() {
		return "A [aCount=" + aCount + "]";
	}
}

class B{
	int bCount;
	public B(int bCount){
		this.bCount = bCount;
	}
	@Override
	public boolean equals(Object obj){
		return true; //设置equals总返回true
	}
	@Override
	public int hashCode(){
		return this.bCount;
	}
}

class C{
	int cCount;
	public C(int cCount){
		this.cCount = cCount;
	}
	@Override
	public boolean equals(Object obj){
		return true; //设置equals总返回true
	}
	@Override
	public int hashCode(){
		return 1; //设置hashCode总返回1
	}
}
