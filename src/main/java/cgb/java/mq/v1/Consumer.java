package cgb.java.mq.v1;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    private Container<Object> container;
    public Consumer(Container<Object> container) {
    	this.container=container;
	}
	@Override
	public void run() {
	    while(true){
	    	Object obj=container.take();
	    	String tName=Thread.currentThread().getName();
	    	System.out.println(tName+":consumer:"+obj);
	    	//try{TimeUnit.SECONDS.sleep(1);}
	    	//catch(Exception e){e.printStackTrace();}
	    }
	}

}
