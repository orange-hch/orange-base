package com.orange.foundation;

public class ObjectOriented {
	public static void main(String[] args){
		Child child = new Child();
		System.out.println(child.tag); //I'm child!
		child.myDesc(); //my name is child!
		
		Parent parent = new Child(); 
		System.out.println(parent.tag); //I'm parent!  此处子类里定义了与父类中已有的变量同名的变量，那么子类中定义的变量会隐藏父类中定义的的变量。不是完全覆盖
		parent.myDesc(); //my name is child! 此处多态。多态是指方法多态。
	}
}

/**
 * 父类
 * @author Administrator
 *
 */
class Parent{
	public String tag="I'm parent!";
	
	public void myDesc(){
		System.out.println("my name is parent!");
	}
}

/**
 * 子类
 * @author Administrator
 *
 */
class Child extends Parent{
	public String tag="I'm child!";
	
	@Override
	public void myDesc(){
		System.out.println("my name is child!");
	}
}