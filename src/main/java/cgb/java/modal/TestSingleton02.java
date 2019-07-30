package cgb.java.modal;
/**
 * 线程单例:一个线程内部此类的对象只有一份。
 * 实现：
 * 如何保证如下类的设计是线程内部单例
 */
class Looper{
	private Looper(){}
	/**
	 * ThreadLocal 对象提供了这样的一种机制：
	 * 能够将某个对象绑定到当前线程，也能够
	 * 从当前线程获取绑定的对象,通过此类可以
	 * 实现线程内部单例。
	 * 常用方法：
	 * 1)set(T t)将某个对象绑定到线程
	 * 2)get()从当前线程获取某个对象
	 * 3)remove 从当前线程移除绑定的对象
	 */
	private static ThreadLocal<Looper> threadLocal=
			new ThreadLocal<>();//set/get
	/**
	 * 获取当前线程的Looper对象
	 */
	public static Looper getLooper(){
		//1.从当前线程获取
		Looper looper=threadLocal.get();
		//2.创建对象并绑定到当前线程
		if(looper==null){
			looper=new Looper();
			threadLocal.set(looper);//存储到线程内部的map对象中
		}
		return looper;
	}
	public void remove(){
		threadLocal.remove();//key是谁？this
	}
}
public class TestSingleton02 {
	public static void main(String[] args) {
		Looper loop1=Looper.getLooper();
		Looper loop2=Looper.getLooper();
		System.out.println(loop1==loop2);
		new Thread(){
			public void run() {
				Looper loop3=Looper.getLooper();
				Looper loop4=Looper.getLooper();
				System.out.println(loop3==loop4);
				System.out.println(loop1==loop3);
				loop4.remove();
			};
		}.start();
	}
}
