package cgb.java.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestBlockingQueue02 {

	static class WorkThread extends Thread{
		private BlockingQueue<Runnable> bQueue=
			//new ArrayBlockingQueue<>(10);
			new LinkedBlockingQueue<>(3);
		@Override
		public void run() {
			while(true){
				try{
				Runnable t=bQueue.take();
				t.run();
				}catch(Exception e){
				e.printStackTrace();
				}
			}
		}
		public void execute(Runnable task){
			bQueue.add(task);
		}
	}
	static class DownTask implements Runnable{
		@Override
		public void run() {
			System.out.println("执行下载:"+System.currentTimeMillis());
		    try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){}
		}
	}
	public static void main(String[] args) {
		WorkThread thread=new WorkThread();
		thread.start();
		DownTask task1=new DownTask();
		DownTask task2=new DownTask();
		DownTask task3=new DownTask();
		thread.execute(task1);
		thread.execute(task2);
		thread.execute(task3);
	}
}
