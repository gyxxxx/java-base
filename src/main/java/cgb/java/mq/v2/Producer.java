package cgb.java.mq.v2;

import java.util.concurrent.TimeUnit;

public class Producer implements Runnable{
	private Container<Object> container;
	public Producer(Container<Object> container) {
		this.container=container;
	}
	@Override
	public void run() {
	   int i=1;
	   while(true){
	    container.put(i);
	    i++;
	    //try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){}
	   }
	}
}
