package cgb.java.oom;
class Outer01{
	static class Inner01 extends Thread{//实例内部类
		@Override
		public void run() {
			while(true){}
		}
	}
	@Override
	protected void finalize() throws Throwable {
	   System.out.println("finzlize");
	}
}
public class TestOOM02 {
	public static void main(String[] args) {
		Outer01 o1=new Outer01();
		Thread t1=new Outer01.Inner01();
		t1.start();
		o1=null;
		System.gc();//告诉JVM这里有垃圾
	}
}
