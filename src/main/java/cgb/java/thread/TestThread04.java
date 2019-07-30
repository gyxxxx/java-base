package cgb.java.thread;
/**
 * 用线程通讯的方式(wait,notify)，保证15行content值不为空
 * @author ta
 */
public class TestThread04 {
	static final String LOCK="LOCK"; 
	static String content;
	public static void main(String[] args) throws InterruptedException {
	    new Thread(){
			@Override
			public  void run() {
				synchronized (LOCK) {
					content="hello CGB-1809";
					LOCK.notifyAll();
				}
			}
		}.start();
		synchronized(LOCK){
		 while(content==null)
		 LOCK.wait();
		 System.out.println(content.toUpperCase());
		}
	}
}
