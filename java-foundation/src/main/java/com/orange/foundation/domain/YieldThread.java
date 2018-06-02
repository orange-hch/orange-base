package com.orange.foundation.domain;

/**
 * 线程让步
 * @author Administrator
 *
 */
public class YieldThread extends Thread{
	public YieldThread(String name){
		super(name);
	}
	
	@Override
	public void run(){
		int count = 0;
		for (int i = 0; i < 50; i++) {
			count = count + i;
			System.out.println(getName() + " " + i);
			if (i == 20){
				//yield可以让当前正在执行的线程暂停，但是它不会阻塞该线程，他只是将该线程转入就绪状态，让系统的线程调度器重新调度一次。
				Thread.yield();
			}
		}
		System.out.println(Thread.currentThread().getName() + "线程计算完成，结果为：" + count);
	}
}
