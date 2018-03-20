package com.orange.foundation;

public enum Gender {
	// 基础用法
	// Male,FEMALE;
	// private String name;
	// public void setName(String name){
	// this.name=name;
	// }
	// public String getName(){
	// return this.name;
	// }

	// 枚举类通常被设计成不可变类。也就是成员变量不允许被改变
	// 此处的枚举值必须调用对应的构造器来创建
	MALE("男"), FEMALE("女");
	// 新加的成员变量
	private final String name;

	private Gender(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
