package cgb.java.thread.pool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 线程池：
 * 1)内存中的一块区域
 * 2)此区域重可以存储一批可重用的线程对象
 */
public class TestThreadPool01 {
	public static void main(String[] args) {
		//用于执行业务的对象(此对象内置一个线程池)
		ExecutorService es=
		//创建一个只有一个线程的线程池
		Executors.newSingleThreadExecutor();
		//执行任务
		es.execute(new Runnable() {
			@Override
			public void run() {
				String tName=
				Thread.currentThread().getName();
				System.out.println(tName+":tast01");
			}
		});
		es.execute(new Runnable() {
			@Override
			public void run() {
				String tName=
				Thread.currentThread().getName();
				System.out.println(tName+":tast02");
			}
		});
		//关闭线程池
		//尝试关闭(不再接收新任务，并等待任务执行结束)
		es.shutdown();
		if(!es.isShutdown()){
		//尝试关闭(不再接收新任务,但有可能会杀掉正在运行的线程)
		es.shutdownNow();
		}
	}
}






