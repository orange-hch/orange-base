package com.orange.foundation;

public class ObjectOriented {
	public static void main(String[] args){
		Child child = new Child("childA");
		System.out.println(child.tag); //I'm child!
		child.myDesc(); //my name is child!
		System.out.println(child.getName());
		
		Parent parent = new Child("childB"); 
		System.out.println(parent.tag); //I'm parent!  此处子类里定义了与父类中已有的变量同名的变量，那么子类中定义的变量会隐藏父类中定义的的变量。不是完全覆盖
		parent.myDesc(); //my name is child! 此处多态。多态是指方法多态。
		System.out.println(parent.getName());
		
		
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
	public Parent(){
		
	}
	public void myDesc(){
		System.out.println("my name is parent!");
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
	public Child(String name1){
		super(name1);
		name=name1;
	}
	@Override
	public void myDesc(){
		System.out.println("my name is child!");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}