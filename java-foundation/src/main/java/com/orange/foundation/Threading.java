package com.orange.foundation;

import com.orange.foundation.domain.PrintHelloThread;
import com.orange.foundation.domain.PrintWorldThread;

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
			new PrintHelloThread().start();
			
			PrintWorldThread printWorldThread = new PrintWorldThread();
			Thread thread = new Thread(printWorldThread, ("printWorldThread" + i));
			thread.start();
		}
	}
	
}





