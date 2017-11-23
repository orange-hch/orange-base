package com.orange.foundation;

public class ObjectOriented {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
//		inheritanceDemo();
//		wrapperClassDemo();
		objectDemo();
	}
	/**
	 * 继承demo
	 */
	public static void inheritanceDemo(){
		Child child = new Child("childA");
		System.out.println(child.tag); //I'm child!
		child.myDesc(); //my name is child!
		System.out.println(child.getName());
		
		//属性无多态，而方法具有多态
		Parent parent = new Child("childB"); 
		System.out.println(parent.tag); //I'm parent!  此处子类里定义了与父类中已有的变量同名的变量，那么子类中定义的变量会隐藏父类中定义的的变量。不是完全覆盖
		parent.myDesc(); //my name is child! 此处多态。多态是指方法多态。
		System.out.println(parent.getName()); //childB  此处getName()也是方法，具有多态
		System.out.println(parent.getClass()); //class com.orange.foundation.Child  getClass()返回的是运行时类
		
		//instanceof用于在强制类型转换之前判断是否可以成功转换，保证代码更加健壮
		if(child instanceof Parent){
			System.out.println("child instanceof Parent!"); //执行
		}
		
		if(parent instanceof Child){
			System.out.println("parent instanceof Child");//执行
		}
		
		Parent parent2 = new Parent();
		if(parent2 instanceof Child){
			System.out.println("parent2 instanceof Child");//不执行
		}
		
		Child child2 = new Child(2); //此处会出现java.lang.NullPointerException异常
	}
	/**
	 * 包装类
	 */
	public static void wrapperClassDemo(){
//		8大基本数据类型的包装类Byte、Short、Integer、Long、Character、Float、Double、Boolean
//		除了Character外其他7个包装类都提供了parseXxx(String s)的静态方法来将字符串装换成基本数据类型
		Integer it1 = Integer.parseInt("12");
		
		System.out.println("两个包装类的实例比较："+(new Integer(2) == new Integer(2))); //两个包装类的实例比较：false 包装类其实是引用类型，指向同一个对象才会返回true
	}
	
	public static void objectDemo(){
		Parent p1 = new Parent();
		System.out.println(p1.toString()); //Object类提供的toString()方法总是返回该对象实现类的“类名+@+hashCode”值
		
//		使用==判断两个变量是否相等时，如果两个变量是基本类型变量，且都是数值类型（不一定要求数据类型严格相同），则只要两个变量的值相等，就将返回true。
//		如果两个变量是引用类型变量，只有他们指向同一个对象时，==判断才会返回true。 ==不可用于比较类型上没有父子关系的两个对象
		int it = 65;
		float fl = 65.0F;
		System.out.println("65和65.0F是否相等："+(it == fl)); //65和65.0F是否相等：true
//		System.out.println("hello" == new Parent()); //此处产生编译错误，2个对象没有继承关系
		
		String str1 = new String("hello");
		String str2 = new String("hello");
		
		//当使用new String("hello")时，JVM会先使用常量池来管理“hello”直接量，再调用String类的构造器来创建一个新的String对象。新创建的String对象被保存再堆内存中。
		System.out.println("str1和str2是否相等："+(str1 == str2)); //str1和str2是否相等：false
//		equals()方法是Object类提供的一个实例方法，使用这个方法判断的标准和==运算符没有区别。所以一般会重写equals方法。
//		Stirng已经重写了Object的equals()方法。 只要两个字符串所包含的字符序列相同，则返回true.
		System.out.println("str1和str2是否equals："+(str1.equals(str2))); //str1和str2是否equals：true
	}
	
}

/**
 * 父类
 * @author Administrator
 *
 */
class Parent{
	public String tag="I'm parent!";
	private String name;
	public Parent(String name1){
		name="parent'"+name1;
	}
	public Parent(int i){
		test(); //尽量不要在父类构造器中调用将要被子类重写的方法
	}
	public Parent(){
		
	}
	public void myDesc(){
		System.out.println("my name is parent!");
	}
	
	public void test(){
		System.out.println("将被子类重写");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

/**
 * 子类
 * @author Administrator
 *
 */
class Child extends Parent{
	public String tag="I'm child!";
	
	private String name;
	private String firstName;
	public Child(String name1){
		super(name1);
		name=name1;
	}
	public Child(int i){
		super(i); //此处调用父类的构造器，父类构造器中调用的test()方法将调用子类的test()方法。
	}
	public Child(){};
	@Override
	public void myDesc(){
		System.out.println("my name is child!");
	}
	@Override
	public void test(){
		System.out.println("子类重写父类的方法，"+"其firstName字符串长度"+firstName.length());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}