package cgb.java.mq.v2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界消息队列：用于存取消息
 * 1)数据结构：数组(线性结构)
 * 2)具体算法：FIFO(先进先出)-First in First out
 */
public class Container<T> {//类泛型 
	
	/**用于存储数据的数组*/
	private Object[] array;
	/**记录有效元素个数*/
	private int size;
	public Container() {
		this(16);//this(参数列表)表示调用本类指定参数的构造函数
	}
    public Container(int cap) {
		array=new Object[cap];//每个元素默认值为null
	}
    //JDK1.5以后引入的可重入锁(相对于synchronized灵活性更好)
    private ReentrantLock lock=new ReentrantLock(true);// true表示使用公平锁，默认是非公平锁
    private Condition producerCondition=lock.newCondition();//通讯条件
    private Condition consumerCondition=lock.newCondition();//通讯条件
    /**
     * 生产者线程通过put方法向容器放数据
     * 数据永远放在size位置
     * 说明：实例方法内部的this永远指向
     * 调用此方法的当前对象(当前实例)
     * 注意：静态方法中没有this，this只能
     * 应用在实例方法，构造方法，实例代码块中
     */
    public void put(T t){//同步锁：this
    	System.out.println("put");
    	lock.lock();
    	try{
    	//1.判定容器是否已满，满了则等待
    	while(size==array.length)
        //等效于Object类中的wait方法
    	try{producerCondition.await();}catch(Exception e){e.printStackTrace();}
    	//2.放数据
    	array[size]=t;
    	//3.有效元素个数加1
    	size++;
    	//4.通知消费者取数据
    	consumerCondition.signalAll();//等效于object类中的notifyall()
    	}finally{
    	lock.unlock();
    	}
    }
    /**
     * 消费者通过此方法取数据
     * 位置：永远取下标为0的位置的数据
     * @return
     */
    @SuppressWarnings("unchecked")
	public  T take(){
    	System.out.println("take");
    	lock.lock();
    	try{
    	//1.判定容器是否为空，空则等待
    	while(size==0)
    	try{consumerCondition.await();}catch(Exception e){}
    	//2.取数据
    	Object obj=array[0];
    	//3.移动元素
    	System.arraycopy(
    			array,//src 原数组
    			1, //srcPos 从哪个位置开始拷贝
    			array,  //dest 放到哪个数组
    			0, //destPost 从哪个位置开始放
    			size-1);//拷贝几个
    	//4.有效元素个数减1
    	size--;
    	//5.将size位置为null
    	array[size]=null;
    	//6.通知生产者放数据
        producerCondition.signalAll();//通知具备相同锁对象正在wait线程
    	return (T)obj;
    	}finally{
    	lock.unlock();
    	}
    }
}





