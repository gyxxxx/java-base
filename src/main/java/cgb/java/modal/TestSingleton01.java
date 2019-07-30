package cgb.java.modal;
import java.util.ArrayList;
import java.util.List;
/**
 * 类的设计：
 * 
 * 1)此类不能在外界直接构建对象：构造方法私有化
 * 2)此类要对外提供一个对象(有且仅有一个，具备全局唯一性)
 * 2.1)在类的内部构建对象
 * 2.2)对外提供一个全局访问点
 * 
 * 此种设计一般我们称之为单例设计:(单例模式)
 * 
 * 单例模式类型：
 * 1)懒汉式单例:(对象使用时创建,延迟加载)
 * a)保证线程安全：
 * b)优化加锁方式:
 * 2)饿汉式单例:(对象在类加载时创建)
 * a)思考对象大小(小对象可在类加时创建，大对象要延迟加载)
 */
class Singleton01{//Singleton01.class
	private Singleton01(){}
	private static Singleton01 instance;
	/**
	 * 线程不安全：
	 * 1)存在并发执行的线程
	 * 2)存在共享数据集(instance)
	 * 3)在共享数据集上的操作不是原子操作
	 * @return
	 */
	public static Singleton01 getInstance(){
		if(instance==null){
			instance=new Singleton01();
		}
		return instance;
	}
}
class Singleton02{//大对象，稀少用
	private Singleton02(){}
	private static Singleton02 instance;
	/**
	 * 悲观锁(排它锁)：某一时刻只能有一个线程执行代码内容。
	 * @return
	 */
	public static synchronized Singleton02 getInstance(){
		if(instance==null){
			instance=new Singleton02();
		}
		return instance;
	}
  /*static ReentrantLock lock=new ReentrantLock(false);
	public static Singleton02 getInstance(){
		try{
		lock.lock();
		if(instance==null){
			instance=new Singleton02();
		}
		return instance;
		}finally{
		lock.unlock();
		}
	}*/
}
class Singleton03{//大对象，稀少用
	private Singleton03(){}
	/**
	 * volatile 可以保证线程的可见性(当使用此修饰符
	 * 修饰一个属性时，只有一个线程改变了此属性的值，
	 * 对其它线程立刻可见。)，但不能保证原子性。
	 */
	private static volatile Singleton03 instance;
	public  static Singleton03 getInstance(){
		if(instance==null){
			//最坏情况的是所有线程都会进行此代码块
			//最好情况只有一个线程会进入如下代码块
			synchronized (Singleton03.class) {
				if(instance==null){
				 instance=new Singleton03();
				}
				//......其它操作
			}//synchronized可以保证操作原子性，可见性
		}//减少阻塞线程的数量(通过双重判定)
		return instance;
	}
	public static void show(){}//其它方法
}
class Singleton04{//小对象，频繁用
	/**对象构建时会初始化此属性*/
	//private int[]array=new int[2048];//大对象
	private Singleton04(){}
	/**
	 * 类加载时会初始化此属性，而且只初始化1次
	 */
	private static Singleton04 instance=new Singleton04();
	public static Singleton04 getInstance(){
		return instance;
	}
	public static void show(){}
}

class Singleton05{//大对象，频繁用
	//实例变量(此变量占用资源比较多)
	private int[]array=new int[2048];
	private Singleton05(){}
	//静态内部类(构建对象，访问类属性，获取方法时，会加载类)
	static class Inner{//使用内部类延迟对象创建
	  private static  Singleton05 instance=
	  new Singleton05();
	}
	//对外的全局访问点(通过此方法获取对象)
	public static Singleton05 getInstance(){
		//访问内部类的instance属性
		return Inner.instance;
	}
	//频繁访问show方法时，是不会加载Inner类的
	public static void show(){
		System.out.println("show()");
	}
	public void put(int a){
		array[0]=a;
	}
}
/**
 * 枚举类：使用enum定义的类为一个枚举类型(特殊的类)。
 * 应用场景：通常用于定义固定的一些常亮值。
 * 例如
 * 1):一年四个季度
 * 2):一周七天
 * 3):订单状态，性别,.....
 * 
 * enum Gender{//性别
 *    MALE,FEMALE
 * };//Gender中的两个值通常会认为这是两个对象实例
 * 
 */
enum Singleton06{//Singleton06.class
	instance;//单例对象(类加载时创建，且只创建一次)
	/*private Singleton06() {//不能使用public修饰
		System.out.println("Singleton06()");
	}//默认就有一个私有的构造函数
	*/
	/**
	 * 外界如何访问此方法
	 * Singleton06.instance.show();
	 */
	public void show(){
	}
}
public class TestSingleton01 {
	public static void main(String[] args) {
		//doMethod01();
		doMethod02();
	}
	public static void doMethod02(){
		List<Thread> list=new ArrayList<>();
		for(int i=0;i<10;i++){
		  Thread t=new Thread(){
			@Override
			public void run() {
				Singleton06.instance.show();
			}
		  };
		  list.add(t);
		}
		for(Thread t:list){
			t.start();//就绪状态，获得CPU以后会执行run方法
		}
	}
    /**
     * alt+shift+m
     */
	private static void doMethod01() {
		Singleton01 t1=Singleton01.getInstance();
		Singleton01 t2=Singleton01.getInstance();
		System.out.println(t1==t2);
	}
}
