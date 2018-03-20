package com.orange.foundation;

/**
 * 单例类
 * 
 * @author Administrator
 *
 */
class Singleton {
	// 使用类变量缓存曾经创建的实例
	private static Singleton instance;

	// 隐藏构造函数
	private Singleton() {
	};

	// 提供静态方法返回Singleton实例
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
