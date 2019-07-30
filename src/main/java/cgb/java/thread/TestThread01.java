package cgb.java.thread;

public class TestThread01 {

	static String content;
	public static void main(String[] args)
	throws Exception{
		new Thread(){
			@Override
			public void run() {
			  content="helloworld";
			}
		}.start();
		Thread.sleep(300);
		System.out.println(content.toUpperCase());
	}
}
