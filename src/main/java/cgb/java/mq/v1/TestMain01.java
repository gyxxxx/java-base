package cgb.java.mq.v1;

public class TestMain01 {

	public static void main(String[] args) {
		//1.构建一个容器
		Container<Object> c=new Container<Object>();
		//2.构建两个线程
		Thread producer1=new Thread(new Producer(c));
		Thread producer2=new Thread(new Producer(c));
		Thread consumer1=new Thread(new Consumer(c));
		Thread consumer2=new Thread(new Consumer(c));
		//3.启动两个线程
		producer1.start();
		//producer2.start();
		consumer1.start();
		consumer2.start();
	}
}
