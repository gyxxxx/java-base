package cgb.java.juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 模拟一组线程执行写操作
 */

public class TestCyclicBarrier01 {
	
	static CyclicBarrier cb=new CyclicBarrier(3);
	
	static class CyclicTask{//类似组团出去旅游，到起了一起走
	  public void service(){
		try{
		String tName=Thread.currentThread().getName();
		System.out.println(tName+":执行写操作");
		try{TimeUnit.SECONDS.sleep(2);}catch(Exception e){}
		System.out.println(tName+":写操作完成");
		cb.await();//执行计数器减的操作
		}catch(Exception e){
		e.printStackTrace();
		}
		System.out.println("所有线程执行结束");
	  }
	}
	public static void main(String[] args)throws Exception {
		CyclicTask ct=new CyclicTask();
		for(int i=0;i<3;i++){
			new Thread(){
				public void run() {
					ct.service();
				};
			}.start();
		}
		/*TimeUnit.SECONDS.sleep(10);
		for(int i=0;i<3;i++){
			new Thread(){
				public void run() {
					ct.service();
				};
			}.start();
		}*/
	}
}
