package com.orange.foundation.domain;

import java.util.concurrent.Callable;

//------------------------实现Callable接口创建线程---------------------
//通过实现Callable接口创建并启动线程一般步骤如下：
//1】定义Callable接口的实现类，一样要重写call()方法，这个call（）方法是线程的执行体,且该call()方法有返回值。
//2】创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call()方法的返回值。
//3】使用FutureTask对象作为Thread对象的target创建线程，并调用start()启动新线程。
//4】调用FutureTask对象的get()方法来获得子线程执行结束后的返回值。
public class SumThread implements Callable<Integer> {
	@Override
	public Integer call() {
		Integer sum = 0;
		for(int i = 1; i <= 100; i++){
			sum += i;
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"线程计算完成！");
		return sum;
	}
}
