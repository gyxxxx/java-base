package cgb.java.thread;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport01 {

	static String content;
	public static void main(String[] args) throws InterruptedException {
	    Thread mainThread=Thread.currentThread();
		new Thread(){
			@Override
			public void run() {
				content="hello CGB-1809";
				LockSupport.unpark(mainThread);
			}
		}.start();
		if(content==null){
			LockSupport.park();//阻塞当前线程(主线程)
		}
		System.out.println(content.toUpperCase());
	}
}
