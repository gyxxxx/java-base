package cgb.java.juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 现有一数组，内存储500000个整数，然后
 * 通过5个线程对数组中数据进行分别求和，
 * 最后再对5个线程的计算结果进行最终求和。
 */

public class TestCyclicBarrier02 {
	static CyclicBarrier cBarrier=
	new CyclicBarrier(3,new Runnable() {
		@Override
		public void run() {
			System.out.println("run()");
		}
	});
    static class SumTask implements Runnable{
    	@Override
    	public void run() {
    		try{
    		String tName=
    		Thread.currentThread().getName();
    		System.out.println("开始计算:"+tName);
    		//TimeUnit.SECONDS.sleep(2);
    		System.out.println("计算完成:"+tName);
    		cBarrier.await();
    		}catch(Exception e){e.printStackTrace();}
    	}
    }
	public static void main(String[] args) {
		SumTask task=new SumTask();
		for(int i=0;i<3;i++){
			new Thread(task).start();
		}
	}
}
