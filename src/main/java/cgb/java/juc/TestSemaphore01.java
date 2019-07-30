package cgb.java.juc;
import java.util.concurrent.Semaphore;
public class TestSemaphore01 {
	/**
	 * 高并发环境下的限流策略。
	 */
	static class LimitService{
		private Semaphore sh=new Semaphore(10,true);
		public void service(){
			try{
			  sh.acquire();//获取一个信号量(令牌)
			  String tName=Thread.currentThread().getName();
			  System.out.println("服务于当前线程:"+tName);
			  try{Thread.sleep(2000);}catch(Exception e){}
			}catch(Exception e){
			 e.printStackTrace();
			}finally{
			  sh.release();//释放信号量(令牌)
			}
		}
	}
	public static void main(String[] args) {
		LimitService limitService=new LimitService();
		for(int i=0;i<30;i++){
			new Thread(){
				public void run() {
					limitService.service();
				};
			}.start();
		}
	}
}
