package cgb.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestThread02 {
   //闭锁(通过设置阀值方式阻塞线程)
   static CountDownLatch cdLatch=
   new CountDownLatch(1000);//阀值为1000
   static class Counter{
    	int count;
    	public synchronized void doCount(){
    		count++;
    	}
    	public int getCount(){
    		return count;
    	}
    }
    /**负责执行计数操作的一个任务对象*/
    static class CountTask implements Runnable{
    	private Counter counter;
    	public CountTask(Counter counter) {
    		this.counter=counter;
		}
    	@Override
    	public void run() {
    		this.counter.doCount();
    		cdLatch.countDown();
    	    //每次调用CountDownLatch对象
    		//的countDown方法，对象的的阀值就会减－
    	}
    }
	public static void main(String[] args) 
	throws Exception{
		//1.构建计数器对象
		Counter counter=new Counter();
		//2.构建任务对象
		CountTask task=new CountTask(counter);
		//3.启动线程执行任务
		List<Thread> list=new ArrayList<Thread>();
		for(int i=1;i<=1000;i++){
			list.add(new Thread(task));
		}
		for(int i=0;i<1000;i++){
			list.get(i).start();
		}
		//阻塞主线程(当CountDownLatch对象的阀值变为0时会解除阻塞)
		cdLatch.await();//在哪个线程调用await方法就会阻塞哪个线程。
		System.out.println(counter.getCount());
	}
}
