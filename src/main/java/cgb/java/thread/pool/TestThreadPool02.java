package cgb.java.thread.pool;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadPool02 {
	//线程工厂
	private static ThreadFactory factory=new ThreadFactory() {
		//基于CAS算法的整数资增长对象
		private AtomicInteger atomicInteger=new AtomicInteger(0);
		//工厂模式(工厂方法模式)
		@Override
		public Thread newThread(Runnable r) {
			String tName= "TEDU-CGB-Thread-"+atomicInteger.getAndIncrement();
            Thread t=new Thread(r,tName);
			return t;
		}
	};
	public static void main(String[] args) {
		//构建一个阻塞式队列(用于存储任务)
		BlockingQueue<Runnable> workQueue=
		new ArrayBlockingQueue<>(50);
		//构建一个线程池对象
		ThreadPoolExecutor tPool=
		new ThreadPoolExecutor(
			2,//corePoolSize核心线程数
			10,//maximumPoolSize 最大线程数
			30,//keepAliveTime线程空闲时间
			TimeUnit.SECONDS, //unit时间单位
			workQueue,factory);//workQueue 工作队列
		   //执行任务
		    tPool.execute(new Runnable() {
			@Override
			public void run() {
				String tName=Thread.currentThread().getName();
				System.out.println(tName+":task01");
				try{Thread.sleep(3000);}
				catch(Exception e){e.printStackTrace();}
			}
		});
		/**
		//过程总结：假如现有多个任务交给线程池中的线程执行
		//1)池中核心线程数小于corePoolSize，每次执行新任务
		//都会创建一个先的线程
		//2)池中核心线程数已经等于corePoolSize，再来新的任务
		//要先检测核心线程是否有空闲的，有则选空闲线程执行，
		//没有则将任务存储到任务队列。
		//3)池中核心线程数已经等于corePoolSize，并且队列已满
		//再有新的任务还会创建新的线程，线程数maximumPoolSize
		//值
		*/
	}
}
