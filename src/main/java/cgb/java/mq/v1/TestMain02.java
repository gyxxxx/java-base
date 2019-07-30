package cgb.java.mq.v1;

public class TestMain02 {

	public static void main(String[] args) {
		//1.构建一个容器
		Container<Object> c=new Container<Object>();
		//2.构建两个线程
		Thread producer=new Thread(new Producer(c));
		Thread consumer=new Thread(new Consumer(c));
		//3.启动两个线程
		producer.start();
		consumer.start();
	}
}
