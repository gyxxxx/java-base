package cgb.java.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestBlockingQueue01 {

	 public static void main(String[] args)
	 throws Exception{
		BlockingQueue<String> bQueue=
		//数组+有界+阻塞
		new ArrayBlockingQueue<>(3);//FIFO
		bQueue.put("A");
		bQueue.put("B");
		bQueue.put("C");
		System.out.println(bQueue);
		//bQueue.put("D");被阻塞
		//System.out.println(bQueue);
		String s1=bQueue.take();
		String s2=bQueue.take();
		String s3=bQueue.take();
		System.out.println(s3);//C
		String s4=bQueue.take();
		System.out.println(s4);
		
		
		
	}
}
