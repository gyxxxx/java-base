package cgb.java.thread;

import java.util.concurrent.CountDownLatch;

public class TestThread03 {

	static String content;
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cd=new CountDownLatch(1);
		new Thread(){
			@Override
			public void run() {
				content="hello CGB-1809";
				cd.countDown();//内置计数器会减1
			}
		}.start();
		cd.await();//CountDownLatch内置计数器为0时，线程会解除阻塞。
		System.out.println(content.toUpperCase());
	}
}
