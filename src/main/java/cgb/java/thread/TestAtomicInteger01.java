package cgb.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger01 {

	static AtomicInteger at=new AtomicInteger(1);
	public static void main(String[] args) {
		//基于CPU的CAS算法实现的乐观锁。
		int count=at.getAndIncrement();
		System.out.println(count);
		count=at.getAndIncrement();
		System.out.println(count);
		count=at.getAndIncrement();
		System.out.println(count);
		count=at.getAndIncrement();
		System.out.println(count);
	}
}
