package com.orange.foundation;

/**
 * 对象
 * @author Administrator
 *
 */
public class ObjectOriented {
	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// inheritanceDemo();
		// wrapperClassDemo();
		// objectDemo();
		// enumDemo();
		gcDemo(); // 使用命令来运行此程序 java -verbose:gc
					// com.orange.foundation.ObjectOriented。 -verbose:gc
					// 选项可以看到每次垃圾回收后的提示信息
	}

	/**
	 * 继承demo
	 */
	public static void inheritanceDemo() {
		Child child = new Child("childA");
		System.out.println(child.tag); // I'm child!
		child.myDesc(); // my name is child!
		System.out.println(child.getName());

		// 属性无多态，而方法具有多态
		Parent parent = new Child("childB");
		System.out.println(parent.tag); // I'm parent!
		// 此处子类里定义了与父类中已有的变量同名的变量，那么子类中定义的变量会隐藏父类中定义的的变量。不是完全覆盖
		parent.myDesc(); // my name is child! 此处多态。多态是指方法多态。
		System.out.println(parent.getName()); // childB 此处getName()也是方法，具有多态
		System.out.println(parent.getClass()); // class com.orange.foundation.Child
		// getClass()返回的是运行时类

		// instanceof用于在强制类型转换之前判断是否可以成功转换，保证代码更加健壮
		if (child instanceof Parent) {
			System.out.println("child instanceof Parent!"); // 执行
		}

		if (parent instanceof Child) {
			System.out.println("parent instanceof Child");// 执行
		}

		Parent parent2 = new Parent();
		if (parent2 instanceof Child) {
			System.out.println("parent2 instanceof Child");// 不执行
		}

		Child child2 = new Child(2); // 此处会出现java.lang.NullPointerException异常
	}

	/**
	 * 包装类
	 */
	public static void wrapperClassDemo() {
		// 8大基本数据类型的包装类Byte、Short、Integer、Long、Character、Float、Double、Boolean
		// 除了Character外其他7个包装类都提供了parseXxx(String s)的静态方法来将字符串装换成基本数据类型
		Integer it1 = Integer.parseInt("12");

		System.out.println("两个包装类的实例比较：" + (new Integer(2) == new Integer(2))); // 两个包装类的实例比较：false
																				// 包装类其实是引用类型，指向同一个对象才会返回true
	}

	/**
	 * 类的一些特殊方法
	 */
	public static void objectDemo() {
		Parent p1 = new Parent();
		System.out.println(p1.toString()); // Object类提供的toString()方法总是返回该对象实现类的“类名+@+hashCode”值

		// 使用==判断两个变量是否相等时，如果两个变量是基本类型变量，且都是数值类型（不一定要求数据类型严格相同），则只要两个变量的值相等，就将返回true。
		// 如果两个变量是引用类型变量，只有他们指向同一个对象时，==判断才会返回true。 ==不可用于比较类型上没有父子关系的两个对象
		int it = 65;
		float fl = 65.0F;
		System.out.println("65和65.0F是否相等：" + (it == fl)); // 65和65.0F是否相等：true
		// System.out.println("hello" == new Parent()); //此处产生编译错误，2个对象没有继承关系

		String str1 = new String("hello");
		String str2 = new String("hello");

		// 当使用new
		// String("hello")时，JVM会先使用常量池来管理“hello”直接量，再调用String类的构造器来创建一个新的String对象。新创建的String对象被保存再堆内存中。
		System.out.println("str1和str2是否相等：" + (str1 == str2)); // str1和str2是否相等：false
		// equals()方法是Object类提供的一个实例方法，使用这个方法判断的标准和==运算符没有区别。所以一般会重写equals方法。
		// Stirng已经重写了Object的equals()方法。 只要两个字符串所包含的字符序列相同，则返回true.
		System.out.println("str1和str2是否equals：" + (str1.equals(str2))); // str1和str2是否equals：true

		// 8大包装类和String类都是不可变类（创建该类的实例后，该实例的实例变量是不可改变的）
		// 不可变类的实例状态不可改变，可以缓存不可变类的实例。如Integer就是缓存了-128~127之间的值
		Integer in1 = new Integer(6); // 新建Integer对象
		Integer in2 = Integer.valueOf(6); // 生成新的Integer对象，并缓存该对象
		Integer in3 = Integer.valueOf(6); // 直接在缓存里取对象
		System.out.println(in1 == in2); // false
		System.out.println(in2 == in3); // true

		Integer in4 = Integer.valueOf(200);
		Integer in5 = Integer.valueOf(200);
		// Integer只缓存了-128~127之间的值，所以200对应的Integer没有被缓存
		System.out.println(in4 == in5); // 输出false
	}

	/**
	 * 枚举类的使用
	 */
	public static void enumDemo() {
		Gender man = Gender.valueOf("MALE");
		Gender man1 = Enum.valueOf(Gender.class, "MALE"); // 返回指定枚举类中指定名称的枚举值
		System.out.println(man == man1); // true

		for (Gender gender : Gender.values()) { // 返回该枚举类所有实例
			System.out.println(gender.toString());// MALE,FEMALE
			System.out.println(gender.getName()); // 男，女 自己定义的方法
			System.out.println(gender.name()); // MALE,FEMALE enum自带的方法，与toString()返回相同。
		}
	}

	/**
	 * 垃圾回收机制
	 */
	public static void gcDemo() {
		for (int i = 0; i < 4; i++) {
			new Child();
			// 以下两个方法都是强制系统垃圾回收-这种机制只是通知系统进行垃圾回收，但系统是否进行垃圾回收依然不确定。
			// System.gc();
			Runtime.getRuntime().gc();

			// 以下两个方法强制垃圾回收机制调用对象的finalize()方法
			// System.runFinalization();
			Runtime.getRuntime().runFinalization();
		}
	}

}

/**
 * 父类
 * 
 * @author Administrator
 *
 */
class Parent {
	public String tag = "I'm parent!";
	private String name;

	public Parent(String name1) {
		name = "parent'" + name1;
	}

	public Parent(int i) {
		test(); // 尽量不要在父类构造器中调用将要被子类重写的方法
	}

	public Parent() {

	}

	public void myDesc() {
		System.out.println("my name is parent!");
	}

	public void test() {
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
 * 
 * @author Administrator
 *
 */
class Child extends Parent {
	public String tag = "I'm child!";

	private String name;
	private String firstName;

	public Child(String name1) {
		super(name1);
		name = name1;
	}

	public Child(int i) {
		super(i); // 此处调用父类的构造器，父类构造器中调用的test()方法将调用子类的test()方法。
	}

	public Child() {
	};

	@Override
	public void myDesc() {
		System.out.println("my name is child!");
	}

	@Override
	public void test() {
		System.out.println("子类重写父类的方法，" + "其firstName字符串长度" + firstName.length());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 在垃圾回收机制回收任何对象之前，总会先调用他的finalize()方法。可以在该方法中重新让一个引用变量引用该对象，则这个对象会由可恢复状态再次变回可达状态。
	 */
	@Override
	public void finalize() {
		System.out.println("系统正在清理Child对象的资源···");
	}
}