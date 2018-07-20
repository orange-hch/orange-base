package com.orange.foundation;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.orange.foundation.domain.PrintHelloThread;
import com.orange.foundation.domain.PrintWorldThread;
import com.orange.foundation.domain.SumThread;
import com.orange.foundation.domain.YieldThread;

/**
 * 线程相关
 * @author Administrator
 *
 */
public class Threading {
	public static void main(String[] args){
//		testThread();
//		joinDemo();
		yieldDemo();
	}
	
	/**
	 * 三种新建线程的方法
	 */
	public static void testThread(){
		for (int i = 0; i <10 ; i++){
			//继承Thread类创建线程
			new PrintHelloThread().start();
			
			//实现Runnable接口创建线程
			PrintWorldThread printWorldThread = new PrintWorldThread();
			Thread thread = new Thread(printWorldThread, ("printWorldThread" + i));
			thread.start();
			
			SumThread sumThread = new SumThread();
			FutureTask<Integer> task = new FutureTask<>(sumThread);
			Thread thread2 = new Thread(task); //线程进入新建状态
			thread2.start(); //线程进入就绪状态
			//thread2.start();  只能对处于新建状态的线程调用start()方法，否则将引发IlleagelThreadStateException
			
			System.out.println("主线程继续执行");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println(new Date());
			try {
			//调用task.get()来返回call()方法的返回值，该方法将导致主线程被柱塞，直到call()方法结束并返回为止
				System.out.println("Callable子线程返回值：" + task.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * join方法
	 */
	public static void joinDemo(){
		for (int i=0; i<10; i++){
			SumThread sumThread = new SumThread();
			FutureTask<Integer> task = new FutureTask<>(sumThread);
			Thread threadJ = new Thread(task);
			threadJ.start();
//			try {
//				//当在某个程序执行流中调用其他线程的join()方法时，调用线程将被阻塞，直到被join()方法加入的join线程执行完为止。
//				//main线程调用了threadJ的join()方法，main线程必须等待threadJ执行结束才会向下执行
//				threadJ.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			
			System.out.println(Thread.currentThread().getName());
		}
	}
	
	/**
	 * 线程控制
	 */
	public static void yieldDemo(){
		YieldThread yieldThread1 = new YieldThread("优先级高");
		//设置yieldThread	1为高优先级
		yieldThread1.setPriority(Thread.MAX_PRIORITY);
		yieldThread1.start();
		
		YieldThread yieldThread2 = new YieldThread("优先级低");
		//设置yieldThread	2为高优先级
		yieldThread2.setPriority(Thread.MIN_PRIORITY);
		yieldThread2.start();
		
		//yield()方法只给优先级相同或者优先级更高的线程执行机会。sleep()方法暂停当前线程，会给其他线程执行机会，不会理会其他线程的优先级。
		//sleep()方法会将线程转入阻塞状态，直到经过阻塞时间才会转入就绪状态；而yield()不会将线程转入阻塞状态，只是强制当前线程进入就绪状态。
		//在多cpu并行的环境下，yield()方法的功能并不明显。所以上面的程序demo在多cpu环境下也不会出现高优先级的线程先执行完再执行低优先级的线程。
		//sldfjlsd测试11
	}
	
}





