package com.orange.foundation;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.orange.foundation.domain.PrintHelloThread;
import com.orange.foundation.domain.PrintWorldThread;
import com.orange.foundation.domain.SumThread;

/**
 * 线程相关
 * @author Administrator
 *
 */
public class Threading {
	public static void main(String[] args){
		testThread();
	}
	
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
			Thread thread2 = new Thread(task);
			thread2.start();
			
//			try {
//				System.out.println("Callable子线程返回值：" + task.get());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
	}
	
}





